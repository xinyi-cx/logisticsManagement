package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 【请填写功能名称】对象 services
 * 
 *
 * @date 2022-01-11
 */
public class Services extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;

    private String codAmount;

    private String codCurrency;

    private String createUser;

    private String updateUser;

    private Date createdTime;

    private Date updatedTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCodAmount(String codAmount) 
    {
        this.codAmount = codAmount;
    }

    public String getCodAmount() 
    {
        return codAmount;
    }
    public void setCodCurrency(String codCurrency) 
    {
        this.codCurrency = codCurrency;
    }

    public String getCodCurrency() 
    {
        return codCurrency;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("codAmount", getCodAmount())
            .append("codCurrency", getCodCurrency())
            .append("createUser", getCreateUser())
            .append("updateUser", getUpdateUser())
            .append("createdTime", getCreatedTime())
            .append("updatedTime", getUpdatedTime())
            .toString();
    }
}
