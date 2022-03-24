
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for policyDSPEnumV2.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="policyDSPEnumV2"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="STOP_ON_FIRST_ERROR"/&gt;
 *     &lt;enumeration value="IGNORE_ERRORS"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "policyDSPEnumV2")
@XmlEnum
public enum PolicyDSPEnumV2 {

    STOP_ON_FIRST_ERROR,
    IGNORE_ERRORS;

    public String value() {
        return name();
    }

    public static PolicyDSPEnumV2 fromValue(String v) {
        return valueOf(v);
    }

}
