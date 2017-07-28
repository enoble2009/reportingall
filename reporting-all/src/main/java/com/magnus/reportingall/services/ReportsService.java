package com.magnus.reportingall.services;

import com.magnus.reportingall.domain.reports.Report;
import com.magnus.reportingall.domain.reports.ReportDTO;
import com.magnus.reportingall.exceptions.ServiceException;

public interface ReportsService {

	/**
	 * 
	 * Find report using id as ID.
	 * 
	 * @param id
	 * @return
	 */
	Report findById(Long id);

	/**
	 * 
	 * Save the report with dto information.
	 * 
	 * @param dto
	 * @return
	 */
	Long save(ReportDTO dto);
	
	/**
	 * 
	 * Delete the report used by id.
	 * 
	 * @param id
	 * @throws ServiceException
	 */
	void delete(Long id) throws ServiceException;
	
}
