package com.ruoyi.system.domain.vo;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 面单对象 package
 * 
 * @date 2022-01-02
 */
@EqualsAndHashCode(callSuper = true)
@Data
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

    private String boxRemarkOne;

    private String boxRemarkTwo;

    private String boxRemarkThree;

}
