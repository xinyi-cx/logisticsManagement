package com.ruoyi.system.domain.busenum;

/**
 * 订单状态详情
 */
public enum ChangeStatusEnum {

    accept("accept","已确认"),
    received("received","已入库"),
    sent("sent","已出库"),
    exception("exception","异常"),
    cancel("cancel","订单取消")
    ;

    private final String statusCode;

    private final String statusName;

    ChangeStatusEnum(String statusCode, String statusName) {
        this.statusCode = statusCode;
        this.statusName = statusName;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public String value() {
        return name();
    }

    public static ChangeStatusEnum fromValue(String v) {
        return valueOf(v);
    }

}