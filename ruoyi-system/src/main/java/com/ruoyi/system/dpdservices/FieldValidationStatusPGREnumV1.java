
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for fieldValidationStatusPGREnumV1.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="fieldValidationStatusPGREnumV1"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="OK"/&gt;
 *     &lt;enumeration value="UNKNOWN_ERROR"/&gt;
 *     &lt;enumeration value="DB_ERROR"/&gt;
 *     &lt;enumeration value="DONT_MATCH_DICTIONARY"/&gt;
 *     &lt;enumeration value="DONT_MATCH_PATTERN"/&gt;
 *     &lt;enumeration value="VALUE_EMPTY"/&gt;
 *     &lt;enumeration value="VALUE_ZERO"/&gt;
 *     &lt;enumeration value="VALUE_OUT_OF_RANGE"/&gt;
 *     &lt;enumeration value="VALUE_INCORRECT"/&gt;
 *     &lt;enumeration value="UNKNOWN_RDB_ERROR"/&gt;
 *     &lt;enumeration value="DUPLICATED_KEY"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "fieldValidationStatusPGREnumV1")
@XmlEnum
public enum FieldValidationStatusPGREnumV1 {

    OK,
    UNKNOWN_ERROR,
    DB_ERROR,
    DONT_MATCH_DICTIONARY,
    DONT_MATCH_PATTERN,
    VALUE_EMPTY,
    VALUE_ZERO,
    VALUE_OUT_OF_RANGE,
    VALUE_INCORRECT,
    UNKNOWN_RDB_ERROR,
    DUPLICATED_KEY;

    public String value() {
        return name();
    }

    public static FieldValidationStatusPGREnumV1 fromValue(String v) {
        return valueOf(v);
    }

}
