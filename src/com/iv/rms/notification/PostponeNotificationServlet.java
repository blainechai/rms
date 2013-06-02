package com.iv.rms.notification;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.iv.rms.core.ServiceLocator;
import com.iv.rms.core.SimpleStringCipher;

@SuppressWarnings("serial")
public class PostponeNotificationServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	ApplicationContext beanFactory = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
	ServiceLocator serviceLocator = (ServiceLocator) beanFactory.getBean("serviceLocator");

	String token = req.getParameter("token");
	if ( token != null ){
	    try {
		String decryptedToken = SimpleStringCipher.decrypt(token);
		Long id = Long.parseLong(decryptedToken.substring(0, decryptedToken.indexOf("-")));
		Integer days = Integer.parseInt(decryptedToken.substring(decryptedToken.indexOf("-") + 1, decryptedToken.length()));
		Notification newNotification = serviceLocator.getNotificationService().postponeNotification(id, days);
		RequestDispatcher rd = req.getRequestDispatcher("/postpone.jsp");
		rd.include(req, resp);
	    } catch (Exception e) {
		e.printStackTrace();
	    }

	}
    }
    
    

}
