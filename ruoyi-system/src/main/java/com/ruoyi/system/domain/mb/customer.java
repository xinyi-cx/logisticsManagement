package com.ruoyi.system.domain.mb;

public class customer {
    private String username;

    private String name;

    private logisticsKeys logisticsKeys;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public com.ruoyi.system.domain.mb.logisticsKeys getLogisticsKeys() {
        return logisticsKeys;
    }

    public void setLogisticsKeys(com.ruoyi.system.domain.mb.logisticsKeys logisticsKeys) {
        this.logisticsKeys = logisticsKeys;
    }
}
