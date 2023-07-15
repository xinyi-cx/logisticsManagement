package com.ruoyi.system.domain.mb;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Userdeliverinfo extends BaseEntity {

    @Excel(name = "id")
    private String id;

    @Excel(name = "用户运单号")
    private String waybill;

    @Excel(name = "用户邮箱")
    private String email;

    @Excel(name = "用户问题描述")
    private String issuedesc;

    @Excel(name = "信息提交时间")
    private String inserttime;

    @Excel(name = "运单国别")
    private String country;

    @Excel(name = "运单物流商")
    private String logistics;

    private String begintime;

    private String endtime;

}
