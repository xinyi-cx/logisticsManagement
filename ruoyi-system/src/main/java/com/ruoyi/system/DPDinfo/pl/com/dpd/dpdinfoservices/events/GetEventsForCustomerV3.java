
package com.ruoyi.system.DPDinfo.pl.com.dpd.dpdinfoservices.events;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getEventsForCustomerV3 complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="getEventsForCustomerV3">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="limit" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
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
@XmlType(name = "getEventsForCustomerV3", propOrder = {
    "limit",
    "authDataV1"
})
public class GetEventsForCustomerV3 {

    protected Integer limit;
    protected AuthDataV1 authDataV1;

    /**
     * ��ȡlimit���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * ����limit���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLimit(Integer value) {
        this.limit = value;
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
