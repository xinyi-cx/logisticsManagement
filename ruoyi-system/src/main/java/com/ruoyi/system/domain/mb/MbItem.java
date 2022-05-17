package com.ruoyi.system.domain.mb;

public class MbItem {
    /**
     * 商品SKU
     */
    private String sku;
    private String platformsku;//平台sku(开放部分物流商)
    private String multiattribute;//商品多属性
    private String productName;//商品名称
    private String declareNameCn;//商品中文申报名称
    private String declareNameEn;//商品英文申报名称
    private String weight;//商品单件重量（单位：g克）
    private String quantity;//数量
    private String length;//包裹长度（单位：mm 毫米）
    private String width;//包裹宽度（单位：mm 毫米）
    private String height;//包裹高度（单位：mm 毫米）
    private String declareValue;//商品申报价值(USD)
    private String itemUrl;//交易平台商品URL地址
    private String itemId;//交易平台商品ID
    private String hsCode;//海关编码
    private String gridCode;//仓位号
    private String sellPrice;// 售 价
    private String warehouseName;//仓库名称
    private String ebayTransactionId;//ebay Transaction ID
    private String ebaySellerId;//ebay 商家ID
    private String ebayBuyerId;//ebay 买家id
    private String commodityMaterial;//商品材质
    private String commodityUse;//商品用途
    private String salesRecordNumber;//平台交易号

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getPlatformsku() {
        return platformsku;
    }

    public void setPlatformsku(String platformsku) {
        this.platformsku = platformsku;
    }

    public String getMultiattribute() {
        return multiattribute;
    }

    public void setMultiattribute(String multiattribute) {
        this.multiattribute = multiattribute;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDeclareNameCn() {
        return declareNameCn;
    }

    public void setDeclareNameCn(String declareNameCn) {
        this.declareNameCn = declareNameCn;
    }

    public String getDeclareNameEn() {
        return declareNameEn;
    }

    public void setDeclareNameEn(String declareNameEn) {
        this.declareNameEn = declareNameEn;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getDeclareValue() {
        return declareValue;
    }

    public void setDeclareValue(String declareValue) {
        this.declareValue = declareValue;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getHsCode() {
        return hsCode;
    }

    public void setHsCode(String hsCode) {
        this.hsCode = hsCode;
    }

    public String getGridCode() {
        return gridCode;
    }

    public void setGridCode(String gridCode) {
        this.gridCode = gridCode;
    }

    public String getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getEbayTransactionId() {
        return ebayTransactionId;
    }

    public void setEbayTransactionId(String ebayTransactionId) {
        this.ebayTransactionId = ebayTransactionId;
    }

    public String getEbaySellerId() {
        return ebaySellerId;
    }

    public void setEbaySellerId(String ebaySellerId) {
        this.ebaySellerId = ebaySellerId;
    }

    public String getEbayBuyerId() {
        return ebayBuyerId;
    }

    public void setEbayBuyerId(String ebayBuyerId) {
        this.ebayBuyerId = ebayBuyerId;
    }

    public String getCommodityMaterial() {
        return commodityMaterial;
    }

    public void setCommodityMaterial(String commodityMaterial) {
        this.commodityMaterial = commodityMaterial;
    }

    public String getCommodityUse() {
        return commodityUse;
    }

    public void setCommodityUse(String commodityUse) {
        this.commodityUse = commodityUse;
    }

    public String getSalesRecordNumber() {
        return salesRecordNumber;
    }

    public void setSalesRecordNumber(String salesRecordNumber) {
        this.salesRecordNumber = salesRecordNumber;
    }
}
