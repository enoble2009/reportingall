package com.magnus.reportingall.daos.users.impl;

import org.springframework.stereotype.Repository;

import com.magnus.reportingall.daos.users.UserSessionsDao;
import com.magnus.reportingall.domain.users.User;
import com.magnus.reportingall.domain.users.UserSession;
import com.magnus.utils.ExtendedHibernateDaoSupport;

@Repository
public class UserSessionsDaoImpl extends ExtendedHibernateDaoSupport implements UserSessionsDao {

	@Override
	public UserSession findByToken(String token) {
		return (UserSession) getHibernateSession().createQuery("from UserSession where token = :token")
				.setParameter("token", token)
				.uniqueResult();
	}

	@Override
	public User login(String login, String password) {
		return (User) getHibernateSession().createQuery("from User where username = :username and password = :password")
				.setParameter("username", login)
				.setParameter("password", password)
				.uniqueResult();
	}

	@Override
	public void save(UserSession us) {
		getHibernateSession().save(us);
	}

	@Override
	public void delete(UserSession us) {
		getHibernateSession().delete(us);
	}

}
