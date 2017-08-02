package com.magnus.reportingall.services.reports.impl;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magnus.reportingall.daos.ReportsDao;
import com.magnus.reportingall.domain.reports.Report;
import com.magnus.reportingall.domain.reports.ReportDTO;
import com.magnus.reportingall.exceptions.ServiceException;
import com.magnus.reportingall.exceptions.service.ObjectNotFoundException;
import com.magnus.reportingall.services.reports.ReportsService;

@Service
public class ReportsServiceImpl implements ReportsService {

	@Autowired
	private ReportsDao reportsDao;
	
	@Override
	@Transactional(readOnly = true)
	public Report findById(Long id) {
		return reportsDao.findById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Long save(ReportDTO dto) {
		Report report = dto.create();
		return reportsDao.save(report);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(Long id) throws ServiceException {
		try {
			reportsDao.delete(id);
		} catch (Exception e) {
			throw new ObjectNotFoundException();
		}
	}

}
