package com.magnus.reportingall.domain.reports.reportfile;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public class FileBody {

	@XmlElementWrapper
    @XmlElement(name="row")
	private List<FileBodyRow> rows;

	@Override
	public String toString() {
		return "FileBody [rows=" + rows + "]";
	}

	public List<FileBodyRow> getRows() {
		return rows;
	}

	public void setRows(List<FileBodyRow> rows) {
		this.rows = rows;
	}
	
}
