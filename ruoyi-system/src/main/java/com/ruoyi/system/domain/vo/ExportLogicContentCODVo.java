package com.ruoyi.system.domain.vo;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.enums.SysWaybill;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

/**
 * 导入查询物流对象 import_logic_content
 *
 * @date 2022-11-20
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ExportLogicContentCODVo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * document表id
     */
//    @Excel(name = "document表id")
    private Long documentFileId;

    /**
     * package表id
     */
//    @Excel(name = "package表id")
    private Long packId;

    /**
     * batch_id
     */
//    @Excel(name = "batch_id")
    private Long batchId;

    /**
     * logic_id
     */
//    @Excel(name = "logic_id")
    private Long logicId;

    private String fileName;

    /**
     * CLIENT
     */
    @Excel(name = "客户")
    private String client;

    /**
     * Country
     */
    @Excel(name = "国家")
    private String country;

    /**
     * Type
     */
//    @Excel(name = "Type")
    private String importType;

    /**
     * LoginID
     */
//    @Excel(name = "LoginID")
    private String loginid;

    /**
     * New waybill
     */
    @Excel(name = "单号")
    private String newWaybill;

    /**
     * Order number
     */
//    @Excel(name = "Order number")
    private String orderNumber;

    /**
     * value PLN (COD)
     */
    @Excel(name = "COD金额")
    private String valuePlnCod;

    /**
     * Weight kg
     */
//    @Excel(name = "Weight kg")
    private String weightKg;

    /**
     * recipient name
     */
//    @Excel(name = "recipient name")
    private String recipientName;

    /**
     * Recipient phone
     */
//    @Excel(name = "Recipient phone")
    private String recipientPhone;

    /**
     * Recipient E-mail
     */
//    @Excel(name = "Recipient E-mail")
    private String recipientEmail;

    /**
     * Description
     */
//    @Excel(name = "Description")
    private String description;

    /**
     * Need box
     */
//    @Excel(name = "Need box")
    private String needBox;

    /**
     * Create Date
     */
//    @Excel(name = "Create Date")
    private String createDate;

    @Excel(name = "激活日期")
    private String activedDate;

    @Excel(name = "签收日期")
    private String lastStatusDate;

//    @Excel(name = "STATUS")
    private String status;

//    @Excel(name = "new number")
    private String newNumber;

//    @Excel(name = "return number")
    private String returnNumber;

    /**
     * 备注2
     */
//    @Excel(name = "Code 1")
    private String remark2;

    /** 备注3 */
//    @Excel(name = "Code 2")
    private String remark3;

    /**
     * 备注4
     */
//    @Excel(name = "old return number")
    private String remark4;

    /** 备注5 */
//    @Excel(name = "old shipment number")
    private String remark5;

    /**
     * 是否删除
     */
//    @Excel(name = "是否删除")
    private Integer isDelete;

    private String boxRemarkOne;

    private String boxRemarkTwo;

    private String boxRemarkThree;

    public void setStatus(String status) {
        String statusInfo = SysWaybill.getInfoByCode(status);
        if (StringUtils.isNotEmpty(statusInfo)) {
            this.status = statusInfo;
        } else {
            this.status = status;
        }
    }

}
