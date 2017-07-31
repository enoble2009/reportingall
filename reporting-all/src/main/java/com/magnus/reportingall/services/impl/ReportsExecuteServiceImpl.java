package com.magnus.reportingall.services.impl;

import org.springframework.stereotype.Service;

import com.magnus.reportingall.domain.reports.Report;
import com.magnus.reportingall.services.ReportsExecuteService;

@Service
public class ReportsExecuteServiceImpl implements ReportsExecuteService {

	@Override
	public String executeHTML(Report report) {
		return "<html><head><title>EXAMPLE</title></head><body></body></html>";
	}

}
