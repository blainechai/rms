package com.iv.rms.core.impl;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.iv.rms.core.ApplicationServiceLocatorProvider;
import com.iv.rms.core.ServiceLocator;

public class WebApplicationServiceLocatorProvider implements ApplicationServiceLocatorProvider {
	
	private ServletContext servletContext;
	
	public WebApplicationServiceLocatorProvider(ServletContext context){
		this.servletContext = context;
	}
	
	/* (non-Javadoc)
	 * @see com.iv.rms.core.ApplicationServiceLocatorProvider#getServiceLocator()
	 */
	@Override
	public ServiceLocator getServiceLocator(){
		ApplicationContext beanFactory = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		if ( beanFactory !=  null ){
			return (ServiceLocator) beanFactory.getBean("serviceLocator");
		}
		throw new RuntimeException("Spring is not initialised");
	}

}
