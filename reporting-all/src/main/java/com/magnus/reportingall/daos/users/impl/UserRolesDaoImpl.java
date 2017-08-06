package com.magnus.reportingall.daos.users.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.magnus.reportingall.daos.users.UserRolesDao;
import com.magnus.reportingall.domain.users.UserRole;
import com.magnus.utils.ExtendedHibernateDaoSupport;

@Repository
public class UserRolesDaoImpl extends ExtendedHibernateDaoSupport implements UserRolesDao {

	@Override
	public UserRole findByName(String name) {
		return (UserRole) getHibernateSession().createQuery("from UserRole where name = :name")
				.setParameter("name", name)
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRole> findByNames(List<String> names) {
		return (List<UserRole>) getHibernateSession().createQuery("from UserRole where name in :names")
				.setParameterList("names", names)
				.list();
	}

}
