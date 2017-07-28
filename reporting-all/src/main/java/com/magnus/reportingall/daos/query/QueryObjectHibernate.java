package com.magnus.reportingall.daos.query;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.magnus.reportingall.exceptions.InvalidFieldException;
import com.magnus.utils.ExtendedHibernateDaoSupport;

@Repository
public class QueryObjectHibernate<T> extends ExtendedHibernateDaoSupport implements QueryObject<T> {

	private static final String SORT_EXP_REGEX = "([\\w]+) (ASC|DESC)";

	private static final Pattern SORT_EXP_PATTERN = Pattern.compile(SORT_EXP_REGEX);

	protected Class<T> argumentType;

	public QueryObjectHibernate() {
	}

	@Override
	@Transactional
	public List<T> get(long startIndex, long pageSize, String sortExpression, HibernateFilter filter)
			throws InvalidFieldException {
		CriteriaBuilder builder = getHibernateSession().getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = builder.createQuery(argumentType);

		Root<T> tRoot = criteriaQuery.from(argumentType);
		criteriaQuery.select(tRoot);

		applySortExpression(criteriaQuery, builder, tRoot, sortExpression);
		applyFilters(criteriaQuery, builder, tRoot, filter);

		return getHibernateSession().createQuery(criteriaQuery).setFirstResult((int) startIndex)
				.setMaxResults((int) pageSize).getResultList();
	}

	@Override
	@Transactional
	public Long getCount(HibernateFilter filter) {
		CriteriaBuilder builder = getHibernateSession().getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = builder.createQuery(argumentType);

		Root<T> tRoot = criteriaQuery.from(argumentType);
		criteriaQuery.select(tRoot);

		applyFilters(criteriaQuery, builder, tRoot, filter);
		return (Long) getHibernateSession().createQuery(criteriaQuery).uniqueResult();
	}

	/**
	 * Parses the sort expression specified and uses it to create the proper
	 * order settings in a criteria
	 * 
	 * @param criteria
	 * @throws InvalidFieldException
	 *             when one of the fields specified in the expression doesn't
	 *             belong to the class
	 */
	protected void applySortExpression(CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder, Root<?> tRoot,
			String sortExpression) throws InvalidFieldException {
		if (StringUtils.isEmpty(sortExpression)) {
			return;
		}

		Matcher matcher = SORT_EXP_PATTERN.matcher(sortExpression);
		List<Order> orderList = new ArrayList<>();
		while (matcher.find()) {
			String fieldName = matcher.group(1);
			if (!isValidField(fieldName)) {
				throw new InvalidFieldException(fieldName + " is not a valid field of " + argumentType);
			}
			orderList.add(matcher.group(2).equals("ASC") ? builder.asc(tRoot.get(fieldName))
					: builder.desc(tRoot.get(fieldName)));
		}
		criteriaQuery.orderBy(orderList);
	}

	/**
	 * Verifies that a field belongs to class provided as argument type
	 * 
	 * @param fieldName
	 *            the field to validate
	 * @return true if the field was found, false otherwise
	 */
	protected boolean isValidField(String fieldName) {
		Field[] fields = argumentType.getDeclaredFields();

		for (Field field : fields) {
			if (field.getName().equals(fieldName)) {
				return true;
			}
		}
		return false;
	}

	protected void applyFilters(CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder, Root<?> tRoot,
			HibernateFilter filter) {
		criteriaQuery.where(filter.getPredicates(builder, tRoot).toArray(new Predicate[0]));
	}

	@Override
	public void setArgumentType(Class<T> argumentType) {
		this.argumentType = argumentType;
	}
	
}
