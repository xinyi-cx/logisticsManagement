
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for pickupCallOperationTypeDPPEnumV1.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="pickupCallOperationTypeDPPEnumV1"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="INSERT"/&gt;
 *     &lt;enumeration value="UPDATE"/&gt;
 *     &lt;enumeration value="CANCEL"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "pickupCallOperationTypeDPPEnumV1")
@XmlEnum
public enum PickupCallOperationTypeDPPEnumV1 {

    INSERT,
    UPDATE,
    CANCEL;

    public String value() {
        return name();
    }

    public static PickupCallOperationTypeDPPEnumV1 fromValue(String v) {
        return valueOf(v);
    }

}
