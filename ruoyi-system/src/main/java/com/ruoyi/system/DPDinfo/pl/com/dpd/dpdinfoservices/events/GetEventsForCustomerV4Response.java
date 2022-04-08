
package com.ruoyi.system.DPDinfo.pl.com.dpd.dpdinfoservices.events;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getEventsForCustomerV4Response complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="getEventsForCustomerV4Response">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://events.dpdinfoservices.dpd.com.pl/}customerEventsResponseV2" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getEventsForCustomerV4Response", propOrder = {
    "_return"
})
public class GetEventsForCustomerV4Response {

    @XmlElement(name = "return")
    protected CustomerEventsResponseV2 _return;

    /**
     * ��ȡreturn���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link CustomerEventsResponseV2 }
     *     
     */
    public CustomerEventsResponseV2 getReturn() {
        return _return;
    }

    /**
     * ����return���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerEventsResponseV2 }
     *     
     */
    public void setReturn(CustomerEventsResponseV2 value) {
        this._return = value;
    }

}
