package opl.sentinel.dao;

import java.util.List;

import opl.sentinel.domain.SentinelContext;

/**
 * Sentinel context DAO interface.
 * @author Quentin
 *
 */
public interface SentinelContextDao extends IAbstractDao<SentinelContext> {

	/**
	 * Count the number of producer.
	 * @return the number of producer.
	 */
	int countProducer();

	/**
	 * Get the producers.
	 * @param offset the offset. 
	 * @param limit the limit.
	 * @return
	 */
	List<SentinelContext> getProducersOffsetLimit(int offset, int limit);
	
}
