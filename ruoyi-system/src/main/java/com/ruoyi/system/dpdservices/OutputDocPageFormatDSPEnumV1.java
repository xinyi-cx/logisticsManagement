
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for outputDocPageFormatDSPEnumV1.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="outputDocPageFormatDSPEnumV1"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="A4"/&gt;
 *     &lt;enumeration value="LBL_PRINTER"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "outputDocPageFormatDSPEnumV1")
@XmlEnum
public enum OutputDocPageFormatDSPEnumV1 {

    @XmlEnumValue("A4")
    A_4("A4"),
    LBL_PRINTER("LBL_PRINTER");
    private final String value;

    OutputDocPageFormatDSPEnumV1(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static OutputDocPageFormatDSPEnumV1 fromValue(String v) {
        for (OutputDocPageFormatDSPEnumV1 c: OutputDocPageFormatDSPEnumV1 .values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
