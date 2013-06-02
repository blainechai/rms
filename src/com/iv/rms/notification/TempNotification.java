package com.iv.rms.notification;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class TempNotification implements Serializable {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    @Persistent
    private String message;

    @Persistent
    private Date triggerDate;

    @Persistent
    private Integer minutes;

    public Key getKey() {
	return key;
    }

    public void setKey(Key key) {
	this.key = key;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    public Date getTriggerDate() {
	return triggerDate;
    }

    public void setTriggerDate(Date triggerDate) {
	this.triggerDate = triggerDate;
    }

    public Integer getMinutes() {
	return minutes;
    }

    public void setMinutes(Integer minutes) {
	this.minutes = minutes;
    }

}
