package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 收货人信息对象 address_receiver
 * 
 * @author ruoyi
 * @date 2022-01-11
 */
@Getter
@Setter
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
    //导入的时候
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

    /** 货物金额 */
    @Excel(name = "货物金额")
    private BigDecimal pln;

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
            .append("name", getName())
            .append("phone", getPhone())
            .append("postalCode", getPostalCode())
            .append("pln", getPln())
            .append("createUser", getCreateUser())
            .append("updateUser", getUpdateUser())
            .append("createdTime", getCreatedTime())
            .append("updatedTime", getUpdatedTime())
            .append("isDelete", getIsDelete())
            .toString();
    }
}
