
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for statusPCREnumV1.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="statusPCREnumV1"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="OK"/&gt;
 *     &lt;enumeration value="NOT_FOUND"/&gt;
 *     &lt;enumeration value="NOT_PROCESSED"/&gt;
 *     &lt;enumeration value="INCORRECT_PICKUP_ADDRESS_FID"/&gt;
 *     &lt;enumeration value="INCORRECT_PICKUP_ADDRESS_NAME"/&gt;
 *     &lt;enumeration value="INCORRECT_PICKUP_ADDRESS_COMPANY"/&gt;
 *     &lt;enumeration value="INCORRECT_PICKUP_ADDRESS_ADDRESS"/&gt;
 *     &lt;enumeration value="INCORRECT_PICKUP_ADDRESS_CITY"/&gt;
 *     &lt;enumeration value="INCORRECT_PICKUP_ADDRESS_COUNTRY"/&gt;
 *     &lt;enumeration value="INCORRECT_PICKUP_ADDRESS_POSTAL_CODE"/&gt;
 *     &lt;enumeration value="INCORRECT_PICKUP_ADDRESS_EMAIL"/&gt;
 *     &lt;enumeration value="INCORRECT_PICKUP_ADDRESS_PHONE"/&gt;
 *     &lt;enumeration value="ACCESS_DENIED_FOR_FID"/&gt;
 *     &lt;enumeration value="UNKNOWN_ERROR"/&gt;
 *     &lt;enumeration value="DB_ERROR"/&gt;
 *     &lt;enumeration value="RANGE_NOT_AVAILABLE_TEMPORARILY"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "statusPCREnumV1")
@XmlEnum
public enum StatusPCREnumV1 {

    OK,
    NOT_FOUND,
    NOT_PROCESSED,
    INCORRECT_PICKUP_ADDRESS_FID,
    INCORRECT_PICKUP_ADDRESS_NAME,
    INCORRECT_PICKUP_ADDRESS_COMPANY,
    INCORRECT_PICKUP_ADDRESS_ADDRESS,
    INCORRECT_PICKUP_ADDRESS_CITY,
    INCORRECT_PICKUP_ADDRESS_COUNTRY,
    INCORRECT_PICKUP_ADDRESS_POSTAL_CODE,
    INCORRECT_PICKUP_ADDRESS_EMAIL,
    INCORRECT_PICKUP_ADDRESS_PHONE,
    ACCESS_DENIED_FOR_FID,
    UNKNOWN_ERROR,
    DB_ERROR,
    RANGE_NOT_AVAILABLE_TEMPORARILY;

    public String value() {
        return name();
    }

    public static StatusPCREnumV1 fromValue(String v) {
        return valueOf(v);
    }

}
