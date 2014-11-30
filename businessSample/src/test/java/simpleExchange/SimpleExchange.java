/**
 *
 */
package simpleExchange;

import java.util.Properties;

import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;
import org.junit.Test;

import connectivity.SampleConsumer;
import connectivity.SampleProducer;

/**
 * @author buissartt
 *
 */
public class SimpleExchange {

	private static final Logger logger = Logger.getLogger(SimpleExchange.class);

	@Test
	public void simpleSendMessageInQueue() {
		String message = "Hello world !";
		SampleProducer producer = new SampleProducer();

		// génération d'une exception
		message.charAt(1000);

		producer.sendToJMSQueue(message);
	}

	@Test
	public void simpleConsumeMessageInQueue() throws Exception {
		// récupération de la destination depuis les propriétés
		Properties jndiProperties = new Properties();
		jndiProperties.load(SampleProducer.class.getClassLoader()
				.getResourceAsStream("jms/jms.properties"));
		InitialContext context = new InitialContext(jndiProperties);
		Destination queue = (Destination) context.lookup("businessSampleQueue");

		// création du consumer
		SampleConsumer consumer = new SampleConsumer((Destination) queue);

		// consommation du message
		Message received = consumer.consume();
		TextMessage receivedTextMessage = (TextMessage) received;
		String text = receivedTextMessage.getText();

		// affichage du message reçu dans les logs
		logger.info("Received: " + text);
	}
}
