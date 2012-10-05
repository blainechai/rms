package com.iv.rms.notification;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.iv.rms.core.jdo.DAOException;
import com.iv.rms.core.jdo.JDODAOSupport;
import com.iv.rms.core.jdo.PMF;
import com.iv.rms.notification.shared.Util;

public class NotificationDAOJDOImpl extends JDODAOSupport implements NotificationDAO{

	
	@Override
	public void save(Notification notification) throws DAOException{
		PersistenceManager pm = null;
		try{
			pm = getPersistenceManager();
			pm.makePersistent(notification);
		}catch (Exception e) {
			throw new DAOException(e);
		}finally{
			PMF.close(pm);
		}
	}
	
	@Override
	public boolean isDuplicate(Notification notification) throws DAOException {
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
			throw new DAOException(e);
		}finally{
			PMF.close(pm);
		}
		return result;
	}
	
	@Override
	public List<Notification> getNotifications(String triggerDate, Integer minutes, Boolean processed){
		PersistenceManager pm = null;
		List<Notification> results = null;
		try{
			pm = getPersistenceManager();
			Query q = pm.newQuery(Notification.class);
		    q.setFilter("triggerDate == " + triggerDate + " && minutes <= " + minutes + " && procesed == " + processed);
		    results = (List<Notification>) q.execute();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			PMF.close(pm);
		}
		return results;
	}
	
}
