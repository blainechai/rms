package com.iv.rms.client;

import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.iv.rms.entity.UserContactMessage;
import com.iv.rms.shared.ApplicationException;

public interface NotificationServiceAsync {

	void saveNotification(SimpleNotification notification, AsyncCallback<Void> callback);

	void hasUserTimeZone(AsyncCallback<Boolean> callback);

	void getTimeZones(Date date, AsyncCallback<Timezones> callback);

	void saveUserContactMessage(String subject, String message, AsyncCallback<Void> callback);

}
