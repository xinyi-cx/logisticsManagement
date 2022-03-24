
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for validationStatusPGREnumV1.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="validationStatusPGREnumV1"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="OK"/&gt;
 *     &lt;enumeration value="UNKNOWN_ERROR"/&gt;
 *     &lt;enumeration value="DB_ERROR"/&gt;
 *     &lt;enumeration value="INCORRECT_DATA"/&gt;
 *     &lt;enumeration value="NOT_PROCESSED"/&gt;
 *     &lt;enumeration value="DUPLICATED_PACKAGE_SEARCH_KEY"/&gt;
 *     &lt;enumeration value="DUPLICATED_PARCEL_SEARCH_KEY"/&gt;
 *     &lt;enumeration value="DISALLOWED_FID"/&gt;
 *     &lt;enumeration value="DUPLICATED_WAYBILL"/&gt;
 *     &lt;enumeration value="UNSUPPORTED_LANG_CODE"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "validationStatusPGREnumV1")
@XmlEnum
public enum ValidationStatusPGREnumV1 {

    OK,
    UNKNOWN_ERROR,
    DB_ERROR,
    INCORRECT_DATA,
    NOT_PROCESSED,
    DUPLICATED_PACKAGE_SEARCH_KEY,
    DUPLICATED_PARCEL_SEARCH_KEY,
    DISALLOWED_FID,
    DUPLICATED_WAYBILL,
    UNSUPPORTED_LANG_CODE;

    public String value() {
        return name();
    }

    public static ValidationStatusPGREnumV1 fromValue(String v) {
        return valueOf(v);
    }

}
