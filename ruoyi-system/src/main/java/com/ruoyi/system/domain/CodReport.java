package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * COD报告对象 cod_report
 * 
 *
 * @date 2023-05-02
 */
public class CodReport extends BaseEntity
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

    /** nr_klienta */
    @Excel(name = "nr_klienta")
    private String nrKlienta;

    /** 下载收件人 */
    @Excel(name = "下载收件人")
    private String downloadRecipient;

    /** 运单号码（货件） */
    @Excel(name = "运单号码", readConverterExp = "货=件")
    private String waybill;

    /** 运单（过账）日期 */
    @Excel(name = "运单", readConverterExp = "过=账")
    private String waybillPostingDate;

    /** 货到付款金额 （货到付款） */
    @Excel(name = "货到付款金额 ", readConverterExp = "货=到付款")
    private String cashOnDelivery;

    /** 货件有效交付日期 */
    @Excel(name = "货件有效交付日期")
    private String goodsEffectiveDeliveryDate;

    /** 货件收件人 */
    @Excel(name = "货件收件人")
    private String addressee;

    /** 邮政编码 */
    @Excel(name = "邮政编码")
    private String postalCode;

    /** 地区 */
    @Excel(name = "地区")
    private String area;

    /** 街 */
    @Excel(name = "街")
    private String street;

    /** Unisoft-Client中的内容字段 */
    @Excel(name = "Unisoft-Client中的内容字段")
    private String unisoftClientContentField;

    /** 批量传输 */
    @Excel(name = "批量传输")
    private String batchTransmission;

    /** 集体移送日期 */
    @Excel(name = "集体移送日期")
    private String collectiveTransferDate;

    /** 结算编号 （怀俄明州） */
    @Excel(name = "结算编号 ", readConverterExp = "怀=俄明州")
    private String settlementNumber;

    /** 激活时间 */
    @Excel(name = "激活时间")
    private String sysActivedDate;

    /** 最近物流时间 */
    @Excel(name = "最近物流时间")
    private String sysLastStatusDate;

    /** 签收时间 */
    @Excel(name = "签收时间")
    private String sysSignDate;

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
    public void setNrKlienta(String nrKlienta) 
    {
        this.nrKlienta = nrKlienta;
    }

    public String getNrKlienta() 
    {
        return nrKlienta;
    }
    public void setDownloadRecipient(String downloadRecipient) 
    {
        this.downloadRecipient = downloadRecipient;
    }

    public String getDownloadRecipient() 
    {
        return downloadRecipient;
    }
    public void setWaybill(String waybill) 
    {
        this.waybill = waybill;
    }

    public String getWaybill() 
    {
        return waybill;
    }
    public void setWaybillPostingDate(String waybillPostingDate) 
    {
        this.waybillPostingDate = waybillPostingDate;
    }

    public String getWaybillPostingDate() 
    {
        return waybillPostingDate;
    }
    public void setCashOnDelivery(String cashOnDelivery) 
    {
        this.cashOnDelivery = cashOnDelivery;
    }

    public String getCashOnDelivery() 
    {
        return cashOnDelivery;
    }
    public void setGoodsEffectiveDeliveryDate(String goodsEffectiveDeliveryDate) 
    {
        this.goodsEffectiveDeliveryDate = goodsEffectiveDeliveryDate;
    }

    public String getGoodsEffectiveDeliveryDate() 
    {
        return goodsEffectiveDeliveryDate;
    }
    public void setAddressee(String addressee) 
    {
        this.addressee = addressee;
    }

    public String getAddressee() 
    {
        return addressee;
    }
    public void setPostalCode(String postalCode) 
    {
        this.postalCode = postalCode;
    }

    public String getPostalCode() 
    {
        return postalCode;
    }
    public void setArea(String area) 
    {
        this.area = area;
    }

    public String getArea() 
    {
        return area;
    }
    public void setStreet(String street) 
    {
        this.street = street;
    }

    public String getStreet() 
    {
        return street;
    }
    public void setUnisoftClientContentField(String unisoftClientContentField) 
    {
        this.unisoftClientContentField = unisoftClientContentField;
    }

    public String getUnisoftClientContentField() 
    {
        return unisoftClientContentField;
    }
    public void setBatchTransmission(String batchTransmission) 
    {
        this.batchTransmission = batchTransmission;
    }

    public String getBatchTransmission() 
    {
        return batchTransmission;
    }
    public void setCollectiveTransferDate(String collectiveTransferDate) 
    {
        this.collectiveTransferDate = collectiveTransferDate;
    }

    public String getCollectiveTransferDate() 
    {
        return collectiveTransferDate;
    }
    public void setSettlementNumber(String settlementNumber) 
    {
        this.settlementNumber = settlementNumber;
    }

    public String getSettlementNumber() 
    {
        return settlementNumber;
    }
    public void setSysActivedDate(String sysActivedDate) 
    {
        this.sysActivedDate = sysActivedDate;
    }

    public String getSysActivedDate() 
    {
        return sysActivedDate;
    }
    public void setSysLastStatusDate(String sysLastStatusDate) 
    {
        this.sysLastStatusDate = sysLastStatusDate;
    }

    public String getSysLastStatusDate() 
    {
        return sysLastStatusDate;
    }
    public void setSysSignDate(String sysSignDate) 
    {
        this.sysSignDate = sysSignDate;
    }

    public String getSysSignDate() 
    {
        return sysSignDate;
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
            .append("nrKlienta", getNrKlienta())
            .append("downloadRecipient", getDownloadRecipient())
            .append("waybill", getWaybill())
            .append("waybillPostingDate", getWaybillPostingDate())
            .append("cashOnDelivery", getCashOnDelivery())
            .append("goodsEffectiveDeliveryDate", getGoodsEffectiveDeliveryDate())
            .append("addressee", getAddressee())
            .append("postalCode", getPostalCode())
            .append("area", getArea())
            .append("street", getStreet())
            .append("unisoftClientContentField", getUnisoftClientContentField())
            .append("batchTransmission", getBatchTransmission())
            .append("collectiveTransferDate", getCollectiveTransferDate())
            .append("settlementNumber", getSettlementNumber())
            .append("sysActivedDate", getSysActivedDate())
            .append("sysLastStatusDate", getSysLastStatusDate())
            .append("sysSignDate", getSysSignDate())
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
