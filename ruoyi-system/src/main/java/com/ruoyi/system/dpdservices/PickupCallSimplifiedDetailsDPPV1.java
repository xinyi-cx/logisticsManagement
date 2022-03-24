
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for pickupCallSimplifiedDetailsDPPV1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="pickupCallSimplifiedDetailsDPPV1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="packagesParams" type="{http://dpdservices.dpd.com.pl/}pickupPackagesParamsDPPV1" minOccurs="0"/&gt;
 *         &lt;element name="pickupCustomer" type="{http://dpdservices.dpd.com.pl/}pickupCustomerDPPV1" minOccurs="0"/&gt;
 *         &lt;element name="pickupPayer" type="{http://dpdservices.dpd.com.pl/}pickupPayerDPPV1" minOccurs="0"/&gt;
 *         &lt;element name="pickupSender" type="{http://dpdservices.dpd.com.pl/}pickupSenderDPPV1" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pickupCallSimplifiedDetailsDPPV1", propOrder = {
    "packagesParams",
    "pickupCustomer",
    "pickupPayer",
    "pickupSender"
})
public class PickupCallSimplifiedDetailsDPPV1 {

    protected PickupPackagesParamsDPPV1 packagesParams;
    protected PickupCustomerDPPV1 pickupCustomer;
    protected PickupPayerDPPV1 pickupPayer;
    protected PickupSenderDPPV1 pickupSender;

    /**
     * Gets the value of the packagesParams property.
     * 
     * @return
     *     possible object is
     *     {@link PickupPackagesParamsDPPV1 }
     *     
     */
    public PickupPackagesParamsDPPV1 getPackagesParams() {
        return packagesParams;
    }

    /**
     * Sets the value of the packagesParams property.
     * 
     * @param value
     *     allowed object is
     *     {@link PickupPackagesParamsDPPV1 }
     *     
     */
    public void setPackagesParams(PickupPackagesParamsDPPV1 value) {
        this.packagesParams = value;
    }

    /**
     * Gets the value of the pickupCustomer property.
     * 
     * @return
     *     possible object is
     *     {@link PickupCustomerDPPV1 }
     *     
     */
    public PickupCustomerDPPV1 getPickupCustomer() {
        return pickupCustomer;
    }

    /**
     * Sets the value of the pickupCustomer property.
     * 
     * @param value
     *     allowed object is
     *     {@link PickupCustomerDPPV1 }
     *     
     */
    public void setPickupCustomer(PickupCustomerDPPV1 value) {
        this.pickupCustomer = value;
    }

    /**
     * Gets the value of the pickupPayer property.
     * 
     * @return
     *     possible object is
     *     {@link PickupPayerDPPV1 }
     *     
     */
    public PickupPayerDPPV1 getPickupPayer() {
        return pickupPayer;
    }

    /**
     * Sets the value of the pickupPayer property.
     * 
     * @param value
     *     allowed object is
     *     {@link PickupPayerDPPV1 }
     *     
     */
    public void setPickupPayer(PickupPayerDPPV1 value) {
        this.pickupPayer = value;
    }

    /**
     * Gets the value of the pickupSender property.
     * 
     * @return
     *     possible object is
     *     {@link PickupSenderDPPV1 }
     *     
     */
    public PickupSenderDPPV1 getPickupSender() {
        return pickupSender;
    }

    /**
     * Sets the value of the pickupSender property.
     * 
     * @param value
     *     allowed object is
     *     {@link PickupSenderDPPV1 }
     *     
     */
    public void setPickupSender(PickupSenderDPPV1 value) {
        this.pickupSender = value;
    }

}
