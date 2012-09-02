package com.iv.rms.shared;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	
	public static Integer formatDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String str = sdf.format(date);
		return Integer.parseInt(str);
	}
	
	public static Integer getMinutesSinceMidnight(Date date){
		Integer minutes = 0 ;
		SimpleDateFormat sdf = new SimpleDateFormat("kk");
		minutes = Integer.parseInt(sdf.format(date)) * 60 ;
		sdf = new SimpleDateFormat("mm");
		minutes += Integer.parseInt(sdf.format(date));
		return minutes;
	}
	
	public static void main(String args[]){
		System.out.println(getMinutesSinceMidnight(new Date()));	
	}

}
