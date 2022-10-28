package com.ruoyi.system.task;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.Parcel;
import com.ruoyi.system.service.IParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * dpd定时任务调度
 * 
 * @author ruoyi
 */
@Component("dpdTask")
public class DPDTask
{
    @Autowired
    private IParcelService parcelService;

    public void ryMultipleParams(String s, Boolean b, Long l, Double d, Integer i)
    {
        System.out.println(StringUtils.format("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{}", s, b, l, d, i));
    }

    public void ryParams(String params)
    {
        System.out.println("执行有参方法：" + params);
    }

    public void dpdTaskGetParcelMsg()
    {
        System.out.println("获取物流信息");
        parcelService.getParcelMsg(new Parcel());
    }

    public void dpdTaskGetAllParcelMsg()
    {
        System.out.println("获取物流信息");
        parcelService.getAllParcelMsg();
    }

}
