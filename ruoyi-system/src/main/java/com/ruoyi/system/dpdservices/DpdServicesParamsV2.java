
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for dpdServicesParamsV2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dpdServicesParamsV2"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Policy" type="{http://dpdservices.dpd.com.pl/}policyDSPEnumV2" minOccurs="0"/&gt;
 *         &lt;element name="Session" type="{http://dpdservices.dpd.com.pl/}sessionDSPV2" minOccurs="0"/&gt;
 *         &lt;element name="PickupAddress" type="{http://dpdservices.dpd.com.pl/}pickupAddressDSPV2" minOccurs="0"/&gt;
 *         &lt;element name="DeliveryDestinations" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="DeliveryDestination" type="{http://dpdservices.dpd.com.pl/}deliveryDestination" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="GenProtForNonMatching" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dpdServicesParamsV2", propOrder = {
    "policy",
    "session",
    "pickupAddress",
    "deliveryDestinations",
    "genProtForNonMatching"
})
public class DpdServicesParamsV2 {

    @XmlElement(name = "Policy")
    @XmlSchemaType(name = "string")
    protected PolicyDSPEnumV2 policy;
    @XmlElement(name = "Session")
    protected SessionDSPV2 session;
    @XmlElement(name = "PickupAddress")
    protected PickupAddressDSPV2 pickupAddress;
    @XmlElement(name = "DeliveryDestinations")
    protected DpdServicesParamsV2 .DeliveryDestinations deliveryDestinations;
    @XmlElement(name = "GenProtForNonMatching")
    protected Boolean genProtForNonMatching;

    /**
     * Gets the value of the policy property.
     *
     * @return
     *     possible object is
     *     {@link PolicyDSPEnumV2 }
     *
     */
    public PolicyDSPEnumV2 getPolicy() {
        return policy;
    }

    /**
     * Sets the value of the policy property.
     *
     * @param value
     *     allowed object is
     *     {@link PolicyDSPEnumV2 }
     *
     */
    public void setPolicy(PolicyDSPEnumV2 value) {
        this.policy = value;
    }

    /**
     * Gets the value of the session property.
     *
     * @return
     *     possible object is
     *     {@link SessionDSPV2 }
     *
     */
    public SessionDSPV2 getSession() {
        return session;
    }

    /**
     * Sets the value of the session property.
     *
     * @param value
     *     allowed object is
     *     {@link SessionDSPV2 }
     *
     */
    public void setSession(SessionDSPV2 value) {
        this.session = value;
    }

    /**
     * Gets the value of the pickupAddress property.
     *
     * @return
     *     possible object is
     *     {@link PickupAddressDSPV2 }
     *
     */
    public PickupAddressDSPV2 getPickupAddress() {
        return pickupAddress;
    }

    /**
     * Sets the value of the pickupAddress property.
     *
     * @param value
     *     allowed object is
     *     {@link PickupAddressDSPV2 }
     *
     */
    public void setPickupAddress(PickupAddressDSPV2 value) {
        this.pickupAddress = value;
    }

    /**
     * Gets the value of the deliveryDestinations property.
     *
     * @return
     *     possible object is
     *     {@link DpdServicesParamsV2 .DeliveryDestinations }
     *
     */
    public DpdServicesParamsV2 .DeliveryDestinations getDeliveryDestinations() {
        return deliveryDestinations;
    }

    /**
     * Sets the value of the deliveryDestinations property.
     *
     * @param value
     *     allowed object is
     *     {@link DpdServicesParamsV2 .DeliveryDestinations }
     *
     */
    public void setDeliveryDestinations(DpdServicesParamsV2 .DeliveryDestinations value) {
        this.deliveryDestinations = value;
    }

    /**
     * Gets the value of the genProtForNonMatching property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isGenProtForNonMatching() {
        return genProtForNonMatching;
    }

    /**
     * Sets the value of the genProtForNonMatching property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setGenProtForNonMatching(Boolean value) {
        this.genProtForNonMatching = value;
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
     *         &lt;element name="DeliveryDestination" type="{http://dpdservices.dpd.com.pl/}deliveryDestination" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "deliveryDestination"
    })
    public static class DeliveryDestinations {

        @XmlElement(name = "DeliveryDestination")
        protected List<DeliveryDestination> deliveryDestination;

        /**
         * Gets the value of the deliveryDestination property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the deliveryDestination property.
         * 
         * <p>
         * For runExamples, to add a new item, do as follows:
         * <pre>
         *    getDeliveryDestination().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DeliveryDestination }
         * 
         * 
         */
        public List<DeliveryDestination> getDeliveryDestination() {
            if (deliveryDestination == null) {
                deliveryDestination = new ArrayList<DeliveryDestination>();
            }
            return this.deliveryDestination;
        }

    }

}
