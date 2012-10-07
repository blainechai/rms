package com.iv.rms.notification;

import com.iv.rms.core.persistence.DAOException;

public interface TempNotificationDAO {

	public void saveTempNotification(TempNotification tempNotification) throws DAOException;

	public TempNotification getTempNotification(Long id) throws DAOException;

}
