package com.ruoyi.system.domain.busenum;

import static java.util.Arrays.stream;

/**
 * 订单状态详情
 */
public enum StatusEnum {

    ycdd(-1,"异常订单"),
    drk(2,"待入库"),
    yrk(3,"已入库"),
    yck(4,"已出库"),
    ywc(5,"已完成"),
    yqr(6,"已确认")
    ;

    private final int statusValue;

    private final String statusName;

    StatusEnum(int statusValue, String statusName) {
        this.statusValue = statusValue;
        this.statusName = statusName;
    }

    public int getStatusValue() {
        return statusValue;
    }

    public String getStatusName() {
        return statusName;
    }

    public String value() {
        return name();
    }

    public static StatusEnum fromValue(String v) {
        return valueOf(v);
    }

    public static String getStatusName(int statusValue) {
        return stream(StatusEnum.values())
                .filter(code -> statusValue == code.getStatusValue())
                .findFirst().map(StatusEnum::getStatusName)
                .orElse(null);
    }

}