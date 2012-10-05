package com.iv.rms.user;

import com.google.appengine.api.users.UserServiceFactory;
import com.iv.rms.core.AbstractService;

public class GAEUserServiceImpl extends AbstractService implements UserService{
	
	private User user;
	
	public GAEUserServiceImpl(){
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public User getCurrentUser(){
		User user = new User(); 
		com.google.appengine.api.users.User gwtUser = UserServiceFactory.getUserService().getCurrentUser();
		if ( gwtUser != null ){
			user.setEmail(gwtUser.getEmail());
			user.setNickName(gwtUser.getNickname());
			user.setUserId(gwtUser.getUserId());
		}
		return user;
	}

}
