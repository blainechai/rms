package com.iv.rms.user;

public interface UserService {
	
	public User getUser();

	public User getCurrentUser();
	
	public Owner getOwner(User user);
	
	public Owner getOwner(String userId);
	
	public Owner createOwner(User user);

}
