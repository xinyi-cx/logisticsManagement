
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for documentGenerationResponseV1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="documentGenerationResponseV1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="documentData" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="documentId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="session" type="{http://dpdservices.dpd.com.pl/}sessionDGRV1" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "documentGenerationResponseV1", propOrder = {
    "documentData",
    "documentId",
    "session"
})
public class DocumentGenerationResponseV1 {

    protected byte[] documentData;
    protected String documentId;
    protected SessionDGRV1 session;

    /**
     * Gets the value of the documentData property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getDocumentData() {
        return documentData;
    }

    /**
     * Sets the value of the documentData property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setDocumentData(byte[] value) {
        this.documentData = value;
    }

    /**
     * Gets the value of the documentId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentId() {
        return documentId;
    }

    /**
     * Sets the value of the documentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentId(String value) {
        this.documentId = value;
    }

    /**
     * Gets the value of the session property.
     * 
     * @return
     *     possible object is
     *     {@link SessionDGRV1 }
     *     
     */
    public SessionDGRV1 getSession() {
        return session;
    }

    /**
     * Sets the value of the session property.
     * 
     * @param value
     *     allowed object is
     *     {@link SessionDGRV1 }
     *     
     */
    public void setSession(SessionDGRV1 value) {
        this.session = value;
    }

}
