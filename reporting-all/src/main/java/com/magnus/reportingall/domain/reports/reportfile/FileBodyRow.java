package com.magnus.reportingall.domain.reports.reportfile;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public class FileBodyRow {

	@XmlElementWrapper
    @XmlElement(name="object")
	private List<FileBodyObject> objects;

	@Override
	public String toString() {
		return "FileBodyRow [objects=" + objects + "]";
	}

	public List<FileBodyObject> getObjects() {
		return objects;
	}

	public void setObjects(List<FileBodyObject> objects) {
		this.objects = objects;
	}
	
}
