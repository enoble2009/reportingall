package com.magnus.utils.reports;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.magnus.reportingall.domain.reports.ReportFile;
import com.magnus.reportingall.domain.reports.reportfile.FileBody;
import com.magnus.reportingall.domain.reports.reportfile.FileBodyObject;
import com.magnus.reportingall.domain.reports.reportfile.FileBodyObjectType;
import com.magnus.reportingall.domain.reports.reportfile.FileBodyRow;

public class HTMLReportViewer extends AbstractReportViewer {

	public static String processReportFile(Connection reportConn, ReportFile rf) throws SQLException {
		return processFileBody(reportConn, rf.getBody());
	}
	
	private static String processFileBody(Connection reportConn, FileBody body) throws SQLException {
		String res = "";
		for (FileBodyRow row: body.getRows()) {
			for (FileBodyObject obj: row.getObjects()) {
				if (FileBodyObjectType.TABLE.equals(obj.getType())) {
					res += processTable(reportConn, obj);
				}
			}
		}
		return res;
	}

	private static String processTable(Connection reportConn, FileBodyObject obj) throws SQLException {
		List<Map<String, String>> table = new ArrayList<>(); 
		ResultSet rSet = reportConn.prepareStatement(obj.getQuery()).executeQuery();
		while (rSet.next()) {
			ResultSetMetaData rsmd = rSet.getMetaData();
			Map<String, String> tableRow = new HashMap<>();
			for (int i = 0; i < rsmd.getColumnCount(); i++) {
				tableRow.put(rsmd.getColumnName(i), rSet.getString(i));
			}
			table.add(tableRow);
		}
		
		List<String> tableHeader = new ArrayList<>();
		ResultSetMetaData rsmd = rSet.getMetaData();
		for (int i = 0; i < rsmd.getColumnCount(); i++) {
			tableHeader.add(rsmd.getColumnName(i+1));
		}
		return tableToHTML(tableHeader, table);
	}

	private static String tableToHTML(List<String> tableHeader, List<Map<String, String>> table) {
		StringBuffer head = new StringBuffer();
		head.append("<tr>");
		for (String th: tableHeader) {
			head.append("<th>").append(th).append("</th>");
		}
		head.append("</tr>");
		StringBuffer body = new StringBuffer();
		body.append("<tr>");
		for (Map<String, String> mapTable: table) {
			for (String th: tableHeader) {
				body.append("<td>").append(mapTable.get(th)).append("</td>");
			}
		}
		body.append("</tr>");
		return String.format("<table class=\"table table-striped\"><thead>%s</thead><tbody>%s</tbody></table>", head.toString(), body.toString());
	}
	
}
