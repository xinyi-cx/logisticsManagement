
package com.ruoyi.system.dpdservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for generatePackagesNumbersV3 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="generatePackagesNumbersV3"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="openUMLFeV2" type="{http://dpdservices.dpd.com.pl/}openUMLFeV2" minOccurs="0"/&gt;
 *         &lt;element name="pkgNumsGenerationPolicyV1" type="{http://dpdservices.dpd.com.pl/}pkgNumsGenerationPolicyV1" minOccurs="0"/&gt;
 *         &lt;element name="langCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
@XmlType(name = "generatePackagesNumbersV3", propOrder = {
    "openUMLFeV2",
    "pkgNumsGenerationPolicyV1",
    "langCode",
    "authDataV1"
})
public class GeneratePackagesNumbersV3 {

    protected OpenUMLFeV2 openUMLFeV2;
    @XmlSchemaType(name = "string")
    protected PkgNumsGenerationPolicyV1 pkgNumsGenerationPolicyV1;
    protected String langCode;
    protected AuthDataV1 authDataV1;

    /**
     * Gets the value of the openUMLFeV2 property.
     * 
     * @return
     *     possible object is
     *     {@link OpenUMLFeV2 }
     *     
     */
    public OpenUMLFeV2 getOpenUMLFeV2() {
        return openUMLFeV2;
    }

    /**
     * Sets the value of the openUMLFeV2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link OpenUMLFeV2 }
     *     
     */
    public void setOpenUMLFeV2(OpenUMLFeV2 value) {
        this.openUMLFeV2 = value;
    }

    /**
     * Gets the value of the pkgNumsGenerationPolicyV1 property.
     * 
     * @return
     *     possible object is
     *     {@link PkgNumsGenerationPolicyV1 }
     *     
     */
    public PkgNumsGenerationPolicyV1 getPkgNumsGenerationPolicyV1() {
        return pkgNumsGenerationPolicyV1;
    }

    /**
     * Sets the value of the pkgNumsGenerationPolicyV1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link PkgNumsGenerationPolicyV1 }
     *     
     */
    public void setPkgNumsGenerationPolicyV1(PkgNumsGenerationPolicyV1 value) {
        this.pkgNumsGenerationPolicyV1 = value;
    }

    /**
     * Gets the value of the langCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLangCode() {
        return langCode;
    }

    /**
     * Sets the value of the langCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLangCode(String value) {
        this.langCode = value;
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
