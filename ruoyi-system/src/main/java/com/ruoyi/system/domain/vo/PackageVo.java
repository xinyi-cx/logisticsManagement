package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 面单对象 package
 * 
 * @author ruoyi
 * @date 2022-01-02
 */
public class PackageVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 面单主键 */
    private Long id;

    /** 付款人类型 */
    @Excel(name = "付款人类型")
    private String payerType;

    /** 发件人id */
    @Excel(name = "发件人id")
    private Long senderId;

    /** 收货人id */
    @Excel(name = "收货人id")
    private Long receiverId;

    /** 货物金额 */
    @Excel(name = "货物金额")
    private BigDecimal pln;

    /** 分类1 */
    @Excel(name = "分类1")
    private String ref1;

    /** 分类2 */
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

    /** 创建人 */
    @Excel(name = "创建人")
    private String createUser;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updateUser;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedTime;

    /** 收件人地址 */
    @Excel(name = "收件人地址")
    private String receiverAddress;

    /** 收件人城市 */
    @Excel(name = "收件人城市")
    private String receiverCity;

    /** 收件人公司 */
    @Excel(name = "收件人公司")
    private String receiverCompany;

    /** 收件人国家编码 */
    @Excel(name = "收件人国家编码")
    private String receiverCountryCode;

    /** 收件人邮件 */
    @Excel(name = "收件人邮件")
    private String receiverEmail;

    /** 收件人姓名 */
    @Excel(name = "收件人姓名")
    private String receiverName;

    /** 收件人手机号码 */
    @Excel(name = "收件人手机号码")
    private String receiverPhone;

    /** 收件人邮政编码 */
    @Excel(name = "收件人邮政编码")
    private String receiverPostalCode;

    /** 发件人街道？地址 */
    @Excel(name = "发件人街道？地址")
    private String senderAddress;

    /** 发件人城市 */
    @Excel(name = "发件人城市")
    private String senderCity;

    /** 发件人公司 */
    @Excel(name = "发件人公司")
    private String senderCompany;

    /** 发件人国家 */
    @Excel(name = "发件人国家")
    private String senderCountryCode;

    /** 发件人邮件 */
    @Excel(name = "发件人邮件")
    private String senderEmail;

    /** 发件人fid */
    @Excel(name = "发件人fid")
    private Long senderFid;

    /** 发件人姓名 */
    @Excel(name = "发件人姓名")
    private String senderName;

    /** 发件人手机号码 */
    @Excel(name = "发件人手机号码")
    private String senderPhone;

    /** 发件人邮政编码 */
    @Excel(name = "发件人邮政编码")
    private String senderPostalCode;

    /** 长 */
    @Excel(name = "长")
    private Integer sizeX;

    /** 宽 */
    @Excel(name = "宽")
    private Integer sizeY;

    /** 高 */
    @Excel(name = "高")
    private Integer sizeZ;

    /** 备注 */
    @Excel(name = "备注")
    private String content;

    /** 客户数据1 */
    @Excel(name = "客户数据1")
    private String customerData1;

    /** 内部引用号 */
    @Excel(name = "内部引用号")
    private String reference;

    /** 重量 */
    @Excel(name = "重量")
    private Integer weight;

    /** pack_id */
    @Excel(name = "pack_id")
    private Long packId;

    /** waybill 运货单 */
    @Excel(name = "waybill 运货单")
    private String waybill;

    /** 包裹状态 */
    @Excel(name = "包裹状态")
    private String status;

    /** package_id */
    @Excel(name = "package_id")
    private Long packageId;

    /** 原面单主键 */
    @Excel(name = "原面单主键")
    private Long originalId;

    public Long getOriginalId() {
        return originalId;
    }

    public void setOriginalId(Long originalId) {
        this.originalId = originalId;
    }

    public BigDecimal getPln() {
        return pln;
    }

    public void setPln(BigDecimal pln) {
        this.pln = pln;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getReceiverCompany() {
        return receiverCompany;
    }

    public void setReceiverCompany(String receiverCompany) {
        this.receiverCompany = receiverCompany;
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

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getSenderCity() {
        return senderCity;
    }

    public void setSenderCity(String senderCity) {
        this.senderCity = senderCity;
    }

    public String getSenderCompany() {
        return senderCompany;
    }

    public void setSenderCompany(String senderCompany) {
        this.senderCompany = senderCompany;
    }

    public String getSenderCountryCode() {
        return senderCountryCode;
    }

    public void setSenderCountryCode(String senderCountryCode) {
        this.senderCountryCode = senderCountryCode;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public Long getSenderFid() {
        return senderFid;
    }

    public void setSenderFid(Long senderFid) {
        this.senderFid = senderFid;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public String getSenderPostalCode() {
        return senderPostalCode;
    }

    public void setSenderPostalCode(String senderPostalCode) {
        this.senderPostalCode = senderPostalCode;
    }

    public Integer getSizeX() {
        return sizeX;
    }

    public void setSizeX(Integer sizeX) {
        this.sizeX = sizeX;
    }

    public Integer getSizeY() {
        return sizeY;
    }

    public void setSizeY(Integer sizeY) {
        this.sizeY = sizeY;
    }

    public Integer getSizeZ() {
        return sizeZ;
    }

    public void setSizeZ(Integer sizeZ) {
        this.sizeZ = sizeZ;
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

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Long getPackId() {
        return packId;
    }

    public void setPackId(Long packId) {
        this.packId = packId;
    }

    public String getWaybill() {
        return waybill;
    }

    public void setWaybill(String waybill) {
        this.waybill = waybill;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }
}
