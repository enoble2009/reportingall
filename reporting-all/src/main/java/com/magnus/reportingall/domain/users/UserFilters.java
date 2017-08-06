package com.magnus.reportingall.domain.users;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.magnus.reportingall.daos.query.HibernateFilter;
import com.magnus.reportingall.domain.AbstractFilters;

public class UserFilters extends AbstractFilters implements HibernateFilter {

	@Override
	public List<Predicate> getPredicates(CriteriaBuilder builder, Root<?> root) {
		List<Predicate> ps = new ArrayList<Predicate>();
		
		return ps;
	}

}
