package com.iv.rms.core.impl;

import com.iv.rms.core.persistence.DAO;

public interface PropertyDAO extends DAO {

    public Property getProperty(String key);

    public void save(Property prop);

}