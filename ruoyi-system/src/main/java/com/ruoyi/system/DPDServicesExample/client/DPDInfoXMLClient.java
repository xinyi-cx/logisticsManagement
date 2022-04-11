package com.ruoyi.system.DPDServicesExample.client;

import com.ruoyi.system.DPDinfo.pl.com.dpd.dpdinfoservices.events.*;
import com.ruoyi.system.domain.LogisticsInfo;
import com.ruoyi.system.domain.Parcel;
import com.ruoyi.system.domain.Sequence;
import com.ruoyi.system.mapper.LogisticsInfoMapper;
import com.ruoyi.system.mapper.SequenceMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@Component
public class DPDInfoXMLClient {

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
    private DPDInfoServicesObjEvents dpdInfoServicesObjEvents;

//    @PostConstruct
    public void runExamples() {
        Parcel parcel = new Parcel();
        parcel.setWaybill("0000368219709W");
        getEventsForOneWaybill(parcel);
    }

    private void getEventsForOneWaybill(Parcel parcel) {
        AuthDataV1 authData = getAuthData();
        LogisticsInfo logisticsInfo = new LogisticsInfo();
        logisticsInfo.setPackId(parcel.getPackId());
        logisticsInfo.setPackageId(parcel.getPackageId());
        logisticsInfo.setParcelId(parcel.getParcelId());
        logisticsInfo.setCreateUser(parcel.getCreateUser());
        logisticsInfo.setUpdateUser(parcel.getUpdateUser());
        logisticsInfo.setWaybill(parcel.getWaybill());
        try {
            CustomerEventsResponseV3 ret = dpdInfoServicesObjEvents.getEventsForWaybillV1(parcel.getWaybill(), EventsSelectTypeEnum.ALL, "EN", authData);
            List<CustomerEventV3> customerEventV3s = ret.getEventsList();
            CustomerEventV3 customerEventV3 = customerEventV3s.get(0);
            logisticsInfo.setLastMsg(customerEventV3.getDescription());
//            parcel.setStatus("");
            System.out.println("test");
        }catch (com.ruoyi.system.DPDinfo.pl.com.dpd.dpdinfoservices.events.Exception_Exception e) {
            e.printStackTrace();
        }
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
