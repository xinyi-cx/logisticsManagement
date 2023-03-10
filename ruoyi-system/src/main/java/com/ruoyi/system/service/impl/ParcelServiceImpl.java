package com.ruoyi.system.service.impl;

import com.ruoyi.common.enums.SysWaybill;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.DPDServicesExample.client.DPDInfoXMLClient;
import com.ruoyi.system.DPDinfo.pl.com.dpd.dpdinfoservices.events.Exception_Exception;
import com.ruoyi.system.domain.LogisticsInfo;
import com.ruoyi.system.domain.Parcel;
import com.ruoyi.system.mapper.LogisticsInfoMapper;
import com.ruoyi.system.mapper.ParcelMapper;
import com.ruoyi.system.service.IParcelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 包裹Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-01-02
 */
@Service
public class ParcelServiceImpl implements IParcelService 
{
    private static final Logger log = LoggerFactory.getLogger(ParcelServiceImpl.class);
    
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
    public String getStringByWaybill(String waybill) throws Exception_Exception {
        return dpdInfoXMLClient.getE(waybill);
    }

    @Override
    @Async
    public void getParcelMsg(Parcel parcel) {
        log.info("getParcelMsg start");
        List<Parcel> parcels = parcelMapper.selectParcelListNeedDeal(parcel);
        log.info("getParcelMsg size" + parcels.size());
        parcels.parallelStream().forEach(item -> {
                    try {
                        log.info("getParcelMsg running");
                        dpdInfoXMLClient.getEventsForOneWaybill(item);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
        log.info("getParcelMsg end");
    }

    @Override
    @Async
    public void getParcelMsgTask(Parcel parcel) {
        log.info("getParcelMsgTask start");
        List<Parcel> needParcels = parcelMapper.selectParcelListNeedDeal(parcel);
        Date lastMonth = DateUtils.getDateBeforeNow(-30);
        List<Parcel> parcels= needParcels.stream().filter(
                item -> item.getCreatedTime().compareTo(lastMonth) > 0).collect(Collectors.toList());
        log.info("getParcelMsgTask size" + parcels.size());

        List<List<Parcel>> cfList = StringUtils.splitList(parcels, 1000);

//        parcels.parallelStream().forEach(item -> {
//                    try {
//                        log.info("getParcelMsgTask running");
//                        dpdInfoXMLClient.getEventsForOneWaybill(item);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//        );

        cfList.stream().forEach(item -> {
                    try {
                        log.info("getParcelMsgTask running");
                        dpdInfoXMLClient.batchUpdateParcel(item);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
        log.info("getParcelMsgTask end");
    }

    @Override
    @Async
    public void getParcelMsgTrans(Parcel parcel) {
        log.info("getParcelMsgTrans start");
        List<Parcel> parcels = parcelMapper.selectParcelListNeedDeal(parcel);
        log.info("getParcelMsgTrans size" + parcels.size());
        parcels.parallelStream().forEach(item -> {
                    try {
                        dpdInfoXMLClient.getEventsForOneWaybillTransa(item);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
        log.info("getParcelMsgTrans end");
    }

    @Override
    @Async
    public void getParcelMsgByLogisticsInfo(LogisticsInfo param){
        log.info("getParcelMsgByLogisticsInfo start");
        List<LogisticsInfo> logisticsInfos = logisticsInfoMapper.selectLogisticsInfoListNeedDeal(param);
        logisticsInfos.parallelStream().forEach(item -> dpdInfoXMLClient.getEventsByLogisticsInfo(item));
        log.info("getParcelMsgByLogisticsInfo end");
    }

    @Override
    public void getParcelMsgById(Long id) {
        Parcel parcel = parcelMapper.selectParcelById(id);
        dpdInfoXMLClient.getEventsForOneWaybillTransa(parcel);
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
        log.info("getAllParcelMsg start");
        dpdInfoXMLClient.getEventsForWaybills();
        log.info("getAllParcelMsg end");
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
        log.info("getParcelMsg start");
        List<Parcel> parcels = parcelMapper.selectParcelListByWaybillIn(waybills);
        log.info("getParcelMsg size"+parcels.size());
        List<List<Parcel>> cfList = StringUtils.splitList(parcels, 100);
//        parcels.parallelStream().forEach(item -> {
//            try {
//                dpdInfoXMLClient.getEventsForOneWaybillTransa(item);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
        cfList.stream().forEach(item -> {
                    try {
                        log.info("getParcelMsg running");
                        dpdInfoXMLClient.batchUpdateParcel(item);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
        log.info("getParcelMsg end");
    }

}
