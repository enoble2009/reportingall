package com.magnus.reportingall.domain.reports.reportfile;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class FileBodyObjectColumn {

	@XmlElement
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static List<String> getColumnNames(List<FileBodyObjectColumn> columns) {
		List<String> res = new ArrayList<>();
		for (FileBodyObjectColumn c: columns) {
			res.add(c.getName());
		}
		return res;
	}
	
}
