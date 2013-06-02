package com.iv.rms.notification;

import java.util.List;
import java.util.logging.Logger;

import javax.jdo.Query;

import com.iv.rms.core.persistence.jdo.JDODAOSupport;

@SuppressWarnings("unchecked")
public class NotificationDAOJDOImpl extends JDODAOSupport implements NotificationDAO {
    
    private static final Logger log = Logger.getLogger(NotificationDAOJDOImpl.class.getName());

    @Override
    public void save(Notification notification) {
	getPersistenceManager().makePersistent(notification);
    }

    @Override
    public boolean isDuplicate(Notification notification) {
	boolean result = false;
	try {
	    Query q = getPersistenceManager().newQuery(Notification.class);
	    q.setFilter("triggerDate == " + notification.getTriggerDate() + " && minutes == " + notification.getMinutes() + " && procesed == " + Boolean.FALSE + " && message =='"
		    + notification.getMessage() + "'");
	    List<Notification> results = (List<Notification>) q.execute();
	    if (!results.isEmpty()) {
		result = true;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return result;
    }

    @Override
    public List<Notification> getNotifications(String triggerDate, Integer minutes, Boolean processed) {
	List<Notification> results = null;
	Query q = getPersistenceManager().newQuery(Notification.class);
	q.setFilter("triggerDate == " + triggerDate + " && minutes <= " + minutes + " && procesed == " + processed);
	log.info("triggerDate == " + triggerDate + " && minutes <= " + minutes + " && procesed == " + processed);
	results = (List<Notification>) q.execute();
	log.info("Results:" +  results.size());
	return results;
    }

}