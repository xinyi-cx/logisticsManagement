package com.ruoyi.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.common.utils.sign.Base64;
import com.ruoyi.system.DPDServicesExample.client.DPDServicesXMLClient;
import com.ruoyi.system.domain.Package;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.domain.mb.*;
import com.ruoyi.system.mapper.*;
import com.ruoyi.system.service.IOuterService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

/**
 * 收货人信息Service业务层处理
 *
 * @author ruoyi
 * @date 2022-01-02
 */
@Service
public class OuterServiceImpl implements IOuterService {

    @Value("${mb.url}")
    private String url;

    @Value("${mb.apiAccountId}")
    private Long apiAccountId;

    @Value("${mb.apiKey}")
    private String apiKey;

    @Autowired
    private MbReturnDtoMapper mbReturnDtoMapper;

    @Autowired
    private PackageMapper packageMapper;

    @Autowired
    private AddressSenderMapper addressSenderMapper;

    @Autowired
    private AddressReceiverMapper addressReceiverMapper;

    @Autowired
    private ServicesMapper servicesMapper;

    @Autowired
    private BatchTaskHistoryMapper batchTaskHistoryMapper;

    @Autowired
    private SequenceMapper sequenceMapper;

    @Autowired
    private ParcelMapper parcelMapper;

    @Autowired
    private PackagesGenerationResponseMapper packagesGenerationResponseMapper;

    @Autowired
    private DocumentsMapper documentsMapper;

    @Autowired
    private DPDServicesXMLClient dpdServicesXMLClient;

    private Map<String, Package> getPackageListByCodes(List<String> codes) {
        List<Parcel> parcelList = parcelMapper.selectParcelListByReferenceIn(codes);
        if (CollectionUtils.isEmpty(parcelList)) {
            return new HashMap<>();
        }
        List<Package> packages = packageMapper.selectPackageByIdIn(parcelList.stream().map(Parcel::getPackId).collect(Collectors.toList()));
        Map<Long, Package> idPackageMap = packages.stream().collect(toMap(Package::getId, Function.identity()));
        List<AddressSender> addressSenders =
                addressSenderMapper.selectAddressSenderByIdIn(packages.stream().map(Package::getSenderId).collect(Collectors.toList()));
        Map<Long, AddressSender> addressSenderMap = addressSenders.stream().collect(toMap(AddressSender::getId, Function.identity()));
        List<AddressReceiver> addressReceivers =
                addressReceiverMapper.selectAddressReceiverByIdIn(packages.stream().map(Package::getReceiverId).collect(Collectors.toList()));
        Map<Long, AddressReceiver> addressReceiverMap = addressReceivers.stream().collect(toMap(AddressReceiver::getId, Function.identity()));
        List<Services> services =
                servicesMapper.selectServicesListByIdIn(packages.stream().map(Package::getServicesId).collect(Collectors.toList()));
        Map<Long, Services> servicesMap = services.stream().collect(toMap(Services::getId, Function.identity()));
        List<PackagesGenerationResponse> packagesGenerationResponses =
                packagesGenerationResponseMapper.selectPackagesGenerationResponseByPackIdIn(packages.stream().map(Package::getId).collect(Collectors.toList()));
        Map<Long, PackagesGenerationResponse> packagesGenerationResponseMap = packagesGenerationResponses.stream().collect(toMap(PackagesGenerationResponse::getPackId, Function.identity()));
        List<Documents> documentsList =
                documentsMapper.selectDocumentsByPackageIdIn(parcelList.stream().map(Parcel::getPackageId).collect(Collectors.toList()));
        Map<Long, Documents> documentsMap = documentsList.stream().collect(toMap(Documents::getPackageId, Function.identity()));

        return parcelList.stream().collect(
                toMap(Parcel::getReference, item -> getPackage(item, idPackageMap, addressSenderMap, addressReceiverMap, servicesMap, packagesGenerationResponseMap, documentsMap)));
    }

    private Package getPackage(Parcel parcel,
                               Map<Long, Package> idPackageMap,
                               Map<Long, AddressSender> addressSenderMap,
                               Map<Long, AddressReceiver> addressReceiverMap,
                               Map<Long, Services> servicesMap,
                               Map<Long, PackagesGenerationResponse> packagesGenerationResponseMap,
                               Map<Long, Documents> documentsMap) {
        Package pac = idPackageMap.get(parcel.getPackId());
        pac.setParcels(Arrays.asList(parcel));
        pac.setSender(addressSenderMap.get(pac.getSenderId()));
        pac.setReceiver(addressReceiverMap.get(pac.getReceiverId()));
        pac.setService(servicesMap.get(pac.getServicesId()));
        if (packagesGenerationResponseMap.containsKey(pac.getId())){
            pac.setPackagesGenerationResponse(packagesGenerationResponseMap.get(pac.getId()));
        }
        if (documentsMap.containsKey(parcel.getPackageId())){
            pac.setDocuments(documentsMap.get(parcel.getPackageId()));
        }
        return pac;
    }

