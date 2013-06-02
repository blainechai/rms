package com.iv.rms.contact.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.iv.rms.contact.shared.ContactException;

@RemoteServiceRelativePath("contact")
public interface ContactServiceServletAdapter extends RemoteService {

    public void saveUserContactMessage(String subject, String message) throws ContactException;

    void test();

}
