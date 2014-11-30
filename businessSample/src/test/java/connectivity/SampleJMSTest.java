/**
 *
 */
package connectivity;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Test;

import utils.Util;
import xml.message.Client;
import xml.message.Facture;

/**
 * @author buissartt
 *
 */
public class SampleJMSTest {

	@Test
	public void sendTopicTest() {
		SampleProducer producer = new SampleProducer();

		Facture facture = new Facture();
		facture.setId(Util.generateUUID());

		Client client = new Client();
		client.setAdresse("10 rue du moulin 20293 Pépé");
		client.setNom("Bibi");
		client.setPrenom("Bobo");

		facture.setClient(client);
		facture.setMontant(new Random().nextFloat());

		producer.sendToJMSTopic(facture);
	}

	@Test
	public void sendQueueTest() {
		SampleProducer producer = new SampleProducer();

		Facture facture = new Facture();
		facture.setId(Util.generateUUID());

		Client client = new Client();
		client.setAdresse("10 rue du moulin 20293 Pépé");
		client.setNom("Bibi");
		client.setPrenom("Bobo");

		facture.setClient(client);
		facture.setMontant(new Random().nextFloat());

		producer.sendToJMSQueue(facture);
	}

	@Test
	public void consumeTest() throws JMSException, NamingException,
			IOException, InterruptedException {
		Properties jndiProperties = new Properties();
		jndiProperties.load(SampleProducer.class.getClassLoader()
				.getResourceAsStream("jms/jms.properties"));
		InitialContext context = new InitialContext(jndiProperties);
		Destination queue = (Destination) context.lookup("businessSampleQueue");

		SampleConsumer consumer = new SampleConsumer((Destination) queue);

		Message received = consumer.consume();
		TextMessage receivedTextMessage = (TextMessage) received;
		String text = receivedTextMessage.getText();
		System.out.println("Received: " + text);
	}
}
