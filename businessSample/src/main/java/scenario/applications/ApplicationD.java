/**
 *
 */
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
public abstract class ApplicationD extends Thread implements IApplication {

	private static final Logger logger = Logger.getLogger(ApplicationD.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		logger.debug("Application D is now running");

		Properties jndiProperties = new Properties();
		try {
			jndiProperties.load(SampleProducer.class.getClassLoader()
					.getResourceAsStream("jms/jms.properties"));
			ExceptionListManager.LIST.next();
		} catch (Exception e) {
			logger.error("an error occurred classname"
					+ e.getStackTrace()[0].getClassName());
			e.printStackTrace();
		}
		InitialContext context = null;
		try {
			context = new InitialContext(jndiProperties);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Destination queue = null;
		try {
			queue = (Destination) context.lookup("businessSampleQueue");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SampleConsumer consumer = new SampleConsumer(queue);
		logger.debug("Application D is now waiting for a message...");

		Message received = consumer.consume();
		TextMessage receivedTextMessage = (TextMessage) received;
		logger.debug("Application D received : \n" + receivedTextMessage);

		String textReceived = "";
		try {
			textReceived = receivedTextMessage.getText();
		} catch (JMSException e) {
			logger.error("an error occurred", e);
		}
		logger.debug("D received :\n" + textReceived);
	}

}
