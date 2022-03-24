
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for dpdPickupCallParamsV1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dpdPickupCallParamsV1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="contactInfo" type="{http://dpdservices.dpd.com.pl/}contactInfoDPPV1" minOccurs="0"/&gt;
 *         &lt;element name="pickupAddress" type="{http://dpdservices.dpd.com.pl/}pickupAddressDSPV1" minOccurs="0"/&gt;
 *         &lt;element name="pickupDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="pickupTimeFrom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pickupTimeTo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="policy" type="{http://dpdservices.dpd.com.pl/}policyDPPEnumV1" minOccurs="0"/&gt;
 *         &lt;element name="protocols" type="{http://dpdservices.dpd.com.pl/}protocolDPPV1" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dpdPickupCallParamsV1", propOrder = {
    "contactInfo",
    "pickupAddress",
    "pickupDate",
    "pickupTimeFrom",
    "pickupTimeTo",
    "policy",
    "protocols"
})
public class DpdPickupCallParamsV1 {

    protected ContactInfoDPPV1 contactInfo;
    protected PickupAddressDSPV1 pickupAddress;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar pickupDate;
    protected String pickupTimeFrom;
    protected String pickupTimeTo;
    @XmlSchemaType(name = "string")
    protected PolicyDPPEnumV1 policy;
    @XmlElement(nillable = true)
    protected List<ProtocolDPPV1> protocols;

    /**
     * Gets the value of the contactInfo property.
     * 
     * @return
     *     possible object is
     *     {@link ContactInfoDPPV1 }
     *     
     */
    public ContactInfoDPPV1 getContactInfo() {
        return contactInfo;
    }

    /**
     * Sets the value of the contactInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContactInfoDPPV1 }
     *     
     */
    public void setContactInfo(ContactInfoDPPV1 value) {
        this.contactInfo = value;
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
     * Gets the value of the pickupDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPickupDate() {
        return pickupDate;
    }

    /**
     * Sets the value of the pickupDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPickupDate(XMLGregorianCalendar value) {
        this.pickupDate = value;
    }

    /**
     * Gets the value of the pickupTimeFrom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPickupTimeFrom() {
        return pickupTimeFrom;
    }

    /**
     * Sets the value of the pickupTimeFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickupTimeFrom(String value) {
        this.pickupTimeFrom = value;
    }

    /**
     * Gets the value of the pickupTimeTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPickupTimeTo() {
        return pickupTimeTo;
    }

    /**
     * Sets the value of the pickupTimeTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickupTimeTo(String value) {
        this.pickupTimeTo = value;
    }

    /**
     * Gets the value of the policy property.
     * 
     * @return
     *     possible object is
     *     {@link PolicyDPPEnumV1 }
     *     
     */
    public PolicyDPPEnumV1 getPolicy() {
        return policy;
    }

    /**
     * Sets the value of the policy property.
     * 
     * @param value
     *     allowed object is
     *     {@link PolicyDPPEnumV1 }
     *     
     */
    public void setPolicy(PolicyDPPEnumV1 value) {
        this.policy = value;
    }

    /**
     * Gets the value of the protocols property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the protocols property.
     * 
     * <p>
     * For runExamples, to add a new item, do as follows:
     * <pre>
     *    getProtocols().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProtocolDPPV1 }
     * 
     * 
     */
    public List<ProtocolDPPV1> getProtocols() {
        if (protocols == null) {
            protocols = new ArrayList<ProtocolDPPV1>();
        }
        return this.protocols;
    }

}
