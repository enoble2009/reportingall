package com.magnus.reportingall.domain.reports;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.magnus.reportingall.daos.query.HibernateFilter;
import com.magnus.reportingall.domain.AbstractFilters;

public class ReportFilters extends AbstractFilters implements HibernateFilter {

	private String name;

	private String creationDate_start;

	private String creationDate_end;

	private String status;
	
	@Override
	public List<Predicate> getPredicates(CriteriaBuilder builder, Root<?> root) {
		List<Predicate> ps = new ArrayList<Predicate>();
		
		Date creationDate_startDate = parseDashDateTime(creationDate_start);
		Date creationDate_endDate = parseDashDateTime(creationDate_end);
		

		if (name != null) {
			ps.add(builder.like(root.get( "name" ), likeString(name)));
		}
		if (creationDate_start != null) {
			ps.add(builder.greaterThanOrEqualTo(root.get( "creationDate" ), creationDate_startDate));
		}
		if (creationDate_end != null) {
			ps.add(builder.lessThanOrEqualTo(root.get( "creationDate" ), creationDate_endDate));
		}
		if (status != null) {
			ps.add(builder.like(root.get( "status" ), likeString(name)));
		}
		
		return ps;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
