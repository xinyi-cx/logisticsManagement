
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for servicesOpenUMLFeV4 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="servicesOpenUMLFeV4"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="carryIn" type="{http://dpdservices.dpd.com.pl/}serviceCarryInOpenUMLFeV1" minOccurs="0"/&gt;
 *         &lt;element name="cod" type="{http://dpdservices.dpd.com.pl/}serviceCODOpenUMLFeV1" minOccurs="0"/&gt;
 *         &lt;element name="cud" type="{http://dpdservices.dpd.com.pl/}serviceCUDOpenUMLeFV1" minOccurs="0"/&gt;
 *         &lt;element name="declaredValue" type="{http://dpdservices.dpd.com.pl/}serviceDeclaredValueOpenUMLFeV1" minOccurs="0"/&gt;
 *         &lt;element name="dedicatedDelivery" type="{http://dpdservices.dpd.com.pl/}serviceDedicatedDeliveryOpenUMLFeV1" minOccurs="0"/&gt;
 *         &lt;element name="documentsInternational" type="{http://dpdservices.dpd.com.pl/}serviceFlagOpenUMLF" minOccurs="0"/&gt;
 *         &lt;element name="dox" type="{http://dpdservices.dpd.com.pl/}servicePalletOpenUMLFeV1" minOccurs="0"/&gt;
 *         &lt;element name="dpdExpress" type="{http://dpdservices.dpd.com.pl/}serviceFlagOpenUMLF" minOccurs="0"/&gt;
 *         &lt;element name="dpdPickup" type="{http://dpdservices.dpd.com.pl/}serviceDpdPickupOpenUMLFeV1" minOccurs="0"/&gt;
 *         &lt;element name="duty" type="{http://dpdservices.dpd.com.pl/}serviceDutyOpenUMLeFV2" minOccurs="0"/&gt;
 *         &lt;element name="guarantee" type="{http://dpdservices.dpd.com.pl/}serviceGuaranteeOpenUMLFeV1" minOccurs="0"/&gt;
 *         &lt;element name="inPers" type="{http://dpdservices.dpd.com.pl/}serviceInPersOpenUMLFeV1" minOccurs="0"/&gt;
 *         &lt;element name="pallet" type="{http://dpdservices.dpd.com.pl/}servicePalletOpenUMLFeV1" minOccurs="0"/&gt;
 *         &lt;element name="privPers" type="{http://dpdservices.dpd.com.pl/}servicePrivPersOpenUMLFeV1" minOccurs="0"/&gt;
 *         &lt;element name="rod" type="{http://dpdservices.dpd.com.pl/}serviceRODOpenUMLFeV1" minOccurs="0"/&gt;
 *         &lt;element name="selfCol" type="{http://dpdservices.dpd.com.pl/}serviceSelfColOpenUMLFeV1" minOccurs="0"/&gt;
 *         &lt;element name="tires" type="{http://dpdservices.dpd.com.pl/}serviceTiresOpenUMLFeV1" minOccurs="0"/&gt;
 *         &lt;element name="tiresExport" type="{http://dpdservices.dpd.com.pl/}serviceTiresExportOpenUMLFeV1" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "servicesOpenUMLFeV4", propOrder = {
    "carryIn",
    "cod",
    "cud",
    "declaredValue",
    "dedicatedDelivery",
    "documentsInternational",
    "dox",
    "dpdExpress",
    "dpdPickup",
    "duty",
    "guarantee",
    "inPers",
    "pallet",
    "privPers",
    "rod",
    "selfCol",
    "tires",
    "tiresExport"
})
public class ServicesOpenUMLFeV4 {

    protected ServiceCarryInOpenUMLFeV1 carryIn;
    protected ServiceCODOpenUMLFeV1 cod;
    protected ServiceCUDOpenUMLeFV1 cud;
    protected ServiceDeclaredValueOpenUMLFeV1 declaredValue;
    protected ServiceDedicatedDeliveryOpenUMLFeV1 dedicatedDelivery;
    protected ServiceFlagOpenUMLF documentsInternational;
    protected ServicePalletOpenUMLFeV1 dox;
    protected ServiceFlagOpenUMLF dpdExpress;
    protected ServiceDpdPickupOpenUMLFeV1 dpdPickup;
    protected ServiceDutyOpenUMLeFV2 duty;
    protected ServiceGuaranteeOpenUMLFeV1 guarantee;
    protected ServiceInPersOpenUMLFeV1 inPers;
    protected ServicePalletOpenUMLFeV1 pallet;
    protected ServicePrivPersOpenUMLFeV1 privPers;
    protected ServiceRODOpenUMLFeV1 rod;
    protected ServiceSelfColOpenUMLFeV1 selfCol;
    protected ServiceTiresOpenUMLFeV1 tires;
    protected ServiceTiresExportOpenUMLFeV1 tiresExport;

