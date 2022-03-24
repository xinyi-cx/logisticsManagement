
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for packagesPickupCallV4Response complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="packagesPickupCallV4Response"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="return" type="{http://dpdservices.dpd.com.pl/}packagesPickupCallResponseV3" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "packagesPickupCallV4Response", propOrder = {
    "_return"
})
public class PackagesPickupCallV4Response {

    @XmlElement(name = "return")
    protected PackagesPickupCallResponseV3 _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link PackagesPickupCallResponseV3 }
     *     
     */
    public PackagesPickupCallResponseV3 getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link PackagesPickupCallResponseV3 }
     *     
     */
    public void setReturn(PackagesPickupCallResponseV3 value) {
        this._return = value;
    }

}
