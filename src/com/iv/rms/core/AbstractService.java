package com.iv.rms.core;

import javax.inject.Inject;
import javax.jdo.PersistenceManager;

import com.iv.rms.core.jdo.PMF;


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
		return PMF.get().getPersistenceManager();
	}
	
	protected String getProperty(String key){
		return serviceLocator.getPropertyService().getValue(key);
	}
	
	@Override
	public String getName() {
		return this.getClass().getCanonicalName();
	}

}
