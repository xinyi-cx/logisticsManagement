package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 转寄关联关系对象 redirect_rel
 * 
 * @author ruoyi
 * @date 2022-08-12
 */
public class ExportRedirectRelVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

//    @Excel(name = "Create Date")

    @Excel(name = "Country")
    private String countryCode;
//    @Excel(name = "Sender")


    /** 旧物流单号 */
//    @Excel(name = "旧物流单号")
    private String oldWaybill;

    /** 新的单号 */
    @Excel(name = "Order number")
    private String newOrder;

    /** 回退单号 */
    @Excel(name = "Old return number")
    private String backOrder;

    /** 新物流单号 */
    @Excel(name = "New Waybill")
    private String newWaybill;

    /** 最新物流时间 */
    @Excel(name = "Last Status Date")
    private String lastTime;

    /** 最新物流信息 */
//    @Excel(name = "最新物流信息")
    private String lastMsg;

    @Excel(name = "Status")
    private String status;

//    @Excel(name = "new parcel number")



    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getLastMsg() {
        return lastMsg;
    }

    public void setLastMsg(String lastMsg) {
        this.lastMsg = lastMsg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    private String boxRemarkOne;

    private String boxRemarkTwo;

    private String boxRemarkThree;

    public String getBoxRemarkOne() {
        return boxRemarkOne;
    }

    public void setBoxRemarkOne(String boxRemarkOne) {
        this.boxRemarkOne = boxRemarkOne;
    }

    public String getBoxRemarkTwo() {
        return boxRemarkTwo;
    }

    public void setBoxRemarkTwo(String boxRemarkTwo) {
        this.boxRemarkTwo = boxRemarkTwo;
    }

    public String getBoxRemarkThree() {
        return boxRemarkThree;
    }

    public void setBoxRemarkThree(String boxRemarkThree) {
        this.boxRemarkThree = boxRemarkThree;
    }


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
