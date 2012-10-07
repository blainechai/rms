package com.iv.rms.contact;

import com.iv.rms.core.persistence.jdo.JDODAOSupport;

public class ContactJDODAOImpl extends JDODAOSupport implements ContactDAO{
	
	@Override
	public void save(UserContactMessage contactMessage){
		getPersistenceManager().makePersistent(contactMessage);
	}

}
