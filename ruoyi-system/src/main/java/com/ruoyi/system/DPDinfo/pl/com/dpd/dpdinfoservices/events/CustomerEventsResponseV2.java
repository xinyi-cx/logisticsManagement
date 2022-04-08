
package com.ruoyi.system.DPDinfo.pl.com.dpd.dpdinfoservices.events;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>customerEventsResponseV2 complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="customerEventsResponseV2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="confirmId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eventsList" type="{http://events.dpdinfoservices.dpd.com.pl/}customerEventV2" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerEventsResponseV2", propOrder = {
    "confirmId",
    "eventsList"
})
public class CustomerEventsResponseV2 {

    protected String confirmId;
    @XmlElement(nillable = true)
    protected List<CustomerEventV2> eventsList;

    /**
     * ��ȡconfirmId���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConfirmId() {
        return confirmId;
    }

    /**
     * ����confirmId���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConfirmId(String value) {
        this.confirmId = value;
    }

    /**
     * Gets the value of the eventsList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eventsList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEventsList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustomerEventV2 }
     * 
     * 
     */
    public List<CustomerEventV2> getEventsList() {
        if (eventsList == null) {
            eventsList = new ArrayList<CustomerEventV2>();
        }
        return this.eventsList;
    }

}
