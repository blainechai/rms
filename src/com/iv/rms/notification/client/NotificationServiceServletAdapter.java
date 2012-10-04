package com.iv.rms.notification.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.iv.rms.notification.shared.ApplicationException;

@RemoteServiceRelativePath("notification")
public interface NotificationServiceServletAdapter extends RemoteService {

	public void saveNotification(SimpleNotification notification) throws ApplicationException;
	
	public Long saveTempNotification(SimpleNotification notification) throws ApplicationException;
	
	public Boolean hasUserTimeZone();
	
	public SimpleNotification getTempNotification(Long id);

	public void processPendingNotification();
	
}

