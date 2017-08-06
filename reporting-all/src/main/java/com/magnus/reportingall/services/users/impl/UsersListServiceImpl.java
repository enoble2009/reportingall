package com.magnus.reportingall.services.users.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magnus.reportingall.daos.query.QueryObject;
import com.magnus.reportingall.domain.users.User;
import com.magnus.reportingall.domain.users.UserFilters;
import com.magnus.reportingall.exceptions.InvalidFieldException;
import com.magnus.reportingall.services.users.UsersListService;

@Service
public class UsersListServiceImpl implements UsersListService {

	@Autowired
	private QueryObject<User> queryObject;
	
	@Override
	@Transactional(readOnly = true)
	public List<User> getUsers(UserFilters filters) throws InvalidFieldException {
		queryObject.setArgumentType(User.class);
		return queryObject.get(filters.getStartIndex(), filters.getPageSize(), filters.getSortExpression(), filters);
	}

}
