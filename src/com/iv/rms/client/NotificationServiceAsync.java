package com.iv.rms.client;

import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface NotificationServiceAsync {

	void saveNotification(SimpleNotification notification, AsyncCallback<Void> callback);

	void hasUserTimeZone(AsyncCallback<Boolean> callback);

	void getTimeZones(Date date, AsyncCallback<Timezones> callback);

}
