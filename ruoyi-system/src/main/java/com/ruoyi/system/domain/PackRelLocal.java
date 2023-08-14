package com.ruoyi.system.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 本地关联关系对象 pack_rel_local
 * 
 *
 * @date 2023-03-08
 */
public class PackRelLocal extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer exportFlag;

    /** 面单主键 */
    private List<Long> ids;

    public Integer getExportFlag() {
        return exportFlag;
    }

    public void setExportFlag(Integer exportFlag) {
        this.exportFlag = exportFlag;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    /** 主键 */
    private Long id;

    /** 旧物流单号 */
    @Excel(name = "旧物流单号")
    private Long oldPackageId;

    /** code1 */
    @Excel(name = "code1")
    private String code1;

    /** code2 */
    @Excel(name = "code2")
    private String code2;

    /** code2 */
    @Excel(name = "code2")
    private String code3;

    /** 国家代码 */
    @Excel(name = "国家代码")
    private String countryCode;

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
    public void setOldPackageId(Long oldPackageId) 
    {
        this.oldPackageId = oldPackageId;
    }

    public Long getOldPackageId() 
    {
        return oldPackageId;
    }
    public void setCode1(String code1) 
    {
        this.code1 = code1;
    }

    public String getCode1() 
    {
        return code1;
    }
    public void setCode2(String code2) 
    {
        this.code2 = code2;
    }

    public String getCode2() 
    {
        return code2;
    }
    public void setCode3(String code3) 
    {
        this.code3 = code3;
    }

    public String getCode3() 
    {
        return code3;
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
            .append("oldPackageId", getOldPackageId())
            .append("code1", getCode1())
            .append("code2", getCode2())
            .append("code3", getCode3())
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
