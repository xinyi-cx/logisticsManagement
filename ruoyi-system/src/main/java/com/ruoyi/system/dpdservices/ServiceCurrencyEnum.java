
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for serviceCurrencyEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="serviceCurrencyEnum"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="PLN"/&gt;
 *     &lt;enumeration value="EUR"/&gt;
 *     &lt;enumeration value="USD"/&gt;
 *     &lt;enumeration value="CHF"/&gt;
 *     &lt;enumeration value="SEK"/&gt;
 *     &lt;enumeration value="NOK"/&gt;
 *     &lt;enumeration value="CZK"/&gt;
 *     &lt;enumeration value="RON"/&gt;
 *     &lt;enumeration value="HUF"/&gt;
 *     &lt;enumeration value="HRK"/&gt;
 *     &lt;enumeration value="BGN"/&gt;
 *     &lt;enumeration value="DKK"/&gt;
 *     &lt;enumeration value="GBP"/&gt;
 *     &lt;enumeration value="RSD"/&gt;
 *     &lt;enumeration value="RUB"/&gt;
 *     &lt;enumeration value="TRY"/&gt;
 *     &lt;enumeration value="UNKNOWN"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "serviceCurrencyEnum")
@XmlEnum
public enum ServiceCurrencyEnum {

    PLN,
    EUR,
    USD,
    CHF,
    SEK,
    NOK,
    CZK,
    RON,
    HUF,
    HRK,
    BGN,
    DKK,
    GBP,
    RSD,
    RUB,
    TRY,
    UNKNOWN;

    public String value() {
        return name();
    }

    public static ServiceCurrencyEnum fromValue(String v) {
        return valueOf(v);
    }

}
