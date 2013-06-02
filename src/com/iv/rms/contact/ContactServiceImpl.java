package com.iv.rms.contact;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.google.appengine.api.datastore.Text;
import com.iv.rms.contact.shared.ContactException;
import com.iv.rms.core.AbstractService;

@Component
public class ContactServiceImpl extends AbstractService implements ContactService {

    private static final String PLEASE_LOGIN_MSG = "pleaseLoginMsg";

    private ContactDAO contactDAO;

    public ContactDAO getContactDAO() {
	return contactDAO;
    }

    public void setContactDAO(ContactDAO contactDAO) {
	this.contactDAO = contactDAO;
    }

    @Override
    public void saveUserContactMessage(String subject, String message) throws ContactException {
	if (getServiceLocator().getUserService().getCurrentUser() == null) {
	    throw new ContactException(getProperty(PLEASE_LOGIN_MSG));
	}
	UserContactMessage m = new UserContactMessage();
	m.setSubject(subject);
	m.setMessage(new Text(message));
	m.setCreationDate(new Date());
	m.setUserId(getServiceLocator().getUserService().getCurrentUser().getUserId());
	contactDAO.save(m);
	getServiceLocator().getMailService().sendAdminMail("New contact message", "Message from " + getServiceLocator().getUserService().getCurrentUser().getEmail() + " Content:" + message);
    }

}
