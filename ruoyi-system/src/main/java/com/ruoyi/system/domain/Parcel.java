package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.ObjectUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 包裹对象 parcel
 *
 * @author ruoyi
 * @date 2022-01-11
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Parcel extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 长
     */
    @Excel(name = "长")
    private Integer sizeX;

    /**
     * 宽
     */
    @Excel(name = "宽")
    private Integer sizeY;

    /**
     * 高
     */
    @Excel(name = "高")
    private Integer sizeZ;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String content;

    /**
     * 客户数据1
     */
    @Excel(name = "客户数据1")
    //物品信息
    private String customerData1;

    /**
     * 内部引用号
     */
    @Excel(name = "内部引用号")
    private String reference;

    /**
     * 重量
     */
    @Excel(name = "重量")
    private BigDecimal weight;

    /**
     * pack_id
     */
    @Excel(name = "pack_id")
    private Long packId;

    /**
     * waybill 运货单
     */
    @Excel(name = "waybill 运货单")
    private String waybill;

    /**
     * 包裹状态
     */
    @Excel(name = "包裹状态")
    //最终状态，签收or回退or重寄
    private String status;

    /**
     * package_id
     */
    @Excel(name = "package_id")
    private Long packageId;

    private Long parcelId;

    /**
     * 创建人
     */
    @Excel(name = "创建人")
    private String createUser;

    /**
     * 更新人
     */
    @Excel(name = "更新人")
    private String updateUser;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedTime;

    /**
     * 是否删除
     */
    @Excel(name = "是否删除")
    private Integer isDelete;

//    update_last_flag
    private String updateLastFlag;

    private boolean updateFlag = false;

    public boolean isUpdateFlag() {
        return updateFlag;
    }

    public void setWeight(BigDecimal weight) {
        if (ObjectUtils.isEmpty(weight)){
            this.weight = BigDecimal.valueOf(1);
        } else {
            if (weight.compareTo(BigDecimal.valueOf(1)) < 1) {
                this.weight = BigDecimal.valueOf(1);
            } else {
                this.weight = weight;
            }
        }
    }

}
