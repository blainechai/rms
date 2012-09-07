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

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.iv.rms.client.NotificationService;
import com.iv.rms.client.NotificationViews;
import com.iv.rms.client.SimpleNotification;
import com.iv.rms.client.Timezones;
import com.iv.rms.entity.Notification;
import com.iv.rms.entity.NotificationView;
import com.iv.rms.entity.Owner;
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
		n.setCreationDate(new Date());
		n.setMessage(notification.getMessage());
		n.setTriggerDate(formatDate(notification.getDate(), notification.getMinutes()));
		n.setMinutes(formatMinutes(notification.getDate(), notification.getMinutes()));
		n.setOwnerId(getOrCreateOwner().getUserId());
		getTimeZone(notification.getTimeZone());
		PersistenceManager pm = PMF.get().getPersistenceManager(); 
		try{
			pm.makePersistent(n);
			System.out.println("Id:" + n.getKey());
			for(NotificationViews v : notification.getViews()){
				NotificationView view = new NotificationView();
				view.setNotificationKey(n.getKey().getId());
				view.setViewType(v.getCode());
				pm.makePersistent(view);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			pm.close();
		}
		
	}
	
	private Integer formatDate(Date date, Integer minutes){
		Calendar cal  = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, minutes / 60);
		cal.set(Calendar.MINUTE, minutes - ((minutes / 60) * 60 ) );
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		String str = sdf.format(cal.getTime());
		return Integer.parseInt(str);
	}
	
	private Integer formatMinutes(Date date, Integer minutes){
		Calendar cal  = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, minutes / 60);
		cal.set(Calendar.MINUTE, minutes - ((minutes / 60) * 60 ) );
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		int mins = Integer.parseInt(sdf.format(cal.getTime())) * 60;
		sdf = new SimpleDateFormat("mm");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		mins += Integer.parseInt(sdf.format(cal.getTime()));
		return mins;
	}
	
	public void processPendingNotification(){
		PersistenceManager pm = null;
		try{
			Date currentDate = new Date();
			Integer dateInt = Util.formatDate(currentDate);
			Integer minutes = Util.getMinutesSinceMidnight(currentDate);
			pm = PMF.get().getPersistenceManager();
			Query q = pm.newQuery(Notification.class);
		    q.setFilter("triggerDate == " + dateInt + " && minutes <= " + minutes + " && procesed == " + Boolean.FALSE);
		    List<Notification> results = (List<Notification>) q.execute();
		    MailService ms = new MailService(); 
		    if (!results.isEmpty()) {
	            for (Notification n : results) {
	                ms.sendMail(n, getOwner(n.getOwnerId()));
	                n.setProcesed(Boolean.TRUE);
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

}
