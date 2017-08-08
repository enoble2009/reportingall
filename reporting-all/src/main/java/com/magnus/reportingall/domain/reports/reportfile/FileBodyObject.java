package com.magnus.reportingall.domain.reports.reportfile;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class FileBodyObject {

	@XmlAttribute
	private Integer columns = 12;
	
	@XmlAttribute
	private FileBodyObjectType type;
	
	@XmlElement
	private String query;

	@Override
	public String toString() {
		return "FileBodyObject [columns=" + columns + ", type=" + type + ", query=" + query + "]";
	}

	public Integer getColumns() {
		return columns;
	}

	public void setColumns(Integer columns) {
		this.columns = columns;
	}

	public FileBodyObjectType getType() {
		return type;
	}

	public void setType(FileBodyObjectType type) {
		this.type = type;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
}
