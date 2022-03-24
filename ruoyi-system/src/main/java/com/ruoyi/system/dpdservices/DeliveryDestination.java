
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for deliveryDestination complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="deliveryDestination"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DepotList" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="ProtocolDepot" type="{http://dpdservices.dpd.com.pl/}protocolDepot" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DestinationName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deliveryDestination", propOrder = {
    "depotList",
    "destinationName"
})
public class DeliveryDestination {

    @XmlElement(name = "DepotList")
    protected DeliveryDestination.DepotList depotList;
    @XmlElement(name = "DestinationName")
    protected String destinationName;

    /**
     * Gets the value of the depotList property.
     *
     * @return
     *     possible object is
     *     {@link DeliveryDestination.DepotList }
     *
     */
    public DeliveryDestination.DepotList getDepotList() {
        return depotList;
    }

    /**
     * Sets the value of the depotList property.
     *
     * @param value
     *     allowed object is
     *     {@link DeliveryDestination.DepotList }
     *
     */
    public void setDepotList(DeliveryDestination.DepotList value) {
        this.depotList = value;
    }

    /**
     * Gets the value of the destinationName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationName() {
        return destinationName;
    }

    /**
     * Sets the value of the destinationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationName(String value) {
        this.destinationName = value;
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
     *         &lt;element name="ProtocolDepot" type="{http://dpdservices.dpd.com.pl/}protocolDepot" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "protocolDepot"
    })
    public static class DepotList {

        @XmlElement(name = "ProtocolDepot")
        protected List<ProtocolDepot> protocolDepot;

        /**
         * Gets the value of the protocolDepot property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the protocolDepot property.
         * 
         * <p>
         * For runExamples, to add a new item, do as follows:
         * <pre>
         *    getProtocolDepot().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ProtocolDepot }
         * 
         * 
         */
        public List<ProtocolDepot> getProtocolDepot() {
            if (protocolDepot == null) {
                protocolDepot = new ArrayList<ProtocolDepot>();
            }
            return this.protocolDepot;
        }

    }

}
