
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for packagesPickupCallV1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="packagesPickupCallV1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dpdPickupParamsV1" type="{http://dpdservices.dpd.com.pl/}dpdPickupCallParamsV1" minOccurs="0"/&gt;
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
@XmlType(name = "packagesPickupCallV1", propOrder = {
    "dpdPickupParamsV1",
    "authDataV1"
})
public class PackagesPickupCallV1 {

    protected DpdPickupCallParamsV1 dpdPickupParamsV1;
    protected AuthDataV1 authDataV1;

    /**
     * Gets the value of the dpdPickupParamsV1 property.
     * 
     * @return
     *     possible object is
     *     {@link DpdPickupCallParamsV1 }
     *     
     */
    public DpdPickupCallParamsV1 getDpdPickupParamsV1() {
        return dpdPickupParamsV1;
    }

    /**
     * Sets the value of the dpdPickupParamsV1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link DpdPickupCallParamsV1 }
     *     
     */
    public void setDpdPickupParamsV1(DpdPickupCallParamsV1 value) {
        this.dpdPickupParamsV1 = value;
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
