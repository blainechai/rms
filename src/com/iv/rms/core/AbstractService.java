package com.iv.rms.core;

import javax.jdo.PersistenceManager;


public abstract class AbstractService implements Service{
	
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
	
	public PersistenceManager getPersistenceManager(){
		return PMF.get().getPersistenceManager();
	}
	
	protected String getProperty(String key){
		return serviceLocator.getPropertyService().getValue(key);
	}

}
