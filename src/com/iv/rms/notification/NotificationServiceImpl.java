package com.iv.rms.notification;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import com.iv.rms.core.AbstractService;
import com.iv.rms.core.SimpleStringCipher;
import com.iv.rms.notification.client.SimpleNotification;
import com.iv.rms.notification.shared.NotificationException;
import com.iv.rms.notification.shared.Util;
import com.iv.rms.user.Owner;
import com.iv.rms.user.User;

@Component
public class NotificationServiceImpl extends AbstractService implements NotificationService {

    private static final Logger log = Logger.getLogger(NotificationServiceImpl.class.getName());

    private static final String DEFAULT_TIMEZONE = "defaultTimeZoneId";

    private NotificationDAO notificationDAO;

    private TempNotificationDAO tempNotificationDAO;

    public TempNotificationDAO getTempNotificationDAO() {
	return tempNotificationDAO;
    }

    public void setTempNotificationDAO(TempNotificationDAO tempNotificationDAO) {
	this.tempNotificationDAO = tempNotificationDAO;
    }

    public NotificationDAO getNotificationDAO() {
	return notificationDAO;
    }

    public void setNotificationDAO(NotificationDAO notificationDAO) {
	this.notificationDAO = notificationDAO;
    }

    @Override
    public void saveNotification(SimpleNotification notification, User user) throws NotificationException {
	Notification n = new Notification();
	Date fullTriggerDate = Util.composeFullTriggerDate(notification, getDefaultTimeZone());
	try {
	    n.setCreationDate(new Date());
	    n.setMessage(notification.getMessage());
	    n.setTriggerDate(Util.extractTriggerDate(fullTriggerDate));
	    n.setMinutes(Util.formatMinutes(fullTriggerDate));
	    n.setOwnerId(getOrCreateOwner(user).getUserId());
	    Util.getTimeZone(notification.getTimeZone(), getDefaultTimeZone());
	    if (!notificationDAO.isDuplicate(n)) {
		notificationDAO.save(n);
	    } else {
		throw new NotificationException("You can't add the same reminder twice");
	    }
	} catch (NotificationException e) {
	    throw e;
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void processPendingNotification() {
	try {
	    String defaultTimeZone = getDefaultTimeZone();
	    Date currentDate = Util.getDateInTimeZone(new Date(), TimeZone.getDefault().getID(), defaultTimeZone);
	    Integer dateInt = Util.formatDate(currentDate, TimeZone.getTimeZone(defaultTimeZone));
	    Integer minutes = Util.getMinutesSinceMidnight(currentDate, TimeZone.getTimeZone(defaultTimeZone));
	    List<Notification> results = notificationDAO.getNotifications(String.valueOf(dateInt), minutes, Boolean.FALSE);
	    if (!results.isEmpty()) {
		for (Notification n : results) {
		    getServiceLocator().getMailService().sendHtmlMail(getHtmlView(n), getServiceLocator().getUserService().getOwner(n.getOwnerId()));
		    n.setProcesed(Boolean.TRUE);
		    n.setSentDate(currentDate);
		    notificationDAO.save(n);
		}
	    }
	} catch (Exception e) {
	    log.log(Level.SEVERE, "", e);
	}
    }

    public Owner getOrCreateOwner(User user) {
	Owner owner = getServiceLocator().getUserService().getOwner(user);
	if (owner == null) {
	    owner = getServiceLocator().getUserService().createOwner(user);
	    String mailMsg = "User email:" + user.getEmail() + "  Nickname:" + user.getNickName();
	    getServiceLocator().getMailService().sendAdminMail("mailRemind: New user", mailMsg);
	}
	return owner;
    }

    public Boolean hasUserTimeZone(User user) {
	if (user != null) {
	    Owner owner = getServiceLocator().getUserService().getOwner(user);
	    if (owner.getTimeZoneId() != null) {
		return true;
	    }
	}
	return false;
    }

    @Override
    public Long saveTempNotification(SimpleNotification notification) throws NotificationException {
	TempNotification tempNotification = new TempNotification();
	Long tempNotificationId = null;
	try {
	    tempNotification.setMessage(notification.getMessage());
	    tempNotification.setTriggerDate(notification.getDate());
	    tempNotification.setMinutes(notification.getMinutes());
	    tempNotificationDAO.saveTempNotification(tempNotification);
	    tempNotificationId = tempNotification.getKey().getId();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return tempNotificationId;
    }

    @Override
    public SimpleNotification getTempNotification(Long id) {
	SimpleNotification sn = new SimpleNotification();
	try {
	    TempNotification tempNotification = tempNotificationDAO.getTempNotification(id);
	    sn.setDate(new Date(tempNotification.getTriggerDate().getTime()));
	    sn.setMessage(tempNotification.getMessage());
	    sn.setMinutes(tempNotification.getMinutes());
	    // TODO: delete temp object
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return sn;
    }

    private String getDefaultTimeZone() {
	return getProperty(DEFAULT_TIMEZONE);
    }
    
    @Override
    public Notification postponeNotification(Long id, int days){
	Notification notification = getNotificationDAO().load(id);
	Notification newNotification = notification.clone();
	newNotification.setTriggerDate(Util.incrementDate(notification.getTriggerDate(), days));
	notificationDAO.save(newNotification);
	return newNotification;
    }
    
    public String getPostponeLink(Long id, int days){
	String link = "www." + getServiceLocator().getPropertyService().getValue("domain") + "/postpone?token=";
	try {
	    link += SimpleStringCipher.encrypt(id + "-" + days);
	} catch (Exception e) {
	    e.printStackTrace();
	} 
	return link;
    }
    
    public String getHtmlView(Notification notification){
	String tomorrowLink = getPostponeLink(notification.getKey().getId(), 1);
	String nextWeekLink = getPostponeLink(notification.getKey().getId(), 7);
	String nextMonthLink = getPostponeLink(notification.getKey().getId(), 30);
	StringBuilder sb = new StringBuilder();
	sb.append("<html><head></head><body>");
	sb.append("<h3>").append(notification.getMessage()).append("</h3>");
	sb.append("<table><tr><td>Remind me  <a href=\"").append(tomorrowLink).append("\">tomorrow</a>, <a href=\"")
	.append(nextWeekLink).append("\">next week</a> or <a href=\"").append(nextMonthLink).append("\">next month</a></tr>");
	sb.append("</table></body></html>");
	return sb.toString();
    }

}