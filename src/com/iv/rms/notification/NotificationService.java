package com.iv.rms.notification;

import com.iv.rms.notification.client.SimpleNotification;
import com.iv.rms.notification.shared.NotificationException;
import com.iv.rms.user.User;

public interface NotificationService {

    public void saveNotification(SimpleNotification notification, User user) throws NotificationException;

    public Long saveTempNotification(SimpleNotification notification) throws NotificationException;

    public Boolean hasUserTimeZone(User user);

    public SimpleNotification getTempNotification(Long id);

    public void processPendingNotification();

}
