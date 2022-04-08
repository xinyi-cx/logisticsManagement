
package com.ruoyi.system.DPDinfo.pl.com.dpd.dpdinfoservices.events;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>eventsSelectTypeEnum�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <p>
 * <pre>
 * &lt;simpleType name="eventsSelectTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ALL"/>
 *     &lt;enumeration value="ONLY_LAST"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "eventsSelectTypeEnum")
@XmlEnum
public enum EventsSelectTypeEnum {

    ALL,
    ONLY_LAST;

    public String value() {
        return name();
    }

    public static EventsSelectTypeEnum fromValue(String v) {
        return valueOf(v);
    }

}
