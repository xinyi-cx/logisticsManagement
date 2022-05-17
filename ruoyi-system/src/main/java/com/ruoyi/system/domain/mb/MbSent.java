package com.ruoyi.system.domain.mb;

public class MbSent {

    private String code;//内部订单号
    private String changeStatus;// sent
    private String supplierInnerCode;//物流内部单号
    private String expressChannelCode;//物流跟踪单号
    private String weightReal;//订单实际重量
    private String priceReal;//订单实际运费
    private String labelHTMLUrl;//结构同例 1
    private String labelPDFUrl;//结构同例 1

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

    public String getWeightReal() {
        return weightReal;
    }

    public void setWeightReal(String weightReal) {
        this.weightReal = weightReal;
    }

    public String getPriceReal() {
        return priceReal;
    }

    public void setPriceReal(String priceReal) {
        this.priceReal = priceReal;
    }

    public String getLabelHTMLUrl() {
        return labelHTMLUrl;
    }

    public void setLabelHTMLUrl(String labelHTMLUrl) {
        this.labelHTMLUrl = labelHTMLUrl;
    }

    public String getLabelPDFUrl() {
        return labelPDFUrl;
    }

    public void setLabelPDFUrl(String labelPDFUrl) {
        this.labelPDFUrl = labelPDFUrl;
    }
}
