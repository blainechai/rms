package com.iv.rms.client;

import java.io.Serializable;
import java.util.Map;

@SuppressWarnings("serial")
public class Timezones implements Serializable{
	
	private Map<String,String> timeZoneIds ;
	
	private String selectedId;

	public Timezones() {
		
	}

	public Timezones(Map<String, String> timeZoneIds, String selectedId) {
		this.timeZoneIds = timeZoneIds;
		this.selectedId = selectedId;
	}

	public Map<String, String> getTimeZoneIds() {
		return timeZoneIds;
	}

	public void setTimeZoneIds(Map<String, String> timeZoneIds) {
		this.timeZoneIds = timeZoneIds;
	}

	public String getSelectedId() {
		return selectedId;
	}

	public void setSelectedId(String selectedId) {
		this.selectedId = selectedId;
	}

}
