
package com.ruoyi.system.DPDinfo.pl.com.dpd.dpdinfoservices.events;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>customerEventV1 complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="customerEventV1">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="businessCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="depot" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eventData1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eventData2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eventData3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eventTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="objectId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="operationType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "customerEventV1", propOrder = {
    "businessCode",
    "country",
    "depot",
    "description",
    "eventData1",
    "eventData2",
    "eventData3",
    "eventTime",
    "id",
    "objectId",
    "operationType",
    "packageReference",
    "parcelReference",
    "waybill"
})
public class CustomerEventV1 {

    protected String businessCode;
    protected String country;
    protected String depot;
    protected String description;
    protected String eventData1;
    protected String eventData2;
    protected String eventData3;
    protected String eventTime;
    protected long id;
    protected long objectId;
    protected String operationType;
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
     * ��ȡeventData1���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventData1() {
        return eventData1;
    }

    /**
     * ����eventData1���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventData1(String value) {
        this.eventData1 = value;
    }

    /**
     * ��ȡeventData2���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventData2() {
        return eventData2;
    }

    /**
     * ����eventData2���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventData2(String value) {
        this.eventData2 = value;
    }

    /**
     * ��ȡeventData3���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventData3() {
        return eventData3;
    }

    /**
     * ����eventData3���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventData3(String value) {
        this.eventData3 = value;
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
     * ��ȡid���Ե�ֵ��
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * ����id���Ե�ֵ��
     * 
     */
    public void setId(long value) {
        this.id = value;
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
     * ��ȡoperationType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperationType() {
        return operationType;
    }

    /**
     * ����operationType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperationType(String value) {
        this.operationType = value;
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
