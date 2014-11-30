package scenario.applications;

import java.util.Properties;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import scenario.ExceptionListManager;
import connectivity.SampleConsumer;
import connectivity.SampleProducer;

/**
 * @author buissartt
 *
 */
public abstract class ApplicationB extends Thread implements IApplication {

	private static final Logger logger = Logger.getLogger(ApplicationB.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		logger.debug("Application B is now running");

		Properties jndiProperties = new Properties();
		try {
			jndiProperties.load(SampleProducer.class.getClassLoader()
					.getResourceAsStream("jms/jms.properties"));
			ExceptionListManager.LIST.next();
		} catch (Exception e) {
			this.onError(e);
			Thread.currentThread().interrupt();
		}
		InitialContext context = null;
		try {
			context = new InitialContext(jndiProperties);
		} catch (NamingException e) {
			logger.error("an error occurred", e);
		}
		Destination topic = null;
		try {
			topic = (Destination) context.lookup("businessSampleTopic");
		} catch (NamingException e) {
			logger.error("an error occurred", e);
		}

		SampleConsumer consumer = new SampleConsumer((Destination) topic);
		logger.debug("B is waiting for a message...");

		Message received = consumer.consume();

		TextMessage receivedTextMessage = (TextMessage) received;
		String text = "";
		try {
			text = receivedTextMessage.getText();
		} catch (JMSException e) {
			logger.error("an error occurred", e);
		}
		logger.debug("B received : \n" + text);
	}

}
