/**
 *
 */
package connectivity;

import java.util.Properties;

import javax.naming.Context;

import org.springframework.jms.core.JmsTemplate;

/**
 * @author buissartt
 *
 */
public class AbstractJMSSample {

	/**
	 * the JNDI context
	 */
	protected static Context jndiContext;

	/**
	 * Properties corresponding to the JNDI context
	 */
	protected static Properties jndiProperties;

	/**
	 * the JMSTemplate used to produce and consume messages
	 */
	protected JmsTemplate jmsTemplate;

	/**
	 * @return the jmsTemplate
	 */
	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	/**
	 * @param jmsTemplate
	 *            the jmsTemplate to set
	 */
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	/**
	 * @return the jndiContext
	 */
	public static Context getJndiContext() {
		return jndiContext;
	}

	/**
	 * @param newJndiContext
	 *            the jndiContext to set
	 */
	public static void setJndiContext(Context newJndiContext) {
		jndiContext = newJndiContext;
	}

}
