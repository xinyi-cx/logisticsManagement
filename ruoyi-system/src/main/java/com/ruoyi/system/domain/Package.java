package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

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
