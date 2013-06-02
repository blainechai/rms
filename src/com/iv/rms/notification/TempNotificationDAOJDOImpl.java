package com.iv.rms.notification;

import javax.jdo.PersistenceManager;

import com.iv.rms.core.persistence.DAOException;
import com.iv.rms.core.persistence.jdo.JDODAOSupport;
import com.iv.rms.core.persistence.jdo.PMF;

public class TempNotificationDAOJDOImpl extends JDODAOSupport implements TempNotificationDAO {

    @Override
    public void saveTempNotification(TempNotification tempNotification) throws DAOException {
	PersistenceManager pm = null;
	try {
	    pm = getPersistenceManager();
	    pm.makePersistent(tempNotification);
	} catch (Exception e) {
	    throw new DAOException(e);
	} finally {
	    PMF.close(pm);
	}
    }

    @Override
    public TempNotification getTempNotification(Long id) throws DAOException {
	PersistenceManager pm = null;
	TempNotification tempNotification = null;
	try {
	    pm = getPersistenceManager();
	    tempNotification = (TempNotification) pm.getObjectById(TempNotification.class, id);
	} catch (Exception e) {
	    throw new DAOException(e);
	} finally {
	    PMF.close(pm);
	}
	return tempNotification;
    }

}
