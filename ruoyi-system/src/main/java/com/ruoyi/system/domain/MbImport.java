package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;

public class MbImport {

    /** 内部订单号 */
    @Excel(name = "马帮内部订单号")
    private String code;

    private String pdfUrl;

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
