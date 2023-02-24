package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户fid对应关系对象 user_fid_rel
 *
 * @author ruoyi
 * @date 2023-02-20
 */
public class UserFidRel extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户ID
     */
    @Excel(name = "用户ID")
    private Long userId;

    /**
     * 用户账号
     */
    @Excel(name = "用户账号")
    private String userName;

    /**
     * 用户账号
     */
    @Excel(name = "用户账号")
    private String customerName;

    /**
     * 用户昵称
     */
    @Excel(name = "用户昵称")
    private String nickName;

    /**
     * 国家
     */
    @Excel(name = "国家")
    private String country;

    /**
     * fid
     */
    @Excel(name = "fid")
    private Long fid;

    /**
     * fid备注
     */
    @Excel(name = "fid备注")
    private String fidCommon;

    /**
     * 备注2
     */
    @Excel(name = "备注2")
    private String remark2;

    /**
     * 是否激活
     */
    @Excel(name = "是否激活")
    private Integer isActive;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public Long getFid() {
        return fid;
    }

    public void setFidCommon(String fidCommon) {
        this.fidCommon = fidCommon;
    }

    public String getFidCommon() {
        return fidCommon;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getIsActive() {
        return isActive;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("userId", getUserId())
                .append("userName", getUserName())
                .append("customerName", getCustomerName())
                .append("nickName", getNickName())
                .append("country", getCountry())
                .append("fid", getFid())
                .append("fidCommon", getFidCommon())
                .append("remark", getRemark())
                .append("remark2", getRemark2())
                .append("isActive", getIsActive())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
