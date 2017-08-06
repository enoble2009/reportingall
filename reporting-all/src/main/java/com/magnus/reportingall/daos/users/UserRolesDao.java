package com.magnus.reportingall.daos.users;

import java.util.List;

import com.magnus.reportingall.domain.users.UserRole;

public interface UserRolesDao {

	UserRole findByName(String name);

	List<UserRole> findByNames(List<String> userRoles);
	
}
