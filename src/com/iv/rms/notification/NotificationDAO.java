package com.iv.rms.notification;

import java.util.List;

import com.iv.rms.core.persistence.DAOException;

public interface NotificationDAO {

    public boolean isDuplicate(Notification notification) throws DAOException;

    public void save(Notification notification) throws DAOException;

    public List<Notification> getNotifications(String triggerDate, Integer minutes, Boolean processed);

}
