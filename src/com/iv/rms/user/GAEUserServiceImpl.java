package com.iv.rms.user;

import com.google.appengine.api.users.UserServiceFactory;
import com.iv.rms.core.AbstractService;

public class GAEUserServiceImpl extends AbstractService implements UserService {

	private OwnerDAO ownerDAO;
	
	private OwnerDetailsDAO ownerDetailsDAO;

	private User user;

	public GAEUserServiceImpl() {

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public User getCurrentUser() {
		User user = new User();
		com.google.appengine.api.users.User gwtUser = UserServiceFactory.getUserService().getCurrentUser();
		if (gwtUser != null) {
			user.setEmail(gwtUser.getEmail());
			user.setNickName(gwtUser.getNickname());
			user.setUserId(gwtUser.getUserId());
		}
		return user;
	}

	@Override
	public Owner getOwner(User user) {
		return ownerDAO.getOwner(user);
	}

	@Override
	public Owner createOwner(User user) {
		return ownerDAO.createNewOwner(user);
	}

	public OwnerDAO getOwnerDAO() {
		return ownerDAO;
	}

	public void setOwnerDAO(OwnerDAO ownerDAO) {
		this.ownerDAO = ownerDAO;
	}

	@Override
	public Owner getOwner(String userId) {
		return ownerDAO.getOwner(userId);
	}
	
	public OwnerDetailsDAO getOwnerDetailsDAO() {
		return ownerDetailsDAO;
	}

	public void setOwnerDetailsDAO(OwnerDetailsDAO ownerDetailsDAO) {
		this.ownerDetailsDAO = ownerDetailsDAO;
	}

	@Override
	public OwnerDetails getOwnerDetails(String ownerId) {
		return ownerDetailsDAO.load(ownerId);
	}

	@Override
	public void save(OwnerDetails details) {
		ownerDetailsDAO.save(details);
	}

}
