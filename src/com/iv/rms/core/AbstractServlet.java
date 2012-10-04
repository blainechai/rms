package com.iv.rms.core;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public abstract class AbstractServlet extends RemoteServiceServlet{

	public ServiceLocator getServiceLocator(){
		ApplicationContext beanFactory = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		return (ServiceLocator) beanFactory.getBean("serviceLocator");
	}

}
