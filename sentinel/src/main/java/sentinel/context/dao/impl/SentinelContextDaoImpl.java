package sentinel.context.dao.impl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import sentinel.context.FlowType;
import sentinel.context.SentinelContext;
import sentinel.context.dao.AbstractDao;
import sentinel.context.dao.SentinelContextDao;

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
		stringBuilder.append("SELECT count(*) FROM SentinelContext as sc ");
		stringBuilder.append("WHERE sc.flowType = :flowType ");
		
		Query query = getEntityManager().createQuery(stringBuilder.toString());
		query.setParameter("flowType", FlowType.PRODUCED);
		
		Long count = (Long) query.getSingleResult();
		
		return count.intValue();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<SentinelContext> getProducersOffsetLimit(int offset, int limit){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("FROM SentinelContext as sc ");
		stringBuilder.append("WHERE sc.flowType = :flowType");
		
		TypedQuery<SentinelContext> query = getEntityManager().createQuery(stringBuilder.toString(), SentinelContext.class);
		query.setParameter("flowType", FlowType.PRODUCED);

		query.setMaxResults(limit);
		query.setFirstResult(offset);

		return query.getResultList();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<SentinelContext> getConsumerByMessageOrigineId(int messageOrigineId){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("FROM SentinelContext as sc ");
		stringBuilder.append("WHERE sc.messageOrigineId = :messageOrigineId");
		
		TypedQuery<SentinelContext> query = getEntityManager().createQuery(stringBuilder.toString(), SentinelContext.class);
		query.setParameter("messageOrigineId", messageOrigineId);

		return query.getResultList();
	}

}
