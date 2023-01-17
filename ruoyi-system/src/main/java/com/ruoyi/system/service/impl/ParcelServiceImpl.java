package com.ruoyi.system.service.impl;

import com.ruoyi.system.DPDServicesExample.client.DPDInfoXMLClient;
import com.ruoyi.system.domain.LogisticsInfo;
import com.ruoyi.system.domain.Parcel;
import com.ruoyi.system.mapper.LogisticsInfoMapper;
import com.ruoyi.system.mapper.ParcelMapper;
import com.ruoyi.system.service.IParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 包裹Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-01-02
 */
@Service
public class ParcelServiceImpl implements IParcelService 
{
    @Autowired
    private ParcelMapper parcelMapper;

    @Autowired
    private LogisticsInfoMapper logisticsInfoMapper;

    @Autowired
    private DPDInfoXMLClient dpdInfoXMLClient;

    /**
     * 查询包裹
     * 
     * @param id 包裹主键
     * @return 包裹
     */
    @Override
    public Parcel selectParcelById(Long id)
    {
        return parcelMapper.selectParcelById(id);
    }

    /**
     * 查询包裹列表
     * 
     * @param parcel 包裹
     * @return 包裹
     */
    @Override
    public List<Parcel> selectParcelList(Parcel parcel)
    {
        return parcelMapper.selectParcelList(parcel);
    }

    /**
     * 新增包裹
     * 
     * @param parcel 包裹
     * @return 结果
     */
    @Override
    public int insertParcel(Parcel parcel)
    {
        return parcelMapper.insertParcel(parcel);
    }

    /**
     * 修改包裹
     * 
     * @param parcel 包裹
     * @return 结果
     */
    @Override
    public int updateParcel(Parcel parcel)
    {
        return parcelMapper.updateParcel(parcel);
    }

    /**
     * 批量删除包裹
     * 
     * @param ids 需要删除的包裹主键
     * @return 结果
     */
    @Override
    public int deleteParcelByIds(Long[] ids)
    {
        return parcelMapper.deleteParcelByIds(ids);
    }

    /**
     * 删除包裹信息
     * 
     * @param id 包裹主键
     * @return 结果
     */
    @Override
    public int deleteParcelById(Long id)
    {
        return parcelMapper.deleteParcelById(id);
    }

    @Override
    @Async
    public void getParcelMsg(Parcel parcel) {
        System.out.println("getParcelMsg start");
        List<Parcel> parcels = parcelMapper.selectParcelListNeedDeal(parcel);
        System.out.println("getParcelMsg size" + parcels.size());
        parcels.parallelStream().forEach(item -> {
                    try {
                        dpdInfoXMLClient.getEventsForOneWaybill(item);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
        System.out.println("getParcelMsg end");
    }

    @Override
    @Async
    public void getParcelMsgByLogisticsInfo(LogisticsInfo param){
        System.out.println("getParcelMsgByLogisticsInfo start");
        List<LogisticsInfo> logisticsInfos = logisticsInfoMapper.selectLogisticsInfoListNeedDeal(param);
        logisticsInfos.parallelStream().forEach(item -> dpdInfoXMLClient.getEventsByLogisticsInfo(item));
        System.out.println("getParcelMsgByLogisticsInfo end");
    }

    @Override
    public void getParcelMsgById(Long id) {
        Parcel parcel = parcelMapper.selectParcelById(id);
        dpdInfoXMLClient.getEventsForOneWaybill(parcel);
    }

    @Override
    public void getParcelMsgByLogisticsInfoId(Long id) {
        LogisticsInfo logisticsInfo = logisticsInfoMapper.selectLogisticsInfoById(id);
        dpdInfoXMLClient.getEventsByLogisticsInfo(logisticsInfo);
    }

    @Override
    public void getAllParcelMsgByLogisticsInfo(List<LogisticsInfo> logisticsInfos) {
        for (LogisticsInfo logisticsInfo : logisticsInfos) {
            dpdInfoXMLClient.getEventsByLogisticsInfo(logisticsInfo);
        }
    }

    @Override
    public void getAllParcelMsg() {
        System.out.println("getAllParcelMsg start");
        dpdInfoXMLClient.getEventsForWaybills();
        System.out.println("getAllParcelMsg end");
    }

    /**
     * 根据物流单号获取物流信息
     * @param waybill
     */
    @Override
    public void getMsgByWaybill(String waybill) {
        LogisticsInfo param = new LogisticsInfo();
        param.setWaybill(waybill);
        List<LogisticsInfo> logisticsInfos = logisticsInfoMapper.selectLogisticsInfoList(param);
        if (CollectionUtils.isEmpty(logisticsInfos)){
            LogisticsInfo logisticsInfo = new LogisticsInfo();
            logisticsInfo.setWaybill(waybill);
            dpdInfoXMLClient.getEventsByLogisticsInfo(logisticsInfo);
        }
    }

    /**
     * 根据物流单号获取物流信息
     * @param waybills
     */
    @Override
    @Async
    public void getMsgByWaybills(List<String> waybills) {
        System.out.println("getParcelMsg start");
        List<Parcel> parcels = parcelMapper.selectParcelListByWaybillIn(waybills);
        System.out.println("getParcelMsg size"+parcels.size());
        parcels.parallelStream().forEach(item -> dpdInfoXMLClient.getEventsForOneWaybill(item));
        System.out.println("getParcelMsg end");
    }

}
