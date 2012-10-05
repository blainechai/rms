package com.iv.rms.core.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.stereotype.Component;

import com.iv.rms.core.AbstractService;
import com.iv.rms.core.PropertyService;
import com.iv.rms.core.jdo.PMF;

@Component
public class PropertyServiceImpl extends AbstractService implements PropertyService {
	
	private static final Map<String, String> simpleCache = new HashMap<String, String>();
	
	private static final PropertyService instance = new PropertyServiceImpl();
	
	private static Properties prop;
	
	private PropertyServiceImpl(){
		loadApplicationPropertiesFile();
	}
	
	public static PropertyService getInstance(){
		return instance;
	}
	
	private void loadApplicationPropertiesFile(){
		prop  = new Properties();
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.iv.rms.server.PropertyService#getValue(java.lang.String)
	 */
	@Override
	public String getValue(String key){
		String value = null;
		if ( !simpleCache.containsKey(key)){
			Property prop = loadValueFromDB(key);
			if ( prop == null ){
				value = loadValueFromPropertiesFile(key);
				if ( value != null ){
					// save key-value to db
					saveProperty(new Property(key, value));
				}
			}else{
				value = prop.getValue();
				//compareValues(prop, loadValueFromPropertiesFile(key));
			}
			simpleCache.put(key, value);
			if ( prop != null && value != null ){
				compareValues(prop, value);
			}
		}
		value = simpleCache.get(key);
		return value;
	}
	
	private Property loadValueFromDB(String key){
		PersistenceManager pm = null;
		Property p = null;
		try{
			pm = PMF.get().getPersistenceManager();
			Query q = pm.newQuery(Property.class);
		    q.setFilter(" propertyKey == keyParam");
		    q.declareParameters("String keyParam");
		    List<Property> result = (List<Property>) q.execute(key);
		    if ( !result.isEmpty() ){
		    	p = result.get(0);
		    }
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			pm.close();
		}
		return p;
	}
	
	private String loadValueFromPropertiesFile(String key){
		if ( prop != null ){
			return prop.getProperty(key);
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.iv.rms.server.PropertyService#saveProperty(com.iv.rms.entity.Property)
	 */
	@Override
	public void saveProperty(Property prop){
		PersistenceManager pm = null;
		try{
			pm = PMF.get().getPersistenceManager();
			pm.makePersistent(prop);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			pm.close();
		}
	}
	
	private void compareValues(Property dbValue, String propValue){
		if ( !dbValue.getValue().equals(propValue)){
			PersistenceManager pm = null;
			try{
				pm = PMF.get().getPersistenceManager();
				pm.deletePersistent(dbValue);
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				pm.close();
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.iv.rms.server.PropertyService#getKeys()
	 */
	@Override
	public Set<String> getKeys(){
		return simpleCache.keySet();
	}

}
