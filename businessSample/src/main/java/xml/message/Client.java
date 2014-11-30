//
// Ce fichier a été généré par l'impl�mentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// généré le : 2014.11.26 à 03:43:31 AM CET
//


package xml.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import xml.AbstractXmlSerializable;


/**
 * <p>Classe Java pour client complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="client"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="nom" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="prenom" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="adresse" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "client", propOrder = {
    "nom",
    "prenom",
    "adresse"
})
public class Client extends AbstractXmlSerializable {

    @XmlElement(required = true)
    protected String nom;
    @XmlElement(required = true)
    protected String prenom;
    @XmlElement(required = true)
    protected String adresse;

    /**
     * Obtient la valeur de la propriété nom.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit la valeur de la propriété nom.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setNom(String value) {
        this.nom = value;
    }

    /**
     * Obtient la valeur de la propriété prenom.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Définit la valeur de la propriété prenom.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPrenom(String value) {
        this.prenom = value;
    }

    /**
     * Obtient la valeur de la propriété adresse.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Définit la valeur de la propriété adresse.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setAdresse(String value) {
        this.adresse = value;
    }
}
