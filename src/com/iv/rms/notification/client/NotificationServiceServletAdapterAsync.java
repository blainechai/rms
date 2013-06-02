package com.iv.rms.notification.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface NotificationServiceServletAdapterAsync {

    void saveNotification(SimpleNotification notification, AsyncCallback<Void> callback);

    void hasUserTimeZone(AsyncCallback<Boolean> callback);

    void saveTempNotification(SimpleNotification notification, AsyncCallback<Long> callback);

    void getTempNotification(Long id, AsyncCallback<SimpleNotification> callback);

    void processPendingNotification(AsyncCallback<Void> callback);
}
