
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for outputDocFormatDSPEnumV1.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="outputDocFormatDSPEnumV1"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="PDF"/&gt;
 *     &lt;enumeration value="TIFF"/&gt;
 *     &lt;enumeration value="PS"/&gt;
 *     &lt;enumeration value="EPL"/&gt;
 *     &lt;enumeration value="ZPL"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "outputDocFormatDSPEnumV1")
@XmlEnum
public enum OutputDocFormatDSPEnumV1 {

    PDF,
    TIFF,
    PS,
    EPL,
    ZPL;

    public String value() {
        return name();
    }

    public static OutputDocFormatDSPEnumV1 fromValue(String v) {
        return valueOf(v);
    }

}
