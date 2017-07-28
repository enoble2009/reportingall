package com.magnus.reportingall.domain.reports;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.magnus.utils.DateUtils;

public class ReportDTO {
	
	private Long id;
	
	private String name;
	
	private String permissionName;
	
	private String creationDate;
	
	private String status;

	public Report create() {
		Report r = new Report();
		r.setId(id);
		r.setName(name);
		r.setStatus(status == null? ReportStatus.LIVE: ReportStatus.valueOf(status));
		r.setPermission(new ReportPermission(name));
		r.setCreationDate(new Date());
		
		return r;
	}

	public static ReportDTO create(Report r) {
		if (r == null) {
			return null;
		}
		ReportDTO dto = new ReportDTO();
		dto.setId(r.getId());
		dto.setName(r.getName());
		dto.setStatus(r.getStatus().name());
		dto.setPermissionName(r.getPermission().getName());
		dto.setCreationDate(DateUtils.formatInDateTimeFormatWithDash(r.getCreationDate()));
		
		return dto;
	}

	public static List<ReportDTO> createList(List<Report> reports) {
		List<ReportDTO> list = new ArrayList<ReportDTO>();
		if (reports == null) {
			return list;
		}
		for (Report r: reports) {
			list.add(ReportDTO.create(r));
		}
		
		return list;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
