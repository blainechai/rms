package com.iv.rms.core.persistence.jdo;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

public final class PMF {
	
	private static final PersistenceManagerFactory pmfInstance = JDOHelper.getPersistenceManagerFactory("transactions-optional");

	private PMF() {
	}

	public static PersistenceManagerFactory get() {
		return pmfInstance;
	}
	
	public static void close(PersistenceManager pm){
		try{
			if ( pm != null ){
				pm.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}