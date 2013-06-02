package com.iv.rms.core.persistence.jdo;

import javax.jdo.PersistenceManager;

import org.aspectj.lang.ProceedingJoinPoint;

import com.iv.rms.core.persistence.DAOException;

public class JDOOperation {

    private static final ThreadLocal<PersistenceManager> threadLocal = new ThreadLocal<PersistenceManager>();

    public Object perform(ProceedingJoinPoint joinpoint) {
	Object result = null;
	try {
	    JDODAOSupport daoSupport = (JDODAOSupport) joinpoint.getTarget();
	    injectPersistanceManager(daoSupport);
	    result = joinpoint.proceed();
	    PMF.close(daoSupport.getPersistenceManager());
	} catch (Throwable t) {
	    throw new DAOException(t);
	}
	return result;
    }

    public static PersistenceManager get() {
	return threadLocal.get();
    }

    private void injectPersistanceManager(JDODAOSupport daoSupport) {
	daoSupport.setPm(PMF.get().getPersistenceManager());
    }

}