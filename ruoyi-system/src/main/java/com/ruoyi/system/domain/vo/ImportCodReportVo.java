package com.ruoyi.system.domain.vo;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * COD报告对象 cod_report
 * 
 * @date 2023-05-02
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ImportCodReportVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** document表id */
//    @Excel(name = "document表id")
    private Long documentFileId;

    /** package表id */
//    @Excel(name = "package表id")
    private Long packId;

    /** batch_id */
//    @Excel(name = "batch_id")
    private Long batchId;

    /** logic_id */
//    @Excel(name = "logic_id")
    private Long logicId;

    /** CLIENT */
//    @Excel(name = "CLIENT")
    private String client;

    /** Country */
//    @Excel(name = "Country")
    private String country;

    /** Type */
//    @Excel(name = "Type")
    private String importType;

    /** LoginID */
//    @Excel(name = "LoginID")
    private String loginid;

    /** nr_klienta */
    @Excel(name = "Nr_klienta")
    private String nrKlienta;

    /** 下载收件人 */
    @Excel(name = "Odbiorca pobrania")
    private String downloadRecipient;

    /** 运单号码（货件） */
    @Excel(name = "Nr listu przewozowego (przesyłki)")
    private String waybill;

    /** 运单（过账）日期 */
    @Excel(name = "Data listu przewozowego (nadania)")
    private String waybillPostingDate;

    /** 货到付款金额 （货到付款） */
    @Excel(name = "Kwota pobrania (COD)")
    private String cashOnDelivery;

    /** 货件有效交付日期 */
    @Excel(name = "Data skutecznego doręczenia przesyłki")
    private String goodsEffectiveDeliveryDate;

    /** 货件收件人 */
    @Excel(name = "Odbiorca przesyłki")
    private String addressee;

    /** 邮政编码 */
    @Excel(name = "Kod pocztowy")
    private String postalCode;

    /** 地区 */
    @Excel(name = "Miejscowość")
    private String area;

    /** 街 */
    @Excel(name = "Ulica")
    private String street;

    /** Unisoft-Client中的内容字段 */
    @Excel(name = "Pole 'zawartość' w programie Unisoft-Klient")
    private String unisoftClientContentField;

    /** 批量传输 */
    @Excel(name = "Zbiorczy przelew")
    private String batchTransmission;

    /** 集体移送日期 */
    @Excel(name = "Data zbiorczego przelewu")
    private String collectiveTransferDate;

    /** 结算编号 （怀俄明州） */
    @Excel(name = "Nr rozliczenia (WY)")
    private String settlementNumber;

    /** 激活时间 */
//    @Excel(name = "激活时间")
    private String sysActivedDate;

    /** 最近物流时间 */
//    @Excel(name = "最近物流时间")
    private String sysLastStatusDate;

    /** 签收时间 */
//    @Excel(name = "签收时间")
    private String sysSignDate;

    /** 备注2 */
//    @Excel(name = "备注2")
    private String remark2;

    /** 备注3 */
//    @Excel(name = "备注3")
    private String remark3;

    /** 备注4 */
//    @Excel(name = "备注4")
    private String remark4;

    /** 备注5 */
//    @Excel(name = "备注5")
    private String remark5;

    /** 是否删除 */
//    @Excel(name = "是否删除")
    private Integer isDelete;

}
