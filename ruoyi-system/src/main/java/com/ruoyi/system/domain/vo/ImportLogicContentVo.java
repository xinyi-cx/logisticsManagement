package com.ruoyi.system.domain.vo;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 导入查询物流对象 import_logic_content
 * 
 * @date 2022-11-20
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ImportLogicContentVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** document表id */
    @Excel(name = "document表id")
    private Long documentFileId;

    /** package表id */
    @Excel(name = "package表id")
    private Long packId;

    /** batch_id */
    @Excel(name = "batch_id")
    private Long batchId;

    /** logic_id */
    @Excel(name = "logic_id")
    private Long logicId;

    /** CLIENT */
    @Excel(name = "CLIENT")
    private String client;

    /** Country */
    @Excel(name = "Country")
    private String country;

    /** Type */
    @Excel(name = "Type")
    private String importType;

    /** LoginID */
    @Excel(name = "LoginID")
    private String loginid;

    /** New waybill */
    @Excel(name = "New waybill")
    private String newWaybill;

    /** Order number */
    @Excel(name = "Order number")
    private String orderNumber;

    /** value PLN (COD) */
    @Excel(name = "value PLN (COD)")
    private String valuePlnCod;

    /** Weight kg */
    @Excel(name = "Weight kg")
    private String weightKg;

    /** recipient name */
    @Excel(name = "recipient name")
    private String recipientName;

    /** Recipient phone */
    @Excel(name = "Recipient phone")
    private String recipientPhone;

    /** Recipient E-mail */
    @Excel(name = "Recipient E-mail")
    private String recipientEmail;

    /** Description */
    @Excel(name = "Description")
    private String description;

    /** Need box */
    @Excel(name = "Need box")
    private String needBox;

    /** Create Date */
    @Excel(name = "Create Date")
    private String createDate;

    /** 备注2 */
    @Excel(name = "备注2")
    private String remark2;

    /** 备注3 */
    @Excel(name = "备注3")
    private String remark3;

    /** 备注4 */
    @Excel(name = "备注4")
    private String remark4;

    /** 备注5 */
    @Excel(name = "备注5")
    private String remark5;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Integer isDelete;

    private String boxRemarkOne;

    private String boxRemarkTwo;

    private String boxRemarkThree;

}
