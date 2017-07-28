package com.magnus.reportingall.daos.query;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

import com.magnus.reportingall.exceptions.InvalidFieldException;

/**
 * A special implementation which retrieves the correct number of entities when those entities have collections
 * which are retrieved eagerly
 *  
 * @author csanchez
 *
 * @param <T> The class of an entity currently handled by Hibernate
 */
public class EagerQueryObjectHibernate<T> extends QueryObjectHibernate<T> implements QueryObject<T> {
	
	public EagerQueryObjectHibernate(){
	}
	
	@Override
	@Transactional
	public List<T> get(long startIndex, long pageSize, String sortExpression, HibernateFilter filters) throws InvalidFieldException {
		CriteriaBuilder builder = getHibernateSession().getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = builder.createQuery(argumentType);
		
		Root<T> tRoot = criteriaQuery.from(argumentType);
		criteriaQuery.select(tRoot);

		applySortExpression(criteriaQuery, builder, tRoot, sortExpression);
		applyFilters(criteriaQuery, builder, tRoot, filters);
		
		return getHibernateSession().createQuery(criteriaQuery).setFirstResult((int) startIndex).setMaxResults((int) pageSize).getResultList();
	}

}