    /**
     * Gets the value of the carryIn property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceCarryInOpenUMLFeV1 }
     *     
     */
    public ServiceCarryInOpenUMLFeV1 getCarryIn() {
        return carryIn;
    }

    /**
     * Sets the value of the carryIn property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceCarryInOpenUMLFeV1 }
     *     
     */
    public void setCarryIn(ServiceCarryInOpenUMLFeV1 value) {
        this.carryIn = value;
    }

    /**
     * Gets the value of the cod property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceCODOpenUMLFeV1 }
     *     
     */
    public ServiceCODOpenUMLFeV1 getCod() {
        return cod;
    }

    /**
     * Sets the value of the cod property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceCODOpenUMLFeV1 }
     *     
     */
    public void setCod(ServiceCODOpenUMLFeV1 value) {
        this.cod = value;
    }

    /**
     * Gets the value of the cud property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceCUDOpenUMLeFV1 }
     *     
     */
    public ServiceCUDOpenUMLeFV1 getCud() {
        return cud;
    }

    /**
     * Sets the value of the cud property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceCUDOpenUMLeFV1 }
     *     
     */
    public void setCud(ServiceCUDOpenUMLeFV1 value) {
        this.cud = value;
    }

    /**
     * Gets the value of the declaredValue property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceDeclaredValueOpenUMLFeV1 }
     *     
     */
    public ServiceDeclaredValueOpenUMLFeV1 getDeclaredValue() {
        return declaredValue;
    }

    /**
     * Sets the value of the declaredValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceDeclaredValueOpenUMLFeV1 }
     *     
     */
    public void setDeclaredValue(ServiceDeclaredValueOpenUMLFeV1 value) {
        this.declaredValue = value;
    }

    /**
     * Gets the value of the dedicatedDelivery property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceDedicatedDeliveryOpenUMLFeV1 }
     *     
     */
    public ServiceDedicatedDeliveryOpenUMLFeV1 getDedicatedDelivery() {
        return dedicatedDelivery;
    }

    /**
     * Sets the value of the dedicatedDelivery property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceDedicatedDeliveryOpenUMLFeV1 }
     *     
     */
    public void setDedicatedDelivery(ServiceDedicatedDeliveryOpenUMLFeV1 value) {
        this.dedicatedDelivery = value;
    }

    /**
     * Gets the value of the documentsInternational property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceFlagOpenUMLF }
     *     
     */
    public ServiceFlagOpenUMLF getDocumentsInternational() {
        return documentsInternational;
    }

    /**
     * Sets the value of the documentsInternational property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceFlagOpenUMLF }
     *     
     */
    public void setDocumentsInternational(ServiceFlagOpenUMLF value) {
        this.documentsInternational = value;
    }

    /**
     * Gets the value of the dox property.
     * 
     * @return
     *     possible object is
     *     {@link ServicePalletOpenUMLFeV1 }
     *     
     */
    public ServicePalletOpenUMLFeV1 getDox() {
        return dox;
    }

    /**
     * Sets the value of the dox property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServicePalletOpenUMLFeV1 }
     *     
     */
    public void setDox(ServicePalletOpenUMLFeV1 value) {
        this.dox = value;
    }

    /**
     * Gets the value of the dpdExpress property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceFlagOpenUMLF }
     *     
     */
    public ServiceFlagOpenUMLF getDpdExpress() {
        return dpdExpress;
    }

    /**
     * Sets the value of the dpdExpress property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceFlagOpenUMLF }
     *     
     */
    public void setDpdExpress(ServiceFlagOpenUMLF value) {
        this.dpdExpress = value;
    }

    /**
     * Gets the value of the dpdPickup property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceDpdPickupOpenUMLFeV1 }
     *     
     */
    public ServiceDpdPickupOpenUMLFeV1 getDpdPickup() {
        return dpdPickup;
    }

