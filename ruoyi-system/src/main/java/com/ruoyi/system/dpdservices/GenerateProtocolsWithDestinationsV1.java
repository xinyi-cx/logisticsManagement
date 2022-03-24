
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for generateProtocolsWithDestinationsV1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="generateProtocolsWithDestinationsV1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dpdServicesParamsV2" type="{http://dpdservices.dpd.com.pl/}dpdServicesParamsV2" minOccurs="0"/&gt;
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
@XmlType(name = "generateProtocolsWithDestinationsV1", propOrder = {
    "dpdServicesParamsV2",
    "authDataV1"
})
public class GenerateProtocolsWithDestinationsV1 {

    protected DpdServicesParamsV2 dpdServicesParamsV2;
    protected AuthDataV1 authDataV1;

    /**
     * Gets the value of the dpdServicesParamsV2 property.
     * 
     * @return
     *     possible object is
     *     {@link DpdServicesParamsV2 }
     *     
     */
    public DpdServicesParamsV2 getDpdServicesParamsV2() {
        return dpdServicesParamsV2;
    }

    /**
     * Sets the value of the dpdServicesParamsV2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link DpdServicesParamsV2 }
     *     
     */
    public void setDpdServicesParamsV2(DpdServicesParamsV2 value) {
        this.dpdServicesParamsV2 = value;
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
