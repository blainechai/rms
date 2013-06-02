package com.iv.rms.notification.shared;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import com.iv.rms.notification.client.SimpleNotification;

public class Util {

    public static Integer formatDate(Date date, TimeZone timeZone) {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	sdf.setTimeZone(timeZone);
	String str = sdf.format(date);
	return Integer.parseInt(str);
    }

    public static Integer getMinutesSinceMidnight(Date date, TimeZone timeZone) {
	Integer minutes = 0;
	SimpleDateFormat sdf = new SimpleDateFormat("HH");
	sdf.setTimeZone(timeZone);
	minutes = Integer.parseInt(sdf.format(date)) * 60;
	sdf = new SimpleDateFormat("mm");
	sdf.setTimeZone(timeZone);
	minutes += Integer.parseInt(sdf.format(date));
	return minutes;
    }

    public static TimeZone getTimeZone(String strTz, String defaultTimeZone) {
	String strFromJavaScript = strTz;
	int timeZone = Integer.parseInt(strFromJavaScript);
	if (timeZone >= 0) {
	    strFromJavaScript = "+" + timeZone;
	}
	TimeZone tz = TimeZone.getTimeZone(defaultTimeZone + strFromJavaScript);
	return tz;
    }

    public static Date getDateInTimeZone(Date currentDate, String currentTimeZoneId, String timeZoneId) {
	Calendar mbCal = new GregorianCalendar(TimeZone.getTimeZone(timeZoneId));
	mbCal.setTimeInMillis(currentDate.getTime());

	Calendar cal = Calendar.getInstance();
	cal.setTimeZone(TimeZone.getTimeZone(currentTimeZoneId));
	cal.set(Calendar.YEAR, mbCal.get(Calendar.YEAR));
	cal.set(Calendar.MONTH, mbCal.get(Calendar.MONTH));
	cal.set(Calendar.DAY_OF_MONTH, mbCal.get(Calendar.DAY_OF_MONTH));
	cal.set(Calendar.HOUR_OF_DAY, mbCal.get(Calendar.HOUR_OF_DAY));
	cal.set(Calendar.MINUTE, mbCal.get(Calendar.MINUTE));
	cal.set(Calendar.SECOND, mbCal.get(Calendar.SECOND));
	cal.set(Calendar.MILLISECOND, mbCal.get(Calendar.MILLISECOND));

	return cal.getTime();
    }

    public static Integer formatMinutes(Date date) {
	SimpleDateFormat sdf = new SimpleDateFormat("HH");
	int mins = Integer.parseInt(sdf.format(date)) * 60;
	sdf = new SimpleDateFormat("mm");
	mins += Integer.parseInt(sdf.format(date));
	return mins;
    }

    public static Integer extractTriggerDate(Date date) {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	String str = sdf.format(date);
	return Integer.parseInt(str);
    }

    public static Date composeFullTriggerDate(SimpleNotification sn, String defaultTimeZone) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(sn.getDate());
	cal.set(Calendar.HOUR_OF_DAY, sn.getMinutes() / 60);
	cal.set(Calendar.MINUTE, sn.getMinutes() - ((sn.getMinutes() / 60) * 60));
	return Util.getDateInTimeZone(cal.getTime(), Util.getTimeZone(sn.getTimeZone(), defaultTimeZone).getID(), defaultTimeZone);
    }

}
