package com.magnus.reportingall.controllers.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.magnus.reportingall.domain.reports.Report;
import com.magnus.reportingall.exceptions.ServiceException;
import com.magnus.reportingall.services.reports.ReportsExecuteService;
import com.magnus.reportingall.services.reports.ReportsService;

@RestController
@RequestMapping(value = "/api/report")
public class ReportExecuteController {

	@Autowired
	private ReportsService reportsService;
	
	@Autowired
	private ReportsExecuteService reportsExecuteService;
	
	@RequestMapping(value = "/execute/html/{id}", method = {RequestMethod.GET}, produces = MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	public String getReport(@PathVariable(name = "id") Long id) throws ServiceException {
		Report report = reportsService.findById(id);
		
		return reportsExecuteService.executeHTML(report);
	}
	
}
