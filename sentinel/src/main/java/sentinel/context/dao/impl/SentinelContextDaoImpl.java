package sentinel.context.dao.impl;

import sentinel.context.SentinelContext;
import sentinel.context.dao.AbstractDao;
import sentinel.context.dao.SentinelContextDao;

/**
 * Sentinel context DAO implementation.
 *
 * @author Quentin
 *
 */
public class SentinelContextDaoImpl extends AbstractDao<SentinelContext>
		implements SentinelContextDao {

	/**
	 * Constructor.
	 */
	public SentinelContextDaoImpl() {
		super(SentinelContext.class);
	}

}
