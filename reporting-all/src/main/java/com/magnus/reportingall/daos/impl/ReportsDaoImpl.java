package com.magnus.reportingall.daos.impl;

import org.springframework.stereotype.Repository;

import com.magnus.reportingall.daos.ReportsDao;
import com.magnus.reportingall.domain.reports.Report;
import com.magnus.utils.ExtendedHibernateDaoSupport;

@Repository
public class ReportsDaoImpl extends ExtendedHibernateDaoSupport implements ReportsDao {

	@Override
	public Report findById(Long id) {
		return (Report) getHibernateSession().createQuery("from Report where id = :id")
				.setParameter("id", id)
				.uniqueResult();
	}

	@Override
	public Long save(Report report) {
		return (Long) getHibernateSession().save(report);
	}

	@Override
	public void delete(Long id) {
		getHibernateSession().delete(findById(id));
	}

}
