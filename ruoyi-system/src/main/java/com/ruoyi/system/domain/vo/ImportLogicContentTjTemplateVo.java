package com.ruoyi.system.domain.vo;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 导入查询物流对象 import_logic_content
 *
 * @author ruoyi
 * @date 2022-11-20
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ImportLogicContentTjTemplateVo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * New waybill
     */
    @Excel(name = "New waybill")
    private String newWaybill;

    @Excel(name = "ReturnParcel")
    private String returnNumber;

}
