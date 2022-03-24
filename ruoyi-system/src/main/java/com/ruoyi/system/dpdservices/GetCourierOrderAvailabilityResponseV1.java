
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for getCourierOrderAvailabilityResponseV1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getCourierOrderAvailabilityResponseV1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ranges" type="{http://dpdservices.dpd.com.pl/}courierOrderAvailabilityRangeV1" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getCourierOrderAvailabilityResponseV1", propOrder = {
    "ranges",
    "status"
})
public class GetCourierOrderAvailabilityResponseV1 {

    @XmlElement(nillable = true)
    protected List<CourierOrderAvailabilityRangeV1> ranges;
    protected String status;

    /**
     * Gets the value of the ranges property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ranges property.
     * 
     * <p>
     * For runExamples, to add a new item, do as follows:
     * <pre>
     *    getRanges().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CourierOrderAvailabilityRangeV1 }
     * 
     * 
     */
    public List<CourierOrderAvailabilityRangeV1> getRanges() {
        if (ranges == null) {
            ranges = new ArrayList<CourierOrderAvailabilityRangeV1>();
        }
        return this.ranges;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

}
