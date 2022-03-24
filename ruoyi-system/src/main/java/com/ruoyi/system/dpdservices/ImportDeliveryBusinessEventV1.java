
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for importDeliveryBusinessEventV1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="importDeliveryBusinessEventV1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dpdParcelBusinessEventV1" type="{http://dpdservices.dpd.com.pl/}dpdParcelBusinessEventV1" minOccurs="0"/&gt;
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
@XmlType(name = "importDeliveryBusinessEventV1", propOrder = {
    "dpdParcelBusinessEventV1",
    "authDataV1"
})
public class ImportDeliveryBusinessEventV1 {

    protected DpdParcelBusinessEventV1 dpdParcelBusinessEventV1;
    protected AuthDataV1 authDataV1;

    /**
     * Gets the value of the dpdParcelBusinessEventV1 property.
     * 
     * @return
     *     possible object is
     *     {@link DpdParcelBusinessEventV1 }
     *     
     */
    public DpdParcelBusinessEventV1 getDpdParcelBusinessEventV1() {
        return dpdParcelBusinessEventV1;
    }

    /**
     * Sets the value of the dpdParcelBusinessEventV1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link DpdParcelBusinessEventV1 }
     *     
     */
    public void setDpdParcelBusinessEventV1(DpdParcelBusinessEventV1 value) {
        this.dpdParcelBusinessEventV1 = value;
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
