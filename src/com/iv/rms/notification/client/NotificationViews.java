package com.iv.rms.notification.client;

public enum NotificationViews {

    MAIL(1), INSTANT_MESSAGING(2), SMS(3);

    private int code;

    private NotificationViews(int c) {
	code = c;
    }

    public int getCode() {
	return code;
    }

}
