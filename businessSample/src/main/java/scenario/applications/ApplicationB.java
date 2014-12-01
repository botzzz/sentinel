package scenario.applications;

import java.util.Properties;

import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;

import sentinel.Sentinel;
import sentinel.context.FlowType;
import utils.Constants;
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
		String textReceived = "";
		try {
			Properties jndiProperties = new Properties();

			jndiProperties.load(SampleProducer.class.getClassLoader()
					.getResourceAsStream("jms/jms.properties"));

			InitialContext context = null;

			context = new InitialContext(jndiProperties);

			Destination topic = null;

			topic = (Destination) context.lookup("businessSampleTopic");

			SampleConsumer consumer = new SampleConsumer((Destination) topic);
			logger.debug("B is waiting for a message...");

			Message received = consumer.consume();

			// ExceptionListManager.LIST.next();

			TextMessage receivedTextMessage = (TextMessage) received;
			textReceived = receivedTextMessage.getText();

			received.getStringProperty(Constants.CONTEXT_ID);
			Sentinel sentinel = new Sentinel();
			sentinel.init("Application B consume", textReceived,
					received.getStringProperty(Constants.APPLICATION_SOURCE),
					"Application B", FlowType.CONSUMED,
					received.getIntProperty(Constants.CONTEXT_ID));

		} catch (Exception e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
		logger.debug("B received : \n" + textReceived);
	}

}
