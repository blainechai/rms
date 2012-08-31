package com.iv.rms.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("notification")
public interface NotificationService extends RemoteService {

	public void saveNotification(SimpleNotification notification);
	
}

