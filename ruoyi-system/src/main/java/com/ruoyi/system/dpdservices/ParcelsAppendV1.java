
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for parcelsAppendV1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="parcelsAppendV1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="packagesearchCriteria" type="{http://dpdservices.dpd.com.pl/}parcelsAppendSearchCriteriaPAV1" minOccurs="0"/&gt;
 *         &lt;element name="parcels" type="{http://dpdservices.dpd.com.pl/}parcelAppendPAV1" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parcelsAppendV1", propOrder = {
    "packagesearchCriteria",
    "parcels"
})
public class ParcelsAppendV1 {

    protected ParcelsAppendSearchCriteriaPAV1 packagesearchCriteria;
    @XmlElement(nillable = true)
    protected List<ParcelAppendPAV1> parcels;

    /**
     * Gets the value of the packagesearchCriteria property.
     * 
     * @return
     *     possible object is
     *     {@link ParcelsAppendSearchCriteriaPAV1 }
     *     
     */
    public ParcelsAppendSearchCriteriaPAV1 getPackagesearchCriteria() {
        return packagesearchCriteria;
    }

    /**
     * Sets the value of the packagesearchCriteria property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParcelsAppendSearchCriteriaPAV1 }
     *     
     */
    public void setPackagesearchCriteria(ParcelsAppendSearchCriteriaPAV1 value) {
        this.packagesearchCriteria = value;
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
     * {@link ParcelAppendPAV1 }
     * 
     * 
     */
    public List<ParcelAppendPAV1> getParcels() {
        if (parcels == null) {
            parcels = new ArrayList<ParcelAppendPAV1>();
        }
        return this.parcels;
    }

}
