package com.magnus.reportingall.domain.reports.reportfile;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public class FileBodyObject {

	@XmlAttribute
	private Integer columns = 12;
	
	@XmlAttribute
	private FileBodyObjectType type;
	
	@XmlAttribute
	private String name;
	
	@XmlElement
	private String query;
	
	@XmlElementWrapper
    @XmlElement(name="type")
	private List<FileBodyObjectDataType> typesList;
	
	@XmlElementWrapper
    @XmlElement(name="column")
	private List<FileBodyObjectColumn> columnsList;

	@XmlElementWrapper
    @XmlElement(name="value")
	private List<FileBodyObjectValue> valuesList;

	@Override
	public String toString() {
		return "FileBodyObject [columns=" + columns + ", type=" + type + ", query=" + query + "]";
	}

	public List<FileBodyObjectDataType> getTypesList() {
		return typesList;
	}

	public void setTypesList(List<FileBodyObjectDataType> typesList) {
		this.typesList = typesList;
	}

	public List<FileBodyObjectColumn> getColumnsList() {
		return columnsList;
	}

	public void setColumnsList(List<FileBodyObjectColumn> columnsList) {
		this.columnsList = columnsList;
	}

	public List<FileBodyObjectValue> getValuesList() {
		return valuesList;
	}

	public void setValuesList(List<FileBodyObjectValue> valuesList) {
		this.valuesList = valuesList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
