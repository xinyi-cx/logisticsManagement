
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for serviceSelfColReceiverTypeEnumOpenUMLFeV1.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="serviceSelfColReceiverTypeEnumOpenUMLFeV1"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="PRIV"/&gt;
 *     &lt;enumeration value="COMP"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "serviceSelfColReceiverTypeEnumOpenUMLFeV1")
@XmlEnum
public enum ServiceSelfColReceiverTypeEnumOpenUMLFeV1 {

    PRIV,
    COMP;

    public String value() {
        return name();
    }

    public static ServiceSelfColReceiverTypeEnumOpenUMLFeV1 fromValue(String v) {
        return valueOf(v);
    }

}
