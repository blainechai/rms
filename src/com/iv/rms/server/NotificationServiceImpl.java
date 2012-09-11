package com.iv.rms.server;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.iv.rms.client.NotificationService;
import com.iv.rms.client.SimpleNotification;
import com.iv.rms.client.Timezones;
import com.iv.rms.entity.Notification;
import com.iv.rms.entity.Owner;
import com.iv.rms.entity.UserContactMessage;
import com.iv.rms.mail.MailService;
import com.iv.rms.shared.ApplicationException;
import com.iv.rms.shared.Util;

@SuppressWarnings("serial")
public class NotificationServiceImpl extends RemoteServiceServlet implements NotificationService {
	
	@Override
	public void saveNotification(SimpleNotification notification) throws ApplicationException{
		if ( UserServiceFactory.getUserService().getCurrentUser() == null ){
			throw new ApplicationException("Please login first. You can use you Google Id.");
		}		
		Calendar cal = Calendar.getInstance();
		log(cal.getTimeZone().getDisplayName());
		Notification n = new Notification();
		PersistenceManager pm = null;
		Date fullTriggerDate = composeFullTriggerDate(notification);
		try{
			n.setCreationDate(new Date());
			n.setMessage(notification.getMessage());
			n.setTriggerDate(formatDate(fullTriggerDate));
			n.setMinutes(formatMinutes(fullTriggerDate));
			n.setOwnerId(getOrCreateOwner().getUserId());
			getTimeZone(notification.getTimeZone());
			pm = PMF.get().getPersistenceManager();
			pm.makePersistent(n);
			System.out.println("Id:" + n.getKey());
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			pm.close();
		}
		
	}
	
	public Integer formatDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String str = sdf.format(date);
		return Integer.parseInt(str);
	}
	
	public Integer formatMinutes(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		int mins = Integer.parseInt(sdf.format(date)) * 60;
		sdf = new SimpleDateFormat("mm");
		mins += Integer.parseInt(sdf.format(date));
		return mins;
	}
	
	public void processPendingNotification(){
		PersistenceManager pm = null;
		try{
			Date currentDate = Util.getDateInTimeZone(new Date(),TimeZone.getDefault().getID(), "GMT");
			Integer dateInt = Util.formatDate(currentDate);
			Integer minutes = Util.getMinutesSinceMidnight(currentDate);
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
	
	public Timezones getTimeZones(Date date){
		Map<String,String> timeZones = new HashMap<String, String>();
		String[] ids = TimeZone.getAvailableIDs();
		for (int i = 0; i < ids.length; i++) {
		        String tz = TimeZone.getTimeZone(ids[i]).getDisplayName();
		        TimeZone.getTimeZone(ids[i]);
		        if (!timeZones.containsKey(ids[i])) {
		        	timeZones.put(ids[i], tz);
		        }       
		}
		
		return new Timezones(timeZones, guessClientTimeZone(date));
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
  
        TimeZone tz = TimeZone.getTimeZone("GMT" + strFromJavaScript);  
        System.out.println(tz.getDisplayName());  
        System.out.println(tz.getRawOffset()); 
        return tz;
	}
	
	private Date composeFullTriggerDate(SimpleNotification sn){
		Calendar cal = Calendar.getInstance();
		cal.setTime(sn.getDate());
		cal.set(Calendar.HOUR_OF_DAY, sn.getMinutes() / 60);
		cal.set(Calendar.MINUTE, sn.getMinutes() - ((sn.getMinutes() / 60) * 60 ) );
		return Util.getDateInTimeZone(cal.getTime(),getTimeZone(sn.getTimeZone()).getID(), "GMT");
	}

	@Override
	public void saveUserContactMessage(String subject, String message) throws ApplicationException{
		if ( UserServiceFactory.getUserService().getCurrentUser() == null ){
			throw new ApplicationException("Please login first. You can use you Google Id.");
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
