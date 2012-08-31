package com.iv.rms.entity;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;

@PersistenceCapable
public class Notification {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private Owner owner;
	
	@Persistent
	private Text message;
	
	@Persistent
	private Date creationDate;
	
	@Persistent
	private Integer triggerDate;
	
	@Persistent
	private Integer minutes;
	
	@Persistent
	private Boolean procesed = Boolean.FALSE;

	public Notification(){
		
	}
	
	public Notification(Key key, Owner owner, Text message, Date creationDate, Integer triggerDate, Integer minutes) {
		super();
		this.key = key;
		this.owner = owner;
		this.message = message;
		this.creationDate = creationDate;
		this.triggerDate = triggerDate;
		this.minutes = minutes;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public Text getMessage() {
		return message;
	}

	public void setMessage(Text message) {
		this.message = message;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getTriggerDate() {
		return triggerDate;
	}

	public void setTriggerDate(Integer triggerDate) {
		this.triggerDate = triggerDate;
	}
	
	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

	public Boolean getProcesed() {
		return procesed;
	}

	public void setProcesed(Boolean procesed) {
		this.procesed = procesed;
	}

}
