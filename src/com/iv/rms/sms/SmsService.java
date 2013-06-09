package com.iv.rms.sms;

public interface SmsService {
	
	void send(String phoneNumber, String message);

}
