package com.iv.rms.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface NotificationServiceAsync {

	void saveNotification(SimpleNotification notification, AsyncCallback<Void> callback);

}
