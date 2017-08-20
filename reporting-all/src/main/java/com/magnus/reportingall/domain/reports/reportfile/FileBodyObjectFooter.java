package com.magnus.reportingall.domain.reports.reportfile;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public class FileBodyObjectFooter {

	@XmlElementWrapper
    @XmlElement(name="row")
	private List<FileBodyObjectFooterRow> rowsList;

	public List<FileBodyObjectFooterRow> getRowsList() {
		return rowsList;
	}

	public void setRowsList(List<FileBodyObjectFooterRow> rowsList) {
		this.rowsList = rowsList;
	}
	
}
