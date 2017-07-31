package com.magnus.reportingall.daos.query;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface HibernateFilter {
	
	List<Predicate> getPredicates(CriteriaBuilder builder, Root<?> root);
	
}
