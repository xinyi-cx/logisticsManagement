package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * 导入查询物流对象 import_logic_content
 * 
 * @author ruoyi
 * @date 2022-11-27
 */
@Getter
@Setter
public class ImportLogicContent extends BaseEntity
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

    /** 原单号 waybill */
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

    @Excel(name = "盒子备注1")
    private String boxRemarkOne;

    @Excel(name = "盒子备注2")
    private String boxRemarkTwo;

    @Excel(name = "盒子备注3")
    private String boxRemarkThree;

    /** 激活时间 */
    @Excel(name = "激活时间")
    private String activedDate;

    /** 最近物流时间 */
    @Excel(name = "最近物流时间")
    private String lastStatusDate;

    /**
     * COD报告时间
     */
    private String collectiveTransferDate;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 新单号 */
    @Excel(name = "新单号")
    private String newNumber;

    /** 退件单号 */
    @Excel(name = "退件单号")
    private String returnNumber;

    private String fileName;

    private List<Long> ids;
    private List<Long> batchIds;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date activeTimeStart;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date activeTimeEnd;

    private String paramMonth;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Integer isDelete;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("documentFileId", getDocumentFileId())
            .append("packId", getPackId())
            .append("batchId", getBatchId())
            .append("logicId", getLogicId())
            .append("client", getClient())
            .append("country", getCountry())
            .append("importType", getImportType())
            .append("loginid", getLoginid())
            .append("newWaybill", getNewWaybill())
            .append("orderNumber", getOrderNumber())
            .append("valuePlnCod", getValuePlnCod())
            .append("weightKg", getWeightKg())
            .append("recipientName", getRecipientName())
            .append("recipientPhone", getRecipientPhone())
            .append("recipientEmail", getRecipientEmail())
            .append("description", getDescription())
            .append("needBox", getNeedBox())
            .append("createDate", getCreateDate())
            .append("remark", getRemark())
            .append("remark2", getRemark2())
            .append("remark3", getRemark3())
            .append("remark4", getRemark4())
            .append("remark5", getRemark5())
            .append("activedDate", getActivedDate())
            .append("lastStatusDate", getLastStatusDate())
            .append("status", getStatus())
            .append("newNumber", getNewNumber())
            .append("returnNumber", getReturnNumber())
            .append("isDelete", getIsDelete())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
