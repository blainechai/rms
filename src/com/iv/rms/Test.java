package com.iv.rms;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class Test {
	
	public static void main(String args[]){
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.getTime());
		cal.setTimeZone(TimeZone.getTimeZone("GMT"));
		System.out.println(TimeZone.getTimeZone("Greenwich Mean Time"));
		System.out.println(cal.getTime());
		DateFormat df = new SimpleDateFormat();
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
		System.out.println(df.format(cal.getTime()));
	
	}

}
