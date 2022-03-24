
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for documentGenerationResponseV2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="documentGenerationResponseV2"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DestinationDataList" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="DestinationsData" type="{http://dpdservices.dpd.com.pl/}destinationsData" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DocumentData" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="nonMatchingDataList" type="{http://dpdservices.dpd.com.pl/}nonMatchingData" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Session" type="{http://dpdservices.dpd.com.pl/}sessionDGRV2" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "documentGenerationResponseV2", propOrder = {
    "destinationDataList",
    "documentData",
    "nonMatchingDataList",
    "session"
})
public class DocumentGenerationResponseV2 {

    @XmlElement(name = "DestinationDataList")
    protected DocumentGenerationResponseV2 .DestinationDataList destinationDataList;
    @XmlElement(name = "DocumentData")
    protected byte[] documentData;
    @XmlElement(nillable = true)
    protected List<NonMatchingData> nonMatchingDataList;
    @XmlElement(name = "Session")
    protected SessionDGRV2 session;

    /**
     * Gets the value of the destinationDataList property.
     *
     * @return
     *     possible object is
     *     {@link DocumentGenerationResponseV2 .DestinationDataList }
     *
     */
    public DocumentGenerationResponseV2 .DestinationDataList getDestinationDataList() {
        return destinationDataList;
    }

    /**
     * Sets the value of the destinationDataList property.
     *
     * @param value
     *     allowed object is
     *     {@link DocumentGenerationResponseV2 .DestinationDataList }
     *
     */
    public void setDestinationDataList(DocumentGenerationResponseV2 .DestinationDataList value) {
        this.destinationDataList = value;
    }

    /**
     * Gets the value of the documentData property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getDocumentData() {
        return documentData;
    }

    /**
     * Sets the value of the documentData property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setDocumentData(byte[] value) {
        this.documentData = value;
    }

    /**
     * Gets the value of the nonMatchingDataList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nonMatchingDataList property.
     * 
     * <p>
     * For runExamples, to add a new item, do as follows:
     * <pre>
     *    getNonMatchingDataList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NonMatchingData }
     * 
     * 
     */
    public List<NonMatchingData> getNonMatchingDataList() {
        if (nonMatchingDataList == null) {
            nonMatchingDataList = new ArrayList<NonMatchingData>();
        }
        return this.nonMatchingDataList;
    }

    /**
     * Gets the value of the session property.
     * 
     * @return
     *     possible object is
     *     {@link SessionDGRV2 }
     *     
     */
    public SessionDGRV2 getSession() {
        return session;
    }

    /**
     * Sets the value of the session property.
     * 
     * @param value
     *     allowed object is
     *     {@link SessionDGRV2 }
     *     
     */
    public void setSession(SessionDGRV2 value) {
        this.session = value;
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
     *         &lt;element name="DestinationsData" type="{http://dpdservices.dpd.com.pl/}destinationsData" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "destinationsData"
    })
    public static class DestinationDataList {

        @XmlElement(name = "DestinationsData")
        protected List<DestinationsData> destinationsData;

        /**
         * Gets the value of the destinationsData property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the destinationsData property.
         * 
         * <p>
         * For runExamples, to add a new item, do as follows:
         * <pre>
         *    getDestinationsData().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DestinationsData }
         * 
         * 
         */
        public List<DestinationsData> getDestinationsData() {
            if (destinationsData == null) {
                destinationsData = new ArrayList<DestinationsData>();
            }
            return this.destinationsData;
        }

    }

}
