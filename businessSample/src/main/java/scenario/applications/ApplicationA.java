/**
 *
 */
package scenario.applications;

import java.util.Random;

import org.apache.log4j.Logger;

import utils.Util;
import xml.message.Client;
import xml.message.Facture;
import connectivity.SampleProducer;

/**
 * @author buissartt
 *
 */
public abstract class ApplicationA extends Thread implements IApplication {

	private static final Logger logger = Logger.getLogger(ApplicationA.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see scenario.applications.IApplication#run()
	 */
	public void run() {
		logger.debug("Application A is now running");
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

}
