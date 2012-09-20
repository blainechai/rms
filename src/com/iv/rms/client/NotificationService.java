package com.iv.rms.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.iv.rms.shared.ApplicationException;

@RemoteServiceRelativePath("notification")
public interface NotificationService extends RemoteService {

	public void saveNotification(SimpleNotification notification) throws ApplicationException;
	
	public Long saveTempNotification(SimpleNotification notification) throws ApplicationException;
	
	public Boolean hasUserTimeZone();
	
	public void saveUserContactMessage(String subject, String message) throws ApplicationException;
	
	public SimpleNotification getTempNotification(Long id);
	
}

