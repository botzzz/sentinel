/**
 *
 */
package connectivity;

import java.io.IOException;
import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;

import utils.Constants;

/**
 * @author buissartt
 *
 */
public class SampleConsumer extends AbstractJMSSample {

	private static final Logger logger = Logger.getLogger(SampleConsumer.class);

	static {
		logger.debug("Initializing context with JNDI properties");

		jndiProperties = new Properties();
		try {
			jndiProperties.load(SampleProducer.class.getClassLoader()
					.getResourceAsStream("jms/jms.properties"));
			setJndiContext(new InitialContext(jndiProperties));
		} catch (IOException e) {
			logger.error("An error occured during properties initialization", e);
		} catch (NamingException e) {
			logger.error("An error occured during JNDI context initialization",
					e);
		}

	}

	public SampleConsumer(Destination destination) {
		ConnectionFactory cf = null;

		try {
			cf = (ConnectionFactory) jndiContext.lookup(jndiProperties
					.getProperty(Constants.JNDI_CONNECTIONFACTORY));
		} catch (NamingException e) {
			logger.error("An error occured during properties initialization", e);
		}

		this.jmsTemplate = new JmsTemplate(cf);
		setDestination(destination);
	}

	/**
	 * @param destination
	 */
	public void setDestination(Destination destination) {
		jmsTemplate.setDefaultDestination(destination);
	}

	/**
	 * consume a message
	 *
	 * @return the received message
	 */
	public Message consume() {
		return this.jmsTemplate.receive();
	}

	/**
	 * receive a message
	 *
	 * @param time
	 *            to wait for the message
	 * @return the received message
	 */
	public Message receive(Long timeout) {
		this.jmsTemplate.setReceiveTimeout(timeout);
		Message received = this.jmsTemplate.receive();
		this.jmsTemplate
				.setReceiveTimeout(JmsTemplate.RECEIVE_TIMEOUT_INDEFINITE_WAIT);
		return received;
	}
}
