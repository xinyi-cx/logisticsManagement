package com.ruoyi.system.domain.mb;

public class grand_qingyi {
    private String api_key;//物流令牌
    private String api_secret;//物流秘钥  //如果物流授权参数只有一个，则接口只有api_key 一个参数。

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getApi_secret() {
        return api_secret;
    }

    public void setApi_secret(String api_secret) {
        this.api_secret = api_secret;
    }
}
