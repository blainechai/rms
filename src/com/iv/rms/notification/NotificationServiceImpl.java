package com.iv.rms.notification;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.jdo.PersistenceManager;

import org.springframework.stereotype.Component;

import com.iv.rms.core.AbstractService;
import com.iv.rms.core.impl.PropertyServiceImpl;
import com.iv.rms.core.jdo.PMF;
import com.iv.rms.notification.client.SimpleNotification;
import com.iv.rms.notification.shared.NotificationException;
import com.iv.rms.notification.shared.Util;
import com.iv.rms.user.Owner;
import com.iv.rms.user.User;

@Component
public class NotificationServiceImpl extends AbstractService implements NotificationService{
	
	private static final String DEFAULT_TIMEZONE = PropertyServiceImpl.getInstance().getValue("defaultTimeZoneId");
	
	private NotificationDAO notificationDAO ;
	
	public NotificationDAO getNotificationDAO() {
		return notificationDAO;
	}

	public void setNotificationDAO(NotificationDAO notificationDAO) {
		this.notificationDAO = notificationDAO;
	}

	@Override
	public void saveNotification(SimpleNotification notification, User user) throws NotificationException{
		Notification n = new Notification();
		Date fullTriggerDate = Util.composeFullTriggerDate(notification, DEFAULT_TIMEZONE);
		try{
			n.setCreationDate(new Date());
			n.setMessage(notification.getMessage());
			n.setTriggerDate(Util.extractTriggerDate(fullTriggerDate));
			n.setMinutes(Util.formatMinutes(fullTriggerDate));
			n.setOwnerId(getOrCreateOwner(user).getUserId());
			Util.getTimeZone(notification.getTimeZone(), DEFAULT_TIMEZONE);
			if  ( !notificationDAO.isDuplicate(n) ){
				notificationDAO.save(n);
			}else{
				throw new NotificationException("You can't add the same reminder twice");
			}
		}catch (NotificationException e) {
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void processPendingNotification(){
		try{
			Date currentDate = Util.getDateInTimeZone(new Date(),TimeZone.getDefault().getID(), DEFAULT_TIMEZONE);
			Integer dateInt = Util.formatDate(currentDate, TimeZone.getTimeZone(DEFAULT_TIMEZONE));// TODO: don't use this method
			Integer minutes = Util.getMinutesSinceMidnight(currentDate, TimeZone.getTimeZone(DEFAULT_TIMEZONE));
		    List<Notification> results = notificationDAO.getNotifications(String.valueOf(dateInt), minutes, Boolean.FALSE);
		    if (!results.isEmpty()) { 
	            for (Notification n : results) {
	                getServiceLocator().getMailService().sendMail(n,getServiceLocator().getUserService().getOwner(n.getOwnerId()));
	                n.setProcesed(Boolean.TRUE);
	                n.setSentDate(currentDate);
	            }
	        }
		}catch (Exception e) {
			e.printStackTrace();
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
	
	public Boolean hasUserTimeZone(User user){
		if ( user != null ){
			Owner owner = getServiceLocator().getUserService().getOwner(user);
			if ( owner.getTimeZoneId() != null ){
				return true;
			}
		}
		return false;
	}

	@Override
	public Long saveTempNotification(SimpleNotification notification) throws NotificationException {
		TempNotification n = new TempNotification();
		PersistenceManager pm = null;
		Long tempNotificationId = null;
		try{
			n.setMessage(notification.getMessage());
			n.setTriggerDate(notification.getDate());
			n.setMinutes(notification.getMinutes());
			pm = getPersistenceManager();
			pm.makePersistent(n);
			tempNotificationId = n.getKey().getId();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			PMF.close(pm);
		}
		return tempNotificationId;
	}

	@Override
	public SimpleNotification getTempNotification(Long id) {
		PersistenceManager pm = null;
		SimpleNotification sn = new SimpleNotification();
		try{
			pm = getPersistenceManager();
			TempNotification tempNotification = (TempNotification) pm.getObjectById(TempNotification.class, id);
			sn.setDate(new Date(tempNotification.getTriggerDate().getTime()));
			sn.setMessage(tempNotification.getMessage());
			sn.setMinutes(tempNotification.getMinutes());
			//TODO: delete temp object
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			PMF.close(pm);
		}
		return sn;
	}

}