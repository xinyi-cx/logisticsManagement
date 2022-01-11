package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 包裹对象 parcel
 * 
 * @author ruoyi
 * @date 2022-01-11
 */
public class Parcel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

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
    public void setSizeX(Integer sizeX) 
    {
        this.sizeX = sizeX;
    }

    public Integer getSizeX() 
    {
        return sizeX;
    }
    public void setSizeY(Integer sizeY) 
    {
        this.sizeY = sizeY;
    }

    public Integer getSizeY() 
    {
        return sizeY;
    }
    public void setSizeZ(Integer sizeZ) 
    {
        this.sizeZ = sizeZ;
    }

    public Integer getSizeZ() 
    {
        return sizeZ;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setCustomerData1(String customerData1) 
    {
        this.customerData1 = customerData1;
    }

    public String getCustomerData1() 
    {
        return customerData1;
    }
    public void setReference(String reference) 
    {
        this.reference = reference;
    }

    public String getReference() 
    {
        return reference;
    }
    public void setWeight(Integer weight) 
    {
        this.weight = weight;
    }

    public Integer getWeight() 
    {
        return weight;
    }
    public void setPackId(Long packId) 
    {
        this.packId = packId;
    }

    public Long getPackId() 
    {
        return packId;
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
            .append("sizeX", getSizeX())
            .append("sizeY", getSizeY())
            .append("sizeZ", getSizeZ())
            .append("content", getContent())
            .append("customerData1", getCustomerData1())
            .append("reference", getReference())
            .append("weight", getWeight())
            .append("packId", getPackId())
            .append("waybill", getWaybill())
            .append("status", getStatus())
            .append("packageId", getPackageId())
            .append("createUser", getCreateUser())
            .append("updateUser", getUpdateUser())
            .append("createdTime", getCreatedTime())
            .append("updatedTime", getUpdatedTime())
            .append("isDelete", getIsDelete())
            .toString();
    }
}
