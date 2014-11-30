package opl.sentinel.dao.impl;

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

}
