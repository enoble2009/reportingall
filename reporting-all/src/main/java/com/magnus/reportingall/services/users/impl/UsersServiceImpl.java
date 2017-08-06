package com.magnus.reportingall.services.users.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magnus.reportingall.daos.users.UserRolesDao;
import com.magnus.reportingall.daos.users.UsersDao;
import com.magnus.reportingall.domain.users.User;
import com.magnus.reportingall.domain.users.UserDTO;
import com.magnus.reportingall.exceptions.ServiceException;
import com.magnus.reportingall.services.users.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private UserRolesDao userRolesDao;
	
	@Override
	@Transactional(readOnly = true)
	public User findById(Long id) {
		return usersDao.findById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Long save(UserDTO dto) {
		User user = dto.createWORoles();
		user.setUserRoles(userRolesDao.findByNames(dto.getUserRoles()));
		return usersDao.save(user);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(Long id) throws ServiceException {
		usersDao.delete(id);
	}

}
