package com.iv.rms.user;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.iv.rms.core.jdo.JDODAOSupport;
import com.iv.rms.core.jdo.PMF;

public class OwnerDAOJDOImpl extends JDODAOSupport implements OwnerDAO{
	
	@Override
	public Owner getOwner(String userId){
		PersistenceManager pm = null;
		try{
			pm = getPersistenceManager();
			Query q = pm.newQuery(Owner.class);
		    q.setFilter("userId == userIdParam");
		    q.declareParameters("String userIdParam");
		    List<Owner> result = (List<Owner>) q.execute(userId);
		    if ( result != null && result.size() > 0){
		    	return result.get(0);
		    }
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			PMF.close(pm);
		}
		return null;
	}
	
	@Override
	public Owner getOwner(User user){
		return getOwner(user.getUserId());
	}
	
	@Override
	public Owner createNewOwner(User user){
		Owner owner = null;
		PersistenceManager pm = null;
		try{
			owner = new Owner();
			owner.setCreationDate(new Date());
			owner.setUserId(user.getUserId());
			owner.setEmail(user.getEmail());
			owner.setName(user.getNickName());
			pm = getPersistenceManager(); 
			pm.makePersistent(owner);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			PMF.close(pm);
		}
		return owner;
	}

}
