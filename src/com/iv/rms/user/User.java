package com.iv.rms.user;

public class User {

    private String userId;

    private String nickName;

    private String fullName;

    private String email;

    public String getUserId() {
	return userId;
    }

    public void setUserId(String userId) {
	this.userId = userId;
    }

    public String getNickName() {
	return nickName;
    }

    public void setNickName(String nickName) {
	this.nickName = nickName;
    }

    public String getFullName() {
	return fullName;
    }

    public void setFullName(String fullName) {
	this.fullName = fullName;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

}
