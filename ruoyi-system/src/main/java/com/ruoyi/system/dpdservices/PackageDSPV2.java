
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for packageDSPV2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="packageDSPV2"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PackageId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="Parcels" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Parcel" type="{http://dpdservices.dpd.com.pl/}parcelDSPV2" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Reference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "packageDSPV2", propOrder = {
    "packageId",
    "parcels",
    "reference"
})
public class PackageDSPV2 {

    @XmlElement(name = "PackageId")
    protected Long packageId;
    @XmlElement(name = "Parcels")
    protected PackageDSPV2 .Parcels parcels;
    @XmlElement(name = "Reference")
    protected String reference;

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
     * @return
     *     possible object is
     *     {@link PackageDSPV2 .Parcels }
     *
     */
    public PackageDSPV2 .Parcels getParcels() {
        return parcels;
    }

    /**
     * Sets the value of the parcels property.
     *
     * @param value
     *     allowed object is
     *     {@link PackageDSPV2 .Parcels }
     *
     */
    public void setParcels(PackageDSPV2 .Parcels value) {
        this.parcels = value;
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
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="Parcel" type="{http://dpdservices.dpd.com.pl/}parcelDSPV2" maxOccurs="unbounded" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "parcel"
    })
    public static class Parcels {

        @XmlElement(name = "Parcel")
        protected List<ParcelDSPV2> parcel;

        /**
         * Gets the value of the parcel property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the parcel property.
         * 
         * <p>
         * For runExamples, to add a new item, do as follows:
         * <pre>
         *    getParcel().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ParcelDSPV2 }
         * 
         * 
         */
        public List<ParcelDSPV2> getParcel() {
            if (parcel == null) {
                parcel = new ArrayList<ParcelDSPV2>();
            }
            return this.parcel;
        }

    }

}
