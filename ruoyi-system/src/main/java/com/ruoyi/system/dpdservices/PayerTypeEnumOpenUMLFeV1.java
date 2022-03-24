
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for payerTypeEnumOpenUMLFeV1.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="payerTypeEnumOpenUMLFeV1"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="RECEIVER"/&gt;
 *     &lt;enumeration value="SENDER"/&gt;
 *     &lt;enumeration value="THIRD_PARTY"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "payerTypeEnumOpenUMLFeV1")
@XmlEnum
public enum PayerTypeEnumOpenUMLFeV1 {

    RECEIVER,
    SENDER,
    THIRD_PARTY;

    public String value() {
        return name();
    }

    public static PayerTypeEnumOpenUMLFeV1 fromValue(String v) {
        return valueOf(v);
    }

}
