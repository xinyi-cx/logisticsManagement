
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for serviceGuaranteeTypeEnumOpenUMLFeV1.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="serviceGuaranteeTypeEnumOpenUMLFeV1"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="TIME0930"/&gt;
 *     &lt;enumeration value="TIME1200"/&gt;
 *     &lt;enumeration value="B2C"/&gt;
 *     &lt;enumeration value="TIMEFIXED"/&gt;
 *     &lt;enumeration value="SATURDAY"/&gt;
 *     &lt;enumeration value="INTER"/&gt;
 *     &lt;enumeration value="DPDNEXTDAY"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "serviceGuaranteeTypeEnumOpenUMLFeV1")
@XmlEnum
public enum ServiceGuaranteeTypeEnumOpenUMLFeV1 {

    @XmlEnumValue("TIME0930")
    TIME_0930("TIME0930"),
    @XmlEnumValue("TIME1200")
    TIME_1200("TIME1200"),
    @XmlEnumValue("B2C")
    B_2_C("B2C"),
    TIMEFIXED("TIMEFIXED"),
    SATURDAY("SATURDAY"),
    INTER("INTER"),
    DPDNEXTDAY("DPDNEXTDAY");
    private final String value;

    ServiceGuaranteeTypeEnumOpenUMLFeV1(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ServiceGuaranteeTypeEnumOpenUMLFeV1 fromValue(String v) {
        for (ServiceGuaranteeTypeEnumOpenUMLFeV1 c: ServiceGuaranteeTypeEnumOpenUMLFeV1 .values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
