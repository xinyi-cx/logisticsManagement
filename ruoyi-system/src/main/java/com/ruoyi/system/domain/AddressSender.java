package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 发件人信息对象 address_sender
 * 
 * @author ruoyi
 * @date 2022-01-11
 */
@Getter
@Setter
public class AddressSender extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 发件人主键 */
    private Long id;

    /** 发件人街道？地址 */
    @Excel(name = "发件人街道？地址")
    private String address;

    /** 发件人城市 */
    @Excel(name = "发件人城市")
    private String city;

    /** 发件人公司 */
    @Excel(name = "发件人公司")
    private String company;

    /** 发件人国家 */
    @Excel(name = "发件人国家")
    private String countryCode;

    /** 发件人邮件 */
    @Excel(name = "发件人邮件")
    private String email;

    /** 发件人fid */
    @Excel(name = "发件人fid")
    private Long fid;

    /** 发件人姓名 */
    @Excel(name = "发件人姓名")
    private String name;

    /** 发件人手机号码 */
    @Excel(name = "发件人手机号码")
    private String phone;

    /** 发件人邮政编码 */
    @Excel(name = "发件人邮政编码")
    private String postalCode;

    /** 发件人创建人 */
    @Excel(name = "发件人创建人")
    private String createUser;

    /** 发件人更新人 */
    @Excel(name = "发件人更新人")
    private String updateUser;

    /** 发件人创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发件人创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 发件人更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发件人更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedTime;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Integer isDelete;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("address", getAddress())
            .append("city", getCity())
            .append("company", getCompany())
            .append("countryCode", getCountryCode())
            .append("email", getEmail())
            .append("fid", getFid())
            .append("name", getName())
            .append("phone", getPhone())
            .append("postalCode", getPostalCode())
            .append("createUser", getCreateUser())
            .append("updateUser", getUpdateUser())
            .append("createdTime", getCreatedTime())
            .append("updatedTime", getUpdatedTime())
            .append("isDelete", getIsDelete())
            .toString();
    }
}
