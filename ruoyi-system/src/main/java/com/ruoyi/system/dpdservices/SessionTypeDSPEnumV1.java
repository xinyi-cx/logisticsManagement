
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sessionTypeDSPEnumV1.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="sessionTypeDSPEnumV1"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="DOMESTIC"/&gt;
 *     &lt;enumeration value="INTERNATIONAL"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "sessionTypeDSPEnumV1")
@XmlEnum
public enum SessionTypeDSPEnumV1 {

    DOMESTIC,
    INTERNATIONAL;

    public String value() {
        return name();
    }

    public static SessionTypeDSPEnumV1 fromValue(String v) {
        return valueOf(v);
    }

}
