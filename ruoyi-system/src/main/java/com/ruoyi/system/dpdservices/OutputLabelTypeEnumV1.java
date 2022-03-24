
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for outputLabelTypeEnumV1.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="outputLabelTypeEnumV1"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="BIC3"/&gt;
 *     &lt;enumeration value="EXTENDED"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "outputLabelTypeEnumV1")
@XmlEnum
public enum OutputLabelTypeEnumV1 {

    @XmlEnumValue("BIC3")
    BIC_3("BIC3"),
    EXTENDED("EXTENDED");
    private final String value;

    OutputLabelTypeEnumV1(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static OutputLabelTypeEnumV1 fromValue(String v) {
        for (OutputLabelTypeEnumV1 c: OutputLabelTypeEnumV1 .values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
