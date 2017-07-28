package com.magnus.reportingall.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magnus.reportingall.daos.query.QueryObject;
import com.magnus.reportingall.domain.reports.Report;
import com.magnus.reportingall.domain.reports.ReportFilters;
import com.magnus.reportingall.exceptions.InvalidFieldException;
import com.magnus.reportingall.services.ReportsListService;

@Service
public class ReportsListServiceImpl implements ReportsListService {

	@Autowired
	private QueryObject<Report> queryObject;
	
	@Override
	@Transactional(readOnly = true)
	public List<Report> getReports(ReportFilters filters) throws InvalidFieldException {
		queryObject.setArgumentType(Report.class);
		return queryObject.get(filters.getStartIndex(), filters.getPageSize(), filters.getSortExpression(), filters);
	}

}
