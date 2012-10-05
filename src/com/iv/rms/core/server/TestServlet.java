package com.iv.rms.core.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.iv.rms.core.ServiceLocator;

public class TestServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApplicationContext beanFactory = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		ServiceLocator serviceLocator = (ServiceLocator) beanFactory.getBean("serviceLocator");
		System.out.println(serviceLocator.getMailService().getServiceLocator());
	}

}
