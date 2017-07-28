package com.magnus.utils;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

/**
 */
public class TypedProperties{

    private Properties properties = new Properties();

    public TypedProperties() {
    }

    public TypedProperties(Properties properties) {
        this.properties = properties;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public Character getCharProperty(String key) {
        String value = getProperty(key);
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return Character.valueOf(value.charAt(0));
    }

    public Integer getIntProperty(String key) {
        return Integer.valueOf(getProperty(key));
    }

    public Double getDoubleProperty(String key) {
        return Double.valueOf(getProperty(key));
    }

    public Float getFloatProperty(String key) {
        return Float.valueOf(getProperty(key));
    }

    public boolean getBooleanProperty(String key) {
        return Boolean.valueOf(getProperty(key));
    }

    public Long getLongProperty(String key) {
        return Long.valueOf(getProperty(key));
    }
    
}
