package com.magnus.reportingall.controllers.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.magnus.reportingall.domain.reports.ReportDTO;
import com.magnus.reportingall.domain.reports.ReportFilters;
import com.magnus.reportingall.domain.system.ResponseDTO;
import com.magnus.reportingall.exceptions.InvalidFieldException;
import com.magnus.reportingall.services.reports.ReportsListService;

@RestController
@RequestMapping(value = "/api/report")
public class ReportListController {

	@Autowired
	private ReportsListService reportsListService;
	
	@RequestMapping(value = "/list", method = {RequestMethod.POST})
	public ResponseDTO<?> getReport(@RequestBody ReportFilters filters) {
		try {
			filters.setPageSize(10);
			return ResponseDTO.createSuccess(ReportDTO.createList(reportsListService.getReports(filters)));
		} catch (InvalidFieldException e) {
			e.printStackTrace();
			return ResponseDTO.createMsgError("Filters are wrong.");
		}
	}
	
}
