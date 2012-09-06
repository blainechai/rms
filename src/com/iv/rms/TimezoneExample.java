package com.iv.rms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;



public class TimezoneExample {
	
	public static void main(String args[]){
		List list = new ArrayList();
		String[] ids = TimeZone.getAvailableIDs();
		for (int i = 0; i < ids.length; i++) {
		        String tz = TimeZone.getTimeZone(ids[i]).getDisplayName();
		        TimeZone.getTimeZone(ids[i]);
		        if (!list.contains(tz)) {
		                list.add(tz);
		        }
		}
		                                
		Collections.sort(list);
		                                
		for (Iterator iter = list.iterator(); iter.hasNext();) {
		        System.out.println(iter.next());
		}
	}

}