    /**
     * 查询收货人信息
     *
     * @param id 收货人信息主键
     * @return 收货人信息
     */
    @Override
    public AddressReceiver selectAddressReceiverById(Long id) {
        return addressReceiverMapper.selectAddressReceiverById(id);
    }

    /**
     * MD5(api=API 方法&apiAccountId= API 账号 ID& encodeParams =订单信息&timestamp=当前 UNIX 时间戳 apiKey)
     *
     * @param api
     * @param apiAccountId
     * @param apiKey
     * @return
     */
    private static String getSign(String api, Long apiAccountId, String apiKey, String encodeParams, long timestamp) {
        StringBuilder sb = new StringBuilder();
        sb.append("api=").append(api)
                .append("&apiAccountId=").append(apiAccountId);
        if (StringUtils.isNotEmpty(encodeParams)) {
            sb.append("&encodeParams=").append(encodeParams);
        }
        sb.append("&timestamp=").append(timestamp)
                .append(apiKey);
        return DigestUtils.md5Hex(sb.toString());
    }

    private void test() throws Exception {
        String url = "http://www.sandbox.i8956.com/interface/index.php";
        Map<String, String> encodeParamsMap = new HashMap<>();
        String res = HttpUtils.sendPost(url, getParamStr("api.biaoju.order.find", (long) 29689, "2675166dc4b2242bf88c3ea25a452b3f", encodeParamsMap));
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isEmpty(res) || res.contains("ErrorCode")) {
            sb.append("马帮接收信息错误");
            if (res.contains("ErrorCode")) {
                JSONObject jsonObject = JSONObject.parseObject(res);
                sb.append(", 错误信息为：")
                        .append(jsonObject.get("Message").toString());
            } else {
                sb.append(", 错误信息为：空");
            }
        }
        System.out.println(sb.toString());
    }

