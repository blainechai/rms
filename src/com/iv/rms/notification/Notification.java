package com.iv.rms.notification;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.iv.rms.user.Owner;

@PersistenceCapable
public class Notification implements Serializable {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    @Persistent
    private String ownerId;

    private Owner owner;

    @Persistent
    private String message;

    @Persistent
    private Date creationDate;

    @Persistent
    private Integer triggerDate;

    @Persistent
    private Integer minutes;

    @Persistent
    private Boolean procesed = Boolean.FALSE;

    @Persistent
    private Date sentDate;

    public Notification() {

    }

    public Notification(Key key, String ownerId, String message, Date creationDate, Integer triggerDate, Integer minutes, Date sentDate) {
	super();
	this.key = key;
	this.ownerId = ownerId;
	this.message = message;
	this.creationDate = creationDate;
	this.triggerDate = triggerDate;
	this.minutes = minutes;
	this.sentDate = sentDate;
    }

    public Key getKey() {
	return key;
    }

    public void setKey(Key key) {
	this.key = key;
    }

    public String getOwnerId() {
	return ownerId;
    }

    public void setOwnerId(String ownerId) {
	this.ownerId = ownerId;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
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

    public Owner getOwner() {
	return owner;
    }

    public void setOwner(Owner owner) {
	this.owner = owner;
    }

    public Date getSentDate() {
	return sentDate;
    }

    public void setSentDate(Date sentDate) {
	this.sentDate = sentDate;
    }

}
