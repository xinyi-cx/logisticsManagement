
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for serviceGuaranteeOpenUMLFeV1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="serviceGuaranteeOpenUMLFeV1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="type" type="{http://dpdservices.dpd.com.pl/}serviceGuaranteeTypeEnumOpenUMLFeV1" minOccurs="0"/&gt;
 *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "serviceGuaranteeOpenUMLFeV1", propOrder = {
    "type",
    "value"
})
public class ServiceGuaranteeOpenUMLFeV1 {

    @XmlSchemaType(name = "string")
    protected ServiceGuaranteeTypeEnumOpenUMLFeV1 type;
    protected String value;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceGuaranteeTypeEnumOpenUMLFeV1 }
     *     
     */
    public ServiceGuaranteeTypeEnumOpenUMLFeV1 getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceGuaranteeTypeEnumOpenUMLFeV1 }
     *     
     */
    public void setType(ServiceGuaranteeTypeEnumOpenUMLFeV1 value) {
        this.type = value;
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

}
