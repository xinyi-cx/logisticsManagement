
package com.ruoyi.system.DPDinfo.pl.com.dpd.dpdinfoservices.events;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getEventsForWaybillV1 complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="getEventsForWaybillV1">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="waybill" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eventsSelectType" type="{http://events.dpdinfoservices.dpd.com.pl/}eventsSelectTypeEnum" minOccurs="0"/>
 *         &lt;element name="language" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="authDataV1" type="{http://events.dpdinfoservices.dpd.com.pl/}authDataV1" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getEventsForWaybillV1", propOrder = {
    "waybill",
    "eventsSelectType",
    "language",
    "authDataV1"
})
public class GetEventsForWaybillV1 {

    protected String waybill;
    @XmlSchemaType(name = "string")
    protected EventsSelectTypeEnum eventsSelectType;
    protected String language;
    protected AuthDataV1 authDataV1;

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

    /**
     * ��ȡeventsSelectType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link EventsSelectTypeEnum }
     *     
     */
    public EventsSelectTypeEnum getEventsSelectType() {
        return eventsSelectType;
    }

    /**
     * ����eventsSelectType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link EventsSelectTypeEnum }
     *     
     */
    public void setEventsSelectType(EventsSelectTypeEnum value) {
        this.eventsSelectType = value;
    }

    /**
     * ��ȡlanguage���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguage() {
        return language;
    }

    /**
     * ����language���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguage(String value) {
        this.language = value;
    }

    /**
     * ��ȡauthDataV1���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link AuthDataV1 }
     *     
     */
    public AuthDataV1 getAuthDataV1() {
        return authDataV1;
    }

    /**
     * ����authDataV1���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link AuthDataV1 }
     *     
     */
    public void setAuthDataV1(AuthDataV1 value) {
        this.authDataV1 = value;
    }

}
