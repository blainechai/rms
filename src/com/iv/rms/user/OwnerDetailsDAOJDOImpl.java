package com.iv.rms.user;

import java.util.List;

import javax.jdo.Query;

import com.iv.rms.core.persistence.jdo.JDODAOSupport;

public class OwnerDetailsDAOJDOImpl extends JDODAOSupport implements OwnerDetailsDAO{

	@Override
	public OwnerDetails load(String userId) {
		OwnerDetails details = null;
		Query q = getPersistenceManager().newQuery(OwnerDetails.class);
		q.setFilter("userId == userIdParam");
		q.declareParameters("String userIdParam");
		List<OwnerDetails> result = (List<OwnerDetails>) q.execute(userId);
		if (result != null && result.size() > 0) {
			details = result.get(0);
		}
		return details;
	}

	@Override
	public OwnerDetails save(OwnerDetails details) {
		return getPersistenceManager().makePersistent(details);
	}
	
	

}
