package com.iv.rms.user;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Owner {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    @Persistent
    private String userId;

    @Persistent
    private Date creationDate;

    @Persistent
    private String email;

    @Persistent
    private String name;

    @Persistent
    private String timeZoneId;

    public Owner() {

    }

    public Owner(Key key, String userId, Date creationDate, String email, String timeZoneId) {
	super();
	this.key = key;
	this.userId = userId;
	this.creationDate = creationDate;
	this.email = email;
	this.timeZoneId = timeZoneId;
    }

    public Key getKey() {
	return key;
    }

    public void setKey(Key key) {
	this.key = key;
    }

    public String getUserId() {
	return userId;
    }

    public void setUserId(String userId) {
	this.userId = userId;
    }

    public Date getCreationDate() {
	return creationDate;
    }

    public void setCreationDate(Date creationDate) {
	this.creationDate = creationDate;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getTimeZoneId() {
	return timeZoneId;
    }

    public void setTimeZoneId(String timeZoneId) {
	this.timeZoneId = timeZoneId;
    }

}
