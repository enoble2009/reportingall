package com.magnus.reportingall.domain.reports;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.magnus.reportingall.domain.reports.reportfile.FileBody;
import com.magnus.reportingall.domain.reports.reportfile.FilePermission;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ReportFile {

	@XmlAttribute
	private String name;
	
	@XmlElement
	private FileDatabase database;
	
	@XmlElement
	private List<FilePermission> permissions;
	
	@XmlElement
	private FileBody body;

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

}
