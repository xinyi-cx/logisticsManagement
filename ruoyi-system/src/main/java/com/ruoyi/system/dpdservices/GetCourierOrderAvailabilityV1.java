
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getCourierOrderAvailabilityV1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getCourierOrderAvailabilityV1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="senderPlaceV1" type="{http://dpdservices.dpd.com.pl/}senderPlaceV1" minOccurs="0"/&gt;
 *         &lt;element name="authDataV1" type="{http://dpdservices.dpd.com.pl/}authDataV1" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getCourierOrderAvailabilityV1", propOrder = {
    "senderPlaceV1",
    "authDataV1"
})
public class GetCourierOrderAvailabilityV1 {

    protected SenderPlaceV1 senderPlaceV1;
    protected AuthDataV1 authDataV1;

    /**
     * Gets the value of the senderPlaceV1 property.
     * 
     * @return
     *     possible object is
     *     {@link SenderPlaceV1 }
     *     
     */
    public SenderPlaceV1 getSenderPlaceV1() {
        return senderPlaceV1;
    }

    /**
     * Sets the value of the senderPlaceV1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link SenderPlaceV1 }
     *     
     */
    public void setSenderPlaceV1(SenderPlaceV1 value) {
        this.senderPlaceV1 = value;
    }

    /**
     * Gets the value of the authDataV1 property.
     * 
     * @return
     *     possible object is
     *     {@link AuthDataV1 }
     *     
     */
    public AuthDataV1 getAuthDataV1() {
        return authDataV1;
    }

    /**
     * Sets the value of the authDataV1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthDataV1 }
     *     
     */
    public void setAuthDataV1(AuthDataV1 value) {
        this.authDataV1 = value;
    }

}
