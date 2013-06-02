package com.iv.rms.user;

import java.util.Date;
import java.util.List;

import javax.jdo.Query;

import com.iv.rms.core.persistence.jdo.JDODAOSupport;

@SuppressWarnings("unchecked")
public class OwnerDAOJDOImpl extends JDODAOSupport implements OwnerDAO {

    @Override
    public Owner getOwner(String userId) {

	Query q = getPersistenceManager().newQuery(Owner.class);
	q.setFilter("userId == userIdParam");
	q.declareParameters("String userIdParam");
	List<Owner> result = (List<Owner>) q.execute(userId);
	if (result != null && result.size() > 0) {
	    return result.get(0);
	}

	return null;
    }

    @Override
    public Owner getOwner(User user) {
	return getOwner(user.getUserId());
    }

    @Override
    public Owner createNewOwner(User user) {
	Owner owner = null;
	owner = new Owner();
	owner.setCreationDate(new Date());
	owner.setUserId(user.getUserId());
	owner.setEmail(user.getEmail());
	owner.setName(user.getNickName());
	getPersistenceManager().makePersistent(owner);
	return owner;
    }

}
