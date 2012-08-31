package com.iv.rms.client;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class SimpleNotification implements Serializable {
	
	private String message;
	
	private Date date;
	
	private Integer minutes;
	
	private List<NotificationViews> views;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

	public List<NotificationViews> getViews() {
		return views;
	}

	public void setViews(List<NotificationViews> views) {
		this.views = views;
	}

}
