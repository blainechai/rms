package com.iv.rms.entity;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class NotificationView {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private Long notificationId;
	
	@Persistent
	private Integer viewType;

	public NotificationView(Key key, Long notificationId, Integer viewType) {
		super();
		this.key = key;
		this.notificationId = notificationId;
		this.viewType = viewType;
	}
	
	public NotificationView(){
		
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public Long getNotificationKey() {
		return notificationId;
	}

	public void setNotificationKey(Long notificationKey) {
		this.notificationId = notificationKey;
	}

	public Integer getViewType() {
		return viewType;
	}

	public void setViewType(Integer viewType) {
		this.viewType = viewType;
	}

}
