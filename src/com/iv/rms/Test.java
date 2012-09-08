package com.iv.rms;

import java.util.Calendar;
import java.util.Date;

import com.iv.rms.server.NotificationServiceImpl;
import com.iv.rms.shared.Util;


public class Test {
	
	public static void main(String args[]){

		NotificationServiceImpl service = new NotificationServiceImpl();
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 00);
		date = cal.getTime();
		System.out.println(date);
		Date newDate = Util.getDateInTimeZone(date,"", "");
		System.out.println(newDate);
		System.out.println(service.formatDate(newDate));
	
	}

}
