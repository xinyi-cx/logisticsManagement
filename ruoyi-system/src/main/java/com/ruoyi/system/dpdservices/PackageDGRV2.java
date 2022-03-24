
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for packageDGRV2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="packageDGRV2"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="packageId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="parcels" type="{http://dpdservices.dpd.com.pl/}parcelDGRV2" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="reference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="statusInfo" type="{http://dpdservices.dpd.com.pl/}statusInfoDGRV2" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "packageDGRV2", propOrder = {
    "packageId",
    "parcels",
    "reference",
    "statusInfo"
})
public class PackageDGRV2 {

    protected Long packageId;
    @XmlElement(nillable = true)
    protected List<ParcelDGRV2> parcels;
    protected String reference;
    protected StatusInfoDGRV2 statusInfo;

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
     * {@link ParcelDGRV2 }
     * 
     * 
     */
    public List<ParcelDGRV2> getParcels() {
        if (parcels == null) {
            parcels = new ArrayList<ParcelDGRV2>();
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
     * Gets the value of the statusInfo property.
     * 
     * @return
     *     possible object is
     *     {@link StatusInfoDGRV2 }
     *     
     */
    public StatusInfoDGRV2 getStatusInfo() {
        return statusInfo;
    }

    /**
     * Sets the value of the statusInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusInfoDGRV2 }
     *     
     */
    public void setStatusInfo(StatusInfoDGRV2 value) {
        this.statusInfo = value;
    }

}
