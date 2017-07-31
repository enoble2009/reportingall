package com.magnus.utils;

/**
 * Lets us access property values from within the application.
 * 
 */
public class ApplicationProperties {


	protected static final String RELOADABLES_KEY = "application.properties.reloadables";
	//Singleton instance
	private static volatile ApplicationProperties instance;
	//Properties that are reloaded every refreshTime milliseconds
	private TypedProperties applicationProperties = new TypedProperties();
	//Elapsed time since last refresh
	protected long lastRefresh = 0;

	private ApplicationProperties() {
	}

	public static ApplicationProperties getInstance() {
		if (instance == null) {
			synchronized (ApplicationProperties.class) {
				if (instance == null) {
					instance = new ApplicationProperties();
				}
			}
		}
		return instance;
	}

	public static String getProperty(String key) {
		return getInstance().applicationProperties.getProperty(key);
	}

	public static Character getCharProperty(String key) {
		return getInstance().applicationProperties.getCharProperty(key);
	}

	public static Integer getIntProperty(String key) {
		return getInstance().applicationProperties.getIntProperty(key);
	}

	public static Double getDoubleProperty(String key) {
		return getInstance().applicationProperties.getDoubleProperty(key);
	}

	public static Float getFloatProperty(String key) {
		return getInstance().applicationProperties.getFloatProperty(key);
	}

	public static boolean getBooleanProperty(String key) {
		return getInstance().applicationProperties.getBooleanProperty(key);
	}

	public static Long getLongProperty(String key) {
		return getInstance().applicationProperties.getLongProperty(key);
	}

	public void setApplicationProperties(TypedProperties applicationProperties) {
		this.applicationProperties = applicationProperties;
	}
}
