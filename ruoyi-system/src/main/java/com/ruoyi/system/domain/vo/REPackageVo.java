package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 转寄面单对象 package
 * 
 *
 * @date 2022-01-02
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class REPackageVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 面单主键 */
    private Long id;

    @Excel(name = "Sender Name")
    private String showSenderName;

    /** 收件人姓名 */
    @Excel(name = "Recipient Name")
    @NotEmpty(message = "收件人信息不能为空")
    private String receiverName;

    /**
     * 收件人地址
     */
    @Excel(name = "Recipient Street")
    @NotEmpty(message = "收件人信息不能为空")
    private String receiverAddress;

    /**
     * 收件人邮政编码
     */
    @Excel(name = "Recipient Postal code")
    @NotEmpty(message = "收件人信息不能为空")
    private String receiverPostalCode;

    /**
     * 收件人城市
     */
    @Excel(name = "Recipient City")
    @NotEmpty(message = "收件人信息不能为空")
    private String receiverCity;

    /**
     * 收件人国家编码
     */
    @Excel(name = "Recipient Country")
//    @NotEmpty(message = "收件人信息不能为空")
    private String receiverCountryCode;

    /**
     * 收件人手机号码
     */
    @Excel(name = "Recipient phone")
    @NotEmpty(message = "收件人信息不能为空")
    private String receiverPhone;

    /**
     * 收件人邮箱
     */
    @Excel(name = "Recipient E-mail")
    private String receiverEmail;
    /**
     * 重量（单位kg）
     * 规则：
     * 1、小于1kg的按1kg算，dpd会取体积和重量中的较大值进行计算；
     * 2、大于10kg的要提示，不能大于10kg.
     */
    @Excel(name = "Weight kg")
    private BigDecimal weight;

    /**
     * 包裹里物件的数量
     */
    @Excel(name = "Qty packages")
    private String qtyPackages;

    /**
     * 备注 商品描述
     */
    @Excel(name = "Description")
    private String customerData1;

    /**
     * 内部引用号
     */
    @Excel(name = "Order")
    private String reference;

    /**
     * 分类1（售后电话）
     */
    @Excel(name = "Reference 1")
    private String ref1;

    /**
     * 分类2（售后邮箱）
     */
    @Excel(name = "Reference 2")
    private String ref2;

    /**
     * 货物金额
     */
    @Excel(name = "COD")
    private BigDecimal pln;

    /** 原面单主键 */
//    @Excel(name = "原面单物流单号")
    private String originalWaybill;

    /** 旧物流单号 */
    @Excel(name = "old shipment number")
    private String oldWaybill;

    /** 新的单号 */
//    @Excel(name = "Order number")
    private String newOrder;

    /** 回退单号 */
    @Excel(name = "old return number")
    private String backOrder;

    private String boxRemarkOne;

    private String boxRemarkTwo;

    private String boxRemarkThree;

    /** 新物流单号 */
//    @Excel(name = "New Waybill")
    private String newWaybill;

    /** 服务id */
    private Long servicesId;

    /** 手机号码 */
    private String phone;

    /** 邮政编码 */
    private String postalCode;

    /** 创建人 */
    private String createUser;

    /** 更新人 */
    private String updateUser;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updatedTime;

    /** 下载次数 */
    private Integer downloadNum;

    /** 收件人公司 */
    private String receiverCompany;

    /** 长 */
    private Integer sizeX;

    /** 宽 */
    private Integer sizeY;

    /** 高 */
    private Integer sizeZ;

    /** 客户数据1 */
    private String content;

    /** pack_id */
    private Long packId;

    /** waybill 运货单 */
    private String waybill;

    /** 包裹状态 */
    private String status;

    /** package_id */
    private Long packageId;

    private Long parcelId;

    private Long sessionId;

    private String packagesGenerationResponseStatus;

    private String pkgReference;

    private String pkgStatus;

    /** 原面单主键 */
    private Long originalId;

    /** 付款人类型 */
    private String payerType;

    /** 发件人id */
    private Long senderId;

    /** 收货人id */
    private Long receiverId;

    private String hisParam;

}
