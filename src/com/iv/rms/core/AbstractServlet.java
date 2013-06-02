package com.iv.rms.core;

import javax.servlet.http.HttpServlet;

import com.iv.rms.core.impl.WebApplicationServiceLocatorProvider;

@SuppressWarnings("serial")
public abstract class AbstractServlet extends HttpServlet implements ApplicationServlet {

    private ApplicationServiceLocatorProvider serviceLocatorProvider;

    public ServiceLocator getServiceLocator() {
	serviceLocatorProvider = new WebApplicationServiceLocatorProvider(getServletContext());
	return serviceLocatorProvider.getServiceLocator();
    }

}
