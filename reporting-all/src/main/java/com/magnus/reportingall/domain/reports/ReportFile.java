package com.magnus.reportingall.domain.reports;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.magnus.reportingall.domain.reports.reportfile.FileBody;
import com.magnus.reportingall.domain.reports.reportfile.FileDatabase;
import com.magnus.reportingall.domain.reports.reportfile.FilePermission;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ReportFile {

	@XmlAttribute
	private String name;
	
	@XmlElement
	private FileDatabase database;
	
	@XmlElementWrapper
    @XmlElement(name="permission")
	private List<FilePermission> permissions;
	
	@XmlElement
	private FileBody body;

	@Override
	public String toString() {
		return "ReportFile [name=" + name + ", database=" + database + ", permissions=" + permissions + ", body=" + body
				+ "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FileBody getBody() {
		return body;
	}

	public void setBody(FileBody body) {
		this.body = body;
	}

	public List<FilePermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<FilePermission> permissions) {
		this.permissions = permissions;
	}

	public FileDatabase getDatabase() {
		return database;
	}

	public void setDatabase(FileDatabase database) {
		this.database = database;
	}

}
