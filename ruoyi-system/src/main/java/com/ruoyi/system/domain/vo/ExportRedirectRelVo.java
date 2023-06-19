package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 转寄关联关系对象 redirect_rel
 * 
 * @date 2022-08-12
 */
@EqualsAndHashCode(callSuper = true)
@Data
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

}
