package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * 面单对象 package
 * 
 * @author ruoyi
 * @date 2022-01-11
 */
public class Package extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 面单主键 */
    private List<Long> ids;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    private Integer exportFlag;

    public Integer getExportFlag() {
        return exportFlag;
    }

    public void setExportFlag(Integer exportFlag) {
        this.exportFlag = exportFlag;
    }

    /** code1 */
    private String code1;

    /** code2 */
    private String code2;

    /** code2 */
    private String code3;

    private String parcelWaybill;

    public String getParcelWaybill() {
        return parcelWaybill;
    }

    public void setParcelWaybill(String parcelWaybill) {
        this.parcelWaybill = parcelWaybill;
    }

    public String getCode1() {
        return code1;
    }

    public void setCode1(String code1) {
        this.code1 = code1;
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

    public String getCode3() {
        return code3;
    }

    public void setCode3(String code3) {
        this.code3 = code3;
    }

    /** 面单主键 */
    private Long id;

    private String mbCode;

    public String getMbCode() {
        return mbCode;
    }

    public void setMbCode(String mbCode) {
        this.mbCode = mbCode;
    }

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

    public ImportLogicContent getImportLogicContent() {
        return importLogicContent;
    }

    public void setImportLogicContent(ImportLogicContent importLogicContent) {
        this.importLogicContent = importLogicContent;
    }

    /**
     * 安月查询参数 yyyyMM
     */
    private String paramMonth;

    public String getParamMonth() {
        return paramMonth;
    }

    public void setParamMonth(String paramMonth) {
        this.paramMonth = paramMonth;
    }

    private AddressReceiver receiver;

    private AddressSender sender;

    private Services service;

    private List<Parcel> parcels;

    private PackagesGenerationResponse packagesGenerationResponse;

    private Documents documents;

    public Documents getDocuments() {
        return documents;
    }

    public void setDocuments(Documents documents) {
        this.documents = documents;
    }

    public PackagesGenerationResponse getPackagesGenerationResponse() {
        return packagesGenerationResponse;
    }

    public void setPackagesGenerationResponse(PackagesGenerationResponse packagesGenerationResponse) {
        this.packagesGenerationResponse = packagesGenerationResponse;
    }

    public AddressReceiver getReceiver() {
        return receiver;
    }

    public void setReceiver(AddressReceiver receiver) {
        this.receiver = receiver;
    }

    public AddressSender getSender() {
        return sender;
    }

    public void setSender(AddressSender sender) {
        this.sender = sender;
    }

    public Services getService() {
        return service;
    }

    public void setService(Services service) {
        this.service = service;
    }

    public List<Parcel> getParcels() {
        return parcels;
    }

    public void setParcels(List<Parcel> parcels) {
        this.parcels = parcels;
    }

    public void setDownloadNum(Integer downloadNum)
    {
        this.downloadNum = downloadNum;
    }

    public Integer getDownloadNum()
    {
        return downloadNum;
    }

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPayerType(String payerType) 
    {
        this.payerType = payerType;
    }

    public String getPayerType() 
    {
        return payerType;
    }
    public void setSenderId(Long senderId) 
    {
        this.senderId = senderId;
    }

    public Long getSenderId() 
    {
        return senderId;
    }
    public void setReceiverId(Long receiverId) 
    {
        this.receiverId = receiverId;
    }

    public Long getReceiverId() 
    {
        return receiverId;
    }
    public void setRef1(String ref1) 
    {
        this.ref1 = ref1;
    }

    public String getRef1() 
    {
        return ref1;
    }
    public void setRef2(String ref2) 
    {
        this.ref2 = ref2;
    }

    public String getRef2() 
    {
        return ref2;
    }
    public void setServicesId(Long servicesId) 
    {
        this.servicesId = servicesId;
    }

    public Long getServicesId() 
    {
        return servicesId;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setPostalCode(String postalCode) 
    {
        this.postalCode = postalCode;
    }

    public String getPostalCode() 
    {
        return postalCode;
    }
    public void setBatchId(Long batchId) 
    {
        this.batchId = batchId;
    }

    public Long getBatchId() 
    {
        return batchId;
    }
    public void setCreateUser(String createUser) 
    {
        this.createUser = createUser;
    }

    public String getCreateUser() 
    {
        return createUser;
    }
    public void setUpdateUser(String updateUser) 
    {
        this.updateUser = updateUser;
    }

    public String getUpdateUser() 
    {
        return updateUser;
    }
    public void setCreatedTime(Date createdTime) 
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() 
    {
        return createdTime;
    }
    public void setUpdatedTime(Date updatedTime) 
    {
        this.updatedTime = updatedTime;
    }

    public Date getUpdatedTime() 
    {
        return updatedTime;
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
            .append("payerType", getPayerType())
            .append("senderId", getSenderId())
            .append("receiverId", getReceiverId())
            .append("ref1", getRef1())
            .append("ref2", getRef2())
            .append("servicesId", getServicesId())
            .append("phone", getPhone())
            .append("postalCode", getPostalCode())
            .append("batchId", getBatchId())
            .append("createUser", getCreateUser())
            .append("updateUser", getUpdateUser())
            .append("createdTime", getCreatedTime())
            .append("updatedTime", getUpdatedTime())
            .append("isDelete", getIsDelete())
            .toString();
    }
}
