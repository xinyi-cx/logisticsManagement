
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for packagePGRV1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="packagePGRV1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="invalidFields" type="{http://dpdservices.dpd.com.pl/}invalidFieldPGRV1" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="packageId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="parcels" type="{http://dpdservices.dpd.com.pl/}parcelPGRV1" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="reference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="status" type="{http://dpdservices.dpd.com.pl/}validationStatusPGREnumV1" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "packagePGRV1", propOrder = {
    "invalidFields",
    "packageId",
    "parcels",
    "reference",
    "status"
})
public class PackagePGRV1 {

    @XmlElement(nillable = true)
    protected List<InvalidFieldPGRV1> invalidFields;
    protected Long packageId;
    @XmlElement(nillable = true)
    protected List<ParcelPGRV1> parcels;
    protected String reference;
    @XmlSchemaType(name = "string")
    protected ValidationStatusPGREnumV1 status;

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
     * {@link InvalidFieldPGRV1 }
     * 
     * 
     */
    public List<InvalidFieldPGRV1> getInvalidFields() {
        if (invalidFields == null) {
            invalidFields = new ArrayList<InvalidFieldPGRV1>();
        }
        return this.invalidFields;
    }

    /**
     * Gets the value of the packageId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPackageId() {
        return packageId;
    }

    /**
     * Sets the value of the packageId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPackageId(Long value) {
        this.packageId = value;
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
     * {@link ParcelPGRV1 }
     * 
     * 
     */
    public List<ParcelPGRV1> getParcels() {
        if (parcels == null) {
            parcels = new ArrayList<ParcelPGRV1>();
        }
        return this.parcels;
    }

    /**
     * Gets the value of the reference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReference() {
        return reference;
    }

    /**
     * Sets the value of the reference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReference(String value) {
        this.reference = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link ValidationStatusPGREnumV1 }
     *     
     */
    public ValidationStatusPGREnumV1 getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValidationStatusPGREnumV1 }
     *     
     */
    public void setStatus(ValidationStatusPGREnumV1 value) {
        this.status = value;
    }

}