//    public static void main(String[] args) throws Exception {
//        String url = "https://www.i8956.com/interface/index.php";
//        Map<String, String> encodeParamsMap = new HashMap<>();
//        encodeParamsMap.put("status", "2");
//        String res = HttpUtils.sendPost(url, getParamStr("api.biaoju.order.find", (long) 29689, "2675166dc4b2242bf88c3ea25a452b3f", encodeParamsMap));
//        JSONObject jsonObject = JSONObject.parseObject(JSONObject.parseObject(res).get("Data").toString());
//        List<MbReturnDto> mbReturnDtos = JSON.parseArray(jsonObject.get("orders").toString(), MbReturnDto.class);
//
//        List<Package> packages = getPackagesByMbReturnDtos(mbReturnDtos);
//        System.out.println("res" + res);
//    }

    private String getMbRes(String methodName, Map<String, String> encodeParamsMap) throws Exception {
        String res = HttpUtils.sendPost(url, getParamStr(methodName, apiAccountId, apiKey, encodeParamsMap));
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isEmpty(res) || res.contains("ErrorCode")) {
            if (res.contains("ErrorCode")) {
                JSONObject jsonObject = JSONObject.parseObject(res);
                if ("9999".equals(jsonObject.get("ErrorCode").toString())) {
//                    成功了
                    return res;
                }
                sb.append("马帮接收信息错误");
                sb.append(", 错误信息为：")
                        .append(jsonObject.get("Message").toString());
            } else {
                sb.append("马帮接收信息错误, 错误信息为：空");
            }
        }
        throw new Exception(sb.toString());
    }

    private static String getParamStr(String api, Long apiAccountId, String apiKey, Map<String, String> encodeParamsMap) throws Exception {
        long timestamp = System.currentTimeMillis() / 1000;
        String enStr = net.arnx.jsonic.JSON.encode(encodeParamsMap);
        String base64EnStr = CollectionUtils.isEmpty(encodeParamsMap) ? "" : Base64.encode(enStr.getBytes());
        StringBuilder sb = new StringBuilder();
        sb.append("api=").append(api)
                .append("&timestamp=").append(timestamp)
                .append("&apiAccountId=").append(apiAccountId);
        sb.append("&encodeParams=").append(Base64.encode(enStr.getBytes()));
        sb.append("&sign=").append(getSign(api, apiAccountId, apiKey, base64EnStr, timestamp));
        return sb.toString();
    }

    /**
     * 当有待处理订单提交到镖局物流平时，触发此通知
     * @param codes
     * @return
     * @throws Exception
     */
    private List<MbReturnDto> getMbReturnDtoByCodes(List<String> codes, String notify) throws Exception {
        //获取到codes
        String codeStr = codes.stream().collect(Collectors.joining(","));
        Map<String, String> param = new HashMap<>();
        param.put("codes", codeStr);
        String returnStr = getMbRes("api.biaoju.order.find", param);
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.parseObject(returnStr).get("Data").toString());
        List<MbReturnDto> mbReturnDtos = JSON.parseArray(jsonObject.get("orders").toString(), MbReturnDto.class);
        if ("orderChange".equals(notify)){
            mbReturnDtoMapper.batchInsert(mbReturnDtos);

        }
        return mbReturnDtos;
    }

    private static List<Package> getPackagesByMbReturnDtos(List<MbReturnDto> mbReturnDtos, Map<String, Package> codePackageMap) {
        if (CollectionUtils.isEmpty(mbReturnDtos)) {
            return new ArrayList<>();
        }
        List<Package> returnList = mbReturnDtos.stream().map(item -> {
            Package pac = codePackageMap.containsKey(item.getCode()) ? codePackageMap.get(item.getCode()) :new Package();
            return getPackage(pac, item);
        }).collect(Collectors.toList());

        return returnList;
    }


    private static Package getPackage(Package pac, MbReturnDto mbReturnDto) {
        pac.setRemark(mbReturnDto.getRemark());
        AddressReceiver addressReceiver =
                ObjectUtils.isEmpty(pac.getReceiver()) ? new AddressReceiver() : pac.getReceiver();
        Services services =
                ObjectUtils.isEmpty(pac.getService()) ? new Services() : pac.getService();
        Parcel parcel =
                CollectionUtils.isEmpty(pac.getParcels()) ? new Parcel() : pac.getParcels().get(0);
        getAddressReceiver(addressReceiver, mbReturnDto.getAddressReceive());
        getParcel(parcel, mbReturnDto);
        getServices(services, mbReturnDto);
        pac.setReceiver(addressReceiver);
        pac.setService(services);
        pac.setParcels(Arrays.asList(parcel));

        pac.setPostalCode(addressReceiver.getPostalCode());
        pac.setPhone(pac.getReceiver().getPhone());
        return pac;
    }

    private static void getAddressReceiver(AddressReceiver addressReceiver, addressReceive addressReceive) {
        addressReceiver.setCountryCode(addressReceive.getCountryCode());
        addressReceiver.setCity(addressReceive.getCity());
        addressReceiver.setCompany(addressReceive.getCompanyStreet());
        addressReceiver.setAddress(addressReceive.getProvince() + addressReceive.getCity() + addressReceive.getDistrict() + addressReceive.getDoorcode() + addressReceive.getStreet1());
        addressReceiver.setEmail(addressReceive.getEmail());
        addressReceiver.setName(addressReceive.getReceiver());
        addressReceiver.setPhone(addressReceive.getTelephone());
        addressReceiver.setPostalCode(addressReceive.getZipcode());
//        addressReceiver.setPostalCode();

    }

    private static void getParcel(Parcel parcel, MbReturnDto mbReturnDto) {
        parcel.setSizeX(Integer.valueOf(mbReturnDto.getLength()));
        parcel.setSizeY(Integer.valueOf(mbReturnDto.getWidth()));
        parcel.setSizeZ(Integer.valueOf(mbReturnDto.getHeight()));
        parcel.setWeight(new BigDecimal(mbReturnDto.getWeightReal()));
        parcel.setCustomerData1(mbReturnDto.getProductNameEn());
//        waybill 运货单 ???
        parcel.setWaybill(mbReturnDto.getExpressChannelCode());
        parcel.setStatus(mbReturnDto.getStatus());
    }

    private static void getServices(Services services, MbReturnDto mbReturnDto) {
        services.setCodAmount(mbReturnDto.getProductValue());
        services.setCodCurrency(mbReturnDto.getCurrencyCode());
    }

    private void receiveMb(MbReceiveDto mbReceiveDto) throws Exception {
        String notify = mbReceiveDto.getNotify().replace(" ", "+");
        String notifyJson = Base64.decryptBASE64(notify);
//        base64_encode(json_encode(xxx))
        JSONObject jsonObject = JSONObject.parseObject(notifyJson);
//        {
//            "notify":"orderChange", "orderInfo":{
//            "codes":[
//            "内部订单号",
//                    "内部订单号",
//            "内部订单号"
//],
//            "count":"返回单号数"
//        }
//        }
        String notifyStr = jsonObject.get("notify").toString();
        List<String> codes = JSON.parseArray(JSONObject.parseObject(jsonObject.get("orderInfo").toString()).get("codes").toString(), String.class);

    }

    @Transactional(rollbackFor = Exception.class)
    public void insertPackages(List<Package> packages) throws Exception {

        BatchTaskHistory batchTaskHistory = new BatchTaskHistory();
        batchTaskHistory.setType("马帮导入");
        batchTaskHistory.setStatus("导入成功");
//        batchTaskHistory.setExcelUrl(documents.getId().toString());
//        batchTaskHistory.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
//        batchTaskHistory.setUpdateUser(SecurityUtils.getLoginUser().getUserId().toString());
        batchTaskHistory.setId(sequenceMapper.selectNextvalByName("bat_task_seq"));

        /**
         * 一系列处理
         */
        //生成id 并且更新
        Map<String, Sequence> nameMap = getSeqMap(packages.size());
        AddressSender addressSender = getSender();
        List<AddressReceiver> addressReceivers = new ArrayList<>();
        List<Services> servicesList = new ArrayList<>();
        List<Parcel> parcels = new ArrayList<>();
        for (Package packageVo : packages) {
            AddressReceiver addressReceiver = packageVo.getReceiver();
//            addressReceiver.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
//            addressReceiver.setUpdateUser(SecurityUtils.getLoginUser().getUserId().toString());
            addressReceiver.setId(getId(nameMap, "receiver_seq"));

            Services services = packageVo.getService();
//            services.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
//            services.setUpdateUser(SecurityUtils.getLoginUser().getUserId().toString());
            services.setId(getId(nameMap, "services_seq"));

            packageVo.setReceiverId(addressReceiver.getId());
            packageVo.setSenderId(addressSender.getId());
            packageVo.setServicesId(services.getId());
            packageVo.setBatchId(batchTaskHistory.getId());
            packageVo.setId(getId(nameMap, "package_seq"));
//            用户还未确定
//            packageVo.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
//            packageVo.setUpdateUser(SecurityUtils.getLoginUser().getUserId().toString());
            Parcel parcel = packageVo.getParcels().get(0);
            parcel.setPackId(packageVo.getId());
//            parcel.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
//            parcel.setUpdateUser(SecurityUtils.getLoginUser().getUserId().toString());

            packageVo.setSender(addressSender);

            addressReceivers.add(packageVo.getReceiver());
            servicesList.add(packageVo.getService());
            parcels.addAll(packageVo.getParcels());
        }
        try {
            List<PackagesGenerationResponse> returnResponses = dpdServicesXMLClient.generatePackagesNumberByBusiness(packages);

            batchTaskHistory.setSessionId(returnResponses.get(0).getSessionId());
            batchTaskHistory.setSuccessNum((int) returnResponses.stream().filter(item -> "OK".equals(item.getPkgStatus())).count());
            batchTaskHistory.setFailNum((int) returnResponses.stream().filter(item -> !"OK".equals(item.getPkgStatus())).count());
            batchTaskHistoryMapper.insertBatchTaskHistoryWithId(batchTaskHistory);
            packageMapper.batchInsert(packages);
            addressReceiverMapper.batchInsert(addressReceivers);
            servicesMapper.batchInsert(servicesList);
            parcelMapper.batchInsert(parcels);
            packagesGenerationResponseMapper.batchInsert(returnResponses);
        }catch (Exception e){
            batchTaskHistory.setStatus("导入失败");
            batchTaskHistoryMapper.insertBatchTaskHistoryWithId(batchTaskHistory);
            e.printStackTrace();
            throw new Exception("马帮数据导入失败");
        }
    }

    private static String[] SEQ_NAMES = {"receiver_seq", "package_seq", "services_seq"};

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

    private AddressSender getSender() {
        AddressSender senderParam = new AddressSender();
        senderParam.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
        return addressSenderMapper.selectAddressSenderList(senderParam).get(0);
    }

    /**
     *
     */
    private void changeStatusToAccept(List<String> codes) {
        Map<String, Package> codePackageMap = this.getPackageListByCodes(codes);

    }

    private void aaa(String code, Package pac){
        MbAccept mbAccept = new MbAccept();
        mbAccept.setCode(code);
        mbAccept.setChangeStatus("accept");
        mbAccept.setExpressChannelCode(pac.getParcels().get(0).getWaybill());

//        ???????
//        mbAccept.setLabelPDFUrl();

//        String res = HttpUtils.sendPost(url, getParamStr("api.biaoju.order.update", apiAccountId, apiKey, encodeParamsMap));

    }

}
