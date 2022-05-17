package com.ruoyi.system.domain.mb;

public class addressReceive {
    private String countryCode;//收件人国家
    private String province;//收件人省州
    private String city;//收件人城市
    private String doorcode;//门牌号
    private String street1;//详细地址
    private String street2;//备用地址
    private String zipcode;//收件人邮编
    private String receiver;//收件人姓名
    private String district;//收件人区
    private String companyStreet;//收件人公司名称
    private String StoreCode;//门店编码,	//针对德国方向为柜号，台湾方向为门店编码
    private String StoreName;//门店名称,  //德国方向为公司名称，台湾方向为门店名称
    private String telephone;//收件人联系方式
    private String mobile;//收件人手机
    private String email;//收件人邮箱

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDoorcode() {
        return doorcode;
    }

    public void setDoorcode(String doorcode) {
        this.doorcode = doorcode;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCompanyStreet() {
        return companyStreet;
    }

    public void setCompanyStreet(String companyStreet) {
        this.companyStreet = companyStreet;
    }

    public String getStoreCode() {
        return StoreCode;
    }

    public void setStoreCode(String storeCode) {
        StoreCode = storeCode;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String storeName) {
        StoreName = storeName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
