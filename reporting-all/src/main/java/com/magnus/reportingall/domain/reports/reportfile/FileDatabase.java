package com.magnus.reportingall.domain.reports.reportfile;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class FileDatabase {

	@XmlElement
	private String username;

	@XmlElement
	private String password;

	@XmlElement
	private String dbms;

	@XmlElement
	private String server;

	@XmlElement
	private String port;

	@XmlElement
	private String dbName;

	@Override
	public String toString() {
		return "FileDatabase [username=" + username + ", password=" + password + ", dbms=" + dbms + ", server=" + server
				+ ", port=" + port + ", dbName=" + dbName + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDbms() {
		return dbms;
	}

	public void setDbms(String dbms) {
		this.dbms = dbms;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

}
