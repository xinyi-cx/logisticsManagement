
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for generateSpedLabelsV3 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="generateSpedLabelsV3"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dpdServicesParamsV1" type="{http://dpdservices.dpd.com.pl/}dpdServicesParamsV1" minOccurs="0"/&gt;
 *         &lt;element name="outputDocFormatV1" type="{http://dpdservices.dpd.com.pl/}outputDocFormatDSPEnumV1" minOccurs="0"/&gt;
 *         &lt;element name="outputDocPageFormatV1" type="{http://dpdservices.dpd.com.pl/}outputDocPageFormatDSPEnumV1" minOccurs="0"/&gt;
 *         &lt;element name="outputLabelType" type="{http://dpdservices.dpd.com.pl/}outputLabelTypeEnumV1" minOccurs="0"/&gt;
 *         &lt;element name="labelVariant" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
@XmlType(name = "generateSpedLabelsV3", propOrder = {
    "dpdServicesParamsV1",
    "outputDocFormatV1",
    "outputDocPageFormatV1",
    "outputLabelType",
    "labelVariant",
    "authDataV1"
})
public class GenerateSpedLabelsV3 {

    protected DpdServicesParamsV1 dpdServicesParamsV1;
    @XmlSchemaType(name = "string")
    protected OutputDocFormatDSPEnumV1 outputDocFormatV1;
    @XmlSchemaType(name = "string")
    protected OutputDocPageFormatDSPEnumV1 outputDocPageFormatV1;
    @XmlSchemaType(name = "string")
    protected OutputLabelTypeEnumV1 outputLabelType;
    protected String labelVariant;
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
     * Gets the value of the outputLabelType property.
     * 
     * @return
     *     possible object is
     *     {@link OutputLabelTypeEnumV1 }
     *     
     */
    public OutputLabelTypeEnumV1 getOutputLabelType() {
        return outputLabelType;
    }

    /**
     * Sets the value of the outputLabelType property.
     * 
     * @param value
     *     allowed object is
     *     {@link OutputLabelTypeEnumV1 }
     *     
     */
    public void setOutputLabelType(OutputLabelTypeEnumV1 value) {
        this.outputLabelType = value;
    }

    /**
     * Gets the value of the labelVariant property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabelVariant() {
        return labelVariant;
    }

    /**
     * Sets the value of the labelVariant property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabelVariant(String value) {
        this.labelVariant = value;
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
