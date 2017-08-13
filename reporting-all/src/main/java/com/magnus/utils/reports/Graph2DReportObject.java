package com.magnus.utils.reports;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Graph2DReportObject {
	
	private static final int[][] colors = new int[][] {
		{255, 99, 132}, {54, 162, 235}, {255, 206, 86},
		{75, 192, 192}, {153, 102, 255}, {255, 159, 64}
	};
	private static String getColor(int[] color, float alpha) {
		return String.format(Locale.US, "rgba(%d, %d, %d, %.2f)", color[0], color[1], color[2], alpha);
	}

	public static String processGraph2D(Connection reportConn, String name, List<String> tableHeader, List<Map<String, String>> table) throws SQLException {
		String canvas = String.format("<canvas id=\"%s\" width=\"400\" height=\"100\"></canvas>", name);
		return String.format("%s%s", canvas, graph2DToHTML(name, tableHeader, table));
	}
	
	private static String graph2DType(String type) {
		return "type: '" + type + "'";
	}
	
	private static String graph2DLabels(List<String> tableHeader, List<Map<String, String>> table) {
		List<String> tableValues = new ArrayList<>();
		for (Map<String, String> valMap: table) {
			tableValues.add(String.format("\"%s\"", valMap.get(tableHeader.get(0))));
		}
		return String.format("labels: [%s]", String.join(",", tableValues));
	}
	
	private static String graph2DData(List<String> tableHeader, List<Map<String, String>> table) {
		return String.format("data: {\r\n" + 
			"        %s, %s,\r\n" + 
			"    options: {\r\n" + 
			"        scales: {\r\n" + 
			"            yAxes: [{\r\n" + 
			"                ticks: {\r\n" + 
			"                    beginAtZero:true\r\n" + 
			"                }\r\n" + 
			"            }]\r\n" + 
			"        }\r\n" + 
			"    }", graph2DLabels(tableHeader, table), graph2DDataSets(tableHeader, table));
	}
	
	private static Object graph2DDataSets(List<String> tableHeader, List<Map<String, String>> table) {
		return String.format("datasets: [{\r\n" + 
			"            label: '# of Votes',\r\n" + 
			"            data: [%s],\r\n" + 
			"            backgroundColor: [%s],\r\n" + 
			"            borderColor: [%s],\r\n" + 
			"            borderWidth: 1\r\n" + 
			"        }]\r\n" + 
			"    }", graph2DDataValues(tableHeader, table), graph2DDataColors(table.size(), 0.2f), graph2DDataColors(table.size(), 1f));
	}

	private static String graph2DDataColors(int size, float alpha) {
		List<String> res = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			res.add(String.format("\"%s\"", getColor(colors[i], alpha)));
		}
		return String.join(",", res);
	}

	private static Object graph2DDataValues(List<String> tableHeader, List<Map<String, String>> table) {
		List<String> tableValues = new ArrayList<>();
		for (Map<String, String> valMap: table) {
			tableValues.add(valMap.get(tableHeader.get(1)));
		}
		return String.join(",", tableValues);
	}

	private static String graph2DToHTML(String graphName, List<String> tableHeader, List<Map<String, String>> table) {
		StringBuffer res = new StringBuffer();
		res.append("<script>");
		res.append(String.format("var graphCtx_%s = document.getElementById(\"%s\").getContext('2d');", graphName, graphName));
		res.append(String.format("var chart_%s = new Chart(graphCtx_%s, {%s, %s});", graphName, graphName, graph2DType("bar"), graph2DData(tableHeader, table)));
		res.append("</script>");
		return res.toString();
	}

}
