package com.magnus.reportingall.daos.users.impl;

import org.springframework.stereotype.Repository;

import com.magnus.reportingall.daos.users.UsersDao;
import com.magnus.reportingall.domain.users.User;
import com.magnus.utils.ExtendedHibernateDaoSupport;

@Repository
public class UsersDaoImpl extends ExtendedHibernateDaoSupport implements UsersDao {

	@Override
	public User findById(Long id) {
		return (User) getHibernateSession().createQuery("from User where id = :id")
				.setParameter("id", id)
				.uniqueResult();
	}

	@Override
	public Long save(User user) {
		return (Long) getHibernateSession().save(user);
	}

	@Override
	public void delete(Long id) {
		getHibernateSession().delete(findById(id));
	}

}
