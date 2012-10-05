package com.iv.rms.notification;

import com.iv.rms.notification.client.SimpleNotification;
import com.iv.rms.notification.shared.ApplicationException;
import com.iv.rms.user.User;

public interface NotificationService   {

	public void saveNotification(SimpleNotification notification, User user) throws ApplicationException;
	
	public Long saveTempNotification(SimpleNotification notification) throws ApplicationException;
	
	public Boolean hasUserTimeZone(User user);
	
	public SimpleNotification getTempNotification(Long id);

	public void processPendingNotification();
	
}

