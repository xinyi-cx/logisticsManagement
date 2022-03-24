
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dpdPickupCallParamsV2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dpdPickupCallParamsV2"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="operationType" type="{http://dpdservices.dpd.com.pl/}pickupCallOperationTypeDPPEnumV1" minOccurs="0"/&gt;
 *         &lt;element name="orderNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="orderType" type="{http://dpdservices.dpd.com.pl/}pickupCallOrderTypeDPPEnumV1" minOccurs="0"/&gt;
 *         &lt;element name="pickupCallSimplifiedDetails" type="{http://dpdservices.dpd.com.pl/}pickupCallSimplifiedDetailsDPPV1" minOccurs="0"/&gt;
 *         &lt;element name="pickupDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pickupTimeFrom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pickupTimeTo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="updateMode" type="{http://dpdservices.dpd.com.pl/}pickupCallUpdateModeDPPEnumV1" minOccurs="0"/&gt;
 *         &lt;element name="waybillsReady" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dpdPickupCallParamsV2", propOrder = {
    "operationType",
    "orderNumber",
    "orderType",
    "pickupCallSimplifiedDetails",
    "pickupDate",
    "pickupTimeFrom",
    "pickupTimeTo",
    "updateMode",
    "waybillsReady"
})
public class DpdPickupCallParamsV2 {

    @XmlSchemaType(name = "string")
    protected PickupCallOperationTypeDPPEnumV1 operationType;
    protected String orderNumber;
    @XmlSchemaType(name = "string")
    protected PickupCallOrderTypeDPPEnumV1 orderType;
    protected PickupCallSimplifiedDetailsDPPV1 pickupCallSimplifiedDetails;
    protected String pickupDate;
    protected String pickupTimeFrom;
    protected String pickupTimeTo;
    @XmlSchemaType(name = "string")
    protected PickupCallUpdateModeDPPEnumV1 updateMode;
    protected Boolean waybillsReady;

    /**
     * Gets the value of the operationType property.
     * 
     * @return
     *     possible object is
     *     {@link PickupCallOperationTypeDPPEnumV1 }
     *     
     */
    public PickupCallOperationTypeDPPEnumV1 getOperationType() {
        return operationType;
    }

    /**
     * Sets the value of the operationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link PickupCallOperationTypeDPPEnumV1 }
     *     
     */
    public void setOperationType(PickupCallOperationTypeDPPEnumV1 value) {
        this.operationType = value;
    }

    /**
     * Gets the value of the orderNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * Sets the value of the orderNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderNumber(String value) {
        this.orderNumber = value;
    }

    /**
     * Gets the value of the orderType property.
     * 
     * @return
     *     possible object is
     *     {@link PickupCallOrderTypeDPPEnumV1 }
     *     
     */
    public PickupCallOrderTypeDPPEnumV1 getOrderType() {
        return orderType;
    }

    /**
     * Sets the value of the orderType property.
     * 
     * @param value
     *     allowed object is
     *     {@link PickupCallOrderTypeDPPEnumV1 }
     *     
     */
    public void setOrderType(PickupCallOrderTypeDPPEnumV1 value) {
        this.orderType = value;
    }

    /**
     * Gets the value of the pickupCallSimplifiedDetails property.
     * 
     * @return
     *     possible object is
     *     {@link PickupCallSimplifiedDetailsDPPV1 }
     *     
     */
    public PickupCallSimplifiedDetailsDPPV1 getPickupCallSimplifiedDetails() {
        return pickupCallSimplifiedDetails;
    }

    /**
     * Sets the value of the pickupCallSimplifiedDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link PickupCallSimplifiedDetailsDPPV1 }
     *     
     */
    public void setPickupCallSimplifiedDetails(PickupCallSimplifiedDetailsDPPV1 value) {
        this.pickupCallSimplifiedDetails = value;
    }

    /**
     * Gets the value of the pickupDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPickupDate() {
        return pickupDate;
    }

    /**
     * Sets the value of the pickupDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickupDate(String value) {
        this.pickupDate = value;
    }

    /**
     * Gets the value of the pickupTimeFrom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPickupTimeFrom() {
        return pickupTimeFrom;
    }

    /**
     * Sets the value of the pickupTimeFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickupTimeFrom(String value) {
        this.pickupTimeFrom = value;
    }

    /**
     * Gets the value of the pickupTimeTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPickupTimeTo() {
        return pickupTimeTo;
    }

    /**
     * Sets the value of the pickupTimeTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickupTimeTo(String value) {
        this.pickupTimeTo = value;
    }

    /**
     * Gets the value of the updateMode property.
     * 
     * @return
     *     possible object is
     *     {@link PickupCallUpdateModeDPPEnumV1 }
     *     
     */
    public PickupCallUpdateModeDPPEnumV1 getUpdateMode() {
        return updateMode;
    }

    /**
     * Sets the value of the updateMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link PickupCallUpdateModeDPPEnumV1 }
     *     
     */
    public void setUpdateMode(PickupCallUpdateModeDPPEnumV1 value) {
        this.updateMode = value;
    }

    /**
     * Gets the value of the waybillsReady property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isWaybillsReady() {
        return waybillsReady;
    }

    /**
     * Sets the value of the waybillsReady property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setWaybillsReady(Boolean value) {
        this.waybillsReady = value;
    }

}
