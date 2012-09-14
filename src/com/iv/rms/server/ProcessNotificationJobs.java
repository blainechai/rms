package com.iv.rms.server;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ProcessNotificationJobs extends HttpServlet{
	
	private static final DateFormat df = new SimpleDateFormat("mm");

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int minutes = Integer.parseInt(df.format(new Date()));
		if ( minutes == 15 || minutes == 30 || minutes == 45 || minutes == 0 ){
			NotificationServiceImpl notificationService = new NotificationServiceImpl();
			notificationService.processPendingNotification();
		}
		
	}

}
