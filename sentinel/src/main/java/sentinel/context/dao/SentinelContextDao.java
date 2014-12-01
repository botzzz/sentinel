package sentinel.context.dao;

import java.util.List;

import sentinel.context.SentinelContext;


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

	/**
	 * Get the consumers for the specified message origine id.
	 * @param messageOrigineId the message origine id.
	 * @return the concerned consumers.
	 */
	List<SentinelContext> getConsumerByMessageOrigineId(int messageOrigineId);
	
}
