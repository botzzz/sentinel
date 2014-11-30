/**
 *
 */
package xml;

import java.io.ByteArrayOutputStream;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;

import xml.serialization.XmlSerializer;
import xml.serialization.exception.XmlSerializerException;

/**
 * @author buissartt
 *
 */
public abstract class AbstractXmlSerializable implements IXmlSerializable {

	public static final Logger logger = Logger
			.getLogger(AbstractXmlSerializable.class);

	public String convertToString() {
		logger.debug("converting the object to String...");

		ByteArrayOutputStream os;
		XmlSerializer xmlSerializer = null;
		try {
			xmlSerializer = new XmlSerializer();
		} catch (XmlSerializerException e) {
			logger.error("Could not create a XML serializer : ", e);
		}
		Marshaller marshaller = null;
		try {
			marshaller = xmlSerializer.getMarshaller();
		} catch (XmlSerializerException e) {
			logger.error("Could not create a marshaller : ", e);
		}
		os = new ByteArrayOutputStream();
		try {
			marshaller.marshal(this, os);
		} catch (JAXBException e) {
			logger.error(
					"An unexpected problem occured during the marshalling", e);
		}
		if (os.toString().equalsIgnoreCase(""))
			logger.error("The message was not converted properly. The buffer is empty.");
		logger.debug("end of converting the wofMessage to String");

		return os.toString();
	}
}
