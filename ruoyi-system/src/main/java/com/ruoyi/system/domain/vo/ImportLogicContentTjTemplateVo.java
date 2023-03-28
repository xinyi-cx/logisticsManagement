package com.ruoyi.system.domain.vo;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 导入查询物流对象 import_logic_content
 *
 * @author ruoyi
 * @date 2022-11-20
 */
public class ImportLogicContentTjTemplateVo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * New waybill
     */
    @Excel(name = "New waybill")
    private String newWaybill;

    @Excel(name = "ReturnParcel")
    private String returnNumber;

    public String getNewWaybill() {
        return newWaybill;
    }

    public void setNewWaybill(String newWaybill) {
        this.newWaybill = newWaybill;
    }

    public String getReturnNumber() {
        return returnNumber;
    }

    public void setReturnNumber(String returnNumber) {
        this.returnNumber = returnNumber;
    }
}
