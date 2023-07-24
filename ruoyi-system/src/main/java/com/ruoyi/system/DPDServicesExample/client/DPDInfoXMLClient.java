package com.ruoyi.system.DPDServicesExample.client;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.enums.SysWaybill;
import com.ruoyi.system.DPDinfo.pl.com.dpd.dpdinfoservices.events.*;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.util.CollectionUtils;

import java.lang.Exception;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

@Component
public class DPDInfoXMLClient {

    private static final Logger log = LoggerFactory.getLogger(DPDInfoXMLClient.class);

    @Value("${dpdservices.authdataV1.login}")
    private String login;

    @Value("${dpdservices.authdataV1.password}")
    private String password;

    @Value("${dpdservices.authdataV1.channel}")
    private String channel;

    @Value("#{'${yqscode}'.empty ? null : '${yqscode}'.split(',')}")
    private List<String> yqsCodes;

    @Value("#{'${ytjcode}'.empty ? null : '${ytjcode}'.split(',')}")
    private List<String> ytjCodes;

    @Value("#{'${gpcode}'.empty ? null : '${gpcode}'.split(',')}")
    private List<String> gpCodes;

    @Autowired
    private SequenceMapper sequenceMapper;

    @Autowired
    private LogisticsInfoMapper logisticsInfoMapper;

    @Autowired
    private ImportLogicContentMapper importLogicContentMapper;

    @Autowired
    private WaybillLRelMapper waybillLRelMapper;

    @Autowired
    private ParcelMapper parcelMapper;

    @Autowired
    private DPDInfoServicesObjEvents dpdInfoServicesObjEvents;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    private TransactionDefinition transactionDefinition;

    //    @PostConstruct
    public void runExamples() {
        Parcel parcel = new Parcel();
        parcel.setWaybill("0000370741582W");
//        getEventsForOneWaybill(parcel);
        getEventsForWaybills();
    }

    /**
     * 根据物流获取信息
     * @param logisticsInfo
     */
//    @Transactional(rollbackFor = Exception.class)
    @Async
    public void getEventsByLogisticsInfo(LogisticsInfo logisticsInfo) {
        TransactionStatus transaction = platformTransactionManager.getTransaction(transactionDefinition);
        log.info("+++getEventsByLogisticsInfo+++logisticsInfo waybill string: {}", logisticsInfo.getWaybill());
        AuthDataV1 authData = getAuthData();

        WaybillLRel waybillLRel = new WaybillLRel();
        waybillLRel.setWaybill(logisticsInfo.getWaybill());
        List<WaybillLRel> waybillLRelList = waybillLRelMapper.selectWaybillLRelList(waybillLRel);
        String oldStatus = logisticsInfo.getStatus();

        try {
            if (SysWaybill.YTJ.getCode().equals(oldStatus)){
                log.info("+++getEventsByLogisticsInfo+++parcel id string: {} 已经退件，不再获取物流", logisticsInfo.getWaybill());
                return;
            }
            if ((!SysWaybill.YTJ.getCode().equals(oldStatus)) && (!CollectionUtils.isEmpty(waybillLRelList))) {
                dealForWaybillL(new Parcel(), waybillLRelList, logisticsInfo);
                return;
            }

            List<CustomerEventDataV3> allEventDataList = new ArrayList<>();
            CustomerEventsResponseV3 ret = dpdInfoServicesObjEvents.getEventsForWaybillV1(logisticsInfo.getWaybill(), EventsSelectTypeEnum.ALL, "EN", authData);
            List<CustomerEventV3> customerEventV3s = ret.getEventsList();
//            Parcel delivered
//            Parcel not delivered - refusal  + l  拒收
//            Parcel delivered      Registered parcel data, parcel not dispatched yet  -未激活
//           Parcel not delivered - recipient not available -> Parcel return 0000018734275L  推荐？
//            改派 - Parcel not delivered - wrong address  +  Parcel return  0000020184525L
            if (CollectionUtils.isEmpty(customerEventV3s)){
                return;
            }
            customerEventV3s.forEach(item -> allEventDataList.addAll(item.getEventDataList()));

            CustomerEventV3 customerEventV3 = customerEventV3s.get(0);
            logisticsInfo.setLastMsg(customerEventV3.getDescription());
            //如果 最终状态，回退or重寄 带L还需要在重新查一下物流数据
            List<WaybillLRel> waybillLRels = getRL(logisticsInfo.getWaybill(), allEventDataList);
            String status = getStatus(customerEventV3s);
            if(StringUtils.isNotEmpty(status)){
                logisticsInfo.setStatus(SysWaybill.GP.getCode().equals(status) ? SysWaybill.YSZ.getCode() : status);
            }

            List<String> waybills = new ArrayList<>();
            waybills.add(logisticsInfo.getWaybill());
            List<LogisticsInfo> logisticsInfos = new ArrayList<>();
            if (!customerEventV3.getEventTime().equals(logisticsInfo.getLastTime())){
                logisticsInfo.setLastTime(customerEventV3.getEventTime());
                logisticsInfos.add(logisticsInfo);
            }
            List<Parcel> parcels = new ArrayList<>();
            dealWlData(waybills, logisticsInfos, parcels, waybillLRels);
            platformTransactionManager.commit(transaction);
        } catch (Exception_Exception e) {
            e.printStackTrace();
        }
    }

