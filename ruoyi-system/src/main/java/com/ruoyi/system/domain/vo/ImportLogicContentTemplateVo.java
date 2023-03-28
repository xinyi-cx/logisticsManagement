package com.ruoyi.system.domain.vo;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 导入查询物流对象 import_logic_content
 * 
 * @author ruoyi
 * @date 2022-11-20
 */
public class ImportLogicContentTemplateVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** document表id */
    private Long documentFileId;

    /** package表id */
    private Long packId;

    /** batch_id */
    private Long batchId;

    /** logic_id */
    private Long logicId;

    /** CLIENT */
    private String client;

    /** Country */
    private String country;

    /** Type */
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
    @Excel(name = "Code 1")
    private String remark2;

    /** 备注3 */
    @Excel(name = "Code 2")
    private String remark3;

    /** 备注4 */
    @Excel(name = "old return number")
    private String remark4;

    /** 备注5 */
    @Excel(name = "old shipment number")
    private String remark5;

    /** 是否删除 */
    private Integer isDelete;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setDocumentFileId(Long documentFileId) 
    {
        this.documentFileId = documentFileId;
    }

    public Long getDocumentFileId() 
    {
        return documentFileId;
    }
    public void setPackId(Long packId) 
    {
        this.packId = packId;
    }

    public Long getPackId() 
    {
        return packId;
    }
    public void setBatchId(Long batchId) 
    {
        this.batchId = batchId;
    }

    public Long getBatchId() 
    {
        return batchId;
    }
    public void setLogicId(Long logicId) 
    {
        this.logicId = logicId;
    }

    public Long getLogicId() 
    {
        return logicId;
    }
    public void setClient(String client) 
    {
        this.client = client;
    }

    public String getClient() 
    {
        return client;
    }
    public void setCountry(String country) 
    {
        this.country = country;
    }

    public String getCountry() 
    {
        return country;
    }
    public void setImportType(String importType) 
    {
        this.importType = importType;
    }

    public String getImportType() 
    {
        return importType;
    }
    public void setLoginid(String loginid) 
    {
        this.loginid = loginid;
    }

    public String getLoginid() 
    {
        return loginid;
    }
    public void setNewWaybill(String newWaybill) 
    {
        this.newWaybill = newWaybill;
    }

    public String getNewWaybill() 
    {
        return newWaybill;
    }
    public void setOrderNumber(String orderNumber) 
    {
        this.orderNumber = orderNumber;
    }

    public String getOrderNumber() 
    {
        return orderNumber;
    }
    public void setValuePlnCod(String valuePlnCod) 
    {
        this.valuePlnCod = valuePlnCod;
    }

    public String getValuePlnCod() 
    {
        return valuePlnCod;
    }
    public void setWeightKg(String weightKg) 
    {
        this.weightKg = weightKg;
    }

    public String getWeightKg() 
    {
        return weightKg;
    }
    public void setRecipientName(String recipientName) 
    {
        this.recipientName = recipientName;
    }

    public String getRecipientName() 
    {
        return recipientName;
    }
    public void setRecipientPhone(String recipientPhone) 
    {
        this.recipientPhone = recipientPhone;
    }

    public String getRecipientPhone() 
    {
        return recipientPhone;
    }
    public void setRecipientEmail(String recipientEmail) 
    {
        this.recipientEmail = recipientEmail;
    }

    public String getRecipientEmail() 
    {
        return recipientEmail;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setNeedBox(String needBox) 
    {
        this.needBox = needBox;
    }

    public String getNeedBox() 
    {
        return needBox;
    }
    public void setCreateDate(String createDate) 
    {
        this.createDate = createDate;
    }

    public String getCreateDate() 
    {
        return createDate;
    }
    public void setRemark2(String remark2) 
    {
        this.remark2 = remark2;
    }

    public String getRemark2() 
    {
        return remark2;
    }
    public void setRemark3(String remark3) 
    {
        this.remark3 = remark3;
    }

    public String getRemark3() 
    {
        return remark3;
    }
    public void setRemark4(String remark4) 
    {
        this.remark4 = remark4;
    }

    public String getRemark4() 
    {
        return remark4;
    }
    public void setRemark5(String remark5) 
    {
        this.remark5 = remark5;
    }

    public String getRemark5() 
    {
        return remark5;
    }
    public void setIsDelete(Integer isDelete) 
    {
        this.isDelete = isDelete;
    }

    public Integer getIsDelete() 
    {
        return isDelete;
    }

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
            .append("isDelete", getIsDelete())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
