package com.magnus.utils;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.UUID;

public class RandomStringUtils {

	private static SecureRandom random = new SecureRandom();
	
	public static String createPassword() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static String createToken() {
		return new BigInteger(130, random).toString(32);
	}
	
}
