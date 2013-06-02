package com.iv.rms.core.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.jdo.PersistenceManager;

import org.springframework.stereotype.Component;

import com.iv.rms.core.AbstractService;
import com.iv.rms.core.PropertyService;
import com.iv.rms.core.persistence.jdo.PMF;

@Component
public class PropertyServiceImpl extends AbstractService implements PropertyService {

    private static final Map<String, String> simpleCache = new HashMap<String, String>();

    private static final PropertyService instance = new PropertyServiceImpl();

    private PropertyDAO propertyDAO;

    private PropertyDAO filePropertyDAO = new PropertyFileDAOImpl(); // inject
								     // this
								     // using
								     // spring

    private PropertyServiceImpl() {

    }

    public static PropertyService getInstance() {
	return instance;
    }

    @Override
    public String getValue(String key) {
	String value = null;
	if (!simpleCache.containsKey(key)) {
	    Property prop = loadValueFromDB(key);
	    if (prop == null) {
		value = filePropertyDAO.getProperty(key).getValue();
		if (value != null) {
		    // save key-value to db
		    saveProperty(new Property(key, value));
		}
	    } else {
		value = prop.getValue();
		// compareValues(prop, loadValueFromPropertiesFile(key));
	    }
	    simpleCache.put(key, value);
	    if (prop != null && value != null) {
		compareValues(prop, value);
	    }
	}
	value = simpleCache.get(key);
	return value;
    }

    private Property loadValueFromDB(String key) {
	return propertyDAO.getProperty(key);
    }

    @Override
    public void saveProperty(Property prop) {
	propertyDAO.save(prop);
    }

    @Override
    public Set<String> getKeys() {
	return simpleCache.keySet();
    }

    public PropertyDAO getPropertyDAO() {
	return propertyDAO;
    }

    public void setPropertyDAO(PropertyDAO propertyDAO) {
	this.propertyDAO = propertyDAO;
    }

    public PropertyDAO getFilePropertyDAO() {
	return filePropertyDAO;
    }

    public void setFilePropertyDAO(PropertyDAO filePropertyDAO) {
	this.filePropertyDAO = filePropertyDAO;
    }

    private void compareValues(Property dbValue, String propValue) {
	if (!dbValue.getValue().equals(propValue)) {
	    PersistenceManager pm = null;
	    try {
		pm = PMF.get().getPersistenceManager();
		pm.deletePersistent(dbValue);
	    } catch (Exception e) {
		e.printStackTrace();
	    } finally {
		pm.close();
	    }
	}
    }

    public void refresh() {
	simpleCache.clear();
    }

}
