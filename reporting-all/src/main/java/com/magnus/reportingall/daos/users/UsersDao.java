package com.magnus.reportingall.daos.users;

import com.magnus.reportingall.domain.users.User;

public interface UsersDao {

	User findById(Long id);
	
	Long save(User user);
	
	void delete(Long id);
	
}
