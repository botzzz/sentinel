/**
 *
 */
package sentinel;

import sentinel.context.FlowType;

/**
 * @author buissartt
 *
 */
public interface ISentinel {

	/**
	 * initialize the current context
	 *
	 * @param name
	 * @param xmlBusinessContent
	 * @param source
	 * @param target
	 * @param flowType
	 * @param messageOrigineId
	 */
	public void init(String name, String xmlBusinessContent, String source,
			String target, FlowType flowType, Integer messageOrigineId);

	/**
	 * initialize the error
	 *
	 * @param errorMessage
	 *            the error message
	 * @param e
	 *            the throwable object
	 */
	public void error(String errorMessage, Throwable e);

	/**
	 * persist the current context
	 */
	public void logContext();

}
