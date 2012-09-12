package com.iv.rms.client;

import java.util.Date;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.iv.rms.shared.ApplicationException;

@RemoteServiceRelativePath("notification")
public interface NotificationService extends RemoteService {

	public void saveNotification(SimpleNotification notification) throws ApplicationException;
	
	public Boolean hasUserTimeZone();
	
	public Timezones getTimeZones(Date date);
	
	public void saveUserContactMessage(String subject, String message) throws ApplicationException;
	
}

