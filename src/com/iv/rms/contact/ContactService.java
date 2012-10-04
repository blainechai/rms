package com.iv.rms.contact;

import com.iv.rms.contact.shared.ContactException;
import com.iv.rms.notification.shared.ApplicationException;

public interface ContactService {
	
	public void saveUserContactMessage(String subject, String message) throws ContactException;

}
