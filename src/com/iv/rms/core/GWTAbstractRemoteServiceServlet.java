package com.iv.rms.core;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.iv.rms.core.impl.WebApplicationServiceLocatorProvider;

@SuppressWarnings("serial")
public abstract class GWTAbstractRemoteServiceServlet extends RemoteServiceServlet implements ApplicationServlet{
	
	private ApplicationServiceLocatorProvider serviceLocatorProvider ; 

	public ServiceLocator getServiceLocator(){
		serviceLocatorProvider = new WebApplicationServiceLocatorProvider(getServletContext());
		return serviceLocatorProvider.getServiceLocator();
	}

}
