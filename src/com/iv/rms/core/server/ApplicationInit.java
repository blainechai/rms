package com.iv.rms.core.server;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationInit implements ServletContextListener {

	private ServletContext context;

	public void contextInitialized(ServletContextEvent contextEvent) {
//		System.out.println("Context Created");
//		context = contextEvent.getServletContext();
//		XmlWebApplicationContext  beanFactory = new  XmlWebApplicationContext();
//		beanFactory.setServletContext(context);
//		contextEvent.getServletContext().setAttribute("beanFactory", beanFactory);
//		beanFactory.refresh();
	}

	public void contextDestroyed(ServletContextEvent contextEvent) {
//		context = contextEvent.getServletContext();
//		System.out.println("Context Destroyed");
	}
}