/**
 *
 */
package connectivity;

import java.io.IOException;
import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import utils.Constants;
import xml.IXmlSerializable;

/**
 * @author buissartt
 *
 */
public class SampleProducer extends AbstractJMSSample {

	private static final Logger logger = Logger.getLogger(SampleProducer.class);

	static {
		logger.debug("Initializing context with JNDI properties");

		jndiProperties = new Properties();
		try {
			jndiProperties.load(SampleProducer.class.getClassLoader()
					.getResourceAsStream("jms/jms.properties"));
			jndiContext = new InitialContext(jndiProperties);
		} catch (IOException e) {
			logger.error("An error occured during properties initialization", e);
		} catch (NamingException e) {
			logger.error("An error occured during JNDI context initialization",
					e);
		}

	}

	private JmsTemplate jmsTemplate;

	/**
	 * the topic destination
	 */
	private Topic topicDestination;

	/**
	 * the queue destination
	 */
	private Queue queueDestination;

	public SampleProducer() {
		ConnectionFactory cf = null;

		try {
			cf = (ConnectionFactory) jndiContext.lookup(jndiProperties
					.getProperty(Constants.JNDI_CONNECTIONFACTORY));
			topicDestination = (Topic) jndiContext.lookup(jndiProperties
					.getProperty(Constants.JNDI_TOPIC));
			queueDestination = (Queue) jndiContext.lookup(jndiProperties
					.getProperty(Constants.JNDI_QUEUE));
		} catch (NamingException e) {
			logger.error("An error occured during properties initialization", e);
		}

		this.jmsTemplate = new JmsTemplate(cf);
	}

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
	 * @return the destination
	 */
	public Topic getTopicDestination() {
		return topicDestination;
	}

	/**
	 * @param destination
	 *            the destination to set
	 */
	public void setTopicDestination(Topic destination) {
		this.topicDestination = destination;
	}

	/**
	 * @return the queueDestination
	 */
	public Queue getQueueDestination() {
		return queueDestination;
	}

	/**
	 * @param queueDestination
	 *            the queueDestination to set
	 */
	public void setQueueDestination(Queue queueDestination) {
		this.queueDestination = queueDestination;
	}

	/**
	 * @param message
	 *            to send to the topicDestination
	 */
	public void sendToJMSTopic(final IXmlSerializable message) {
		this.jmsTemplate.send(topicDestination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message.convertToString());
			}
		});
	}

	/**
	 * @param message
	 * @param attachedContextId
	 *            the id to set in the header
	 */
	public void sendToJMSTopic(final IXmlSerializable message,
			final Integer attachedContextId, final String applicationSource) {
		this.jmsTemplate.send(topicDestination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				TextMessage messageToSend = session.createTextMessage(message
						.convertToString());
				messageToSend.setIntProperty(Constants.CONTEXT_ID,
						attachedContextId);
				messageToSend.setStringProperty(Constants.APPLICATION_SOURCE,
						applicationSource);
				return messageToSend;
			}
		});
	}

	/**
	 * @param message
	 *            to send to the topicDestination
	 */
	public void sendToJMSTopic(final String message) {
		this.jmsTemplate.send(topicDestination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
	}

	/**
	 * @param message
	 *            to send to the queueDestination
	 */
	public void sendToJMSQueue(final IXmlSerializable message) {
		this.jmsTemplate.send(queueDestination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message.convertToString());
			}
		});
	}

	public void sendToJMSQueue(final IXmlSerializable message,
			final int attachedContextId, final String applicationSource) {
		this.jmsTemplate.send(queueDestination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				TextMessage messageToSend = session.createTextMessage(message
						.convertToString());
				messageToSend.setIntProperty(Constants.CONTEXT_ID,
						attachedContextId);
				messageToSend.setStringProperty(Constants.APPLICATION_SOURCE,
						applicationSource);
				return messageToSend;
			}
		});
	}

	/**
	 * @param message
	 *            to send to the queue destination
	 */
	public void sendToJMSQueue(final String message) {
		this.jmsTemplate.send(queueDestination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
	}

}
