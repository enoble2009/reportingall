package com.magnus.reportingall.controllers.reports;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.magnus.reportingall.controllers.AbstractObjectController;
import com.magnus.reportingall.domain.reports.ReportDTO;
import com.magnus.reportingall.domain.system.ResponseDTO;
import com.magnus.reportingall.exceptions.ServiceException;
import com.magnus.reportingall.services.ReportsService;

@RestController
@RequestMapping(value = "/api/report")
public class ReportController extends AbstractObjectController {

	private static final Log logger = LogFactory.getLog(ReportController.class);
	
	@Autowired
	private ReportsService reportsService;
	
	@RequestMapping(value = "/get/{id}", method = {RequestMethod.GET})
	public ResponseDTO<?> getReport(@PathVariable(name = "id") Long id) {
		return ResponseDTO.createSuccess(ReportDTO.create(reportsService.findById(id)));
	}
	
	@RequestMapping(value = "/save", method = {RequestMethod.POST})
	public ResponseDTO<?> saveReport(@RequestBody ReportDTO dto) {
		Long id = reportsService.save(dto);
		return ResponseDTO.createSuccess(ReportDTO.create(reportsService.findById(id)));
	}
	
	@RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET})
	public ResponseDTO<?> deleteReport(@PathVariable(name = "id") Long id) {
		try {
			reportsService.delete(id);
			return OK_RESPONSE;
		} catch (ServiceException e) {
			logger.error(String.format("Error when deleting report with id: %d.", id), e);
			return UNEXPECTED_ERROR_RESPONSE;
		}
	}
	
}
