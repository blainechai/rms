package com.iv.rms.core.jdo;

import javax.jdo.PersistenceManager;


public class JDODAOSupport {
	
	public PersistenceManager getPersistenceManager(){
		return PMF.get().getPersistenceManager();
	}

}
