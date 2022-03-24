
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for findPostalCodeV1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="findPostalCodeV1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="postalCodeV1" type="{http://dpdservices.dpd.com.pl/}postalCodeV1" minOccurs="0"/&gt;
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
@XmlType(name = "findPostalCodeV1", propOrder = {
    "postalCodeV1",
    "authDataV1"
})
public class FindPostalCodeV1 {

    protected PostalCodeV1 postalCodeV1;
    protected AuthDataV1 authDataV1;

    /**
     * Gets the value of the postalCodeV1 property.
     * 
     * @return
     *     possible object is
     *     {@link PostalCodeV1 }
     *     
     */
    public PostalCodeV1 getPostalCodeV1() {
        return postalCodeV1;
    }

    /**
     * Sets the value of the postalCodeV1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link PostalCodeV1 }
     *     
     */
    public void setPostalCodeV1(PostalCodeV1 value) {
        this.postalCodeV1 = value;
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
