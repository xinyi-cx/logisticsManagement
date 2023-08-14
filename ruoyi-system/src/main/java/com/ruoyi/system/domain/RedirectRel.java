package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * 转寄关联关系对象 redirect_rel
 * 
 *
 * @date 2022-08-12
 */
public class RedirectRel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer exportFlag;

    /** 面单主键 */
    private List<Long> ids;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public Integer getExportFlag() {
        return exportFlag;
    }

    public void setExportFlag(Integer exportFlag) {
        this.exportFlag = exportFlag;
    }

    /** 主键 */
    private Long id;

    /** 旧物流单号 */
    @Excel(name = "旧物流单号")
    private String oldWaybill;

    /** 回退单号 */
    @Excel(name = "回退单号")
    private String backOrder;

    /** 新的单号 */
    @Excel(name = "新的单号")
    private String newOrder;

    /** 新物流单号 */
    private String newWaybill;

    private Long newPackageId;

    private Long oldPackageId;

    public Long getNewPackageId() {
        return newPackageId;
    }

    public void setNewPackageId(Long newPackageId) {
        this.newPackageId = newPackageId;
    }

    public Long getOldPackageId() {
        return oldPackageId;
    }

    public void setOldPackageId(Long oldPackageId) {
        this.oldPackageId = oldPackageId;
    }

    /** 国家代码 */
    private String countryCode;

    /** 创建人 */
    private String createUser;

    /** 更新人 */
    private String updateUser;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updatedTime;

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
    public void setOldWaybill(String oldWaybill) 
    {
        this.oldWaybill = oldWaybill;
    }

    public String getOldWaybill() 
    {
        return oldWaybill;
    }
    public void setBackOrder(String backOrder) 
    {
        this.backOrder = backOrder;
    }

    public String getBackOrder() 
    {
        return backOrder;
    }
    public void setNewOrder(String newOrder) 
    {
        this.newOrder = newOrder;
    }

    public String getNewOrder() 
    {
        return newOrder;
    }
    public void setNewWaybill(String newWaybill) 
    {
        this.newWaybill = newWaybill;
    }

    public String getNewWaybill() 
    {
        return newWaybill;
    }
    public void setCountryCode(String countryCode) 
    {
        this.countryCode = countryCode;
    }

    public String getCountryCode() 
    {
        return countryCode;
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
            .append("oldWaybill", getOldWaybill())
            .append("backOrder", getBackOrder())
            .append("newOrder", getNewOrder())
            .append("newWaybill", getNewWaybill())
            .append("countryCode", getCountryCode())
            .append("remark", getRemark())
            .append("createUser", getCreateUser())
            .append("updateUser", getUpdateUser())
            .append("createdTime", getCreatedTime())
            .append("updatedTime", getUpdatedTime())
            .append("isDelete", getIsDelete())
            .toString();
    }
}
