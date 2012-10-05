package com.iv.rms.notification;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.stereotype.Component;

import com.iv.rms.core.AbstractService;
import com.iv.rms.core.PMF;
import com.iv.rms.core.impl.PropertyServiceImpl;
import com.iv.rms.notification.client.SimpleNotification;
import com.iv.rms.notification.shared.NotificationException;
import com.iv.rms.notification.shared.Util;
import com.iv.rms.user.Owner;
import com.iv.rms.user.User;

@Component
public class NotificationServiceImpl extends AbstractService implements NotificationService{
	
	private static final String DEFAULT_TIMEZONE = PropertyServiceImpl.getInstance().getValue("defaultTimeZoneId");
	
	@Override
	public void saveNotification(SimpleNotification notification, User user) throws NotificationException{
		Notification n = new Notification();
		PersistenceManager pm = null;
		Date fullTriggerDate = Util.composeFullTriggerDate(notification, DEFAULT_TIMEZONE);
		try{
			n.setCreationDate(new Date());
			n.setMessage(notification.getMessage());
			n.setTriggerDate(Util.extractTriggerDate(fullTriggerDate));
			n.setMinutes(Util.formatMinutes(fullTriggerDate));
			n.setOwnerId(getOrCreateOwner(user).getUserId());
			Util.getTimeZone(notification.getTimeZone(), DEFAULT_TIMEZONE);
			if  ( !isDuplicate(n) ){
				pm = getPersistenceManager();
				pm.makePersistent(n);
			}else{
				throw new NotificationException("You can't add the same reminder twice");
			}
		}catch (NotificationException e) {
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			PMF.close(pm);
		}
		
	}
	
	private boolean isDuplicate(Notification notification) {
		PersistenceManager pm = null;
		boolean result = false;
		try{
			pm = getPersistenceManager();
			Query q = pm.newQuery(Notification.class);
		    q.setFilter("triggerDate == " + notification.getTriggerDate() + " && minutes == " + notification.getMinutes() + " && procesed == " + Boolean.FALSE + " && message =='" + notification.getMessage() + "'");
		    List<Notification> results = (List<Notification>) q.execute();
		    if (!results.isEmpty()) { 
	            result = true;
	        }
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			PMF.close(pm);
		}
		return result;
	}

	public void processPendingNotification(){
		PersistenceManager pm = null;
		try{
			Date currentDate = Util.getDateInTimeZone(new Date(),TimeZone.getDefault().getID(), DEFAULT_TIMEZONE);
			Integer dateInt = Util.formatDate(currentDate, TimeZone.getTimeZone(DEFAULT_TIMEZONE));// TODO: don't use this method
			Integer minutes = Util.getMinutesSinceMidnight(currentDate, TimeZone.getTimeZone(DEFAULT_TIMEZONE));
			pm = getPersistenceManager();
			Query q = pm.newQuery(Notification.class);
		    q.setFilter("triggerDate == " + dateInt + " && minutes <= " + minutes + " && procesed == " + Boolean.FALSE);
		    List<Notification> results = (List<Notification>) q.execute();
		    if (!results.isEmpty()) { 
	            for (Notification n : results) {
	                getServiceLocator().getMailService().sendMail(n, getOwner(n.getOwnerId()));
	                n.setProcesed(Boolean.TRUE);
	                n.setSentDate(currentDate);
	            }
	        }
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			PMF.close(pm);
		}
	}
	
	/**
	 * Refactor this method, it's ugly
	 * 
	 * @return
	 */
	public Owner getOrCreateOwner(User user) {
		Owner owner = getOwner(user.getUserId());
		if (owner == null) {
			owner = createNewOwner(user);
			String mailMsg = "User email:" + user.getEmail() + "  Nickname:" + user.getNickName();
			getServiceLocator().getMailService().sendAdminMail("mailRemind: New user", mailMsg);
		}
		return owner;
	}
	
	public Owner createNewOwner(User user){
		Owner owner = null;
		PersistenceManager pm = null;
		try{
			owner = new Owner();
			owner.setCreationDate(new Date());
			owner.setUserId(user.getUserId());
			owner.setEmail(user.getEmail());
			owner.setName(user.getNickName());
			pm = getPersistenceManager(); 
			pm.makePersistent(owner);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			PMF.close(pm);
		}
		return owner;
	}
	
	public Owner getOwner(String userId){
		PersistenceManager pm = null;
		try{
			pm = getPersistenceManager();
			Query q = pm.newQuery(Owner.class);
		    q.setFilter("userId == userIdParam");
		    q.declareParameters("String userIdParam");
		    List<Owner> result = (List<Owner>) q.execute(userId);
		    if ( result != null && result.size() > 0){
		    	return result.get(0);
		    }
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			PMF.close(pm);
		}
		return null;
	}
	
	public Boolean hasUserTimeZone(User user){
		if ( user != null ){
			Owner owner = getOwner(user.getUserId());
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