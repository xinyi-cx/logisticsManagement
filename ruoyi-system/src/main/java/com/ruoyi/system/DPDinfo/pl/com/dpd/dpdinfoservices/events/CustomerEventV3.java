
package com.ruoyi.system.DPDinfo.pl.com.dpd.dpdinfoservices.events;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>customerEventV3 complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="customerEventV3">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="businessCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="depot" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="depotName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eventDataList" type="{http://events.dpdinfoservices.dpd.com.pl/}customerEventDataV3" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="eventTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="objectId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="packageReference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="parcelReference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="waybill" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerEventV3", propOrder = {
    "businessCode",
    "country",
    "depot",
    "depotName",
    "description",
    "eventDataList",
    "eventTime",
    "objectId",
    "packageReference",
    "parcelReference",
    "waybill"
})
public class CustomerEventV3 {

    protected String businessCode;
    protected String country;
    protected String depot;
    protected String depotName;
    protected String description;
    @XmlElement(nillable = true)
    protected List<CustomerEventDataV3> eventDataList;
    protected String eventTime;
    protected long objectId;
    protected String packageReference;
    protected String parcelReference;
    protected String waybill;

    /**
     * ��ȡbusinessCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessCode() {
        return businessCode;
    }

    /**
     * ����businessCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessCode(String value) {
        this.businessCode = value;
    }

    /**
     * ��ȡcountry���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountry() {
        return country;
    }

    /**
     * ����country���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountry(String value) {
        this.country = value;
    }

    /**
     * ��ȡdepot���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepot() {
        return depot;
    }

    /**
     * ����depot���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepot(String value) {
        this.depot = value;
    }

    /**
     * ��ȡdepotName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepotName() {
        return depotName;
    }

    /**
     * ����depotName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepotName(String value) {
        this.depotName = value;
    }

    /**
     * ��ȡdescription���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * ����description���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the eventDataList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eventDataList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEventDataList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustomerEventDataV3 }
     * 
     * 
     */
    public List<CustomerEventDataV3> getEventDataList() {
        if (eventDataList == null) {
            eventDataList = new ArrayList<CustomerEventDataV3>();
        }
        return this.eventDataList;
    }

    /**
     * ��ȡeventTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventTime() {
        return eventTime;
    }

    /**
     * ����eventTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventTime(String value) {
        this.eventTime = value;
    }

    /**
     * ��ȡobjectId���Ե�ֵ��
     * 
     */
    public long getObjectId() {
        return objectId;
    }

    /**
     * ����objectId���Ե�ֵ��
     * 
     */
    public void setObjectId(long value) {
        this.objectId = value;
    }

    /**
     * ��ȡpackageReference���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackageReference() {
        return packageReference;
    }

    /**
     * ����packageReference���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackageReference(String value) {
        this.packageReference = value;
    }

    /**
     * ��ȡparcelReference���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParcelReference() {
        return parcelReference;
    }

    /**
     * ����parcelReference���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParcelReference(String value) {
        this.parcelReference = value;
    }

    /**
     * ��ȡwaybill���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWaybill() {
        return waybill;
    }

    /**
     * ����waybill���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWaybill(String value) {
        this.waybill = value;
    }

}
