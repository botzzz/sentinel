/**
 *
 */
package xml.serialization;

import java.io.File;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;

import org.apache.log4j.Logger;

import xml.serialization.exception.XmlSerializerException;

/**
 * The class XmlSerializer to serialize/deserialize.
 *
 * @author buissartt
 *
 */
public class XmlSerializer {

	private JAXBContext jaxbContext;

	/**
	 * The logger.
	 */
	public Logger logger = Logger.getLogger(XmlSerializer.class);

	/**
	 * The constructor
	 *
	 * @param contextPath
	 *            the context path to create the jaxb context instance
	 * @throws XmlSerializerException
	 */
	public XmlSerializer(Class<?> clazz) throws XmlSerializerException {
		if (clazz == null)
			new XmlSerializer();
		// JAXBContext creation
		jaxbContext = JAXBContextInitializer.getContext(clazz);
	}

	/**
	 * The constructor. Sets the jaxb context with the value of
	 * JAXBContextInitializer.CONTEXT.
	 *
	 * @throws XmlSerializerException
	 */
	public XmlSerializer() throws XmlSerializerException {
		jaxbContext = JAXBContextInitializer.CONTEXT;
	}

	/**
	 * @return the server xml encoding
	 */
	public final String getServerXMLEncoding() {
		return "ISO-8859-1";
	}

	/**
	 * Create an unmarshaller from the JAXBContext
	 *
	 * @return an unmarshaller created from the current JAXBContext
	 * @throws XmlSerializerException
	 *             if JAXBException caught
	 */
	public synchronized Unmarshaller getUnmarshaller()
			throws XmlSerializerException {
		try {
			logger.debug("Initializing the unmarshaller.");
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			return unmarshaller;
		} catch (JAXBException e) {
			throw new XmlSerializerException(e);
		}
		// return JAXBContextInitializer.UNMARSHALLER;
	}

	/**
	 * Create an unmarshaller from the JAXBContext
	 *
	 * @return an unmarshaller created from the current JAXBContext
	 * @throws XmlSerializerException
	 *             if JAXBException caught
	 */
	public synchronized Marshaller getMarshaller()
			throws XmlSerializerException {
		try {
			logger.debug("marshaller initialization");
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			return marshaller;
		} catch (JAXBException e) {
			throw new XmlSerializerException(e);
		}
		// return JAXBContextInitializer.MARSHALLER;
	}

	/**
	 * Convert from source to object
	 *
	 * @param src
	 * @return
	 * @throws XmlSerializerException
	 */
	public Object convertFromSourceToObject(Source src)
			throws XmlSerializerException {
		try {
			JAXBElement<?> root = (JAXBElement<?>) this.getUnmarshaller()
					.unmarshal(src);
			return root.getValue();
		} catch (JAXBException e) {
			throw new XmlSerializerException(e);
		}
	}

	/**
	 * Convert from a file to a Java object using the unmarshaller
	 *
	 * @param file
	 *            the file to convert
	 * @return a Java object
	 * @throws XmlSerializerException
	 */
	public Object convertFromFileToObject(File file)
			throws XmlSerializerException {
		try {
			JAXBElement<?> root = (JAXBElement<?>) this.getUnmarshaller()
					.unmarshal(file);
			this.getUnmarshaller();
			return root.getValue();
		} catch (JAXBException e) {
			throw new XmlSerializerException(e);
		}
	}

	/**
	 * Convert from an input stream to a Java object using
	 *
	 * @param is
	 * @return
	 * @throws XmlSerializerException
	 */
	public Object convertFromInputStreamToObject(InputStream is)
			throws XmlSerializerException {

		try {
			JAXBElement<?> root = (JAXBElement<?>) this.getUnmarshaller()
					.unmarshal(is);
			return root.getValue();
		} catch (JAXBException e) {
			throw new XmlSerializerException(e);
		}

	}

}
