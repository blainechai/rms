package com.iv.rms.core.impl;

import java.util.List;

import javax.jdo.Query;

import com.iv.rms.core.persistence.jdo.JDODAOSupport;

@SuppressWarnings("unchecked")
public class PropertyJDODAOImpl extends JDODAOSupport implements PropertyDAO {

    /*
     * (non-Javadoc)
     * 
     * @see com.iv.rms.core.impl.PropertyDAO#getProperty(java.lang.String)
     */
    @Override
    public Property getProperty(String key) {
	Property p = null;
	Query q = getPersistenceManager().newQuery(Property.class);
	q.setFilter(" propertyKey == keyParam");
	q.declareParameters("String keyParam");
	List<Property> result = (List<Property>) q.execute(key);
	if (!result.isEmpty()) {
	    p = result.get(0);
	}
	return p;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.iv.rms.core.impl.PropertyDAO#save(com.iv.rms.core.impl.Property)
     */
    @Override
    public void save(Property prop) {
	getPersistenceManager().makePersistent(prop);
    }

}
