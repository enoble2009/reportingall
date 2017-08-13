package com.magnus.utils.reports;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptException;

import com.magnus.reportingall.domain.reports.ReportFile;
import com.magnus.reportingall.domain.reports.reportfile.FileBody;
import com.magnus.reportingall.domain.reports.reportfile.FileBodyObject;
import com.magnus.reportingall.domain.reports.reportfile.FileBodyObjectDataType;
import com.magnus.reportingall.domain.reports.reportfile.FileBodyObjectType;
import com.magnus.reportingall.domain.reports.reportfile.FileBodyRow;

public class HTMLReportViewer extends AbstractReportViewer {

	private static final String BOOTSTRAP_CSS = "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css";
	private static final String CHARTJS_BUNDLE = "https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.bundle.min.js";
	private static final String CHARTJS = "https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.min.js";
	
	public static String processReportFile(Connection reportConn, ReportFile rf) throws SQLException, ScriptException {
		return String.format("<html><head>%s</head><body>%s</body></html>", getHead(), processFileBody(reportConn, rf.getBody()));
	}
	
	private static Object getHead() {
		StringBuffer head = new StringBuffer();
		head.append(String.format("<link rel='stylesheet' type='text/css' href='%s'>", BOOTSTRAP_CSS));
		head.append(String.format("<script src=\"%s\"></script>", CHARTJS_BUNDLE));
		head.append(String.format("<script src=\"%s\"></script>", CHARTJS));
		return head.toString();
	}

	private static String processFileBody(Connection reportConn, FileBody body) throws SQLException, ScriptException {
		String res = "";
		for (FileBodyRow row: body.getRows()) {
			for (FileBodyObject obj: row.getObjects()) {
				ResultSet rSet = reportConn.prepareStatement(obj.getQuery()).executeQuery();
				List<Map<String, String>> table = fetchTable(reportConn, obj, rSet);
				List<String> tableHeader = fetchTableHeader(rSet);
				List<FileBodyObjectDataType> tableTypes = fetchTableTypes(obj);
				if (FileBodyObjectType.TABLE.equals(obj.getType())) {
					res += String.format("<div class='col-xs-%d'>%s</div>", obj.getColumns(), TableReportObject.processTable(reportConn, obj.getName(), obj.getColumnsList(), obj.getValuesList(), tableHeader, tableTypes, table));
				}
				if (FileBodyObjectType.GRAPH2D.equals(obj.getType())) {
					res += String.format("<div class='col-xs-%d'>%s</div>", obj.getColumns(), Graph2DReportObject.processGraph2D(reportConn, obj.getName(), tableHeader, table));
				}
			}
		}
		return res;
	}

	private static List<FileBodyObjectDataType> fetchTableTypes(FileBodyObject obj) {
		return obj.getTypesList();
	}

	private static List<Map<String, String>> fetchTable(Connection reportConn, FileBodyObject obj, ResultSet rSet) throws SQLException {
		List<Map<String, String>> table = new ArrayList<>(); 
		while (rSet.next()) {
			ResultSetMetaData rsmd = rSet.getMetaData();
			Map<String, String> tableRow = new HashMap<>();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				tableRow.put(rsmd.getColumnLabel(i), rSet.getString(i));
			}
			table.add(tableRow);
		}
		
		return table;
	}
	
	private static List<String> fetchTableHeader(ResultSet rSet) throws SQLException {
		List<String> tableHeader = new ArrayList<>();
		ResultSetMetaData rsmd = rSet.getMetaData();
		for (int i = 0; i < rsmd.getColumnCount(); i++) {
			tableHeader.add(rsmd.getColumnLabel(i+1));
		}
		
		return tableHeader;
	}

}
