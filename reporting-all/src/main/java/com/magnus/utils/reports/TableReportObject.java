package com.magnus.utils.reports;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.magnus.reportingall.domain.reports.reportfile.FileBodyObject;
import com.magnus.reportingall.domain.reports.reportfile.FileBodyObjectColumn;
import com.magnus.reportingall.domain.reports.reportfile.FileBodyObjectDataType;
import com.magnus.reportingall.domain.reports.reportfile.FileBodyObjectValue;

public class TableReportObject {
	
	private static ScriptEngineManager mgr = new ScriptEngineManager();
	private static ScriptEngine engine = mgr.getEngineByName("JavaScript");

	public static String processTable(Connection reportConn, FileBodyObject obj, List<String> tableHeader, List<Map<String, String>> table) throws SQLException, ScriptException {
		return String.format("%s", tableToHTML(obj, tableHeader, table));
	}

	private static String tableHeaderHTML(List<FileBodyObjectColumn> columns, List<FileBodyObjectValue> values, List<String> tableHeader) {
		return String.format("<th>%s</th>", String.join("</th><th>", FileBodyObjectColumn.getColumnNames(columns)));
	}
	
	private static String tableBodyHTML(List<FileBodyObjectColumn> columns, List<FileBodyObjectValue> values, List<String> tableHeader, List<FileBodyObjectDataType> tableTypes, List<Map<String, String>> table) throws ScriptException {
		StringBuffer res = new StringBuffer();
		for (Map<String, String> mapTable: table) {
			res.append("<tr>");
			for (FileBodyObjectValue v: values) {
				StringBuffer addTD = new StringBuffer();
				for (int i = 0; i < tableHeader.size(); i++) {
					String th = tableHeader.get(i);
					addTD.append(String.format("var %s = " + (tableTypes.get(i).equals(FileBodyObjectDataType.STRING)? "'%s'": "%s") + ";", th, mapTable.get(th)));
				}
				addTD.append(v.getRef());
				res.append(String.format("<td>%s</td>", engine.eval(addTD.toString())));
			}
			res.append("</tr>");
		}
		return res.toString();
	}

	private static Object tableFooterHTML(List<FileBodyObjectColumn> columns, List<FileBodyObjectValue> values,
			List<String> tableHeader, List<FileBodyObjectDataType> tableTypes, List<Map<String, String>> table) {
		StringBuffer res = new StringBuffer();
		
		return res.toString();
	}
	
	private static String tableToHTML(FileBodyObject obj, List<String> tableHeader, List<Map<String, String>> table) throws ScriptException {
		List<FileBodyObjectColumn> columns = obj.getColumnsList(); 
		List<FileBodyObjectValue> values = obj.getValuesList(); 
		List<FileBodyObjectDataType> tableTypes = obj.getTypesList();
		
		String head = String.format("<tr>%s<tr>", tableHeaderHTML(columns, values, tableHeader));
		String body = tableBodyHTML(columns, values, tableHeader, tableTypes, table);
		String footer = "";
		if (obj.getFooter() != null) {
			String.format("<tfoot>%s</tfoot>", tableFooterHTML(columns, values, tableHeader, tableTypes, table));
		}
		return String.format("<table class=\"table table-striped\"><thead>%s</thead><tbody>%s</tbody>%s</table>", head, body, footer);
	}
	
}
