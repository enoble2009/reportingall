package com.magnus.reportingall.services.reports;

import com.magnus.reportingall.domain.reports.Report;

public interface ReportsExecuteService {

	/**
	 * 
	 * Execute report passed as parameter and return HTML as reponse.
	 * 
	 * @param report
	 * @return
	 */
	String executeHTML(Report report);
	
}
