package com.magnus.reportingall.services.reports;

import com.magnus.reportingall.domain.reports.Report;
<<<<<<< HEAD
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
=======

public interface ReportsExecuteService {

	/**
	 * 
	 * Execute report passed as parameter and return HTML as reponse.
	 * 
	 * @param report
	 * @return
	 */
	String executeHTML(Report report);
>>>>>>> branch 'master' of https://github.com/enoble2009/reportingall.git
	
}
