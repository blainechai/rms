package com.iv.rms.server;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.iv.rms.client.NotificationService;
import com.iv.rms.client.SimpleNotification;
import com.iv.rms.entity.Notification;
import com.iv.rms.entity.Owner;
import com.iv.rms.entity.UserContactMessage;
import com.iv.rms.mail.MailService;
import com.iv.rms.shared.ApplicationException;
import com.iv.rms.shared.Util;

@SuppressWarnings("serial")
public class NotificationServiceImpl extends RemoteServiceServlet implements NotificationService {
	
	private static final String DEFAULT_TIMEZONE = PropertyService.getInstance().getValue("defaultTimeZoneId");
	
	private static final String PLEASE_LOGIN_MSG = "pleaseLoginMsg";

	@Override
	public void saveNotification(SimpleNotification notification) throws ApplicationException{
		if ( UserServiceFactory.getUserService().getCurrentUser() == null ){
			throw new ApplicationException(PropertyService.getInstance().getValue(PLEASE_LOGIN_MSG));
		}		
		Calendar cal = Calendar.getInstance();
		log(cal.getTimeZone().getDisplayName());
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
			pm = PMF.get().getPersistenceManager();
			pm.makePersistent(n);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			pm.close();
		}
		
	}
	
	public void processPendingNotification(){
		PersistenceManager pm = null;
		try{
			Date currentDate = Util.getDateInTimeZone(new Date(),TimeZone.getDefault().getID(), DEFAULT_TIMEZONE);
			Integer dateInt = Util.formatDate(currentDate, TimeZone.getTimeZone(DEFAULT_TIMEZONE));// TODO: don't use this method
			Integer minutes = Util.getMinutesSinceMidnight(currentDate, TimeZone.getTimeZone(DEFAULT_TIMEZONE));
			pm = PMF.get().getPersistenceManager();
			Query q = pm.newQuery(Notification.class);
		    q.setFilter("triggerDate == " + dateInt + " && minutes <= " + minutes + " && procesed == " + Boolean.FALSE);
		    List<Notification> results = (List<Notification>) q.execute();
		    if (!results.isEmpty()) {
		    	MailService ms = new MailService(); 
	            for (Notification n : results) {
	                ms.sendMail(n, getOwner(n.getOwnerId()));
	                n.setProcesed(Boolean.TRUE);
	                n.setSentDate(currentDate);
	            }
	        }
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			pm.close();
		}
	}
	
	public Owner getOrCreateOwner(){
		User user = UserServiceFactory.getUserService().getCurrentUser();
		if ( user != null ){
			// check if this user has an owner object
			Owner owner = getOwner(user.getUserId());
			if ( owner == null ){
				//create owner
				owner = new Owner();
				owner.setCreationDate(new Date());
				owner.setUserId(user.getUserId());
				owner.setEmail(user.getEmail());
				PersistenceManager pm = PMF.get().getPersistenceManager(); 
				try{
					pm.makePersistent(owner);
				}catch (Exception e) {
					e.printStackTrace();
				}finally{
					pm.close();
				}
			}
			return owner;
		}
		return null;
	}
	
	public Owner getOwner(String userId){
		PersistenceManager pm = null;
		try{
			pm = PMF.get().getPersistenceManager();
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
			pm.close();
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
	public void saveUserContactMessage(String subject, String message) throws ApplicationException{
		if ( UserServiceFactory.getUserService().getCurrentUser() == null ){
			throw new ApplicationException(PropertyService.getInstance().getValue(PLEASE_LOGIN_MSG));
		}
		UserContactMessage m = new UserContactMessage();
		m.setSubject(subject);
		m.setMessage(new Text(message));
		m.setCreationDate(new Date());
		m.setUserId(UserServiceFactory.getUserService().getCurrentUser().getUserId());
		PersistenceManager pm = null;
		try{
			pm = PMF.get().getPersistenceManager();
			pm.makePersistent(m);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			pm.close();
		}
		
	}

}
