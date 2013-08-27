package com.iv.rms.notification;

import java.util.List;

import com.iv.rms.core.persistence.DAOException;

public interface NotificationDAO {

    boolean isDuplicate(Notification notification) throws DAOException;

    void save(Notification notification) throws DAOException;

    List<Notification> getNotifications(String triggerDate, Integer minutes, Boolean processed);

    Notification load(Long id);

    void save(NotificationView notificationView);

    List<NotificationView> loadNotificationView(Long notificationId);

}
