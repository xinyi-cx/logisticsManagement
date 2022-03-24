
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for sessionDSPV2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sessionDSPV2"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Packages" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Package" type="{http://dpdservices.dpd.com.pl/}packageDSPV2" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="SessionId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sessionDSPV2", propOrder = {
    "packages",
    "sessionId"
})
public class SessionDSPV2 {

    @XmlElement(name = "Packages")
    protected SessionDSPV2 .Packages packages;
    @XmlElement(name = "SessionId")
    protected Long sessionId;

    /**
     * Gets the value of the packages property.
     *
     * @return
     *     possible object is
     *     {@link SessionDSPV2 .Packages }
     *
     */
    public SessionDSPV2 .Packages getPackages() {
        return packages;
    }

    /**
     * Sets the value of the packages property.
     *
     * @param value
     *     allowed object is
     *     {@link SessionDSPV2 .Packages }
     *
     */
    public void setPackages(SessionDSPV2 .Packages value) {
        this.packages = value;
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
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="Package" type="{http://dpdservices.dpd.com.pl/}packageDSPV2" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "_package"
    })
    public static class Packages {

        @XmlElement(name = "Package")
        protected List<PackageDSPV2> _package;

        /**
         * Gets the value of the package property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the package property.
         * 
         * <p>
         * For runExamples, to add a new item, do as follows:
         * <pre>
         *    getPackage().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link PackageDSPV2 }
         * 
         * 
         */
        public List<PackageDSPV2> getPackage() {
            if (_package == null) {
                _package = new ArrayList<PackageDSPV2>();
            }
            return this._package;
        }

    }

}
