package com.magnus.reportingall.domain.reports.reportfile;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class FileBodyRow {

	@XmlElement
	private List<FileBodyObject> objects;

	public List<FileBodyObject> getObjects() {
		return objects;
	}

	public void setObjects(List<FileBodyObject> objects) {
		this.objects = objects;
	}
	
}
