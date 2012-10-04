package com.iv.rms.notification;

import com.iv.rms.notification.client.SimpleNotification;
import com.iv.rms.notification.shared.ApplicationException;

public interface NotificationService   {

	public void saveNotification(SimpleNotification notification) throws ApplicationException;
	
	public Long saveTempNotification(SimpleNotification notification) throws ApplicationException;
	
	public Boolean hasUserTimeZone();
	
	public SimpleNotification getTempNotification(Long id);

	public void processPendingNotification();
	
}

