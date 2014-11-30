package opl.sentinel.dao.impl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import opl.sentinel.dao.AbstractDao;
import opl.sentinel.dao.SentinelContextDao;
import opl.sentinel.domain.SentinelContext;

/**
 * Sentinel context DAO implementation.
 * @author Quentin
 *
 */
public class SentinelContextDaoImpl extends AbstractDao<SentinelContext> implements SentinelContextDao  {

	/**
	 * Constructor.
	 */
	public SentinelContextDaoImpl() {
		super(SentinelContext.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public int countProducer() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("SLECT count(*) FROM SentinelContext as sc WHERE sc.flowType = :flowType");
		
		Query query = getEntityManager().createQuery(stringBuilder.toString());
		query.setParameter("flowType", "PRODUCED");
		
		return (Integer) query.getSingleResult();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<SentinelContext> getProducersOffsetLimit(int offset, int limit){
		StringBuilder stringBuilber = new StringBuilder();
		stringBuilber.append("FROM SentinelContext as sc WHERE sc.flowType = :flowType");
		
		TypedQuery<SentinelContext> query = getEntityManager().createQuery(stringBuilber.toString(), SentinelContext.class);
		query.setMaxResults(limit);
		query.setFirstResult(offset);
		
		return query.getResultList();
	}

}
