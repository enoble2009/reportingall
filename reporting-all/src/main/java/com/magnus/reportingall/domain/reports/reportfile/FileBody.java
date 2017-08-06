package com.magnus.reportingall.domain.reports.reportfile;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class FileBody {

	@XmlElement
	private List<FileBodyRow> rows;

	public List<FileBodyRow> getRows() {
		return rows;
	}

	public void setRows(List<FileBodyRow> rows) {
		this.rows = rows;
	}
	
}
