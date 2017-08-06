package com.magnus.reportingall.daos.users;

import com.magnus.reportingall.domain.users.User;
import com.magnus.reportingall.domain.users.UserSession;

public interface UserSessionsDao {

	User login(String login, String password);

	void save(UserSession us);
	
	UserSession findByToken(String token);

	void delete(UserSession us);

}
