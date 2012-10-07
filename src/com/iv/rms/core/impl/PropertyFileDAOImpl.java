package com.iv.rms.core.impl;

import java.util.Properties;

public class PropertyFileDAOImpl implements PropertyDAO{
	
	private Properties prop;
	
	public PropertyFileDAOImpl(){
		init();
	}
	
	private void init(){
		prop  = new Properties();
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Property getProperty(String key) {
		String value = prop.getProperty(key);
		return new Property(value, key);
	}

	@Override
	public void save(Property prop) {
		
	}

}
