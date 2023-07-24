package com.ruoyi.system.service.impl;

import com.ruoyi.common.enums.SysWaybill;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.DPDServicesExample.client.DPDInfoXMLClient;
import com.ruoyi.system.DPDinfo.pl.com.dpd.dpdinfoservices.events.Exception_Exception;
import com.ruoyi.system.domain.LogisticsInfo;
import com.ruoyi.system.domain.Parcel;
import com.ruoyi.system.mapper.*;
import com.ruoyi.system.service.IParcelService;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    private PackRelLocalMapper packRelLocalMapper;

    @Autowired
    private PackageMapper packageMapper;

    @Autowired
    private RedirectRelMapper redirectRelMapper;

    @Autowired
    private ImportLogicContentMapper importLogicContentMapper;

    @Autowired
    private LogisticsInfoMapper logisticsInfoMapper;

    @Autowired
    private DocumentsMapper documentsMapper;

    @Autowired
    private DPDInfoXMLClient dpdInfoXMLClient;

    @Value("${getnum}")
    private Integer getnum;

    @Value("${dayNum}")
    private Integer dayNum;

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
    public int clearDocuments(String endDate){
        return documentsMapper.clearDocuments(endDate);
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
        Date lastMonth = DateUtils.getDateBeforeNow(dayNum);
        List<Parcel> parcels = needParcels.stream().filter(
                item -> !(SysWaybill.YQS.getCode().equals(item.getStatus()) && ObjectUtils.isNotEmpty(item.getUpdatedTime()) && item.getUpdatedTime().compareTo(lastMonth) < 0)).collect(Collectors.toList());
//        List<Parcel> parcels = needParcels;
        log.info("getParcelMsgTask size" + parcels.size());

        List<List<Parcel>> cfList = StringUtils.splitList(parcels, getnum);

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
                        dpdInfoXMLClient.batchUpdateParcel(item, false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
        log.info("getParcelMsgTask end");
    }

    @Override
    @Async
    public void getParcelMsgTaskWithoutQs(Parcel parcel) {
        log.info("getParcelMsgTaskWithoutQs start");
        List<Parcel> needParcels = parcelMapper.selectParcelListNeedDeal(parcel);
//        Date lastMonth = DateUtils.getDateBeforeNow(dayNum);
        List<Parcel> parcels = needParcels.stream().filter(
                item -> !(SysWaybill.YQS.getCode().equals(item.getStatus())) && (!"1".equals(item.getUpdateLastFlag())) ).collect(Collectors.toList());
//        List<Parcel> parcels = needParcels;
        log.info("getParcelMsgTaskWithoutQs size" + parcels.size());

        List<List<Parcel>> cfList = StringUtils.splitList(parcels, getnum);

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
                        log.info("getParcelMsgTaskWithoutQs running");
                        dpdInfoXMLClient.batchUpdateParcel(item, false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
        log.info("getParcelMsgTaskWithoutQs end");
    }

    @Override
    public void getParcelMsgTaskStatusIsNull() {
        log.info("getParcelMsgTaskStatusIsNull start");
        List<Parcel> parcels = parcelMapper.selectParcelListStatusNull();
        log.info("getParcelMsgTaskStatusIsNull size" + parcels.size());
        List<List<Parcel>> cfList = StringUtils.splitList(parcels, getnum);
        cfList.stream().forEach(item -> {
                    try {
                        log.info("getParcelMsgTaskWithoutQs running");
                        dpdInfoXMLClient.batchUpdateParcel(item, false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
        log.info("getParcelMsgTaskStatusIsNull end");
    }

    @Override
    @Async
    public void getParcelMsgTrans(Parcel parcel) {
        log.info("getParcelMsgTrans start");
        List<Parcel> parcelsAll = parcelMapper.selectParcelListNeedDeal(parcel);
        List<Parcel> parcels = parcelsAll.stream().filter(item -> !"1".equals(item.getUpdateLastFlag())).collect(Collectors.toList());
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
        List<Parcel> parcelsAll = parcelMapper.selectParcelListByWaybillIn(waybills);
        List<Parcel> parcels = parcelsAll.stream().filter(item -> !"1".equals(item.getUpdateLastFlag())).collect(Collectors.toList());
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
                        dpdInfoXMLClient.batchUpdateParcel(item, true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
        log.info("getParcelMsg end");
    }

    @Override
    public void deleteParcelWithWaybillIsNull() {
        log.info("deleteParcelWithWaybillIsNull start");
        List<Parcel> parcels = parcelMapper.selectParcelListWithNull();
        if (CollectionUtils.isEmpty(parcels)){
            return;
        }
        Long[] ids = parcels.stream().map(Parcel::getPackId).filter(ObjectUtils::isNotEmpty).collect(Collectors.toList()).toArray(new Long[parcels.size()]);

        packageMapper.deletePackageByIdsReal(ids);
        packRelLocalMapper.deletePackRelLocalByOldPackageIds(ids);
        redirectRelMapper.deleteRedirectRelByNewPackageIds(ids);
        importLogicContentMapper.deleteImportLogicContentByPackIds(ids);
        parcelMapper.deleteParcelByWlNull();
        log.info("deleteParcelWithWaybillIsNull end");
    }

    @Override
    public void syncStatus() {
        parcelMapper.deleteNoSyncRel();
        parcelMapper.syncStatus();
    }

    @Autowired
    private MbAddressReceiverMapper mbAddressReceiverMapper;
    @Autowired
    private MbPackageMapper mbPackageMapper;
    @Autowired
    private MbImportLogicContentMapper mbImportLogicContentMapper;
    @Autowired
    private MbDocumentsMapper mbDocumentsMapper;
    @Autowired
    private MbParcelMapper mbParcelMapper;
    @Autowired
    private MbBatchTaskHistoryMapper mbBatchTaskHistoryMapper;
    @Autowired
    private MbServicesMapper mbServicesMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void syncMb() {
        mbPackageMapper.insertInToWithOutMb();
        log.info("mbPackageMapper success1");

        mbAddressReceiverMapper.insertInToWithOutMb();
        log.info("mbAddressReceiverMapper success2");

        mbServicesMapper.insertInToWithOutMb();
        log.info("mbServicesMapper success3");

        mbParcelMapper.insertInToWithOutMb();
        log.info("mbParcelMapper success4");

        mbDocumentsMapper.insertInToWithOutMb();
        log.info("mbDocumentsMapper success5");

        mbBatchTaskHistoryMapper.insertInToWithOutMb();
        log.info("mbBatchTaskHistoryMapper success6");

        mbImportLogicContentMapper.insertInToWithOutMb();
        log.info("mbImportLogicContentMapper success7");

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delMb() {
        mbPackageMapper.deleteMbData();
        mbAddressReceiverMapper.deleteMbData();
        mbServicesMapper.deleteMbData();
        mbParcelMapper.deleteMbData();
        mbDocumentsMapper.deleteMbData();
        mbBatchTaskHistoryMapper.deleteMbData();
        mbImportLogicContentMapper.deleteMbData();
    }
}
