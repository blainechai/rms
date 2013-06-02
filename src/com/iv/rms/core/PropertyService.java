package com.iv.rms.core;

import java.util.Set;

import com.iv.rms.core.impl.Property;

public interface PropertyService extends Service {

    public String getValue(String key);

    public void saveProperty(Property prop);

    public Set<String> getKeys();

}