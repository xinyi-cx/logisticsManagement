
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for pickupCallUpdateModeDPPEnumV1.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="pickupCallUpdateModeDPPEnumV1"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="DONT_CREATE_NEW_IF_CLOSED"/&gt;
 *     &lt;enumeration value="CREATE_NEW_IF_CLOSED"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "pickupCallUpdateModeDPPEnumV1")
@XmlEnum
public enum PickupCallUpdateModeDPPEnumV1 {

    DONT_CREATE_NEW_IF_CLOSED,
    CREATE_NEW_IF_CLOSED;

    public String value() {
        return name();
    }

    public static PickupCallUpdateModeDPPEnumV1 fromValue(String v) {
        return valueOf(v);
    }

}
