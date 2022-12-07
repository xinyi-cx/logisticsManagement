package com.ruoyi.system.DPDServicesExample.client;

import com.ruoyi.common.enums.SysWaybill;
import com.ruoyi.system.DPDinfo.pl.com.dpd.dpdinfoservices.events.*;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
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

//    @PostConstruct
    public void runExamples() {
        Parcel parcel = new Parcel();
//        0000368219706W
//        0000368219709W
        parcel.setWaybill("0000370741582W");
//        getEventsForOneWaybill(parcel);
        getEventsForWaybills();
    }

    /**
     * 根据物流获取信息
     * @param logisticsInfo
     */
    @Async
    @Transactional(rollbackFor = Exception.class)
    public void getEventsByLogisticsInfo(LogisticsInfo logisticsInfo) {
        log.info("+++getEventsByLogisticsInfo+++logisticsInfo string: {}", logisticsInfo.toString());
        AuthDataV1 authData = getAuthData();

        WaybillLRel waybillLRel = new WaybillLRel();
        waybillLRel.setWaybill(logisticsInfo.getWaybill());
        List<WaybillLRel> waybillLRelList = waybillLRelMapper.selectWaybillLRelList(waybillLRel);

        try {
            if (!CollectionUtils.isEmpty(waybillLRelList)){
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
                logisticsInfo.setStatus(status);
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

        } catch (Exception_Exception e) {
            e.printStackTrace();
        }
    }

    @Async
    @Transactional(rollbackFor = Exception.class)
    public void getEventsForOneWaybill(Parcel parcel) {
        log.info("+++getEventsForOneWaybill+++parcel string: {}", parcel.toString());
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

        try {
            if (!CollectionUtils.isEmpty(waybillLRelList)){
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
            //如果 最终状态，回退or重寄 带L还需要在重新查一下物流数据
            List<WaybillLRel> waybillLRels = getRL(parcel.getWaybill(), allEventDataList);

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

            String status = getStatus(customerEventV3s);
            if(StringUtils.isNotEmpty(status)){
                parcel.setStatus(status);
                logisticsInfo.setStatus(status);
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
        log.info("+++dealForWaybillL+++parcel string: {}", parcel.toString());
        AuthDataV1 authData = getAuthData();
        WaybillLRel waybillLRel = waybillLRels.get(0);
        CustomerEventsResponseV3 ret = dpdInfoServicesObjEvents.getEventsForWaybillV1(waybillLRel.getWaybillL(), EventsSelectTypeEnum.ALL, "EN", authData);
        List<CustomerEventV3> customerEventV3s = ret.getEventsList();
        String status = getStatus(customerEventV3s);
        parcel.setStatus(status);
        logisticsInfo.setStatus(status);
        logisticsInfo.setWaybill(waybillLRel.getWaybillL());
        logisticsInfo.setLastMsg(customerEventV3s.get(0).getDescription());
        logisticsInfo.setLastTime(customerEventV3s.get(0).getEventTime());

        List<String> waybills = new ArrayList<>();
        waybills.add(waybillLRel.getWaybillL());
        List<LogisticsInfo> logisticsInfos = new ArrayList<>();
        logisticsInfos.add(logisticsInfo);
        logisticsInfo.setId(null);
        dealWlData(waybills, logisticsInfos);
    }

    @Transactional(rollbackFor = Exception.class)
    public void dealWlData(List<String> waybills, List<LogisticsInfo> logisticsInfos, List<Parcel> parcels, List<WaybillLRel> waybillLRels) {
        if (!CollectionUtils.isEmpty(logisticsInfos)) {
            logisticsInfoMapper.deleteLogisticsInfoByWaybills(waybills);
            logisticsInfos.forEach(item -> item.setId(null));
            logisticsInfoMapper.batchInsert(logisticsInfos);
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
        log.info("+++dealForContent+++logisticsInfos string: {}", logisticsInfos.toString());
        for (LogisticsInfo logisticsInfo : logisticsInfos) {
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
                }
                Map<String, Object> lMap = logisticsInfo.getlMap();
                if (!CollectionUtils.isEmpty(lMap)){
                    if (lMap.containsKey("status")){
                        logicContent.setStatus(lMap.get("status").toString());
                    }
//                    if (lMap.containsKey("lastMsg")){
//                        logicContent.setStatus(lMap.get("lastMsg").toString());
//                    }
                    if (lMap.containsKey("lastTime")){
                        logicContent.setLastStatusDate(lMap.get("lastTime").toString());
                    }
                }
            }
            importLogicContentMapper.updateImportLogicContentByWaybill(logicContent);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void dealWlData(List<String> waybills, List<LogisticsInfo> logisticsInfos) {
        if (!CollectionUtils.isEmpty(logisticsInfos)) {
            logisticsInfoMapper.deleteLogisticsInfoByWaybills(waybills);
            logisticsInfos.forEach(item -> item.setId(null));
            logisticsInfoMapper.batchInsert(logisticsInfos);
        }
    }

    private List<WaybillLRel> getRL(String waybill, List<CustomerEventDataV3> allEventDataList) {
        if (CollectionUtils.isEmpty(allEventDataList)) {
            return new ArrayList<>();
        }
        List<WaybillLRel> returnList = new ArrayList<>();
        List<CustomerEventDataV3> customerEventDataV3sL =
                allEventDataList.stream().filter(item -> StringUtils.isNotEmpty(item.getValue()) && item.getValue().endsWith("L")).collect(Collectors.toList());
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
        return allEventDataList.stream().filter(item -> StringUtils.isNotEmpty(item.getValue()) && item.getValue().endsWith("L"))
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
            return "";
        }
        if (customerEventV3s.size() == 1 && customerEventV3s.get(0).getBusinessCode().equals("030103")) {
            return SysWaybill.WJH.getCode();
        }
        if (listContain(customerEventV3s, "190101")){
            return SysWaybill.YQS.getCode();
        }
        if (listContain(customerEventV3s, "230403")){
            return SysWaybill.YTJ.getCode();
        }
        if (listContain(customerEventV3s, "230402")){
            return SysWaybill.GP.getCode();
        }
        return SysWaybill.YSZ.getCode();
    }

    private boolean listContain(List<CustomerEventV3> customerEventV3s, String key){
        for (CustomerEventV3 customerEventV3 : customerEventV3s) {
            if (customerEventV3.getBusinessCode().contains(key)){
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
                Sequence sequence = new Sequence();
                BeanUtils.copyProperties(nameMap.get(seqName), sequence);
                sequence.setCurrentVal(sequence.getCurrentVal() + addNum * sequence.getIncrementVal());
                sequenceMapper.updateSequence(sequence);
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
