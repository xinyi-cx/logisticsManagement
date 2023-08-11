package com.ruoyi.system.domain.mb;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

@Getter
@Setter
public class MbReturnDto extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 内部订单号 */
    @Excel(name = "内部订单号")
    private String code;

    /** _e_r_p自定义单号 */
    @Excel(name = "_e_r_p自定义单号")
    private String customizeCode;

    /** 平台订单编号：例如ebay,wish 等订单编号 */
    @Excel(name = "平台订单编号：例如ebay,wish 等订单编号")
    private String platformTradeCode;

    /** 订单状态 */
    @Excel(name = "订单状态")
    private String status;

    /** 是否有异常 */
    @Excel(name = "是否有异常")
    private String hasException;

    /** 异常信息，物流供应商处理信息 */
    @Excel(name = "异常信息，物流供应商处理信息")
    private String processMessage;

    /** package _id */
    @Excel(name = "package _id")
    private String packageId;

    /** 预计订单运费 */
    @Excel(name = "预计订单运费")
    private String priceForcast;

    /** 实际订单运费 */
    @Excel(name = "实际订单运费")
    private String priceReal;

    /** 包裹地址国家代码 */
    @Excel(name = "包裹地址国家代码")
    private String shippingCountryCode;

    @Excel(name = "currencyId")
    private String currencyId;

    /** 订单生成时间 */
    @Excel(name = "订单生成时间")
    private String timeCreated;

    /** 客户预报订单重量（单位：g 克） */
    @Excel(name = "客户预报订单重量", readConverterExp = "单位：g,克=")
    private String weightForcast;

    /** 供应商实际称重订单重量（单位：g 克） */
    @Excel(name = "供应商实际称重订单重量", readConverterExp = "单位：g,克=")
    private String weightReal;

    /** 包裹长度（单位：mm 毫米） */
    @Excel(name = "包裹长度", readConverterExp = "单位：mm,毫=米")
    private String length;

    /** 包裹宽度（单位：mm 毫米） */
    @Excel(name = "包裹宽度", readConverterExp = "单位：mm,毫=米")
    private String width;

    /** 包裹高度（单位：mm 毫米） */
    @Excel(name = "包裹高度", readConverterExp = "单位：mm,毫=米")
    private String height;

    /** 订单申报物品中文名 */
    @Excel(name = "订单申报物品中文名")
    private String productNameCn;

    /** 订单申报物品英文名 */
    @Excel(name = "订单申报物品英文名")
    private String productNameEn;

    /** 订单申报价值(US_d) */
    @Excel(name = "订单申报价值(US_d)")
    private String productValue;

    /**  货物数量 */
    @Excel(name = " 货物数量")
    private String itemListQuantity;

    /**  订单项数量 */
    @Excel(name = " 订单项数量")
    private String itemListCount;

    /**  申报币种 */
    @Excel(name = " 申报币种")
    private String currencyCode;

    /** 交易平台付款时间 */
    @Excel(name = "交易平台付款时间")
    private String platformPayTime;

    /** { _vendor_i_d : 澳洲GS_t供应商_i_d\ abnnumber : 税号 } */
    private String AUSDetails;

    /** cod 订单标识  1:是;2:否 */
    @Excel(name = "cod 订单标识  1:是;2:否")
    private String codFlag;

    /** cod 订单金额（币种为买家当地币种） */
    @Excel(name = "cod 订单金额", readConverterExp = "币种为买家当地币种")
    private String codValue;

    /** 商家税号 */
    @Excel(name = "商家税号")
    private String vatNo;

    /** 商家税号类型 1.欧盟国_iOSS 编号；2.非_iOSS或其他国家税号 编号；3.空表示other */
    @Excel(name = "商家税号类型 1.欧盟国_iOSS 编号；2.非_iOSS或其他国家税号 编号；3.空表示other")
    private String vatType;

    /** 订单是否带电 1.是 2.否 */
    @Excel(name = "订单是否带电 1.是 2.否")
    private String hasBattery;

    /** 订单是否含磁 1.是 2.否 */
    @Excel(name = "订单是否含磁 1.是 2.否")
    private String hasMagnetic;

    /** 订单是否含粉末 1.是 2.否 */
    @Excel(name = "订单是否含粉末 1.是 2.否")
    private String hasPowder;

    private extendFields extendFields;
    private List<MbItem> itemList;
    private addressPickup addressPickup;
    private addressReceive addressReceive;
    private addressBack addressBack;

    /** $column.columnComment */
    @Excel(name = "customer")
    private String customer;

    /** 物流产品运单号 */
    @Excel(name = "物流产品运单号")
    private String expressChannelCode;

    /** 物流商内部单 */
    @Excel(name = "物流商内部单")
    private String supplierInnerCode;

    /** $column.columnComment */
    @Excel(name = "expressChannel")
    private String expressChannel;

    /** $column.columnComment */
    @Excel(name = "myExpressChannel")
    private String myExpressChannel;

    /** $column.columnComment */
    @Excel(name = "labelHTMLUrl")
    private String labelHTMLUrl;

    /** $column.columnComment */
    @Excel(name = "extendFieldsStr")
    private String labelPDFUrl;

    /** $column.columnComment */
    @Excel(name = "extendFieldsStr")
    private String extendFieldsStr;

    /** 商品SKU */
    @Excel(name = "商品SKU")
    private String itemListStr;

    /** 发件人 */
    @Excel(name = "发件人")
    private String addressPickupStr;

    /** 收件人 */
    @Excel(name = "收件人")
    private String addressReceiveStr;

    /** 退件人 */
    @Excel(name = "退件人")
    private String addressBackStr;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("code", getCode())
                .append("customizeCode", getCustomizeCode())
                .append("platformTradeCode", getPlatformTradeCode())
                .append("status", getStatus())
                .append("hasException", getHasException())
                .append("processMessage", getProcessMessage())
                .append("packageId", getPackageId())
                .append("priceForcast", getPriceForcast())
                .append("priceReal", getPriceReal())
                .append("shippingCountryCode", getShippingCountryCode())
                .append("timeCreated", getTimeCreated())
                .append("weightForcast", getWeightForcast())
                .append("weightReal", getWeightReal())
                .append("length", getLength())
                .append("width", getWidth())
                .append("height", getHeight())
                .append("productNameCn", getProductNameCn())
                .append("productNameEn", getProductNameEn())
                .append("productValue", getProductValue())
                .append("remark", getRemark())
                .append("itemListQuantity", getItemListQuantity())
                .append("itemListCount", getItemListCount())
                .append("currencyCode", getCurrencyCode())
                .append("platformPayTime", getPlatformPayTime())
                .append("AUSDetails", getAUSDetails())
                .append("codFlag", getCodFlag())
                .append("codValue", getCodValue())
                .append("vatNo", getVatNo())
                .append("vatType", getVatType())
                .append("hasBattery", getHasBattery())
                .append("hasMagnetic", getHasMagnetic())
                .append("hasPowder", getHasPowder())
                .append("customer", getCustomer())
                .append("expressChannelCode", getExpressChannelCode())
                .append("supplierInnerCode", getSupplierInnerCode())
                .append("expressChannel", getExpressChannel())
                .append("myExpressChannel", getMyExpressChannel())
                .append("labelHTMLUrl", getLabelHTMLUrl())
                .append("labelPDFUrl", getLabelPDFUrl())
                .append("extendFieldsStr", getExtendFieldsStr())
                .append("itemListStr", getItemListStr())
                .append("addressPickupStr", getAddressPickupStr())
                .append("addressReceiveStr", getAddressReceiveStr())
                .append("addressBackStr", getAddressBackStr())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
