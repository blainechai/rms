package com.iv.rms.notification.server;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.iv.rms.core.ServiceLocator;

@SuppressWarnings("serial")
public class ProcessNotificationJobs extends HttpServlet {

    private static final DateFormat df = new SimpleDateFormat("mm");

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	int minutes = Integer.parseInt(df.format(new Date()));
	if (minutes == 15 || minutes == 30 || minutes == 45 || minutes == 0) {
	    getServiceLocator().getNotificationService().processPendingNotification();
	}
    }

    public ServiceLocator getServiceLocator() {
	ApplicationContext beanFactory = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
	return (ServiceLocator) beanFactory.getBean("serviceLocator");
    }

}
