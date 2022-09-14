package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 导出 面单对象 package
 *
 * @author ruoyi
 * @date 2022-01-02
 */
public class ExportPackageCzVo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 面单主键
     */
    private Long id;

    @Excel(name = "Sender Name")
    private String showSenderName;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    /**
     * waybill 运货单
     */
    @Excel(name = "waybill")
    private String waybill;

    /**
     * 收件人姓名
     */
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
    @Excel(name = "RecipientPostalcode")
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
    @Excel(name = "Recipient country")
//    @NotEmpty(message = "收件人信息不能为空")
    private String receiverCountryCode;

    /**
     * 收件人手机号码
     */
    @Excel(name = "Recipientphone")
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

    /** 旧物流单号 */
//    @Excel(name = "旧物流单号")
    private String oldWaybill;

    /** 新的单号 */
//    @Excel(name = "Order number")
    private String newOrder;

    /** 回退单号 */
//    @Excel(name = "Old return number")
    private String backOrder;

    /** 新物流单号 */
//    @Excel(name = "New Waybill")
    private String newWaybill;

    public String getOldWaybill() {
        return oldWaybill;
    }

    public void setOldWaybill(String oldWaybill) {
        this.oldWaybill = oldWaybill;
    }

    public String getBackOrder() {
        return backOrder;
    }

    public void setBackOrder(String backOrder) {
        this.backOrder = backOrder;
    }

    public String getNewOrder() {
        return newOrder;
    }

    public void setNewOrder(String newOrder) {
        this.newOrder = newOrder;
    }

    public String getNewWaybill() {
        return newWaybill;
    }

    public void setNewWaybill(String newWaybill) {
        this.newWaybill = newWaybill;
    }

    /**
     * 付款人类型
     */
//    @Excel(name = "付款人类型")
    private String payerType;

    /**
     * 收件人公司
     */
//    @Excel(name = "收件人公司")
    private String receiverCompany;

    /**
     * 客户数据1
     */
//    @Excel(name = "客户数据1")
    private String content;


    /**
     * 长
     */
//    @Excel(name = "长")
    private Integer sizeX;

    /**
     * 宽
     */
//    @Excel(name = "宽")
    private Integer sizeY;

    /**
     * 高
     */
//    @Excel(name = "高")
    private Integer sizeZ;

    /**
     * pack_id
     */
//    @Excel(name = "pack_id")
    private Long packId;

    /**
     * 包裹状态
     */
//    @Excel(name = "包裹状态")
    private String status;

    /**
     * package_id
     */
//    @Excel(name = "package_id")
    private Long packageId;

    private Long parcelId;

    private Long sessionId;

    private String packagesGenerationResponseStatus;

    private String pkgReference;

    private String pkgStatus;

    /**
     * 发件人id
     */
//    @Excel(name = "发件人id")
    private Long senderId;

    /**
     * 收货人id
     */
//    @Excel(name = "收货人id")
    private Long receiverId;

    /**
     * 原面单主键
     */
//    @Excel(name = "原面单主键")
    private Long originalId;

    /**
     * 原面单主键
     */
//    @Excel(name = "原面单物流单号")
    private String originalWaybill;


    /**
     * 服务id
     */
//    @Excel(name = "服务id")
    private Long servicesId;

    /**
     * 手机号码
     */
//    @Excel(name = "手机号码")
    private String phone;

    /**
     * 邮政编码
     */
//    @Excel(name = "邮政编码")
    private String postalCode;

    /**
     * 创建人
     */
//    @Excel(name = "创建人")
    private String createUser;

    /**
     * 更新人
     */
//    @Excel(name = "更新人")
    private String updateUser;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
//    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedTime;

    /**
     * 下载次数
     */
//    @Excel(name = "下载次数")
    private Integer downloadNum;

    private String hisParam;

    private String datStr;

    public String getDatStr() {
        return datStr;
    }

    public void setDatStr(String datStr) {
        this.datStr = datStr;
    }

    public String getHisParam() {
        return hisParam;
    }

    public void setHisParam(String hisParam) {
        this.hisParam = hisParam;
    }

    public void setDownloadNum(Integer downloadNum) {
        this.downloadNum = downloadNum;
    }

    public Integer getDownloadNum() {
        return downloadNum;
    }

    public String getOriginalWaybill() {
        return originalWaybill;
    }

    public void setOriginalWaybill(String originalWaybill) {
        this.originalWaybill = originalWaybill;
    }

    public Long getOriginalId() {
        return originalId;
    }

    public void setOriginalId(Long originalId) {
        this.originalId = originalId;
    }

    public Long getParcelId() {
        return parcelId;
    }

    public void setParcelId(Long parcelId) {
        this.parcelId = parcelId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public String getPackagesGenerationResponseStatus() {
        return packagesGenerationResponseStatus;
    }

    public void setPackagesGenerationResponseStatus(String packagesGenerationResponseStatus) {
        this.packagesGenerationResponseStatus = packagesGenerationResponseStatus;
    }

    public String getPkgReference() {
        return pkgReference;
    }

    public void setPkgReference(String pkgReference) {
        this.pkgReference = pkgReference;
    }

    public String getPkgStatus() {
        return pkgStatus;
    }

    public void setPkgStatus(String pkgStatus) {
        this.pkgStatus = pkgStatus;
    }

    public BigDecimal getPln() {
        return pln;
    }

    public void setPln(BigDecimal pln) {
        this.pln = pln;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setPayerType(String payerType) {
        this.payerType = payerType;
    }

    public String getPayerType() {
        return payerType;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setRef1(String ref1) {
        this.ref1 = ref1;
    }

    public String getRef1() {
        return ref1;
    }

    public void setRef2(String ref2) {
        this.ref2 = ref2;
    }

    public String getRef2() {
        return ref2;
    }

    public void setServicesId(Long servicesId) {
        this.servicesId = servicesId;
    }

    public Long getServicesId() {
        return servicesId;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Date getUpdatedTime() {
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

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
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

    public String getShowSenderName() {
        return showSenderName;
    }

    public void setShowSenderName(String showSenderName) {
        this.showSenderName = showSenderName;
    }

    public String getQtyPackages() {
        return qtyPackages;
    }

    public void setQtyPackages(String qtyPackages) {
        this.qtyPackages = qtyPackages;
    }
}
