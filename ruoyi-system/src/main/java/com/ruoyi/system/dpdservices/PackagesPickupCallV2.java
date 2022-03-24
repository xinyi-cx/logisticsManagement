
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for packagesPickupCallV2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="packagesPickupCallV2"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dpdPickupParamsV2" type="{http://dpdservices.dpd.com.pl/}dpdPickupCallParamsV2" minOccurs="0"/&gt;
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
@XmlType(name = "packagesPickupCallV2", propOrder = {
    "dpdPickupParamsV2",
    "authDataV1"
})
public class PackagesPickupCallV2 {

    protected DpdPickupCallParamsV2 dpdPickupParamsV2;
    protected AuthDataV1 authDataV1;

    /**
     * Gets the value of the dpdPickupParamsV2 property.
     * 
     * @return
     *     possible object is
     *     {@link DpdPickupCallParamsV2 }
     *     
     */
    public DpdPickupCallParamsV2 getDpdPickupParamsV2() {
        return dpdPickupParamsV2;
    }

    /**
     * Sets the value of the dpdPickupParamsV2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link DpdPickupCallParamsV2 }
     *     
     */
    public void setDpdPickupParamsV2(DpdPickupCallParamsV2 value) {
        this.dpdPickupParamsV2 = value;
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
