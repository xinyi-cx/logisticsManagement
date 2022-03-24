
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for generateSpedLabelsV2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="generateSpedLabelsV2"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dpdServicesParamsV1" type="{http://dpdservices.dpd.com.pl/}dpdServicesParamsV1" minOccurs="0"/&gt;
 *         &lt;element name="outputDocFormatV1" type="{http://dpdservices.dpd.com.pl/}outputDocFormatDSPEnumV1" minOccurs="0"/&gt;
 *         &lt;element name="outputDocPageFormatV1" type="{http://dpdservices.dpd.com.pl/}outputDocPageFormatDSPEnumV1" minOccurs="0"/&gt;
 *         &lt;element name="outputLabelTypeV2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="authDataV1" type="{http://dpdservices.dpd.com.pl/}authDataV1" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "generateSpedLabelsV2", propOrder = {
    "dpdServicesParamsV1",
    "outputDocFormatV1",
    "outputDocPageFormatV1",
    "outputLabelTypeV2",
    "authDataV1"
})
public class GenerateSpedLabelsV2 {

    protected DpdServicesParamsV1 dpdServicesParamsV1;
    @XmlSchemaType(name = "string")
    protected OutputDocFormatDSPEnumV1 outputDocFormatV1;
    @XmlSchemaType(name = "string")
    protected OutputDocPageFormatDSPEnumV1 outputDocPageFormatV1;
    protected String outputLabelTypeV2;
    protected AuthDataV1 authDataV1;

    /**
     * Gets the value of the dpdServicesParamsV1 property.
     * 
     * @return
     *     possible object is
     *     {@link DpdServicesParamsV1 }
     *     
     */
    public DpdServicesParamsV1 getDpdServicesParamsV1() {
        return dpdServicesParamsV1;
    }

    /**
     * Sets the value of the dpdServicesParamsV1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link DpdServicesParamsV1 }
     *     
     */
    public void setDpdServicesParamsV1(DpdServicesParamsV1 value) {
        this.dpdServicesParamsV1 = value;
    }

    /**
     * Gets the value of the outputDocFormatV1 property.
     * 
     * @return
     *     possible object is
     *     {@link OutputDocFormatDSPEnumV1 }
     *     
     */
    public OutputDocFormatDSPEnumV1 getOutputDocFormatV1() {
        return outputDocFormatV1;
    }

    /**
     * Sets the value of the outputDocFormatV1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link OutputDocFormatDSPEnumV1 }
     *     
     */
    public void setOutputDocFormatV1(OutputDocFormatDSPEnumV1 value) {
        this.outputDocFormatV1 = value;
    }

    /**
     * Gets the value of the outputDocPageFormatV1 property.
     * 
     * @return
     *     possible object is
     *     {@link OutputDocPageFormatDSPEnumV1 }
     *     
     */
    public OutputDocPageFormatDSPEnumV1 getOutputDocPageFormatV1() {
        return outputDocPageFormatV1;
    }

    /**
     * Sets the value of the outputDocPageFormatV1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link OutputDocPageFormatDSPEnumV1 }
     *     
     */
    public void setOutputDocPageFormatV1(OutputDocPageFormatDSPEnumV1 value) {
        this.outputDocPageFormatV1 = value;
    }

    /**
     * Gets the value of the outputLabelTypeV2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutputLabelTypeV2() {
        return outputLabelTypeV2;
    }

    /**
     * Sets the value of the outputLabelTypeV2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutputLabelTypeV2(String value) {
        this.outputLabelTypeV2 = value;
    }

    /**
     * Gets the value of the authDataV1 property.
     * 
     * @return
     *     possible object is
     *     {@link AuthDataV1 }
     *     
     */
    public AuthDataV1 getAuthDataV1() {
        return authDataV1;
    }

    /**
     * Sets the value of the authDataV1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthDataV1 }
     *     
     */
    public void setAuthDataV1(AuthDataV1 value) {
        this.authDataV1 = value;
    }

}
