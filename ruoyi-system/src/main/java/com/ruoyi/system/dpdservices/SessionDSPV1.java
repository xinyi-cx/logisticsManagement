
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for sessionDSPV1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sessionDSPV1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="packages" type="{http://dpdservices.dpd.com.pl/}packageDSPV1" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="sessionId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="sessionType" type="{http://dpdservices.dpd.com.pl/}sessionTypeDSPEnumV1" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sessionDSPV1", propOrder = {
    "packages",
    "sessionId",
    "sessionType"
})
public class SessionDSPV1 {

    @XmlElement(nillable = true)
    protected List<PackageDSPV1> packages;
    protected Long sessionId;
    @XmlSchemaType(name = "string")
    protected SessionTypeDSPEnumV1 sessionType;

    /**
     * Gets the value of the packages property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the packages property.
     * 
     * <p>
     * For runExamples, to add a new item, do as follows:
     * <pre>
     *    getPackages().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PackageDSPV1 }
     * 
     * 
     */
    public List<PackageDSPV1> getPackages() {
        if (packages == null) {
            packages = new ArrayList<PackageDSPV1>();
        }
        return this.packages;
    }

    /**
     * Gets the value of the sessionId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSessionId() {
        return sessionId;
    }

    /**
     * Sets the value of the sessionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSessionId(Long value) {
        this.sessionId = value;
    }

    /**
     * Gets the value of the sessionType property.
     * 
     * @return
     *     possible object is
     *     {@link SessionTypeDSPEnumV1 }
     *     
     */
    public SessionTypeDSPEnumV1 getSessionType() {
        return sessionType;
    }

    /**
     * Sets the value of the sessionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link SessionTypeDSPEnumV1 }
     *     
     */
    public void setSessionType(SessionTypeDSPEnumV1 value) {
        this.sessionType = value;
    }

}
