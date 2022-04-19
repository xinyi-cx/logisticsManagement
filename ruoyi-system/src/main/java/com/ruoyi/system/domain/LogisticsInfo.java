package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 物流信息对象 logistics_info
 * 
 * @author ruoyi
 * @date 2022-04-07
 */
public class LogisticsInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 物流公司 */
    @Excel(name = "物流公司")
    private String company;

    /** 发货时间 */
    @Excel(name = "发货时间")
    private String deliveryTime;

    /** 订单号 */
    @Excel(name = "订单号")
    private String order;

    /** 最新物流时间 */
    @Excel(name = "最新物流时间")
    private String lastTime;

    /** 最新物流信息 */
    @Excel(name = "最新物流信息")
    private String lastMsg;

    /** 回复信息 */
    @Excel(name = "回复信息")
    private String repMsg;

    /** 失败原因 */
    @Excel(name = "失败原因")
    private String failureMsg;

    /** 备注 */
    @Excel(name = "备注")
    private String content;

    /** 内部引用号 */
    @Excel(name = "内部引用号")
    private String reference;

    /** pack_id */
    @Excel(name = "pack_id")
    private Long packId;

    /** 转寄pack_id */
    @Excel(name = "转寄pack_id")
    private Long secPackId;

    /** 物流单号 */
    @Excel(name = "物流单号")
    private String waybill;

    /** 包裹状态 */
    @Excel(name = "包裹状态")
    private String status;

    /** package_id */
    @Excel(name = "package_id")
    private Long packageId;

    /** parcel_id */
    @Excel(name = "parcel_id")
    private Long parcelId;

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

    /** 是否删除 */
    private Integer isDelete;

    /** package内部引用号 */
    @Excel(name = "package内部引用号")
    private String packageReference;

    /** 内部引用号 */
    @Excel(name = "内部引用号")
    private String depot;

    public String getPackageReference() {
        return packageReference;
    }

    public void setPackageReference(String packageReference) {
        this.packageReference = packageReference;
    }

    public String getDepot() {
        return depot;
    }

    public void setDepot(String depot) {
        this.depot = depot;
    }

    /**
     * 转寄次数
     */
    @Excel(name = "转寄次数")
    private Integer redirectNum;

    public Integer getRedirectNum() {
        return redirectNum;
    }

    public void setRedirectNum(Integer redirectNum) {
        this.redirectNum = redirectNum;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCompany(String company) 
    {
        this.company = company;
    }

    public String getCompany() 
    {
        return company;
    }
    public void setDeliveryTime(String deliveryTime)
    {
        this.deliveryTime = deliveryTime;
    }

    public String getDeliveryTime()
    {
        return deliveryTime;
    }
    public void setOrder(String order) 
    {
        this.order = order;
    }

    public String getOrder() 
    {
        return order;
    }
    public void setLastTime(String lastTime)
    {
        this.lastTime = lastTime;
    }

    public String getLastTime()
    {
        return lastTime;
    }
    public void setLastMsg(String lastMsg) 
    {
        this.lastMsg = lastMsg;
    }

    public String getLastMsg() 
    {
        return lastMsg;
    }
    public void setRepMsg(String repMsg) 
    {
        this.repMsg = repMsg;
    }

    public String getRepMsg() 
    {
        return repMsg;
    }
    public void setFailureMsg(String failureMsg) 
    {
        this.failureMsg = failureMsg;
    }

    public String getFailureMsg() 
    {
        return failureMsg;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setReference(String reference) 
    {
        this.reference = reference;
    }

    public String getReference() 
    {
        return reference;
    }
    public void setPackId(Long packId) 
    {
        this.packId = packId;
    }

    public Long getPackId() 
    {
        return packId;
    }
    public void setSecPackId(Long secPackId) 
    {
        this.secPackId = secPackId;
    }

    public Long getSecPackId() 
    {
        return secPackId;
    }
    public void setWaybill(String waybill) 
    {
        this.waybill = waybill;
    }

    public String getWaybill() 
    {
        return waybill;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setPackageId(Long packageId) 
    {
        this.packageId = packageId;
    }

    public Long getPackageId() 
    {
        return packageId;
    }
    public void setParcelId(Long parcelId) 
    {
        this.parcelId = parcelId;
    }

    public Long getParcelId() 
    {
        return parcelId;
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
            .append("company", getCompany())
            .append("deliveryTime", getDeliveryTime())
            .append("order", getOrder())
            .append("lastTime", getLastTime())
            .append("lastMsg", getLastMsg())
            .append("repMsg", getRepMsg())
            .append("failureMsg", getFailureMsg())
            .append("content", getContent())
            .append("reference", getReference())
            .append("packId", getPackId())
            .append("secPackId", getSecPackId())
            .append("waybill", getWaybill())
            .append("status", getStatus())
            .append("packageId", getPackageId())
            .append("parcelId", getParcelId())
            .append("createUser", getCreateUser())
            .append("updateUser", getUpdateUser())
            .append("createdTime", getCreatedTime())
            .append("updatedTime", getUpdatedTime())
            .append("isDelete", getIsDelete())
            .toString();
    }
}
