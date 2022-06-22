package com.ruoyi.system.domain.mb;

import java.util.HashMap;
import java.util.Map;

public class MbAccept {

    private String code;//内部订单号
    private String changeStatus;//accept
    private String supplierInnerCode;//物流内部单号
    private String expressChannelCode;//物流跟踪单号
    private String extetiona;//扩展字段 1 客户代码等
    private String extetionb;//扩展字段 2 转单号等
    private String flowCode;//扩展字段 3 流向码等
    private String labelHTMLUrl;
    private Map<String, Object> labelPDFUrl;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getChangeStatus() {
        return changeStatus;
    }

    public void setChangeStatus(String changeStatus) {
        this.changeStatus = changeStatus;
    }

    public String getSupplierInnerCode() {
        return supplierInnerCode;
    }

    public void setSupplierInnerCode(String supplierInnerCode) {
        this.supplierInnerCode = supplierInnerCode;
    }

    public String getExpressChannelCode() {
        return expressChannelCode;
    }

    public void setExpressChannelCode(String expressChannelCode) {
        this.expressChannelCode = expressChannelCode;
    }

    public String getExtetiona() {
        return extetiona;
    }

    public void setExtetiona(String extetiona) {
        this.extetiona = extetiona;
    }

    public String getExtetionb() {
        return extetionb;
    }

    public void setExtetionb(String extetionb) {
        this.extetionb = extetionb;
    }

    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    public String getLabelHTMLUrl() {
        return labelHTMLUrl;
    }

    public void setLabelHTMLUrl(String labelHTMLUrl) {
        this.labelHTMLUrl = labelHTMLUrl;
    }

    public Map<String, Object> getLabelPDFUrl() {
        return labelPDFUrl;
    }

    public void setLabelPDFUrl(String labelPDFUrl) {
        Map<String, Object> labelPDFUrlMap = new HashMap<>();
        Map<String, Object> b10_10 = new HashMap<>();
        b10_10.put("a", "");
        b10_10.put("c", "");
        b10_10.put("p", "");
        b10_10.put("i", "");
        b10_10.put("ac", "");
        labelPDFUrlMap.put("b10_10", b10_10);

        Map<String, Object> a4 = new HashMap<>();
        a4.put("a", "");
        a4.put("c", "");
        a4.put("p", "");
        a4.put("i", "");
        a4.put("ac", labelPDFUrl);
        labelPDFUrlMap.put("a4", a4);

        this.labelPDFUrl = labelPDFUrlMap;
    }
}
