package com.magnus.reportingall.services.users;

import java.util.List;

import com.magnus.reportingall.domain.users.User;
import com.magnus.reportingall.domain.users.UserFilters;
import com.magnus.reportingall.exceptions.InvalidFieldException;

public interface UsersListService {

	List<User> getUsers(UserFilters filters) throws InvalidFieldException;
	
}
