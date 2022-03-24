
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for packageOpenUMLFeV3 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="packageOpenUMLFeV3"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="parcels" type="{http://dpdservices.dpd.com.pl/}parcelOpenUMLFeV1" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="payerType" type="{http://dpdservices.dpd.com.pl/}payerTypeEnumOpenUMLFeV1" minOccurs="0"/&gt;
 *         &lt;element name="receiver" type="{http://dpdservices.dpd.com.pl/}packageAddressOpenUMLFeV1" minOccurs="0"/&gt;
 *         &lt;element name="ref1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ref2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ref3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="reference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sender" type="{http://dpdservices.dpd.com.pl/}packageAddressOpenUMLFeV1" minOccurs="0"/&gt;
 *         &lt;element name="services" type="{http://dpdservices.dpd.com.pl/}servicesOpenUMLFeV4" minOccurs="0"/&gt;
 *         &lt;element name="thirdPartyFID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "packageOpenUMLFeV3", propOrder = {
    "parcels",
    "payerType",
    "receiver",
    "ref1",
    "ref2",
    "ref3",
    "reference",
    "sender",
    "services",
    "thirdPartyFID"
})
public class PackageOpenUMLFeV3 {

    @XmlElement(nillable = true)
    protected List<ParcelOpenUMLFeV1> parcels;
    @XmlSchemaType(name = "string")
    protected PayerTypeEnumOpenUMLFeV1 payerType;
    protected PackageAddressOpenUMLFeV1 receiver;
    protected String ref1;
    protected String ref2;
    protected String ref3;
    protected String reference;
    protected PackageAddressOpenUMLFeV1 sender;
    protected ServicesOpenUMLFeV4 services;
    protected Integer thirdPartyFID;

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
     * {@link ParcelOpenUMLFeV1 }
     * 
     * 
     */
    public List<ParcelOpenUMLFeV1> getParcels() {
        if (parcels == null) {
            parcels = new ArrayList<ParcelOpenUMLFeV1>();
        }
        return this.parcels;
    }

    /**
     * Gets the value of the payerType property.
     * 
     * @return
     *     possible object is
     *     {@link PayerTypeEnumOpenUMLFeV1 }
     *     
     */
    public PayerTypeEnumOpenUMLFeV1 getPayerType() {
        return payerType;
    }

    /**
     * Sets the value of the payerType property.
     * 
     * @param value
     *     allowed object is
     *     {@link PayerTypeEnumOpenUMLFeV1 }
     *     
     */
    public void setPayerType(PayerTypeEnumOpenUMLFeV1 value) {
        this.payerType = value;
    }

    /**
     * Gets the value of the receiver property.
     * 
     * @return
     *     possible object is
     *     {@link PackageAddressOpenUMLFeV1 }
     *     
     */
    public PackageAddressOpenUMLFeV1 getReceiver() {
        return receiver;
    }

    /**
     * Sets the value of the receiver property.
     * 
     * @param value
     *     allowed object is
     *     {@link PackageAddressOpenUMLFeV1 }
     *     
     */
    public void setReceiver(PackageAddressOpenUMLFeV1 value) {
        this.receiver = value;
    }

    /**
     * Gets the value of the ref1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRef1() {
        return ref1;
    }

    /**
     * Sets the value of the ref1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRef1(String value) {
        this.ref1 = value;
    }

    /**
     * Gets the value of the ref2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRef2() {
        return ref2;
    }

    /**
     * Sets the value of the ref2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRef2(String value) {
        this.ref2 = value;
    }

    /**
     * Gets the value of the ref3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRef3() {
        return ref3;
    }

    /**
     * Sets the value of the ref3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRef3(String value) {
        this.ref3 = value;
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
     * Gets the value of the sender property.
     * 
     * @return
     *     possible object is
     *     {@link PackageAddressOpenUMLFeV1 }
     *     
     */
    public PackageAddressOpenUMLFeV1 getSender() {
        return sender;
    }

    /**
     * Sets the value of the sender property.
     * 
     * @param value
     *     allowed object is
     *     {@link PackageAddressOpenUMLFeV1 }
     *     
     */
    public void setSender(PackageAddressOpenUMLFeV1 value) {
        this.sender = value;
    }

    /**
     * Gets the value of the services property.
     * 
     * @return
     *     possible object is
     *     {@link ServicesOpenUMLFeV4 }
     *     
     */
    public ServicesOpenUMLFeV4 getServices() {
        return services;
    }

    /**
     * Sets the value of the services property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServicesOpenUMLFeV4 }
     *     
     */
    public void setServices(ServicesOpenUMLFeV4 value) {
        this.services = value;
    }

    /**
     * Gets the value of the thirdPartyFID property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getThirdPartyFID() {
        return thirdPartyFID;
    }

    /**
     * Sets the value of the thirdPartyFID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setThirdPartyFID(Integer value) {
        this.thirdPartyFID = value;
    }

}
