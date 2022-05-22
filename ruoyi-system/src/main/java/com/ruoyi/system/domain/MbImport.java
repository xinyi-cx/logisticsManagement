package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;

public class MbImport {

    /** 内部订单号 */
    @Excel(name = "马帮内部订单号")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
