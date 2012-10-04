package com.iv.rms.notification;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import com.iv.rms.core.AbstractService;
import com.iv.rms.core.PMF;
import com.iv.rms.core.PropertyServiceImpl;
import com.iv.rms.entity.Notification;
import com.iv.rms.entity.Owner;
import com.iv.rms.entity.TempNotification;
import com.iv.rms.notification.client.SimpleNotification;
import com.iv.rms.notification.shared.ApplicationException;
import com.iv.rms.notification.shared.Util;

public class NotificationServiceImpl extends AbstractService implements NotificationService{
	
	private static final String DEFAULT_TIMEZONE = PropertyServiceImpl.getInstance().getValue("defaultTimeZoneId");
	
	private static final String PLEASE_LOGIN_MSG = "pleaseLoginMsg";
	
	@Override
	public void saveNotification(SimpleNotification notification) throws ApplicationException{
		if ( UserServiceFactory.getUserService().getCurrentUser() == null ){
			throw new ApplicationException(getProperty(PLEASE_LOGIN_MSG));
		}
		Notification n = new Notification();
		PersistenceManager pm = null;
		Date fullTriggerDate = composeFullTriggerDate(notification);
		try{
			n.setCreationDate(new Date());
			n.setMessage(notification.getMessage());
			n.setTriggerDate(Util.extractTriggerDate(fullTriggerDate));
			n.setMinutes(Util.formatMinutes(fullTriggerDate));
			n.setOwnerId(getOrCreateOwner().getUserId());
			getTimeZone(notification.getTimeZone());
			if  ( !isDuplicate(n) ){
				pm = getPersistenceManager();
				pm.makePersistent(n);
			}else{
				throw new ApplicationException("You can't add the same reminder twice");
			}
		}catch (ApplicationException e) {
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
	 * @return
	 */
	public Owner getOrCreateOwner(){
		User user = UserServiceFactory.getUserService().getCurrentUser();
		if ( user != null ){
			// check if this user has an owner object
			Owner owner = getOwner(user.getUserId());
			if ( owner == null ){
				//new user... create owner
				owner = createNewOwner(user);
				String mailMsg = "User email:" + user.getEmail() + "  Nickname:" + user.getFederatedIdentity();
				getServiceLocator().getMailService().sendAdminMail("mailRemind: New user", mailMsg);
			}
			return owner;
		}
		return null;
	}
	
	public Owner createNewOwner(User user){
		Owner owner = null;
		PersistenceManager pm = null;
		try{
			owner = new Owner();
			owner.setCreationDate(new Date());
			owner.setUserId(user.getUserId());
			owner.setEmail(user.getEmail());
			owner.setName(user.getNickname());
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
		    if ( result != null ){
		    	return result.get(0);
		    }
		    return null;
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			PMF.close(pm);
		}
		return null;
	}
	
	public Boolean hasUserTimeZone(){
		User user = UserServiceFactory.getUserService().getCurrentUser();
		if ( user != null ){
			Owner owner = getOwner(user.getUserId());
			if ( owner.getTimeZoneId() != null ){
				return true;
			}
		}
		return false;
	}
	
	public String guessClientTimeZone(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		TimeZone timeZone = cal.getTimeZone();
		return timeZone.getID();
	}
	
	private TimeZone getTimeZone(String strTz){
		String strFromJavaScript = strTz; 
        int timeZone = Integer.parseInt(strFromJavaScript);  
        if (timeZone >= 0) {  
            strFromJavaScript = "+" + timeZone;  
        }
        TimeZone tz = TimeZone.getTimeZone(DEFAULT_TIMEZONE + strFromJavaScript);  
        return tz;
	}
	
	private Date composeFullTriggerDate(SimpleNotification sn){
		Calendar cal = Calendar.getInstance();
		cal.setTime(sn.getDate());
		cal.set(Calendar.HOUR_OF_DAY, sn.getMinutes() / 60);
		cal.set(Calendar.MINUTE, sn.getMinutes() - ((sn.getMinutes() / 60) * 60 ) );
		return Util.getDateInTimeZone(cal.getTime(),getTimeZone(sn.getTimeZone()).getID(), DEFAULT_TIMEZONE);
	}

	@Override
	public Long saveTempNotification(SimpleNotification notification) throws ApplicationException {
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

	@Override
	public String getName() {
		return "NotificationService";
	}

}