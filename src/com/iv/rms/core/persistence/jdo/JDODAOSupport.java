package com.iv.rms.core.persistence.jdo;

import javax.jdo.PersistenceManager;

import com.iv.rms.core.persistence.DAO;

public abstract class JDODAOSupport implements DAO {

    private PersistenceManager pm;

    public PersistenceManager getPm() {
	return pm;
    }

    public void setPm(PersistenceManager pm) {
	this.pm = pm;
    }

    public PersistenceManager getPersistenceManager() {
	return getPm();
    }

}
