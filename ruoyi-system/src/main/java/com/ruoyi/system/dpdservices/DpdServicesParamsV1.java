
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dpdServicesParamsV1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dpdServicesParamsV1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="documentId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pickupAddress" type="{http://dpdservices.dpd.com.pl/}pickupAddressDSPV1" minOccurs="0"/&gt;
 *         &lt;element name="policy" type="{http://dpdservices.dpd.com.pl/}policyDSPEnumV1" minOccurs="0"/&gt;
 *         &lt;element name="session" type="{http://dpdservices.dpd.com.pl/}sessionDSPV1" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dpdServicesParamsV1", propOrder = {
    "documentId",
    "pickupAddress",
    "policy",
    "session"
})
public class DpdServicesParamsV1 {

    protected String documentId;
    protected PickupAddressDSPV1 pickupAddress;
    @XmlSchemaType(name = "string")
    protected PolicyDSPEnumV1 policy;
    protected SessionDSPV1 session;

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
     * Gets the value of the pickupAddress property.
     * 
     * @return
     *     possible object is
     *     {@link PickupAddressDSPV1 }
     *     
     */
    public PickupAddressDSPV1 getPickupAddress() {
        return pickupAddress;
    }

    /**
     * Sets the value of the pickupAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link PickupAddressDSPV1 }
     *     
     */
    public void setPickupAddress(PickupAddressDSPV1 value) {
        this.pickupAddress = value;
    }

    /**
     * Gets the value of the policy property.
     * 
     * @return
     *     possible object is
     *     {@link PolicyDSPEnumV1 }
     *     
     */
    public PolicyDSPEnumV1 getPolicy() {
        return policy;
    }

    /**
     * Sets the value of the policy property.
     * 
     * @param value
     *     allowed object is
     *     {@link PolicyDSPEnumV1 }
     *     
     */
    public void setPolicy(PolicyDSPEnumV1 value) {
        this.policy = value;
    }

    /**
     * Gets the value of the session property.
     * 
     * @return
     *     possible object is
     *     {@link SessionDSPV1 }
     *     
     */
    public SessionDSPV1 getSession() {
        return session;
    }

    /**
     * Sets the value of the session property.
     * 
     * @param value
     *     allowed object is
     *     {@link SessionDSPV1 }
     *     
     */
    public void setSession(SessionDSPV1 value) {
        this.session = value;
    }

}