    /**
     * Sets the value of the dpdPickup property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceDpdPickupOpenUMLFeV1 }
     *     
     */
    public void setDpdPickup(ServiceDpdPickupOpenUMLFeV1 value) {
        this.dpdPickup = value;
    }

    /**
     * Gets the value of the duty property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceDutyOpenUMLeFV2 }
     *     
     */
    public ServiceDutyOpenUMLeFV2 getDuty() {
        return duty;
    }

    /**
     * Sets the value of the duty property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceDutyOpenUMLeFV2 }
     *     
     */
    public void setDuty(ServiceDutyOpenUMLeFV2 value) {
        this.duty = value;
    }

    /**
     * Gets the value of the guarantee property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceGuaranteeOpenUMLFeV1 }
     *     
     */
    public ServiceGuaranteeOpenUMLFeV1 getGuarantee() {
        return guarantee;
    }

    /**
     * Sets the value of the guarantee property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceGuaranteeOpenUMLFeV1 }
     *     
     */
    public void setGuarantee(ServiceGuaranteeOpenUMLFeV1 value) {
        this.guarantee = value;
    }

    /**
     * Gets the value of the inPers property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceInPersOpenUMLFeV1 }
     *     
     */
    public ServiceInPersOpenUMLFeV1 getInPers() {
        return inPers;
    }

    /**
     * Sets the value of the inPers property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceInPersOpenUMLFeV1 }
     *     
     */
    public void setInPers(ServiceInPersOpenUMLFeV1 value) {
        this.inPers = value;
    }

    /**
     * Gets the value of the pallet property.
     * 
     * @return
     *     possible object is
     *     {@link ServicePalletOpenUMLFeV1 }
     *     
     */
    public ServicePalletOpenUMLFeV1 getPallet() {
        return pallet;
    }

    /**
     * Sets the value of the pallet property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServicePalletOpenUMLFeV1 }
     *     
     */
    public void setPallet(ServicePalletOpenUMLFeV1 value) {
        this.pallet = value;
    }

    /**
     * Gets the value of the privPers property.
     * 
     * @return
     *     possible object is
     *     {@link ServicePrivPersOpenUMLFeV1 }
     *     
     */
    public ServicePrivPersOpenUMLFeV1 getPrivPers() {
        return privPers;
    }

    /**
     * Sets the value of the privPers property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServicePrivPersOpenUMLFeV1 }
     *     
     */
    public void setPrivPers(ServicePrivPersOpenUMLFeV1 value) {
        this.privPers = value;
    }

    /**
     * Gets the value of the rod property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRODOpenUMLFeV1 }
     *     
     */
    public ServiceRODOpenUMLFeV1 getRod() {
        return rod;
    }

    /**
     * Sets the value of the rod property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRODOpenUMLFeV1 }
     *     
     */
    public void setRod(ServiceRODOpenUMLFeV1 value) {
        this.rod = value;
    }

    /**
     * Gets the value of the selfCol property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceSelfColOpenUMLFeV1 }
     *     
     */
    public ServiceSelfColOpenUMLFeV1 getSelfCol() {
        return selfCol;
    }

    /**
     * Sets the value of the selfCol property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceSelfColOpenUMLFeV1 }
     *     
     */
    public void setSelfCol(ServiceSelfColOpenUMLFeV1 value) {
        this.selfCol = value;
    }

    /**
     * Gets the value of the tires property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceTiresOpenUMLFeV1 }
     *     
     */
    public ServiceTiresOpenUMLFeV1 getTires() {
        return tires;
    }

    /**
     * Sets the value of the tires property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceTiresOpenUMLFeV1 }
     *     
     */
    public void setTires(ServiceTiresOpenUMLFeV1 value) {
        this.tires = value;
    }

    /**
     * Gets the value of the tiresExport property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceTiresExportOpenUMLFeV1 }
     *     
     */
    public ServiceTiresExportOpenUMLFeV1 getTiresExport() {
        return tiresExport;
    }

    /**
     * Sets the value of the tiresExport property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceTiresExportOpenUMLFeV1 }
     *     
     */
    public void setTiresExport(ServiceTiresExportOpenUMLFeV1 value) {
        this.tiresExport = value;
    }

}