    @Async
    public void getLogic(List<Parcel> parcels){
        parcels.parallelStream().forEach( item -> {
            try {
                this.getEventsForOneWaybillTransa(item);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public String getE(String waybill) throws Exception_Exception {
        AuthDataV1 authData = getAuthData();
        CustomerEventsResponseV3 ret = dpdInfoServicesObjEvents.getEventsForWaybillV1(waybill, EventsSelectTypeEnum.ALL, "EN", authData);
        return JSONObject.toJSONString(ret);
    }

    /**批量更新物流
     *
     * @param params
     */
    public void batchUpdateParcel(List<Parcel> params, boolean updateFlag){
//        TransactionStatus transaction = platformTransactionManager.getTransaction(transactionDefinition);
        List<Parcel> dealParcels = new ArrayList<>();
        List<WaybillLRel> dealWaybillLRels = new ArrayList<>();
        List<WaybillLRel> updateWaybillLRels = new ArrayList<>();
        List<ImportLogicContent> dealImportLogicContents = new ArrayList<>();
        List<ImportLogicContent> dealForRLImportLogicContents  = new ArrayList<>();

        params.parallelStream().forEach(item -> {
                    try {
                        item.setUpdateFlag(updateFlag);
                        getEventsForOneWaybillByBatch(item, dealParcels,
                                dealImportLogicContents, dealForRLImportLogicContents,
                                dealWaybillLRels, updateWaybillLRels);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
        dealWlDataByBatch(dealParcels, dealWaybillLRels, updateWaybillLRels, dealImportLogicContents, dealForRLImportLogicContents);
//        platformTransactionManager.commit(transaction);
    }

    public void getEventsForOneWaybillByBatch(Parcel parcel,
                                              List<Parcel> dealParcels,
                                              List<ImportLogicContent> dealImportLogicContents,
                                              List<ImportLogicContent> dealForRLImportLogicContents,
                                              List<WaybillLRel> dealWaybillLRels,
                                              List<WaybillLRel> updateWaybillLRels) {
//        TransactionStatus transaction = platformTransactionManager.getTransaction(transactionDefinition);
        log.info("+++getEventsForOneWaybillByBatch+++parcel id string: {}", parcel.getWaybill());
        AuthDataV1 authData = getAuthData();
        LogisticsInfo logisticsInfo = new LogisticsInfo();
        logisticsInfo.setPackId(parcel.getPackId());
        //转寄字段
        logisticsInfo.setPackageId(parcel.getPackageId());
        logisticsInfo.setParcelId(parcel.getParcelId());
        logisticsInfo.setCreateUser(parcel.getCreateUser());
        logisticsInfo.setUpdateUser(parcel.getUpdateUser());
        logisticsInfo.setWaybill(parcel.getWaybill());
        logisticsInfo.setCompany("DPD");

        WaybillLRel waybillLRel = new WaybillLRel();
        waybillLRel.setWaybill(parcel.getWaybill());
        List<WaybillLRel> waybillLRelList = waybillLRelMapper.selectWaybillLRelList(waybillLRel);

        String oldStatus = parcel.getStatus();
        try {
            if (SysWaybill.YTJ.getCode().equals(oldStatus)){
                log.info("+++getEventsForOneWaybillByBatch+++parcel id string: {} 已经退件，不再获取物流", parcel.getWaybill());
                return;
            }
            if ((!SysWaybill.YTJ.getCode().equals(oldStatus)) && (!CollectionUtils.isEmpty(waybillLRelList))) {
                dealForWaybillLByBatch(parcel, waybillLRelList, logisticsInfo,
                        dealParcels,
                        updateWaybillLRels,
                        dealImportLogicContents,
                        dealForRLImportLogicContents);
                return;
            }

            List<CustomerEventDataV3> allEventDataList = new ArrayList<>();
            List<LogisticsInfo> logisticsInfos = new ArrayList<>();

            CustomerEventsResponseV3 ret = dpdInfoServicesObjEvents.getEventsForWaybillV1(parcel.getWaybill(), EventsSelectTypeEnum.ALL, "EN", authData);
            List<CustomerEventV3> customerEventV3s = ret.getEventsList();
//            Parcel return    Parcel readdressed according to instructions
//            Parcel not delivered - refusal  + l  拒收
//            Parcel delivered      Registered parcel data, parcel not dispatched yet  -未激活
//           Parcel not delivered - recipient not available -> Parcel return 0000018734275L  推荐？
//            改派 - Parcel not delivered - wrong address  +  Parcel return  0000020184525L
            if (CollectionUtils.isEmpty(customerEventV3s)){
                return;
            }
            customerEventV3s.forEach(item -> allEventDataList.addAll(item.getEventDataList()));

            if (customerEventV3s.size() > 1) {
                CustomerEventV3 customerEventV3Jh = customerEventV3s.get(customerEventV3s.size() - 2);
                logisticsInfo.setActivationTime(customerEventV3Jh.getEventTime());
            }
            CustomerEventV3 customerEventV3 = customerEventV3s.get(0);
            logisticsInfo.setLastMsg(customerEventV3.getDescription());
            String status = getStatus(customerEventV3s);
            if (!SysWaybill.GP.getCode().equals(status) && status.equals(oldStatus) && !parcel.isUpdateFlag()){
                log.info("+++getEventsForOneWaybillByBatch+++getStatusSame waybill: {}, status: {}", parcel.getWaybill(), status);
                return;
            }
            log.info("+++getEventsForOneWaybillByBatch+++getStatus waybill: {}, status: {}", parcel.getWaybill(), status);
            List<WaybillLRel> waybillLRels =new ArrayList<>();
            if (SysWaybill.YTJ.getCode().equals(status) || SysWaybill.GP.getCode().equals(status) || SysWaybill.ZJ.getCode().equals(status)){
                //如果 最终状态，回退or重寄 带L还需要在重新查一下物流数据
                waybillLRels = getRL(parcel.getWaybill(), allEventDataList);
            }

//            for (WaybillLRel lRel : waybillLRels) {
//                List<LogisticsInfo> logisticsInfos1 = logisticsInfoMapper.selectLogisticsInfoListByWaybillIn(Collections.singletonList(lRel.getWaybillL()));
//                if (CollectionUtils.isEmpty(logisticsInfos1)){
//                    LogisticsInfo logisticsInfoL = new LogisticsInfo();
//                    BeanUtils.copyProperties(logisticsInfo, logisticsInfoL);
//                    logisticsInfoL.setWaybill(lRel.getWaybillL());
//                    logisticsInfoL.setStatus(SysWaybill.WJH.getCode());
//                    logisticsInfos.add(logisticsInfoL);
//                }
//            }

            if(StringUtils.isNotEmpty(status)){
                parcel.setStatus(SysWaybill.GP.getCode().equals(status) ? SysWaybill.YSZ.getCode() : status);
                logisticsInfo.setStatus(SysWaybill.GP.getCode().equals(status) ? SysWaybill.YSZ.getCode() : status);
            }
            logisticsInfo.setLastTime(customerEventV3.getEventTime());

            LogisticsInfo logisticsInfoL = new LogisticsInfo();
            if (!CollectionUtils.isEmpty(waybillLRels)){
                dealForWaybillL(parcel, waybillLRels, logisticsInfoL);
                logisticsInfo.setWaybillLRel(waybillLRels.get(0));
                Map<String, Object> lMap = new HashMap<>();
                lMap.put("status",logisticsInfoL.getStatus());
                lMap.put("lastMsg",logisticsInfoL.getLastMsg());
                lMap.put("lastTime",logisticsInfoL.getLastTime());
                logisticsInfo.setlMap(lMap);
            }

            List<String> waybills = new ArrayList<>();
            waybills.add(parcel.getWaybill());

            logisticsInfos.add(logisticsInfo);

            dealParcels.add(parcel);
            dealWaybillLRels.addAll(waybillLRels);
//aaaaaaa
            if (!CollectionUtils.isEmpty(logisticsInfos)) {
                dealForContentByBatch(logisticsInfos, dealImportLogicContents);
            }

//            platformTransactionManager.commit(transaction);
        } catch (Exception_Exception e) {
            e.printStackTrace();
        }
    }

    private void dealForContentByBatch(List<LogisticsInfo> logisticsInfos, List<ImportLogicContent> importLogicContents){
        for (LogisticsInfo logisticsInfo : logisticsInfos) {
            log.info("+++dealForContentByBatch+++logisticsInfos waybill string: {}", logisticsInfo.getWaybill());
            ImportLogicContent logicContent = new ImportLogicContent();
            logicContent.setNewWaybill(logisticsInfo.getWaybill());
            logicContent.setLastStatusDate(logisticsInfo.getLastTime());
            logicContent.setStatus(logisticsInfo.getStatus());
            logicContent.setActivedDate(logisticsInfo.getActivationTime());
            logicContent.setLogicId(logisticsInfo.getId());
            if (ObjectUtils.isNotEmpty(logisticsInfo.getWaybillLRel())){
                if (SysWaybill.YTJ.getCode().equals(logicContent.getStatus())){
                    logicContent.setReturnNumber(logisticsInfo.getWaybillLRel().getWaybillL());
                }
                if ( SysWaybill.YSZ.getCode().equals(logicContent.getStatus())
                        ||SysWaybill.ZJ.getCode().equals(logicContent.getStatus()) || SysWaybill.GP.getCode().equals(logicContent.getStatus())) {
                    logicContent.setNewNumber(logisticsInfo.getWaybillLRel().getWaybillL());
                    Map<String, Object> lMap = logisticsInfo.getlMap();
                    //转寄需要获取最新物流信息
                    if (!CollectionUtils.isEmpty(lMap)) {
                        if (lMap.containsKey("status")) {
                            logicContent.setStatus(ObjectUtils.isEmpty(lMap.get("status")) ? "" : lMap.get("status").toString());
                        }
//                    if (lMap.containsKey("lastMsg")){
//                        logicContent.setStatus(lMap.get("lastMsg").toString());
//                    }
                        if (lMap.containsKey("lastTime")) {
                            logicContent.setLastStatusDate(ObjectUtils.isEmpty(lMap.get("lastTime")) ? "" : lMap.get("lastTime").toString());
                        }
                    }
                }
            }
            importLogicContents.add(logicContent);
//            importLogicContentMapper.updateImportLogicContentByWaybill(logicContent);
        }
    }

    public void dealWlDataByBatch(List<Parcel> parcels,
                                  List<WaybillLRel> deelWaybillLRels,
                                  List<WaybillLRel> updateWaybillLRels,
                                  List<ImportLogicContent> dealImportLogicContents,
                                  List<ImportLogicContent> dealForRLImportLogicContents) {
        if (!CollectionUtils.isEmpty(parcels)) {
            parcelMapper.batchUpdate(parcels);
        }
        if (!CollectionUtils.isEmpty(deelWaybillLRels)) {
            waybillLRelMapper.batchInsert(deelWaybillLRels);
        }
        if (!CollectionUtils.isEmpty(updateWaybillLRels)) {
            waybillLRelMapper.batchUpdateWaybillLRel(updateWaybillLRels);
        }
        if (!CollectionUtils.isEmpty(dealImportLogicContents)) {
            importLogicContentMapper.batchUpdateImportLogicContentByWaybill(dealImportLogicContents);
        }
        //批量更新转寄状态
        if (!CollectionUtils.isEmpty(dealForRLImportLogicContents)) {
            importLogicContentMapper.batchUpdateZjImportLogicContentByWayBill(dealForRLImportLogicContents);
        }

    }

    /**
     *
     * @param parcel
     * @param waybillLRels
     * @param logisticsInfo
     * @param dealParcels
     * @param updateWaybillLRels
     * @param dealImportLogicContents  改派又改派
     * @param dealForRLImportLogicContents
     * @throws Exception_Exception
     */
    private void dealForWaybillLByBatch(Parcel parcel,
                                        List<WaybillLRel> waybillLRels,
                                        LogisticsInfo logisticsInfo,
                                        List<Parcel> dealParcels,
                                        List<WaybillLRel> updateWaybillLRels,
                                        List<ImportLogicContent> dealImportLogicContents,
                                        List<ImportLogicContent> dealForRLImportLogicContents) throws Exception_Exception {
        log.info("+++dealForWaybillLByBatch+++parcel waybill string: {}", parcel.getWaybill());
        AuthDataV1 authData = getAuthData();
        WaybillLRel waybillLRel = waybillLRels.get(0);
        List<CustomerEventDataV3> allEventDataList = new ArrayList<>();
        CustomerEventsResponseV3 ret = dpdInfoServicesObjEvents.getEventsForWaybillV1(waybillLRel.getWaybillL(), EventsSelectTypeEnum.ALL, "EN", authData);
        List<CustomerEventV3> customerEventV3s = ret.getEventsList();
        String status = getStatus(customerEventV3s);
        if (SysWaybill.WJH.getCode().equals(status)){
            log.info("转寄未激活");
            status = SysWaybill.YSZ.getCode();
//            return;
        }
        if (!SysWaybill.GP.getCode().equals(status) && status.equals(parcel.getStatus()) && !parcel.isUpdateFlag()){
            log.info("+++dealForWaybillLByBatch+++parcel waybill same status: {}", status);
            return;
        }
        parcel.setStatus(SysWaybill.GP.getCode().equals(status) ? SysWaybill.YSZ.getCode() : status);
        logisticsInfo.setOldWaybill(parcel.getWaybill());
        logisticsInfo.setStatus(SysWaybill.GP.getCode().equals(status) ? SysWaybill.YSZ.getCode() : status);
        logisticsInfo.setWaybill(waybillLRel.getWaybillL());
        if (CollectionUtils.isEmpty(customerEventV3s)){
            logisticsInfo.setLastMsg("未查询到物流信息");
        }else {
            logisticsInfo.setLastMsg(customerEventV3s.get(0).getDescription());
            logisticsInfo.setLastTime(customerEventV3s.get(0).getEventTime());
            customerEventV3s.forEach(item -> allEventDataList.addAll(item.getEventDataList()));
        }

        String newRel = "";
        if (SysWaybill.YTJ.getCode().equals(status) || SysWaybill.GP.getCode().equals(status) || SysWaybill.ZJ.getCode().equals(status)){
            //如果 最终状态，回退or重寄 带L还需要在重新查一下物流数据
            newRel = getRLForRL(allEventDataList);
        }

        List<String> waybills = new ArrayList<>();
        waybills.add(waybillLRel.getWaybillL());
//        List<LogisticsInfo> logisticsInfos = new ArrayList<>();
//        logisticsInfos.add(logisticsInfo);
        logisticsInfo.setId(null);

        if (ObjectUtils.isNotEmpty(parcel.getId())){
            dealParcels.add(parcel);
        }

        if (StringUtils.isNotEmpty(newRel)){
            WaybillLRel updateWaybillLRel = new WaybillLRel();
            updateWaybillLRel.setStatus(logisticsInfo.getStatus());
            updateWaybillLRel.setWaybill(logisticsInfo.getOldWaybill());
            updateWaybillLRel.setWaybillL(newRel);
            updateWaybillLRels.add(updateWaybillLRel);
//            waybillLRelMapper.updateWaybillLRel(waybillLRel);
        }

        ImportLogicContent importLogicContent = new ImportLogicContent();
        importLogicContent.setStatus(logisticsInfo.getStatus());
        if (StringUtils.isNotEmpty(newRel)){
            // 处理 改派物流信息又改派
            importLogicContent.setNewWaybill(logisticsInfo.getOldWaybill());
            ImportLogicContent importLogicContent1 = importLogicContentMapper.selectImportLogicContentByNewWaybill(logisticsInfo.getOldWaybill());
            // 原来的状态需要不是 已经退件
            if (ObjectUtils.isNotEmpty(importLogicContent1) && (!SysWaybill.YTJ.getCode().equals(importLogicContent1.getStatus()))){
                if (SysWaybill.YTJ.getCode().equals(logisticsInfo.getStatus())){
                    importLogicContent1.setReturnNumber(newRel);
                } else {
                    importLogicContent1.setNewNumber(newRel);
                }
                importLogicContent1.setStatus(logisticsInfo.getStatus());
                importLogicContent1.setLastStatusDate(logisticsInfo.getLastTime());
                importLogicContent1.setLogicId(logisticsInfo.getId());

                dealImportLogicContents.add(importLogicContent1);
//                importLogicContentMapper.updateImportLogicContentByWaybill(importLogicContent1);
            }
        }else {
            importLogicContent.setNewWaybill(logisticsInfo.getWaybill());
            importLogicContent.setLastStatusDate(logisticsInfo.getLastTime());
            dealForRLImportLogicContents.add(importLogicContent);
//            importLogicContentMapper.updateImportLogicContentByWayBill(importLogicContent);
        }
    }

//    @Transactional(rollbackFor = Exception.class)
//    @Async
    public void getEventsForOneWaybill(Parcel parcel) {
//        TransactionStatus transaction = platformTransactionManager.getTransaction(transactionDefinition);
        log.info("+++getEventsForOneWaybill+++parcel id string: {}", parcel.getWaybill());
        AuthDataV1 authData = getAuthData();
        LogisticsInfo logisticsInfo = new LogisticsInfo();
        logisticsInfo.setPackId(parcel.getPackId());
        //转寄字段
        logisticsInfo.setPackageId(parcel.getPackageId());
        logisticsInfo.setParcelId(parcel.getParcelId());
        logisticsInfo.setCreateUser(parcel.getCreateUser());
        logisticsInfo.setUpdateUser(parcel.getUpdateUser());
        logisticsInfo.setWaybill(parcel.getWaybill());
        logisticsInfo.setCompany("DPD");

        WaybillLRel waybillLRel = new WaybillLRel();
        waybillLRel.setWaybill(parcel.getWaybill());
        List<WaybillLRel> waybillLRelList = waybillLRelMapper.selectWaybillLRelList(waybillLRel);

        String oldStatus = parcel.getStatus();
        try {
            if (SysWaybill.YTJ.getCode().equals(oldStatus)){
                log.info("+++getEventsForOneWaybill+++parcel id string: {} 已经退件，不再获取物流", parcel.getWaybill());
                return;
            }
            if ((!SysWaybill.YTJ.getCode().equals(oldStatus)) && (!CollectionUtils.isEmpty(waybillLRelList))) {
                dealForWaybillL(parcel, waybillLRelList, logisticsInfo);
                return;
            }

            List<CustomerEventDataV3> allEventDataList = new ArrayList<>();
            List<LogisticsInfo> logisticsInfos = new ArrayList<>();

            CustomerEventsResponseV3 ret = dpdInfoServicesObjEvents.getEventsForWaybillV1(parcel.getWaybill(), EventsSelectTypeEnum.ALL, "EN", authData);
            List<CustomerEventV3> customerEventV3s = ret.getEventsList();
//            Parcel return    Parcel readdressed according to instructions
//            Parcel not delivered - refusal  + l  拒收
//            Parcel delivered      Registered parcel data, parcel not dispatched yet  -未激活
//           Parcel not delivered - recipient not available -> Parcel return 0000018734275L  推荐？
//            改派 - Parcel not delivered - wrong address  +  Parcel return  0000020184525L
            if (CollectionUtils.isEmpty(customerEventV3s)){
                return;
            }
            customerEventV3s.forEach(item -> allEventDataList.addAll(item.getEventDataList()));

            if (customerEventV3s.size() > 1) {
                CustomerEventV3 customerEventV3Jh = customerEventV3s.get(customerEventV3s.size() - 2);
                logisticsInfo.setActivationTime(customerEventV3Jh.getEventTime());
            }
            CustomerEventV3 customerEventV3 = customerEventV3s.get(0);
            logisticsInfo.setLastMsg(customerEventV3.getDescription());
            String status = getStatus(customerEventV3s);
            if (!SysWaybill.GP.getCode().equals(status) && oldStatus.equals(status) && !parcel.isUpdateFlag()){
                log.info("+++getEventsForOneWaybill+++getStatusSame waybill: {}, status: {}", parcel.getWaybill(), status);
                return;
            }
            log.info("+++getEventsForOneWaybill+++getStatus waybill: {}, status: {}", parcel.getWaybill(), status);
            List<WaybillLRel> waybillLRels =new ArrayList<>();
            if (SysWaybill.YTJ.getCode().equals(status) || SysWaybill.GP.getCode().equals(status) || SysWaybill.ZJ.getCode().equals(status)){
                //如果 最终状态，回退or重寄 带L还需要在重新查一下物流数据
                waybillLRels = getRL(parcel.getWaybill(), allEventDataList);
            }

//            for (WaybillLRel lRel : waybillLRels) {
//                List<LogisticsInfo> logisticsInfos1 = logisticsInfoMapper.selectLogisticsInfoListByWaybillIn(Collections.singletonList(lRel.getWaybillL()));
//                if (CollectionUtils.isEmpty(logisticsInfos1)){
//                    LogisticsInfo logisticsInfoL = new LogisticsInfo();
//                    BeanUtils.copyProperties(logisticsInfo, logisticsInfoL);
//                    logisticsInfoL.setWaybill(lRel.getWaybillL());
//                    logisticsInfoL.setStatus(SysWaybill.WJH.getCode());
//                    logisticsInfos.add(logisticsInfoL);
//                }
//            }

            if(StringUtils.isNotEmpty(status)){
                parcel.setStatus(SysWaybill.GP.getCode().equals(status) ? SysWaybill.YSZ.getCode() : status);
                logisticsInfo.setStatus(SysWaybill.GP.getCode().equals(status) ? SysWaybill.YSZ.getCode() : status);
            }
            logisticsInfo.setLastTime(customerEventV3.getEventTime());

            LogisticsInfo logisticsInfoL = new LogisticsInfo();
            if (!CollectionUtils.isEmpty(waybillLRels)){
                dealForWaybillL(parcel, waybillLRels, logisticsInfoL);
                logisticsInfo.setWaybillLRel(waybillLRels.get(0));
                Map<String, Object> lMap = new HashMap<>();
                lMap.put("status",logisticsInfoL.getStatus());
                lMap.put("lastMsg",logisticsInfoL.getLastMsg());
                lMap.put("lastTime",logisticsInfoL.getLastTime());
                logisticsInfo.setlMap(lMap);
            }

            List<String> waybills = new ArrayList<>();
            waybills.add(parcel.getWaybill());

            logisticsInfos.add(logisticsInfo);

            List<Parcel> parcels = new ArrayList<>();
            parcels.add(parcel);
            dealWlData(waybills, logisticsInfos, parcels, waybillLRels);
//            platformTransactionManager.commit(transaction);
        } catch (Exception_Exception e) {
            e.printStackTrace();
        }
    }

    public void getEventsForOneWaybillTransa(Parcel parcel) {
        log.info("+++getEventsForOneWaybillTransa+++parcel id string: {}", parcel.getWaybill());
        AuthDataV1 authData = getAuthData();
        LogisticsInfo logisticsInfo = new LogisticsInfo();
        logisticsInfo.setPackId(parcel.getPackId());
        //转寄字段
        logisticsInfo.setPackageId(parcel.getPackageId());
        logisticsInfo.setParcelId(parcel.getParcelId());
        logisticsInfo.setCreateUser(parcel.getCreateUser());
        logisticsInfo.setUpdateUser(parcel.getUpdateUser());
        logisticsInfo.setWaybill(parcel.getWaybill());
        logisticsInfo.setCompany("DPD");

        WaybillLRel waybillLRel = new WaybillLRel();
        waybillLRel.setWaybill(parcel.getWaybill());
        List<WaybillLRel> waybillLRelList = waybillLRelMapper.selectWaybillLRelList(waybillLRel);
        String oldStatus = parcel.getStatus();

        try {
            if (SysWaybill.YTJ.getCode().equals(oldStatus)){
                log.info("+++getEventsForOneWaybillByBatch+++parcel id string: {} 已经退件，不再获取物流", parcel.getWaybill());
                return;
            }
            if ((!SysWaybill.YTJ.getCode().equals(oldStatus)) && (!CollectionUtils.isEmpty(waybillLRelList))) {
                dealForWaybillL(parcel, waybillLRelList, logisticsInfo);
                return;
            }

            List<CustomerEventDataV3> allEventDataList = new ArrayList<>();
            List<LogisticsInfo> logisticsInfos = new ArrayList<>();

            CustomerEventsResponseV3 ret = dpdInfoServicesObjEvents.getEventsForWaybillV1(parcel.getWaybill(), EventsSelectTypeEnum.ALL, "EN", authData);
            List<CustomerEventV3> customerEventV3s = ret.getEventsList();
//            Parcel return    Parcel readdressed according to instructions
//            Parcel not delivered - refusal  + l  拒收
//            Parcel delivered      Registered parcel data, parcel not dispatched yet  -未激活
//           Parcel not delivered - recipient not available -> Parcel return 0000018734275L  推荐？
//            改派 - Parcel not delivered - wrong address  +  Parcel return  0000020184525L
            if (CollectionUtils.isEmpty(customerEventV3s)){
                return;
            }
            customerEventV3s.forEach(item -> allEventDataList.addAll(item.getEventDataList()));

            if (customerEventV3s.size() > 1) {
                CustomerEventV3 customerEventV3Jh = customerEventV3s.get(customerEventV3s.size() - 2);
                logisticsInfo.setActivationTime(customerEventV3Jh.getEventTime());
            }
            CustomerEventV3 customerEventV3 = customerEventV3s.get(0);
            logisticsInfo.setLastMsg(customerEventV3.getDescription());
            String status = getStatus(customerEventV3s);
            log.info("+++getEventsForOneWaybillTransa+++getStatus waybill: {}, status: {}", parcel.getWaybill(), status);
            List<WaybillLRel> waybillLRels =new ArrayList<>();
            if (SysWaybill.YTJ.getCode().equals(status) || SysWaybill.GP.getCode().equals(status) || SysWaybill.ZJ.getCode().equals(status)){
                //如果 最终状态，回退or重寄 带L还需要在重新查一下物流数据
                waybillLRels = getRL(parcel.getWaybill(), allEventDataList);
            }

//            for (WaybillLRel lRel : waybillLRels) {
//                List<LogisticsInfo> logisticsInfos1 = logisticsInfoMapper.selectLogisticsInfoListByWaybillIn(Collections.singletonList(lRel.getWaybillL()));
//                if (CollectionUtils.isEmpty(logisticsInfos1)){
//                    LogisticsInfo logisticsInfoL = new LogisticsInfo();
//                    BeanUtils.copyProperties(logisticsInfo, logisticsInfoL);
//                    logisticsInfoL.setWaybill(lRel.getWaybillL());
//                    logisticsInfoL.setStatus(SysWaybill.WJH.getCode());
//                    logisticsInfos.add(logisticsInfoL);
//                }
//            }

            if(StringUtils.isNotEmpty(status)){
                parcel.setStatus(SysWaybill.GP.getCode().equals(status) ? SysWaybill.YSZ.getCode() : status);
                logisticsInfo.setStatus(SysWaybill.GP.getCode().equals(status) ? SysWaybill.YSZ.getCode() : status);
            }
            logisticsInfo.setLastTime(customerEventV3.getEventTime());

            LogisticsInfo logisticsInfoL = new LogisticsInfo();
            if (!CollectionUtils.isEmpty(waybillLRels)){
                dealForWaybillL(parcel, waybillLRels, logisticsInfoL);
                logisticsInfo.setWaybillLRel(waybillLRels.get(0));
                Map<String, Object> lMap = new HashMap<>();
                lMap.put("status",logisticsInfoL.getStatus());
                lMap.put("lastMsg",logisticsInfoL.getLastMsg());
                lMap.put("lastTime",logisticsInfoL.getLastTime());
                logisticsInfo.setlMap(lMap);
            }

            List<String> waybills = new ArrayList<>();
            waybills.add(parcel.getWaybill());

            logisticsInfos.add(logisticsInfo);

            List<Parcel> parcels = new ArrayList<>();
            parcels.add(parcel);
            dealWlData(waybills, logisticsInfos, parcels, waybillLRels);
        } catch (Exception_Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 带L单号查询，拒收或者改派 后查询
     */
    private void dealForWaybillL(Parcel parcel, List<WaybillLRel> waybillLRels, LogisticsInfo logisticsInfo) throws Exception_Exception {
        log.info("+++dealForWaybillL+++parcel waybill string: {}", parcel.getWaybill());
        if (SysWaybill.YTJ.getCode().equals(parcel.getStatus())){
            return;
        }
        AuthDataV1 authData = getAuthData();
        WaybillLRel waybillLRel = waybillLRels.get(0);
        List<CustomerEventDataV3> allEventDataList = new ArrayList<>();
        CustomerEventsResponseV3 ret = dpdInfoServicesObjEvents.getEventsForWaybillV1(waybillLRel.getWaybillL(), EventsSelectTypeEnum.ALL, "EN", authData);
        List<CustomerEventV3> customerEventV3s = ret.getEventsList();
        String status = getStatus(customerEventV3s);
        if (SysWaybill.WJH.getCode().equals(status)){
            log.info("转寄未激活");
            status = SysWaybill.YSZ.getCode();
//            return;
        }
        if (!SysWaybill.GP.getCode().equals(status) && status.equals(parcel.getStatus()) && !parcel.isUpdateFlag()) {
            log.info("+++dealForWaybillL+++parcel waybill same status: {}", status);
            return;
        }
        parcel.setStatus(SysWaybill.GP.getCode().equals(status) ? SysWaybill.YSZ.getCode() : status);
        logisticsInfo.setOldWaybill(parcel.getWaybill());
        logisticsInfo.setStatus(SysWaybill.GP.getCode().equals(status) ? SysWaybill.YSZ.getCode() : status);
        logisticsInfo.setWaybill(waybillLRel.getWaybillL());
        if (CollectionUtils.isEmpty(customerEventV3s)){
            logisticsInfo.setLastMsg("未查询到物流信息");
        }else {
            logisticsInfo.setLastMsg(customerEventV3s.get(0).getDescription());
            logisticsInfo.setLastTime(customerEventV3s.get(0).getEventTime());
            customerEventV3s.forEach(item -> allEventDataList.addAll(item.getEventDataList()));
        }

        String newRel = "";
        if (SysWaybill.YTJ.getCode().equals(status) || SysWaybill.GP.getCode().equals(status) || SysWaybill.ZJ.getCode().equals(status)){
            //如果 最终状态，回退or重寄 带L还需要在重新查一下物流数据
            newRel = getRLForRL(allEventDataList);
        }

        List<String> waybills = new ArrayList<>();
        waybills.add(waybillLRel.getWaybillL());
        List<LogisticsInfo> logisticsInfos = new ArrayList<>();
        logisticsInfos.add(logisticsInfo);
        logisticsInfo.setId(null);

        if (ObjectUtils.isNotEmpty(parcel.getId())){
            parcelMapper.updateParcel(parcel);
        }

        dealWlData(newRel, waybills, logisticsInfos);
    }

//    @Transactional(rollbackFor = Exception.class)
    public void dealWlData(List<String> waybills, List<LogisticsInfo> logisticsInfos, List<Parcel> parcels, List<WaybillLRel> waybillLRels) {
        if (!CollectionUtils.isEmpty(logisticsInfos)) {
//            暂时不删除
//            logisticsInfoMapper.deleteLogisticsInfoByWaybills(waybills);
//            logisticsInfos.forEach(item -> item.setId(null));
//            logisticsInfoMapper.batchInsert(logisticsInfos);
            dealForContent(logisticsInfos);
        }
        if (!CollectionUtils.isEmpty(parcels)) {
            parcelMapper.batchUpdate(parcels);
        }
        if (!CollectionUtils.isEmpty(waybillLRels)) {
            waybillLRelMapper.batchInsert(waybillLRels);
        }
    }

    private void dealForContent(List<LogisticsInfo> logisticsInfos){
        for (LogisticsInfo logisticsInfo : logisticsInfos) {
            log.info("+++dealForContent+++logisticsInfos waybill string: {}", logisticsInfo.getWaybill());
            ImportLogicContent logicContent = new ImportLogicContent();
            logicContent.setNewWaybill(logisticsInfo.getWaybill());
            logicContent.setLastStatusDate(logisticsInfo.getLastTime());
            logicContent.setStatus(logisticsInfo.getStatus());
            logicContent.setActivedDate(logisticsInfo.getActivationTime());
            logicContent.setLogicId(logisticsInfo.getId());
            if (ObjectUtils.isNotEmpty(logisticsInfo.getWaybillLRel())){
                if (SysWaybill.YTJ.getCode().equals(logicContent.getStatus())){
                    logicContent.setReturnNumber(logisticsInfo.getWaybillLRel().getWaybillL());
                }
                if (SysWaybill.ZJ.getCode().equals(logicContent.getStatus()) || SysWaybill.GP.getCode().equals(logicContent.getStatus())) {
                    logicContent.setNewNumber(logisticsInfo.getWaybillLRel().getWaybillL());
                    Map<String, Object> lMap = logisticsInfo.getlMap();
                    //转寄需要获取最新物流信息
                    if (!CollectionUtils.isEmpty(lMap)) {
                        if (lMap.containsKey("status")) {
                            logicContent.setStatus(ObjectUtils.isEmpty(lMap.get("status")) ? "" : lMap.get("status").toString());
                        }
//                    if (lMap.containsKey("lastMsg")){
//                        logicContent.setStatus(lMap.get("lastMsg").toString());
//                    }
                        if (lMap.containsKey("lastTime")) {
                            logicContent.setLastStatusDate(ObjectUtils.isEmpty(lMap.get("lastTime")) ? "" : lMap.get("lastTime").toString());
                        }
                    }
                }
            }
            importLogicContentMapper.updateImportLogicContentByWaybill(logicContent);
        }
    }

//    @Transactional(rollbackFor = Exception.class)
    public void dealWlData(String newRel, List<String> waybills, List<LogisticsInfo> logisticsInfos) {
        if (!CollectionUtils.isEmpty(logisticsInfos)) {
//            logisticsInfoMapper.deleteLogisticsInfoByWaybills(waybills);
            logisticsInfos.forEach(item ->
            {
                item.setId(null);
                updateImportContent(newRel, item);
                updateRel(newRel, item);
            });
//            logisticsInfoMapper.batchInsert(logisticsInfos);
        }
    }

    private void updateRel(String newRel, LogisticsInfo logisticsInfo){
        if (StringUtils.isNotEmpty(newRel)){
            WaybillLRel waybillLRel = new WaybillLRel();
            waybillLRel.setStatus(logisticsInfo.getStatus());
            waybillLRel.setWaybill(logisticsInfo.getOldWaybill());
            waybillLRel.setWaybillL(newRel);
            waybillLRelMapper.updateWaybillLRel(waybillLRel);
        }
    }

    private void updateImportContent(String newRel, LogisticsInfo logisticsInfo){
        ImportLogicContent importLogicContent = new ImportLogicContent();
        importLogicContent.setStatus(logisticsInfo.getStatus());
        if (StringUtils.isNotEmpty(newRel)){
            // 处理 改派物流信息又改派
            importLogicContent.setNewWaybill(logisticsInfo.getOldWaybill());
            ImportLogicContent importLogicContent1 = importLogicContentMapper.selectImportLogicContentByNewWaybill(logisticsInfo.getOldWaybill());
            if (ObjectUtils.isNotEmpty(importLogicContent1) && (!SysWaybill.YTJ.getCode().equals(importLogicContent1.getStatus()))){
                if (SysWaybill.YTJ.getCode().equals(logisticsInfo.getStatus())){
                    importLogicContent1.setReturnNumber(newRel);
                } else {
                    importLogicContent1.setNewNumber(newRel);
                }
                importLogicContent1.setStatus(logisticsInfo.getStatus());
                importLogicContentMapper.updateImportLogicContentByWaybill(importLogicContent1);
            }
        }else {
            importLogicContent.setNewWaybill(logisticsInfo.getWaybill());
            importLogicContent.setLastStatusDate(logisticsInfo.getLastTime());
            importLogicContentMapper.updateImportLogicContentByWayBill(importLogicContent);
        }
    }

    private String getRLForRL(List<CustomerEventDataV3> allEventDataList) {
        if (CollectionUtils.isEmpty(allEventDataList)) {
            return "";
        }
        List<CustomerEventDataV3> customerEventDataV3sL =
                allEventDataList.stream().filter(item -> StringUtils.isNotEmpty(item.getValue()) && !item.getValue().contains("@") &&
                        (item.getValue().endsWith("L") || item.getValue().endsWith("l"))).collect(Collectors.toList());
        for (CustomerEventDataV3 item : customerEventDataV3sL) {
            return item.getValue().trim();
        }
        return "";
    }

    private List<WaybillLRel> getRL(String waybill, List<CustomerEventDataV3> allEventDataList) {
        if (CollectionUtils.isEmpty(allEventDataList)) {
            return new ArrayList<>();
        }
        List<WaybillLRel> returnList = new ArrayList<>();
        List<CustomerEventDataV3> customerEventDataV3sL =
                allEventDataList.stream().filter(item -> StringUtils.isNotEmpty(item.getValue()) && !item.getValue().contains("@")
                        && (item.getValue().endsWith("L") || item.getValue().endsWith("l"))).collect(Collectors.toList());
        List<String> lStrings = new ArrayList<>();
        for (CustomerEventDataV3 item : customerEventDataV3sL) {
            if (lStrings.contains(item.getValue())) {
                break;
            }
            WaybillLRel waybillLRel = new WaybillLRel();
            waybillLRel.setWaybill(waybill);
            waybillLRel.setWaybillL(item.getValue());
            waybillLRel.setIsDelete(1);
            returnList.add(waybillLRel);
            lStrings.add(item.getValue());
        }
        if (!CollectionUtils.isEmpty(returnList)){
            returnList.get(0).setIsDelete(0);
        }
        return returnList;
    }

    private List<WaybillLRel> getRLV2(String waybill, CustomerEventV2 customerEventV2) {
        List<CustomerEventDataV2> allEventDataList = customerEventV2.getEventDataList();
        if (CollectionUtils.isEmpty(allEventDataList)) {
            return new ArrayList<>();
        }
        return allEventDataList.stream().filter(item -> StringUtils.isNotEmpty(item.getValue()) && (item.getValue().endsWith("L") || item.getValue().endsWith("l")))
                .map(item -> {
                    WaybillLRel waybillLRel = new WaybillLRel();
                    waybillLRel.setStatus(customerEventV2.getOperationType());
                    if ("回退".equals(customerEventV2.getOperationType())){
                        waybillLRel.setBackDate(customerEventV2.getEventTime());
                    }
                    waybillLRel.setWaybill(waybill);
                    waybillLRel.setWaybillL(item.getValue());
                    return waybillLRel;
                }).collect(Collectors.toList());
    }

    /**
     * //            Parcel delivered
     * //            Parcel not delivered - refusal  + l  拒收
     * //            Parcel delivered      Registered parcel data, parcel not dispatched yet  -未激活
     * //           Parcel not delivered - recipient not available -> Parcel return 0000018734275L  推荐？
     * //            改派 - Parcel not delivered - wrong address  +  Parcel return  0000020184525L
     * @param customerEventV3s
     * @return
     */
    private String getStatus(List<CustomerEventV3> customerEventV3s) {
        if (CollectionUtils.isEmpty(customerEventV3s)) {
            return SysWaybill.WJH.getCode();
        }
        if (customerEventV3s.size() == 1 && customerEventV3s.get(0).getBusinessCode().equals("030103")) {
            return SysWaybill.WJH.getCode();
        }
        if (listContain(customerEventV3s, ytjCodes)){
            return SysWaybill.YTJ.getCode();
        }
//        501300
//        Parcel delivered  190103
        if (listContain(customerEventV3s, yqsCodes)){
            return SysWaybill.YQS.getCode();
        }
        if (listContain(customerEventV3s, gpCodes)){
            return SysWaybill.GP.getCode();
        }
        return SysWaybill.YSZ.getCode();
    }

    private boolean listContain(List<CustomerEventV3> customerEventV3s, List<String> keys){
        for (CustomerEventV3 customerEventV3 : customerEventV3s) {
            if (!CollectionUtils.isEmpty(keys) && keys.contains(customerEventV3.getBusinessCode())){
                return true;
            }
        }
        return false;
    }

    private static final String upper = "^[A-Z]+$";

    private boolean isStatus(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        if (value.contains("@") || value.contains(".")) {
            return false;
        }
        return value.replace(" ", "").matches(upper);
    }

    public void getEventsForWaybills() {
        AuthDataV1 authData = getAuthData();
        try {
//            markEventsAsProcessedV1
            CustomerEventsResponseV2 ret = dpdInfoServicesObjEvents.getEventsForCustomerV4(10, "english", authData);
            CustomerEventsResponseV2 ret2 = dpdInfoServicesObjEvents.getEventsForCustomerV4(10000, "ENG", authData);
            List<CustomerEventV2> customerEventV2s = ret2.getEventsList();

            if (CollectionUtils.isEmpty(customerEventV2s)){
                return;
            }
            List<String> waybills = customerEventV2s.stream().map(CustomerEventV2::getWaybill).collect(Collectors.toList());
            List<LogisticsInfo> oriLogisticsInfos = logisticsInfoMapper.selectLogisticsInfoListByWaybillIn(waybills);
            List<Parcel> parcels = parcelMapper.selectParcelListByWaybillIn(waybills);
            Map<String, Parcel> waybillParcelMap = parcels.stream().collect(toMap(Parcel::getWaybill, Function.identity()));
            List<LogisticsInfo> allLogisticsInfoList = new ArrayList<>();
            List<Parcel> changeParcels = new ArrayList<>();
            List<WaybillLRel> waybillLRels = new ArrayList<>();

            for (CustomerEventV2 customerEventV2 : customerEventV2s) {
                LogisticsInfo logisticsInfo1 = getLogisticsInfo(customerEventV2, waybillParcelMap, changeParcels, waybillLRels);
                allLogisticsInfoList.add(logisticsInfo1);
            }
            List<LogisticsInfo> needDataLogisticsInfos = needDataLogisticsInfo(allLogisticsInfoList, oriLogisticsInfos);

            dealWlData(waybills, needDataLogisticsInfos, changeParcels, waybillLRels);

            System.out.println("test");
        } catch (Exception_Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 根据CustomerEventV2获取到需要存储的 LogisticsInfo
     *
     * @param customerEventV2
     * @param waybillParcelMap
     */
    private LogisticsInfo getLogisticsInfo(CustomerEventV2 customerEventV2,
                                           Map<String, Parcel> waybillParcelMap,
                                           List<Parcel> changeParcels,
                                           List<WaybillLRel> waybillLRels) {
        String waybill = customerEventV2.getWaybill();
        waybillLRels.addAll(getRLV2(waybill, customerEventV2));

        LogisticsInfo logisticsInfo = new LogisticsInfo();
        if (waybillParcelMap.containsKey(waybill)) {
            Parcel parcel = waybillParcelMap.get(waybill);
            logisticsInfo.setPackId(parcel.getPackId());
            //转寄字段
            logisticsInfo.setPackageId(parcel.getPackageId());
            logisticsInfo.setParcelId(parcel.getParcelId());
            logisticsInfo.setCreateUser(parcel.getCreateUser());
            logisticsInfo.setUpdateUser(parcel.getUpdateUser());

            parcel.setStatus(customerEventV2.getOperationType());
            changeParcels.add(parcel);
        }
        logisticsInfo.setWaybill(waybill);
        logisticsInfo.setCompany("DPD");
        logisticsInfo.setReference(customerEventV2.getParcelReference());
        logisticsInfo.setPackageReference(customerEventV2.getPackageReference());
        logisticsInfo.setDepot(customerEventV2.getDepot());
        logisticsInfo.setLastMsg(customerEventV2.getDescription());
        logisticsInfo.setLastTime(customerEventV2.getEventTime());
        //如果 最终状态，回退or重寄 带L还需要在重新查一下物流数据
        logisticsInfo.setStatus(customerEventV2.getOperationType());

        return logisticsInfo;
    }

    /**
     * 获取到需要保存到数据库中的数据
     *
     * @param logisticsInfos    获取到的所有logisticsInfo
     * @param oriLogisticsInfos 数据库中的logisticsInfo 直接搜索未删除的
     */
    private List<LogisticsInfo> needDataLogisticsInfo(List<LogisticsInfo> logisticsInfos, List<LogisticsInfo> oriLogisticsInfos) {
        Map<String, List<LogisticsInfo>> waybillLogisticsInfosMap = oriLogisticsInfos.stream().collect(groupingBy(LogisticsInfo::getWaybill));
        List<LogisticsInfo> returnList = new ArrayList<>();
        for (LogisticsInfo logisticsInfo : logisticsInfos) {
            if (waybillLogisticsInfosMap.containsKey(logisticsInfo.getWaybill())) {
                List<LogisticsInfo> key = waybillLogisticsInfosMap.get(logisticsInfo.getWaybill()).stream()
                        .filter(item -> StringUtils.isNotEmpty(item.getLastTime()) && item.getLastTime().equals(logisticsInfo.getLastTime()))
                        .collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(key)) {
                    continue;
                }
            }
            returnList.add(logisticsInfo);
        }
        return returnList;
    }

    private void getEventsForWaybills(List<Parcel> parcel) {
        List<LogisticsInfo> logisticsInfos = new ArrayList<>();
        logisticsInfoMapper.batchInsert(logisticsInfos);
    }

    private static String[] SEQ_NAMES = {"wl_seq"};

    private Map<String, Sequence> getSeqMap(int addNum) {
        List<Sequence> sequences = sequenceMapper.selectSequenceList(new Sequence());
        Map<String, Sequence> nameMap = sequences.stream().collect(toMap(Sequence::getSeqName, Function.identity()));
        for (String seqName : SEQ_NAMES) {
            if (nameMap.containsKey(seqName)) {
                Sequence sequence = nameMap.get(seqName);
                Map<String, Object> paramMap = new HashMap();
                paramMap.put("seqName", sequence.getSeqName());
                paramMap.put("seqSize", addNum);
                sequenceMapper.selectNextvalWithSizeByName(paramMap);
            }
        }
        return nameMap;
    }

    private Long getId(Map<String, Sequence> nameMap, String seqName) {
        Sequence sequence = nameMap.get(seqName);
        Long id = sequence.getCurrentVal() + sequence.getIncrementVal();
        sequence.setCurrentVal(id);
        return id;
    }

    private AuthDataV1 getAuthData() {
        AuthDataV1 authDataV1 = new AuthDataV1();
        authDataV1.setLogin(login);
        authDataV1.setPassword(password);
        authDataV1.setChannel(channel);
        return authDataV1;
    }
}
