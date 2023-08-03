package com.ruoyi.system.task;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.Parcel;
import com.ruoyi.system.service.IOuterService;
import com.ruoyi.system.service.IParcelService;
import com.ruoyi.system.service.ISyncHistoryDpdService;
import com.ruoyi.system.service.ISysConfigService;
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

    @Autowired
    private IOuterService outerService;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private ISyncHistoryDpdService syncHistoryDpdService;

    public void ryMultipleParams(String s, Boolean b, Long l, Double d, Integer i)
    {
        System.out.println(StringUtils.format("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{}", s, b, l, d, i));
    }

    public void ryParams(String params)
    {
        System.out.println("执行有参方法：" + params);
    }

    /**
     * 获取物流信息定时任务
     */
    public void dpdTaskGetParcelMsg()
    {
        System.out.println("获取物流信息");
        parcelService.getParcelMsgTask(new Parcel());
    }

    public void dpdTaskGetParcelMsgWithoutQs()
    {
        System.out.println("获取物流信息-签收不获取");
        parcelService.getParcelMsgTaskWithoutQs(new Parcel());
    }

    public void dpdTaskGetParcelMsgTaskStatusIsNull()
    {
        System.out.println("获取物流状态为空的物流信息");
        parcelService.getParcelMsgTaskStatusIsNull();
    }

    public void dpdTaskGetAllParcelMsg()
    {
        System.out.println("获取物流信息");
        parcelService.getAllParcelMsg();
    }

    public void dpdTaskDeleteParcelWithWaybillIsNull()
    {
        System.out.println("删除物流信息-不包含物流单号");
        parcelService.deleteParcelWithWaybillIsNull();
    }

    public void clearDocuments(Integer dayBefore)
    {
        System.out.println("清除Documents表文件数据");
        parcelService.clearDocuments(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, DateUtils.getDateBeforeNow(dayBefore)));
    }

    public void dpdTaskSyncStatus()
    {
        System.out.println("同步两表状态信息");
        parcelService.syncStatus();
    }

    public void dpdTaskSyncMbInfo()
    {
        System.out.println("同步马帮信息");
        parcelService.syncMb();
    }

    public void dpdTaskDelMbInfo()
    {
        System.out.println("删除马帮信息");
        parcelService.delMb();
    }

    public void dpdTaskSendWaybillTodayEmail()
    {
        System.out.println("发送物流今日状态统计");
        outerService.sendEmail();
    }

    public void dpdTaskSyncRefPl()
    {
        System.out.println("同步转寄面单信息-波兰");
        String filePath = configService.selectConfigByKey("sys.sync.dpd.ref.pl.path");
        syncHistoryDpdService.syncDpdFile(filePath);
    }

    public void dpdTaskSyncRefCz()
    {
        System.out.println("同步转寄面单信息-其他国家");
        String filePath = configService.selectConfigByKey("sys.sync.dpd.ref.cz.path");
        syncHistoryDpdService.syncDpdFile(filePath);
    }

    public void dpdTaskDelSyncFile()
    {
        System.out.println("删除同步完的文件");
        syncHistoryDpdService.delSyncFile();
    }
}
