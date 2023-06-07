package com.ruoyi.system.domain.busenum;

/**
 * 订单状态详情
 */
public enum ImportTypeEnum {

    local("local", "本地面单导入"),
    ref("ref", "转寄面单导入"),
    original("original", "面单导入");

    private final String typeValue;

    private final String typeName;

    ImportTypeEnum(String typeValue, String typeName) {
        this.typeValue = typeValue;
        this.typeName = typeName;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public String getTypeName() {
        return typeName;
    }

    public String value() {
        return name();
    }

    public static ImportTypeEnum fromValue(String v) {
        return valueOf(v);
    }

}
