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

	private static final String BOOTSTRAP_CSS = "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css";
	private static final String CHARTJS_BUNDLE = "https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.bundle.min.js";
	private static final String CHARTJS = "https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.min.js";
	
	public static String processReportFile(Connection reportConn, ReportFile rf) throws SQLException {
		return String.format("<html><head>%s</head><body>%s</body></html>", getHead(), processFileBody(reportConn, rf.getBody()));
	}
	
	private static Object getHead() {
		StringBuffer head = new StringBuffer();
		head.append(String.format("<link rel='stylesheet' type='text/css' href='%s'>", BOOTSTRAP_CSS));
		head.append(String.format("<script src=\"%s\"></script>", CHARTJS_BUNDLE));
		head.append(String.format("<script src=\"%s\"></script>", CHARTJS));
		return head.toString();
	}

	private static String processFileBody(Connection reportConn, FileBody body) throws SQLException {
		String res = "";
		for (FileBodyRow row: body.getRows()) {
			for (FileBodyObject obj: row.getObjects()) {
				if (FileBodyObjectType.TABLE.equals(obj.getType())) {
					res += processTable(reportConn, obj);
				}
				if (FileBodyObjectType.GRAPH2D.equals(obj.getType())) {
					res += processGraph2D(reportConn, obj);
				}
			}
		}
		return res;
	}

	private static String processGraph2D(Connection reportConn, FileBodyObject obj) throws SQLException {
		ResultSet rSet = reportConn.prepareStatement(obj.getQuery()).executeQuery();
		List<Map<String, String>> table = fetchTable(reportConn, obj, rSet);
		List<String> tableHeader = fetchTableHeader(rSet);
		String canvas = String.format("<canvas id=\"%s\" width=\"400\" height=\"100\"></canvas>", obj.getName());
		return String.format("<div class='col-xs-%d'>%s%s</div>", obj.getColumns(), canvas, graph2DToHTML(obj.getName(), tableHeader, table));
	}
	
	private static String graph2DToHTML(String graphName, List<String> tableHeader, List<Map<String, String>> table) {
		StringBuffer res = new StringBuffer();
		res.append("<script>");
		res.append(String.format("var ctx = document.getElementById(\"%s\").getContext('2d');", graphName));
		res.append("var myChart = new Chart(ctx, {\r\n" + 
				"    type: 'bar',\r\n" + 
				"    data: {\r\n" + 
				"        labels: [\"Red\", \"Blue\", \"Yellow\", \"Green\", \"Purple\", \"Orange\"],\r\n" + 
				"        datasets: [{\r\n" + 
				"            label: '# of Votes',\r\n" + 
				"            data: [12, 19, 3, 5, 2, 3],\r\n" + 
				"            backgroundColor: [\r\n" + 
				"                'rgba(255, 99, 132, 0.2)',\r\n" + 
				"                'rgba(54, 162, 235, 0.2)',\r\n" + 
				"                'rgba(255, 206, 86, 0.2)',\r\n" + 
				"                'rgba(75, 192, 192, 0.2)',\r\n" + 
				"                'rgba(153, 102, 255, 0.2)',\r\n" + 
				"                'rgba(255, 159, 64, 0.2)'\r\n" + 
				"            ],\r\n" + 
				"            borderColor: [\r\n" + 
				"                'rgba(255,99,132,1)',\r\n" + 
				"                'rgba(54, 162, 235, 1)',\r\n" + 
				"                'rgba(255, 206, 86, 1)',\r\n" + 
				"                'rgba(75, 192, 192, 1)',\r\n" + 
				"                'rgba(153, 102, 255, 1)',\r\n" + 
				"                'rgba(255, 159, 64, 1)'\r\n" + 
				"            ],\r\n" + 
				"            borderWidth: 1\r\n" + 
				"        }]\r\n" + 
				"    },\r\n" + 
				"    options: {\r\n" + 
				"        scales: {\r\n" + 
				"            yAxes: [{\r\n" + 
				"                ticks: {\r\n" + 
				"                    beginAtZero:true\r\n" + 
				"                }\r\n" + 
				"            }]\r\n" + 
				"        }\r\n" + 
				"    }\r\n" + 
				"});");
		res.append("</script>");
		return res.toString();
	}

	private static List<Map<String, String>> fetchTable(Connection reportConn, FileBodyObject obj, ResultSet rSet) throws SQLException {
		List<Map<String, String>> table = new ArrayList<>(); 
		while (rSet.next()) {
			ResultSetMetaData rsmd = rSet.getMetaData();
			Map<String, String> tableRow = new HashMap<>();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				tableRow.put(rsmd.getColumnName(i), rSet.getString(i));
			}
			table.add(tableRow);
		}
		
		return table;
	}
	
	private static List<String> fetchTableHeader(ResultSet rSet) throws SQLException {
		List<String> tableHeader = new ArrayList<>();
		ResultSetMetaData rsmd = rSet.getMetaData();
		for (int i = 0; i < rsmd.getColumnCount(); i++) {
			tableHeader.add(rsmd.getColumnName(i+1));
		}
		
		return tableHeader;
	}

	private static String processTable(Connection reportConn, FileBodyObject obj) throws SQLException {
		ResultSet rSet = reportConn.prepareStatement(obj.getQuery()).executeQuery();
		List<Map<String, String>> table = fetchTable(reportConn, obj, rSet);
		List<String> tableHeader = fetchTableHeader(rSet);
		return String.format("<div class='col-xs-%d'>%s</div>", obj.getColumns(), tableToHTML(tableHeader, table));
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
