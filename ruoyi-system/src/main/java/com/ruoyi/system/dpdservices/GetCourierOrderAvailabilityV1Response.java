
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getCourierOrderAvailabilityV1Response complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getCourierOrderAvailabilityV1Response"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="return" type="{http://dpdservices.dpd.com.pl/}getCourierOrderAvailabilityResponseV1" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getCourierOrderAvailabilityV1Response", propOrder = {
    "_return"
})
public class GetCourierOrderAvailabilityV1Response {

    @XmlElement(name = "return")
    protected GetCourierOrderAvailabilityResponseV1 _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link GetCourierOrderAvailabilityResponseV1 }
     *     
     */
    public GetCourierOrderAvailabilityResponseV1 getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetCourierOrderAvailabilityResponseV1 }
     *     
     */
    public void setReturn(GetCourierOrderAvailabilityResponseV1 value) {
        this._return = value;
    }

}
