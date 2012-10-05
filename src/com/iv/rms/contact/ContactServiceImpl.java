package com.iv.rms.contact;

import java.util.Date;

import javax.jdo.PersistenceManager;

import org.springframework.stereotype.Component;

import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.users.UserServiceFactory;
import com.iv.rms.contact.shared.ContactException;
import com.iv.rms.core.AbstractService;
import com.iv.rms.core.PMF;

@Component
public class ContactServiceImpl extends AbstractService implements ContactService{
	
	private static final String PLEASE_LOGIN_MSG = "pleaseLoginMsg";
	
	@Override
	public void saveUserContactMessage(String subject, String message) throws ContactException{
		if ( UserServiceFactory.getUserService().getCurrentUser() == null ){
			throw new ContactException(getProperty(PLEASE_LOGIN_MSG));
		}
		UserContactMessage m = new UserContactMessage();
		m.setSubject(subject);
		m.setMessage(new Text(message));
		m.setCreationDate(new Date());
		m.setUserId(UserServiceFactory.getUserService().getCurrentUser().getUserId());
		PersistenceManager pm = null;
		try{
			pm = getPersistenceManager();
			pm.makePersistent(m);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			PMF.close(pm);
		}
		// send email
		getServiceLocator().getMailService().sendAdminMail("New contact message", "Message from " + UserServiceFactory.getUserService().getCurrentUser().getEmail() + " Content:" + message);
	}

}
