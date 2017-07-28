package com.magnus.reportingall.daos;

import com.magnus.reportingall.domain.reports.Report;

public interface ReportsDao {

	Report findById(Long id);
	
	Long save(Report report);
	
	void delete(Long id);

}
