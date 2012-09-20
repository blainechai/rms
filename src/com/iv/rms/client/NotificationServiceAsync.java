package com.iv.rms.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface NotificationServiceAsync {

	void saveNotification(SimpleNotification notification, AsyncCallback<Void> callback);

	void hasUserTimeZone(AsyncCallback<Boolean> callback);

	void saveUserContactMessage(String subject, String message, AsyncCallback<Void> callback);

	void saveTempNotification(SimpleNotification notification, AsyncCallback<Long> callback);

	void getTempNotification(Long id, AsyncCallback<SimpleNotification> callback);

}
