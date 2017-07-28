package com.magnus.utils;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTransactionManager;


/**
 * All DAO's of the ags system should extend from this class. Its purpose is to
 * wrap the implementation of the DaoSupport.
 * */
public abstract class ExtendedHibernateDaoSupport {
	
	@Autowired
	private HibernateTransactionManager transactionManager;
	
	protected Session getHibernateSession() {
		return transactionManager.getSessionFactory().getCurrentSession();
	}
}
