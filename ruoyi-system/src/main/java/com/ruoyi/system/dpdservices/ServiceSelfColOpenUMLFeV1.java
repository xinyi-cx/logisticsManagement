
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for serviceSelfColOpenUMLFeV1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="serviceSelfColOpenUMLFeV1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="receiver" type="{http://dpdservices.dpd.com.pl/}serviceSelfColReceiverTypeEnumOpenUMLFeV1" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "serviceSelfColOpenUMLFeV1", propOrder = {
    "receiver"
})
public class ServiceSelfColOpenUMLFeV1 {

    @XmlSchemaType(name = "string")
    protected ServiceSelfColReceiverTypeEnumOpenUMLFeV1 receiver;

    /**
     * Gets the value of the receiver property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceSelfColReceiverTypeEnumOpenUMLFeV1 }
     *     
     */
    public ServiceSelfColReceiverTypeEnumOpenUMLFeV1 getReceiver() {
        return receiver;
    }

    /**
     * Sets the value of the receiver property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceSelfColReceiverTypeEnumOpenUMLFeV1 }
     *     
     */
    public void setReceiver(ServiceSelfColReceiverTypeEnumOpenUMLFeV1 value) {
        this.receiver = value;
    }

}
