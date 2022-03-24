
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for pickupPackagesParamsDPPV1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="pickupPackagesParamsDPPV1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dox" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="doxCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="pallet" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="palletMaxHeight" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="palletMaxWeight" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="palletsCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="palletsWeight" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="parcelMaxDepth" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="parcelMaxHeight" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="parcelMaxWeight" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="parcelMaxWidth" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="parcelsCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="parcelsWeight" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="standardParcel" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pickupPackagesParamsDPPV1", propOrder = {
    "dox",
    "doxCount",
    "pallet",
    "palletMaxHeight",
    "palletMaxWeight",
    "palletsCount",
    "palletsWeight",
    "parcelMaxDepth",
    "parcelMaxHeight",
    "parcelMaxWeight",
    "parcelMaxWidth",
    "parcelsCount",
    "parcelsWeight",
    "standardParcel"
})
public class PickupPackagesParamsDPPV1 {

    protected Boolean dox;
    protected Integer doxCount;
    protected Boolean pallet;
    protected Double palletMaxHeight;
    protected Double palletMaxWeight;
    protected Integer palletsCount;
    protected Double palletsWeight;
    protected Double parcelMaxDepth;
    protected Double parcelMaxHeight;
    protected Double parcelMaxWeight;
    protected Double parcelMaxWidth;
    protected Integer parcelsCount;
    protected Double parcelsWeight;
    protected Boolean standardParcel;

    /**
     * Gets the value of the dox property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDox() {
        return dox;
    }

    /**
     * Sets the value of the dox property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDox(Boolean value) {
        this.dox = value;
    }

    /**
     * Gets the value of the doxCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDoxCount() {
        return doxCount;
    }

    /**
     * Sets the value of the doxCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDoxCount(Integer value) {
        this.doxCount = value;
    }

    /**
     * Gets the value of the pallet property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPallet() {
        return pallet;
    }

    /**
     * Sets the value of the pallet property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPallet(Boolean value) {
        this.pallet = value;
    }

    /**
     * Gets the value of the palletMaxHeight property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPalletMaxHeight() {
        return palletMaxHeight;
    }

    /**
     * Sets the value of the palletMaxHeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPalletMaxHeight(Double value) {
        this.palletMaxHeight = value;
    }

    /**
     * Gets the value of the palletMaxWeight property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPalletMaxWeight() {
        return palletMaxWeight;
    }

    /**
     * Sets the value of the palletMaxWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPalletMaxWeight(Double value) {
        this.palletMaxWeight = value;
    }

    /**
     * Gets the value of the palletsCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPalletsCount() {
        return palletsCount;
    }

    /**
     * Sets the value of the palletsCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPalletsCount(Integer value) {
        this.palletsCount = value;
    }

    /**
     * Gets the value of the palletsWeight property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPalletsWeight() {
        return palletsWeight;
    }

    /**
     * Sets the value of the palletsWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPalletsWeight(Double value) {
        this.palletsWeight = value;
    }

    /**
     * Gets the value of the parcelMaxDepth property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getParcelMaxDepth() {
        return parcelMaxDepth;
    }

    /**
     * Sets the value of the parcelMaxDepth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setParcelMaxDepth(Double value) {
        this.parcelMaxDepth = value;
    }

    /**
     * Gets the value of the parcelMaxHeight property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getParcelMaxHeight() {
        return parcelMaxHeight;
    }

    /**
     * Sets the value of the parcelMaxHeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setParcelMaxHeight(Double value) {
        this.parcelMaxHeight = value;
    }

    /**
     * Gets the value of the parcelMaxWeight property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getParcelMaxWeight() {
        return parcelMaxWeight;
    }

    /**
     * Sets the value of the parcelMaxWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setParcelMaxWeight(Double value) {
        this.parcelMaxWeight = value;
    }

    /**
     * Gets the value of the parcelMaxWidth property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getParcelMaxWidth() {
        return parcelMaxWidth;
    }

    /**
     * Sets the value of the parcelMaxWidth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setParcelMaxWidth(Double value) {
        this.parcelMaxWidth = value;
    }

    /**
     * Gets the value of the parcelsCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getParcelsCount() {
        return parcelsCount;
    }

    /**
     * Sets the value of the parcelsCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setParcelsCount(Integer value) {
        this.parcelsCount = value;
    }

    /**
     * Gets the value of the parcelsWeight property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getParcelsWeight() {
        return parcelsWeight;
    }

    /**
     * Sets the value of the parcelsWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setParcelsWeight(Double value) {
        this.parcelsWeight = value;
    }

    /**
     * Gets the value of the standardParcel property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isStandardParcel() {
        return standardParcel;
    }

    /**
     * Sets the value of the standardParcel property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setStandardParcel(Boolean value) {
        this.standardParcel = value;
    }

}
