package com.iv.rms.core;

import javax.inject.Inject;
import javax.jdo.PersistenceManager;

import com.iv.rms.core.persistence.jdo.PMF;
import com.iv.rms.core.persistence.jdo.JDOOperation;


public abstract class AbstractService implements Service{
	
	@Inject
	private ServiceLocator serviceLocator;
	
	public AbstractService(){
		
	}
	
	/**
	 * Call this from spring
	 */
	public void initService(){
		System.out.println("Initialize service " + getName());
	}

	public ServiceLocator getServiceLocator() {
		return serviceLocator;
	}

	public void setServiceLocator(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}
	
	@Deprecated
	public PersistenceManager getPersistenceManager(){
		PersistenceManager pm = JDOOperation.get();
		if ( pm == null || !pm.isClosed() ){
			pm = PMF.get().getPersistenceManager();
		}
		return pm;
	}
	
	protected String getProperty(String key){
		return serviceLocator.getPropertyService().getValue(key);
	}
	
	@Override
	public String getName() {
		return this.getClass().getCanonicalName();
	}

}
