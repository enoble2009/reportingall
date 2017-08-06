package com.magnus.reportingall.services.users;

import com.magnus.reportingall.domain.users.UserLoginDTO;

public interface UserSessionsService {

	/**
	 * 
	 * Try to login user in report system.
	 * 
	 * @param loginDTO
	 * @return session token
	 */
	String login(UserLoginDTO loginDTO);
	
	/**
	 * 
	 * Logout user from system
	 * 
	 * @param token
	 */
	void logout(String token);
	
}
