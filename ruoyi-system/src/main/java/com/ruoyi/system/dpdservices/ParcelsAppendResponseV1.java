
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for parcelsAppendResponseV1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="parcelsAppendResponseV1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="invalidFields" type="{http://dpdservices.dpd.com.pl/}invalidFieldPAV1" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="parcels" type="{http://dpdservices.dpd.com.pl/}parcelsAppendParcelPAV1" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parcelsAppendResponseV1", propOrder = {
    "invalidFields",
    "parcels",
    "status"
})
public class ParcelsAppendResponseV1 {

    @XmlElement(nillable = true)
    protected List<InvalidFieldPAV1> invalidFields;
    @XmlElement(nillable = true)
    protected List<ParcelsAppendParcelPAV1> parcels;
    protected String status;

    /**
     * Gets the value of the invalidFields property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the invalidFields property.
     * 
     * <p>
     * For runExamples, to add a new item, do as follows:
     * <pre>
     *    getInvalidFields().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InvalidFieldPAV1 }
     * 
     * 
     */
    public List<InvalidFieldPAV1> getInvalidFields() {
        if (invalidFields == null) {
            invalidFields = new ArrayList<InvalidFieldPAV1>();
        }
        return this.invalidFields;
    }

    /**
     * Gets the value of the parcels property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the parcels property.
     * 
     * <p>
     * For runExamples, to add a new item, do as follows:
     * <pre>
     *    getParcels().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ParcelsAppendParcelPAV1 }
     * 
     * 
     */
    public List<ParcelsAppendParcelPAV1> getParcels() {
        if (parcels == null) {
            parcels = new ArrayList<ParcelsAppendParcelPAV1>();
        }
        return this.parcels;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

}
