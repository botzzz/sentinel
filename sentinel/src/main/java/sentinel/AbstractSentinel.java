/**
 *
 */
package sentinel;

import sentinel.context.ISentinelExcecutionContext;

/**
 * @author buissartt
 *
 */
public class AbstractSentinel implements ISentinel {

	private ISentinelExcecutionContext context;

	public void init(String xmlBusinessContent) {
		// TODO initialize un context

	}

	public void error(String errorMessage) {
		// TODO status en erreur + maj date ?

	}

	public void logContext() {
		// TODO log entity en bdd + maj date ?
		this.context.log();
	}

}
