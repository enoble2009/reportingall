package com.magnus.reportingall.services;

import java.util.List;

import com.magnus.reportingall.domain.reports.Report;
import com.magnus.reportingall.domain.reports.ReportFilters;
import com.magnus.reportingall.exceptions.InvalidFieldException;

public interface ReportsListService {

	List<Report> getReports(ReportFilters filters) throws InvalidFieldException;
	
}
