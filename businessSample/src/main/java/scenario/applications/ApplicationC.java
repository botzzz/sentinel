/**
 *
 */
package scenario.applications;

import java.io.StringReader;
import java.util.Properties;

import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.xml.bind.JAXB;

import org.apache.log4j.Logger;

import scenario.ExceptionListManager;
import sentinel.Sentinel;
import sentinel.context.FlowType;
import utils.Constants;
import xml.message.Facture;
import connectivity.SampleConsumer;
import connectivity.SampleProducer;

/**
 * @author buissartt
 *
 */
public abstract class ApplicationC extends Thread implements IApplication {

	private static final Logger logger = Logger.getLogger(ApplicationC.class);

	public void run() {
		logger.debug("Application C is now running");
		String textReceived = "";
		Sentinel sentinel = new Sentinel();
		try {
			// message reception from topic
			Properties jndiProperties = new Properties();
			jndiProperties.load(SampleProducer.class.getClassLoader()
					.getResourceAsStream("jms/jms.properties"));
			ExceptionListManager.LIST.next();

			InitialContext context = null;
			context = new InitialContext(jndiProperties);

			Destination topic = null;
			topic = (Destination) context.lookup("businessSampleTopic");

			SampleConsumer consumer = new SampleConsumer((Destination) topic);
			logger.debug("C is waiting for a message...");
			Message received = consumer.consume();
			TextMessage receivedTextMessage = (TextMessage) received;
			textReceived = "";
			textReceived = receivedTextMessage.getText();

			sentinel = new Sentinel();
			sentinel.init("Application C consume from topic", textReceived,
					received.getStringProperty(Constants.APPLICATION_SOURCE),
					"Application C", FlowType.CONSUMED,
					received.getIntProperty(Constants.CONTEXT_ID));

			logger.debug("C received :\n" + textReceived);
		} catch (Exception e) {
			sentinel.error(
					"C encountered an error during consoming from the topic", e);
		}
		try {
			// update facture
			Facture factureReceived = JAXB.unmarshal(new StringReader(
					textReceived), Facture.class);
			factureReceived.setMontant(factureReceived.getMontant() + 100000);

			// send message received in the queue
			logger.debug("C is sending the message to the queue");

			SampleProducer producer = new SampleProducer();
			producer.sendToJMSQueue(factureReceived);
			sentinel = new Sentinel();
			sentinel.init("Application C produces in queue", textReceived,
					"Application C", null, FlowType.PRODUCED, sentinel
							.getContext().getId());
		} catch (Exception e) {
			sentinel.error("C encountered an error during producing in queue",
					e);
		}
	}
}
