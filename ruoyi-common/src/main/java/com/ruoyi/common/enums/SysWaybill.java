package com.ruoyi.common.enums;

/**
 * 物流状态
 * 
 */
public enum SysWaybill
{
    WJH("0", "未激活"),
    YSZ("1", "运输中"),
    GP("2", "改派"),
    YQS("3", "已签收"),
    YTJ("4", "已退件"),
    ZJ("5", "转寄");

    private final String code;
    private final String info;

    SysWaybill(String code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public String getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
}
