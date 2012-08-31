package com.iv.rms.client;

public enum NotificationViews {
	
	MAIL(1), INSTANT_MESSAGING(2);
	
	private int code;
	
	private NotificationViews(int c){
		code = c;
	}
	
	public int getCode(){
		return code;
	}

}
