package com.magnus.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.magnus.reportingall.domain.reports.reportfile.FileDatabase;

public class DBConnectionUtils {

	public static Connection getConnection(String username, String password, String dbms, String serverName, String portNumber, String dbName) throws SQLException {
	    Connection conn = null;
	    Properties connectionProps = new Properties();
	    connectionProps.put("user", username);
	    connectionProps.put("password", password);

	    if (dbms.equals("mysql")) {
	        conn = DriverManager.getConnection(
	                   "jdbc:" + dbms + "://" +
	                   serverName +
	                   ":" + portNumber + "/" + (dbName != null? dbName: ""),
	                   connectionProps);
	    } else if (dbms.equals("derby")) {
	        conn = DriverManager.getConnection(
	                   "jdbc:" + dbms + ":" +
	                   dbName +
	                   ";create=true",
	                   connectionProps);
	    }
	    System.out.println("Connected to database");
	    return conn;
	}

	public static Connection getConnection(FileDatabase db) throws SQLException {
		return getConnection(
				db.getUsername(), 
				db.getPassword(), 
				db.getDbms(), 
				db.getServer(), 
				db.getPort(), 
				db.getDbName()
				);
	}
	
}
