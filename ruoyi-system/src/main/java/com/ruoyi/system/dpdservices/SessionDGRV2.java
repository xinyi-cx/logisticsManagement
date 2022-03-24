
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for sessionDGRV2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sessionDGRV2"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Packages" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Package" type="{http://dpdservices.dpd.com.pl/}packageDGRV2" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="SessionId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="StatusInfo" type="{http://dpdservices.dpd.com.pl/}statusInfoDGRV2" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sessionDGRV2", propOrder = {
    "packages",
    "sessionId",
    "statusInfo"
})
public class SessionDGRV2 {

    @XmlElement(name = "Packages")
    protected SessionDGRV2 .Packages packages;
    @XmlElement(name = "SessionId")
    protected Long sessionId;
    @XmlElement(name = "StatusInfo")
    protected StatusInfoDGRV2 statusInfo;

    /**
     * Gets the value of the packages property.
     *
     * @return
     *     possible object is
     *     {@link SessionDGRV2 .Packages }
     *
     */
    public SessionDGRV2 .Packages getPackages() {
        return packages;
    }

    /**
     * Sets the value of the packages property.
     *
     * @param value
     *     allowed object is
     *     {@link SessionDGRV2 .Packages }
     *
     */
    public void setPackages(SessionDGRV2 .Packages value) {
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
     * Gets the value of the statusInfo property.
     * 
     * @return
     *     possible object is
     *     {@link StatusInfoDGRV2 }
     *     
     */
    public StatusInfoDGRV2 getStatusInfo() {
        return statusInfo;
    }

    /**
     * Sets the value of the statusInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusInfoDGRV2 }
     *     
     */
    public void setStatusInfo(StatusInfoDGRV2 value) {
        this.statusInfo = value;
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
     *         &lt;element name="Package" type="{http://dpdservices.dpd.com.pl/}packageDGRV2" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        protected List<PackageDGRV2> _package;

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
         * {@link PackageDGRV2 }
         * 
         * 
         */
        public List<PackageDGRV2> getPackage() {
            if (_package == null) {
                _package = new ArrayList<PackageDGRV2>();
            }
            return this._package;
        }

    }

}
