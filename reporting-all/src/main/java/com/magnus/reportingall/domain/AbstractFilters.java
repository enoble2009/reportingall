package com.magnus.reportingall.domain;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.magnus.utils.DateUtils;

public abstract class AbstractFilters {

	private long startIndex;
	
	private long pageSize;

	private String sortExpression;
	
	private static final Log logger = LogFactory.getLog(AbstractFilters.class);
	
	protected Date parseDashDateTime(String dateString) {
		Date ret;
		try {
			ret = DateUtils.parseInDateTimeFormatWithDash(dateString);
		} catch (ParseException e) {
			// Dates that not are supported, it is not used to select.
			logger.warn(String.format("String of date %s not having the correct format %s", dateString, DateUtils.DATE_TIME_FORMAT_DASH), e);
			ret = null;
		}
		
		return ret;
	}
	
	protected String likeString(String text) {
		return "%" + text + "%";
	}
	

	public long getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(long startIndex) {
		this.startIndex = startIndex;
	}

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortExpression() {
		return sortExpression;
	}

	public void setSortExpression(String sortExpression) {
		this.sortExpression = sortExpression;
	}

}
