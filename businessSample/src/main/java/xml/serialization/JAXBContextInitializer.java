/**
 *
 */
package xml.serialization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import xml.message.Facture;

/**
 * @author buissartt
 *
 */
public class JAXBContextInitializer {

	public static final Logger logger = Logger
			.getLogger(JAXBContextInitializer.class);

	public static final JAXBContext CONTEXT = getContext(Facture.class);

	public static final Marshaller MARSHALLER = getMarshaller();

	public static final Unmarshaller UNMARSHALLER = getUnmarshaller();

	public static JAXBContext getContext(Class<?> clazz) {
		try {
			logger.debug("Initializing the JAXB context.");
			return JAXBContext.newInstance(clazz);
		} catch (JAXBException e) {
			logger.error("Could not initialize the JAXB context.", e);
			return null;
		}
	}

	private static synchronized Marshaller getMarshaller() {
		try {
			logger.debug("Initializing the marshaller.");
			return CONTEXT.createMarshaller();
		} catch (JAXBException e) {
			logger.error("Could not create a marshaller. Null is returned !", e);
			return null;
		}
	}

	private static synchronized Unmarshaller getUnmarshaller() {
		try {
			logger.debug("Initializing the unmarshaller.");
			return CONTEXT.createUnmarshaller();
		} catch (JAXBException e) {
			logger.error("Could not create a marshaller. Null is returned !", e);
			return null;
		}
	}
}
