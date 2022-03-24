
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for appendParcelsToPackageV1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="appendParcelsToPackageV1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="parcelsAppend" type="{http://dpdservices.dpd.com.pl/}parcelsAppendV1" minOccurs="0"/&gt;
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
@XmlType(name = "appendParcelsToPackageV1", propOrder = {
    "parcelsAppend",
    "authDataV1"
})
public class AppendParcelsToPackageV1 {

    protected ParcelsAppendV1 parcelsAppend;
    protected AuthDataV1 authDataV1;

    /**
     * Gets the value of the parcelsAppend property.
     * 
     * @return
     *     possible object is
     *     {@link ParcelsAppendV1 }
     *     
     */
    public ParcelsAppendV1 getParcelsAppend() {
        return parcelsAppend;
    }

    /**
     * Sets the value of the parcelsAppend property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParcelsAppendV1 }
     *     
     */
    public void setParcelsAppend(ParcelsAppendV1 value) {
        this.parcelsAppend = value;
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
