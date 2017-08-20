package com.magnus.reportingall.services.reports;

import com.magnus.reportingall.domain.reports.Report;
import com.magnus.reportingall.exceptions.ServiceException;

public interface ReportsExecuteService {

	/**
	 * 
	 * Execute report passed as parameter and return HTML as reponse.
	 * 
	 * @param report
	 * @return
	 * @throws ServiceException 
	 */
	String executeHTML(Report report) throws ServiceException;
	
}
