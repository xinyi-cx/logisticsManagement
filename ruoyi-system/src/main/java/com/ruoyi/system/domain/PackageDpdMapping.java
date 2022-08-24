package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 面单dpd对应关系对象 package_dpd_mapping
 * 
 * @author ruoyi
 * @date 2022-07-27
 */
public class PackageDpdMapping extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** package字段 */
    @Excel(name = "package字段")
    private String packageField;

    /** dpd字段 */
    @Excel(name = "dpd字段")
    private String dpdField;

    /** 导入字段 */
    @Excel(name = "导入字段")
    private String importField;

    /** 导入名字 */
    @Excel(name = "导入名字")
    private String importName;

    /** 国家代码 */
    @Excel(name = "国家代码")
    private String countryCode;

    /** 表名字 */
    @Excel(name = "表名字")
    private String tableName;

    /** 备用字段 */
    @Excel(name = "备用字段")
    private String backUpField;

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
    public void setPackageField(String packageField) 
    {
        this.packageField = packageField;
    }

    public String getPackageField() 
    {
        return packageField;
    }
    public void setDpdField(String dpdField) 
    {
        this.dpdField = dpdField;
    }

    public String getDpdField() 
    {
        return dpdField;
    }
    public void setImportField(String importField) 
    {
        this.importField = importField;
    }

    public String getImportField() 
    {
        return importField;
    }
    public void setImportName(String importName) 
    {
        this.importName = importName;
    }

    public String getImportName() 
    {
        return importName;
    }
    public void setCountryCode(String countryCode) 
    {
        this.countryCode = countryCode;
    }

    public String getCountryCode() 
    {
        return countryCode;
    }
    public void setTableName(String tableName) 
    {
        this.tableName = tableName;
    }

    public String getTableName() 
    {
        return tableName;
    }
    public void setBackUpField(String backUpField) 
    {
        this.backUpField = backUpField;
    }

    public String getBackUpField() 
    {
        return backUpField;
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
            .append("packageField", getPackageField())
            .append("dpdField", getDpdField())
            .append("importField", getImportField())
            .append("importName", getImportName())
            .append("countryCode", getCountryCode())
            .append("tableName", getTableName())
            .append("backUpField", getBackUpField())
            .append("remark", getRemark())
            .append("createUser", getCreateUser())
            .append("updateUser", getUpdateUser())
            .append("createdTime", getCreatedTime())
            .append("updatedTime", getUpdatedTime())
            .append("isDelete", getIsDelete())
            .toString();
    }
}
