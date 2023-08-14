package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 面单对象 package
 * 
 *
 * @date 2022-01-11
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Package extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private String masterPwd;

    private String masterId;

    /** 面单主键 */
    private List<Long> ids;

    private Integer exportFlag;

    /** code1 */
    private String code1;

    /** code2 */
    private String code2;

    /** code2 */
    private String code3;

    private String parcelWaybill;

    private String parcelReference ;

    private String qtyPackages;

    /** 面单主键 */
    private Long id;

    private String mbCode;

    /** 付款人类型 */
    @Excel(name = "付款人类型")
    private String payerType;

    /** 发件人id */
    @Excel(name = "发件人id")
    private Long senderId;

    /** 收货人id */
    @Excel(name = "收货人id")
    private Long receiverId;

    /** 分类1 */
    @Excel(name = "分类1")
    //售后字段 售后邮箱
    private String ref1;

    /** 分类2 */
    // 售后电话
    @Excel(name = "分类2")
    private String ref2;

    /** 服务id */
    @Excel(name = "服务id")
    private Long servicesId;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phone;

    /** 邮政编码 */
    @Excel(name = "邮政编码")
    private String postalCode;

    /** 批量id */
    @Excel(name = "批量id")
    private Long batchId;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createUser;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updateUser;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Integer isDelete;

    /** 下载次数 */
    @Excel(name = "下载次数")
    private Integer downloadNum;

    private ImportLogicContent importLogicContent;

    /**
     * 安月查询参数 yyyyMM
     */
    private String paramMonth;

    private AddressReceiver receiver;

    private AddressSender sender;

    private Services service;

    private List<Parcel> parcels;

    private PackagesGenerationResponse packagesGenerationResponse;

    private Documents documents;

}
