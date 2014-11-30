/**
 *
 */
package scenario.applications;

import java.io.StringReader;
import java.util.Properties;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.bind.JAXB;

import org.apache.log4j.Logger;

import scenario.ExceptionListManager;
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

		// message reception from topic
		Properties jndiProperties = new Properties();
		try {
			jndiProperties.load(SampleProducer.class.getClassLoader()
					.getResourceAsStream("jms/jms.properties"));
			ExceptionListManager.LIST.next();
		} catch (Exception e) {
			logger.error("an error occurred classname"
					+ e.getStackTrace()[0].getClassName());
			logger.error("an error occurred", e);
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
		logger.debug("C is waiting for a message...");
		Message received = consumer.consume();
		TextMessage receivedTextMessage = (TextMessage) received;
		String textReceived = "";
		try {
			textReceived = receivedTextMessage.getText();
		} catch (JMSException e) {
			logger.error("an error occurred", e);
		}
		logger.debug("C received :\n" + textReceived);

		// update facture
		Facture factureReceived = JAXB.unmarshal(
				new StringReader(textReceived), Facture.class);
		factureReceived.setMontant(factureReceived.getMontant() + 100000);

		// send message received in the queue
		logger.debug("C is sending the message to the queue");

		SampleProducer producer = new SampleProducer();
		producer.sendToJMSQueue(factureReceived);
	}
}
