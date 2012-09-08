package com.iv.rms.shared;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Util {

	public static Integer formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		String str = sdf.format(date);
		return Integer.parseInt(str);
	}

	public static Integer getMinutesSinceMidnight(Date date) {
		Integer minutes = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		minutes = Integer.parseInt(sdf.format(date)) * 60;
		sdf = new SimpleDateFormat("mm");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		minutes += Integer.parseInt(sdf.format(date));
		return minutes;
	}

	private static String convTimeZone(String time, String sourceTZ, String destTZ) {
		final String DATE_TIME_FORMAT = "yyyyMMdd-HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
		Date specifiedTime;
		try {
			if (sourceTZ != null) {
				sdf.setTimeZone(TimeZone.getTimeZone(sourceTZ));
			} else {
				sdf.setTimeZone(TimeZone.getDefault()); // default to server's
														// timezone
			}
			specifiedTime = sdf.parse(time);
		} catch (Exception e1) {
			return "";
		}
		// switch timezone
		if (destTZ != null) {
			sdf.setTimeZone(TimeZone.getTimeZone(destTZ));
		} else {
			sdf.setTimeZone(TimeZone.getDefault()); // default to server's
													// timezone
		}
		return sdf.format(specifiedTime);
	}

	public static Date getDateInTimeZone(Date currentDate, String timeZoneId) {
		Calendar mbCal = new GregorianCalendar(TimeZone.getTimeZone(timeZoneId));
		mbCal.setTimeInMillis(currentDate.getTime());
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, mbCal.get(Calendar.YEAR));
		cal.set(Calendar.MONTH, mbCal.get(Calendar.MONTH));
		cal.set(Calendar.DAY_OF_MONTH, mbCal.get(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, mbCal.get(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, mbCal.get(Calendar.MINUTE));
		cal.set(Calendar.SECOND, mbCal.get(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, mbCal.get(Calendar.MILLISECOND));

		return cal.getTime();
	}

	public static void main(String args[]) throws ParseException {
		Date date = new Date();
		System.out.println(date);
		System.out.println(getDateInTimeZone(date, "GMT"));
	}

}
