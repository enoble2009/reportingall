package com.magnus.reportingall.daos.query;

import java.util.List;

import com.magnus.reportingall.daos.query.HibernateFilter;
import com.magnus.reportingall.exceptions.InvalidFieldException;

public interface QueryObject<T> {
	/**
	 * Retrieves a list of objects from the database
	 * 
	 * @param startIndex
	 *            the result set will start from this position
	 * @param pageSize
	 *            the number of objects that will be retrieved
	 * @return a list of objects
	 */
	List<T> get(long startIndex, long pageSize, String sortExpression, HibernateFilter filter) throws InvalidFieldException;
	/**
	 * Retrieves the total of objects that can be retrieved by the query object
	 */
	Long getCount(HibernateFilter filter);
	
	void setArgumentType(Class<T> argumentType);

}
