package com.iv.rms.contact;

import com.iv.rms.contact.shared.ContactException;

public interface ContactService {

    public void saveUserContactMessage(String subject, String message) throws ContactException;

}
