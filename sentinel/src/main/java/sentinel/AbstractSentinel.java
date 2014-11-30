/**
 *
 */
package sentinel;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import sentinel.context.FlowType;
import sentinel.context.SentinelContext;
import sentinel.context.StatusType;
import sentinel.context.dao.SentinelContextDao;
import sentinel.context.dao.impl.SentinelContextDaoImpl;
import utils.Util;

/**
 * @author buissartt
 *
 */
public class AbstractSentinel implements ISentinel {

	private static final Logger logger = Logger
			.getLogger(AbstractSentinel.class);

	private SentinelContext context;

	/*
	 * (non-Javadoc)
	 *
	 * @see sentinel.ISentinel#init(java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, sentinel.context.FlowType)
	 */
	public void init(String name, String xmlBusinessContent, String source,
			String target, FlowType type) {
		logger.debug("Sentinel context initialization");
		this.context = new SentinelContext();
		context.setName(name);
		context.setMessageOrigine(xmlBusinessContent);
		context.setFlowType(type);
		context.setStatus(StatusType.SUCCESS);

		this.logContext();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see sentinel.ISentinel#error(java.lang.String, java.lang.Throwable)
	 */
	public void error(String errorMessage, Throwable e) {
		logger.debug("setting error");

		if (this.context == null) {
			this.context = new SentinelContext();
			context.setName(Util.generateUUID());
		}

		this.context.setErrorMessage(errorMessage);
		this.context.setStackTrace(ExceptionUtils.getFullStackTrace(e));
		this.context.setStatus(StatusType.ERROR);
		this.logContext();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see sentinel.ISentinel#logContext()
	 */
	public void logContext() {
		logger.debug("logging context");

		SentinelContextDao sentinelDAO = new SentinelContextDaoImpl();
		sentinelDAO.persist(context);
	}

}
