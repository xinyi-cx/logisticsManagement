package com.ruoyi.system.domain.vo;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 面单对象 package
 * 
 * @author ruoyi
 * @date 2022-01-02
 */
public class PackageImportVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 货物金额 */
    @Excel(name = "COD Amount")
    private BigDecimal pln;

    /** 分类1 */
    @Excel(name = "Category")
    private String ref1;

    /** 分类2 */
    @Excel(name = "Category_2")
    private String ref2;

    /** 收件人地址 */
    @Excel(name = "Street")
    private String receiverAddress;

    /** 收件人城市 */
    @Excel(name = "City")
    private String receiverCity;

    /** 收件人国家编码 */
    @Excel(name = "Country")
    private String receiverCountryCode;

    /** 收件人邮件 */
    @Excel(name = "e-mail/Contact")
    private String receiverEmail;

    /** 收件人姓名 */
    @Excel(name = "Name")
    private String receiverName;

    /** 收件人手机号码 */
    @Excel(name = "Phone")
    private String receiverPhone;

    /** 收件人邮政编码 */
    @Excel(name = "Zip code")
    private String receiverPostalCode;

    /** 备注 */
    @Excel(name = "Note")
    private String content;

    /** 客户数据1 */
//    @Excel(name = "客户数据1")
    private String customerData1;

    /** 内部引用号 */
    @Excel(name = "Reference")
    private String reference;

    /** 重量 */
    @Excel(name = "Weight")
    private BigDecimal weight;

    public BigDecimal getPln() {
        return pln;
    }

    public void setPln(BigDecimal pln) {
        this.pln = pln;
    }

    public String getRef1() {
        return ref1;
    }

    public void setRef1(String ref1) {
        this.ref1 = ref1;
    }

    public String getRef2() {
        return ref2;
    }

    public void setRef2(String ref2) {
        this.ref2 = ref2;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReceiverCountryCode() {
        return receiverCountryCode;
    }

    public void setReceiverCountryCode(String receiverCountryCode) {
        this.receiverCountryCode = receiverCountryCode;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverPostalCode() {
        return receiverPostalCode;
    }

    public void setReceiverPostalCode(String receiverPostalCode) {
        this.receiverPostalCode = receiverPostalCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCustomerData1() {
        return customerData1;
    }

    public void setCustomerData1(String customerData1) {
        this.customerData1 = customerData1;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }
}
