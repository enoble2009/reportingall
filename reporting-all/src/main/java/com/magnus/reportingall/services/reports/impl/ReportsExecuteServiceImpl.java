package com.magnus.reportingall.services.reports.impl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.magnus.reportingall.domain.reports.Report;
import com.magnus.reportingall.domain.reports.ReportFile;
import com.magnus.reportingall.services.reports.ReportsExecuteService;
import com.magnus.reportingall.services.users.impl.UserSessionsServiceImpl;

@Service
public class ReportsExecuteServiceImpl implements ReportsExecuteService {

	private static final Log logger = LogFactory.getLog(UserSessionsServiceImpl.class);

	JAXBContext context;
	
	public ReportsExecuteServiceImpl() {
		try {
			context = JAXBContext.newInstance(ReportFile.class);
		} catch (JAXBException e) {
			logger.fatal(String.format("JAXBContext is not working for %s", ReportFile.class.getName()), e);
		}
	}
	
	@Override
	public String executeHTML(Report report) {
		
		return "<html><head><title>EXAMPLE</title></head><body></body></html>";
	}

}
