package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 面单返回值对象 packages_generation_response
 * 
 * @author ruoyi
 * @date 2022-01-11
 */
public class PackagesGenerationResponse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long sessionId;

    private Long packageId;

    private String status;

    private String pkgReference;

    private String pkgStatus;

    private Long packId;

    private String createUser;

    private String updateUser;

    private Date createdTime;

    private Date updatedTime;

    private Integer isDelete;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSessionId(Long sessionId) 
    {
        this.sessionId = sessionId;
    }

    public Long getSessionId() 
    {
        return sessionId;
    }
    public void setPackageId(Long packageId) 
    {
        this.packageId = packageId;
    }

    public Long getPackageId() 
    {
        return packageId;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setPkgReference(String pkgReference) 
    {
        this.pkgReference = pkgReference;
    }

    public String getPkgReference() 
    {
        return pkgReference;
    }
    public void setPkgStatus(String pkgStatus) 
    {
        this.pkgStatus = pkgStatus;
    }

    public String getPkgStatus() 
    {
        return pkgStatus;
    }
    public void setPackId(Long packId) 
    {
        this.packId = packId;
    }

    public Long getPackId() 
    {
        return packId;
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
            .append("sessionId", getSessionId())
            .append("packageId", getPackageId())
            .append("status", getStatus())
            .append("pkgReference", getPkgReference())
            .append("pkgStatus", getPkgStatus())
            .append("packId", getPackId())
            .append("createUser", getCreateUser())
            .append("updateUser", getUpdateUser())
            .append("createdTime", getCreatedTime())
            .append("updatedTime", getUpdatedTime())
            .append("isDelete", getIsDelete())
            .toString();
    }
}
