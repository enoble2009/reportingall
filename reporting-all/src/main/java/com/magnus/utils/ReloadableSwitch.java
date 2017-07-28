package com.magnus.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;


/**
 * This Switch is based on ApplicationProperties reloadable data.
 * It relies on the {@link ApplicationProperties} class and that it
 * loads all configured keys.
 * <br />
 * Keys need to be configured as follows for user list:
 * <ul>
 * <li>[keyname].enabled=[true|false]
 * <li>[keyname].users=[list of comma-separated userids that are enabled]
 * </ul>
 * Keys need to be configured as follows for modulo:
 * <ul>
 * <li>[keyname].enabled=[true|false]
 * <li>[keyname].mod.value=[int] value to use for modulo
 * <li>[keyname].mod.list=[list of comma-separated values]. Remainders to match for modulo op. Defaults to 0
 * Keys need to be configured as follows for range of users:
 * <ul>
 * <li>[keyname].enabled=[true|false]
 * <li>[keyname].authorized.min=[int] minimum value to accept (exclusive)
 * <li>[keyname].authorized.max=[int] maximum value to accept (exclusive)
 * </ul>
 * </ul>
 * userlist, mod and range can be used together to create more complex scenarios
 * Do NOT leave spaces or blanks in between userIds and commas, or end of line.
 * 
 * @author Andres
 */
public class ReloadableSwitch {

	private static ReloadableSwitch instance;

	protected static final String ENABLED_KEY = ".enabled";
	protected static final String USERS_KEY   = ".users";
	protected static final String ALL_USERS = "*";
	protected static final String ZERO = "0";
	
	protected static final String MIN_RANGE = ".authorized.min";
	protected static final String MAX_RANGE = ".authorized.max";
	
	protected static final String MOD_VALUE = ".mod.value";
	protected static final String MOD_LIST = ".mod.list";
	
	protected static final String REGEXP_KEY   = ".regexp";
	
	private TypedProperties properties = new TypedProperties();

	public static ReloadableSwitch getInstance(){
		if(instance == null){
			instance = new ReloadableSwitch();
		}
		return instance;
	}

	/**
	 * Returns true if [switchKey].enabled is true.
	 */
	public static boolean isEnabled(String switchKey) {
		return getInstance().properties.getBooleanProperty(switchKey + ENABLED_KEY);
	}

	/**
	 * If the userid is found in the [switchKey].users list
	 * then this method returns true. This is usually slower
	 * than just checking {@link #isEnabled(String)}
	 */
	public static boolean isEnabled(String switchKey, long userId) {
		if( isEnabled(switchKey)){
			
			// First check for user list
			final String users = getProperty(switchKey + USERS_KEY);
			if(!isBlank(users)){
				//check if it is enabled for all users
				if( ALL_USERS.equals(users)){
					return true;
				}
				//check if it is enabled for any of these users (comma-separated list)
				Matcher m = Pattern.compile("(^|,)"+userId+"($|,)").matcher(users);
				if(m.find()){
					return true;
				}
			}

			//check modulo-enabled users
			final String mod = StringUtils.trim(getProperty(switchKey + MOD_VALUE));
			if(!isBlank(mod)) {
				final long userIdMod = userId % Integer.parseInt(mod);
				final String prop = getProperty(switchKey + MOD_LIST);
				if(isBlank(prop)) {
					//if not defined, default to mod 0
					if(0 == userIdMod) {
						return true;
					}
				} else {
					Matcher moduloM = Pattern.compile("(^|,)"+userIdMod+"($|,)").matcher(prop);
					if(moduloM.find()){
						return true;
					}
				}
			}
			
			//check for range of users
			String min = StringUtils.trim(getProperty(switchKey + MIN_RANGE));
			String max = StringUtils.trim(getProperty(switchKey + MAX_RANGE));
			if(!isBlank(min) && !isBlank(max)) {
				boolean inRange = Integer.parseInt(min) < userId && Integer.parseInt(max) > userId;
				if(inRange){
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isEnabled(String switchKey, String code) {
	    boolean enabled = false;
		if( isEnabled(switchKey)){
	        final String regexp = getProperty(switchKey + REGEXP_KEY);
	        enabled = isBlank(regexp) || Pattern.matches(regexp, code);
	    }
	    return enabled;
	}
	
	protected static boolean isBlank(String str) {
		return StringUtils.isBlank(str); 
	}
	
	/**
	 * Retrieves a String property
	 * @param name Property name
	 * @return property value
	 */
	public static String getProperty(String name) {
		return getInstance().properties.getProperty(name);
	}
	
	/**
	 * Retrieves a boolean property
	 * @param name Property name
	 * @return property value
	 */
	protected static  boolean getBooleanProperty(String name) {
		return getInstance().properties.getBooleanProperty(name);
	}

	public void setProperties(TypedProperties properties) {
		this.properties = properties;
	}
}

