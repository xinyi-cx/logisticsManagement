
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for importDeliveryBusinessEventStatusEnumV1.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="importDeliveryBusinessEventStatusEnumV1"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="OK"/&gt;
 *     &lt;enumeration value="UNKNOWN_ERROR"/&gt;
 *     &lt;enumeration value="PARCEL_NOT_FOUND"/&gt;
 *     &lt;enumeration value="PARCEL_PERMISSION_DENIED"/&gt;
 *     &lt;enumeration value="EVENT_PERMISSION_DENIED"/&gt;
 *     &lt;enumeration value="INCORRECT_DATA"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "importDeliveryBusinessEventStatusEnumV1")
@XmlEnum
public enum ImportDeliveryBusinessEventStatusEnumV1 {

    OK,
    UNKNOWN_ERROR,
    PARCEL_NOT_FOUND,
    PARCEL_PERMISSION_DENIED,
    EVENT_PERMISSION_DENIED,
    INCORRECT_DATA;

    public String value() {
        return name();
    }

    public static ImportDeliveryBusinessEventStatusEnumV1 fromValue(String v) {
        return valueOf(v);
    }

}
