package com.iv.rms.contact.server;

import com.iv.rms.contact.client.ContactServiceServletAdapter;
import com.iv.rms.contact.shared.ContactException;
import com.iv.rms.core.GWTAbstractRemoteServiceServlet;

@SuppressWarnings("serial")
public class ContactServiceServletAdapterImpl extends GWTAbstractRemoteServiceServlet implements ContactServiceServletAdapter {

    @Override
    public void saveUserContactMessage(String subject, String message) throws ContactException {
	getServiceLocator().getContactService().saveUserContactMessage(subject, message);
    }

    @Override
    public void test() {
	// TODO Auto-generated method stub

    }

}
