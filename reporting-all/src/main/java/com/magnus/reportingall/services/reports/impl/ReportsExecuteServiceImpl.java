package com.magnus.reportingall.services.reports.impl;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import javax.script.ScriptException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.magnus.reportingall.domain.reports.Report;
import com.magnus.reportingall.domain.reports.ReportFile;
import com.magnus.reportingall.exceptions.ServiceException;
import com.magnus.reportingall.exceptions.service.DatabaseException;
import com.magnus.reportingall.exceptions.service.XMLBadParseException;
import com.magnus.reportingall.services.reports.ReportsExecuteService;
import com.magnus.reportingall.services.users.impl.UserSessionsServiceImpl;
import com.magnus.utils.ApplicationProperties;
import com.magnus.utils.reports.AbstractReportViewer;
import com.magnus.utils.reports.HTMLReportViewer;

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
	public String executeHTML(Report report) throws ServiceException {
		try {
			ReportFile rf = loadReportFile(report);
			Connection reportConn = AbstractReportViewer.connectDBReportFile(rf);
			String htmlResult = HTMLReportViewer.processReportFile(reportConn, rf);
			return htmlResult;
		} catch (JAXBException e) {
			throw new XMLBadParseException();
		} catch (SQLException e) {
			throw new DatabaseException();
		} catch (ScriptException e) {
			logger.error(e);
			throw new DatabaseException();
		}
	}

	private ReportFile loadReportFile(Report report) throws JAXBException {
		File f = new File(String.format("%s%s.xml", ApplicationProperties.getProperty("path.reportfiles"), report.getName()));
		ReportFile rf = (ReportFile) context.createUnmarshaller().unmarshal(f);
		return rf;
	}

}
