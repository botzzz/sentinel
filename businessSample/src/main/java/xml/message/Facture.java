//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// généré le : 2014.11.26 à 03:43:31 AM CET
//

package xml.message;

import java.io.ByteArrayOutputStream;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.log4j.Logger;

import xml.IXmlSerializable;
import xml.serialization.XmlSerializer;
import xml.serialization.exception.XmlSerializerException;

/**
 * <p>
 * Classe Java pour anonymous complex type.
 *
 * <p>
 * Le fragment de schéma suivant indique le contenu attendu figurant dans cette
 * classe.
 *
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="montant" type="{http://www.w3.org/2001/XMLSchema}float"/&gt;
 *         &lt;element name="client" type="{http:///xml/message}client"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "id", "montant", "client" })
@XmlRootElement(name = "facture")
public class Facture implements IXmlSerializable {

	private static Logger logger = Logger.getLogger(Facture.class);

	@XmlElement(required = true)
	protected String id;
	protected float montant;
	@XmlElement(required = true)
	protected Client client;

	/**
	 * Obtient la valeur de la propriété id.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getId() {
		return id;
	}

	/**
	 * Définit la valeur de la propriété id.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setId(String value) {
		this.id = value;
	}

	/**
	 * Obtient la valeur de la propriété montant.
	 *
	 */
	public float getMontant() {
		return montant;
	}

	/**
	 * Définit la valeur de la propriété montant.
	 *
	 */
	public void setMontant(float value) {
		this.montant = value;
	}

	/**
	 * Obtient la valeur de la propriété client.
	 *
	 * @return possible object is {@link Client }
	 *
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * Définit la valeur de la propriété client.
	 *
	 * @param value
	 *            allowed object is {@link Client }
	 *
	 */
	public void setClient(Client value) {
		this.client = value;
	}

	/**
	 * convert and return the wof message in a string
	 *
	 * @return the wof message as a String
	 */
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
		logger.debug("end of converting the object to String");

		return os.toString();
	}

}
