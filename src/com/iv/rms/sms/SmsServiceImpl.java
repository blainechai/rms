package com.iv.rms.sms;

import java.util.HashMap;
import java.util.Map;

import com.iv.rms.core.AbstractService;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Account;

public class SmsServiceImpl extends AbstractService implements SmsService {

	private static final String BODY = "Body";
	private static final String FROM = "From";
	private static final String TO = "To";
	private static final String TWILIO_PHONE_NUMBER = "TWILIO_PHONE_NUMBER";
	private static final String TWILIO_AUTH_TOKEN = "TWILIO_AUTH_TOKEN";
	private static final String TWILIO_ACCOUNT_SID = "TWILIO_ACCOUNT_SID";

	@Override
	public void send(String phoneNumber, String message) {
		try {
			TwilioRestClient client = new TwilioRestClient(getProperty(TWILIO_ACCOUNT_SID), getProperty(TWILIO_AUTH_TOKEN));
			Account mainAccount = client.getAccount();
			SmsFactory smsFactory = mainAccount.getSmsFactory();
			Map<String, String> smsParams = new HashMap<String, String>();
			smsParams.put(TO, phoneNumber);
			smsParams.put(FROM, getProperty(TWILIO_PHONE_NUMBER));
			smsParams.put(BODY, message);
			if (phoneNumber != null && phoneNumber.length() > 0) {
				smsFactory.create(smsParams);
			}
		} catch (TwilioRestException e) {
			e.printStackTrace();
		}

	}

}
