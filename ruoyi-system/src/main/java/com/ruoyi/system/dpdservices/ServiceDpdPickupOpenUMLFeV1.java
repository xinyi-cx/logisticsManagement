
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for serviceDpdPickupOpenUMLFeV1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="serviceDpdPickupOpenUMLFeV1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="pudo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "serviceDpdPickupOpenUMLFeV1", propOrder = {
    "pudo"
})
public class ServiceDpdPickupOpenUMLFeV1 {

    protected String pudo;

    /**
     * Gets the value of the pudo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPudo() {
        return pudo;
    }

    /**
     * Sets the value of the pudo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPudo(String value) {
        this.pudo = value;
    }

}
