package com.magnus.utils.reports;

import java.sql.Connection;
import java.sql.SQLException;

import com.magnus.reportingall.domain.reports.ReportFile;
import com.magnus.utils.DBConnectionUtils;

public abstract class AbstractReportViewer {

	public static Connection connectDBReportFile(ReportFile rf) throws SQLException {
		Connection reportConn = DBConnectionUtils.getConnection(rf.getDatabase());
		return reportConn;
	}

}
