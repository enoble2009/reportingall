package com.magnus.reportingall.services.users.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.magnus.reportingall.daos.users.UserSessionsDao;
import com.magnus.reportingall.domain.users.User;
import com.magnus.reportingall.domain.users.UserLoginDTO;
import com.magnus.reportingall.domain.users.UserSession;
import com.magnus.reportingall.services.users.UserSessionsService;
import com.magnus.utils.RandomStringUtils;

public class UserSessionsServiceImpl implements UserSessionsService {

	private static final Log logger = LogFactory.getLog(UserSessionsServiceImpl.class);

	@Autowired
	private UserSessionsDao userSessionsDao;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public String login(UserLoginDTO loginDTO) {
		User u = userSessionsDao.login(loginDTO.getLogin(), loginDTO.getPassword());
		if (u != null) {
			String token = RandomStringUtils.createToken();
			UserSession us = new UserSession(u, token);
			userSessionsDao.save(us);
			logger.debug(String.format("%s logged in with session %s", loginDTO.getLogin(), us));
			return token;
		}
		logger.debug(String.format("%s tried a failed login", loginDTO));
		return null;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void logout(String token) {
		UserSession us = userSessionsDao.findByToken(token);
		if (us == null) {
			logger.warn(String.format("Token %s is not valid or was expired", token));
		} else {
			userSessionsDao.delete(us);
			logger.debug(String.format("%s was logged out.", us));
		}
	}
	
}
