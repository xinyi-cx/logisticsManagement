package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 收货人信息对象 address_receiver
 * 
 * @author ruoyi
 * @date 2022-01-02
 */
public class AddressReceiver extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 收件人主键 */
    private Long id;

    /** 收件人地址 */
    @Excel(name = "收件人地址")
    private String address;

    /** 收件人城市 */
    @Excel(name = "收件人城市")
    private String city;

    /** 收件人公司 */
    @Excel(name = "收件人公司")
    private String company;

    /** 收件人国家编码 */
    @Excel(name = "收件人国家编码")
    private String countryCode;

    /** 收件人邮件 */
    @Excel(name = "收件人邮件")
    private String email;

    /** 收件人姓名 */
    @Excel(name = "收件人姓名")
    private String name;

    /** 收件人手机号码 */
    @Excel(name = "收件人手机号码")
    private String phone;

    /** 收件人邮政编码 */
    @Excel(name = "收件人邮政编码")
    private String postalCode;

    /** 收件人创建人 */
    @Excel(name = "收件人创建人")
    private String createUser;

    /** 收件人更新人 */
    @Excel(name = "收件人更新人")
    private String updateUser;

    /** 收件人创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "收件人创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 收件人更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "收件人更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setCompany(String company) 
    {
        this.company = company;
    }

    public String getCompany() 
    {
        return company;
    }
    public void setCountryCode(String countryCode) 
    {
        this.countryCode = countryCode;
    }

    public String getCountryCode() 
    {
        return countryCode;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("address", getAddress())
            .append("city", getCity())
            .append("company", getCompany())
            .append("countryCode", getCountryCode())
            .append("email", getEmail())
            .append("name", getName())
            .append("phone", getPhone())
            .append("postalCode", getPostalCode())
            .append("createUser", getCreateUser())
            .append("updateUser", getUpdateUser())
            .append("createdTime", getCreatedTime())
            .append("updatedTime", getUpdatedTime())
            .toString();
    }
}
