/**
 *
 */
package scenario.applications;

import java.util.Properties;

import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;

import scenario.ExceptionListManager;
import sentinel.Sentinel;
import sentinel.context.FlowType;
import utils.Constants;
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

		String textReceived = "";
		Sentinel sentinel = new Sentinel();
		try {
			Properties jndiProperties = new Properties();

			jndiProperties.load(SampleProducer.class.getClassLoader()
					.getResourceAsStream("jms/jms.properties"));
			ExceptionListManager.LIST.next();

			InitialContext context = null;
			context = new InitialContext(jndiProperties);

			Destination queue = null;
			queue = (Destination) context.lookup("businessSampleQueue");

			SampleConsumer consumer = new SampleConsumer(queue);
			logger.debug("Application D is now waiting for a message...");

			Message received = consumer.consume();
			TextMessage receivedTextMessage = (TextMessage) received;
			logger.debug("Application D received : \n" + receivedTextMessage);

			textReceived = receivedTextMessage.getText();
			sentinel.init("Application D consume from queue", textReceived,
					received.getStringProperty(Constants.APPLICATION_SOURCE),
					"Application D", FlowType.CONSUMED,
					received.getIntProperty(Constants.CONTEXT_ID));
		} catch (Exception e) {
			sentinel.error(
					"Application D encountered an error during message consumption",
					e);

		}
		logger.debug("D received :\n" + textReceived);
	}

}
