package com.ruoyi.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.entity.SysUser;
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

    @Value("${mb.phone}")
    private String phone;

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

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private UserAuthorizationMapper userAuthorizationMapper;

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
        if (packagesGenerationResponseMap.containsKey(pac.getId())) {
            pac.setPackagesGenerationResponse(packagesGenerationResponseMap.get(pac.getId()));
        }
        if (documentsMap.containsKey(parcel.getPackageId())) {
            pac.setDocuments(documentsMap.get(parcel.getPackageId()));
        }
        return pac;
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
        String enStr = net.arnx.jsonic.JSON.encode(encodeParamsMap);
        String res = HttpUtils.sendPost(url, getParamStr("api.biaoju.order.find", (long) 29689, "2675166dc4b2242bf88c3ea25a452b3f", encodeParamsMap, enStr));
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isEmpty(res) || res.contains("ErrorCode")) {
            sb.append("马帮接收信息错误");
            if (res.contains("ErrorCode")) {
                JSONObject jsonObject = JSON.parseObject(res);
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
//        String enStr = net.arnx.jsonic.JSON.encode(encodeParamsMap);
//        String res = HttpUtils.sendPost(url, getParamStr("api.biaoju.order.find", (long) 29689, "2675166dc4b2242bf88c3ea25a452b3f", encodeParamsMap, enStr));
//        JSONObject jsonObject = JSONObject.parseObject(JSONObject.parseObject(res).get("Data").toString());
//        List<MbReturnDto> mbReturnDtos = JSON.parseArray(jsonObject.get("orders").toString(), MbReturnDto.class);
//
////        List<Package> packages = getPackagesByMbReturnDtos(mbReturnDtos);
//        System.out.println("res" + res);
//    }

    /**
     * 获取马帮结果
     *
     * @param methodName      方法名称
     * @param encodeParamsMap 参数
     * @return
     * @throws Exception
     */
    private String getMbRes(String methodName, Map<String, String> encodeParamsMap) throws Exception {
        String enStr = net.arnx.jsonic.JSON.encode(encodeParamsMap);
        String res = HttpUtils.sendPost(url, getParamStr(methodName, apiAccountId, apiKey, encodeParamsMap, enStr));
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isEmpty(res) || res.contains("ErrorCode")) {
            if (res.contains("ErrorCode")) {
                JSONObject jsonObject = JSON.parseObject(res);
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

    private static String getParamStr(String api,
                                      Long apiAccountId,
                                      String apiKey,
                                      Map<String, String> encodeParamsMap,
                                      String encodeParamsMapStr) {
        long timestamp = System.currentTimeMillis() / 1000;
//        String enStr = net.arnx.jsonic.JSON.encode(encodeParamsMap);
        String base64EnStr = CollectionUtils.isEmpty(encodeParamsMap) ? "" : Base64.encode(encodeParamsMapStr.getBytes());
        StringBuilder sb = new StringBuilder();
        sb.append("api=").append(api)
                .append("&timestamp=").append(timestamp)
                .append("&apiAccountId=").append(apiAccountId);
        sb.append("&encodeParams=").append(Base64.encode(encodeParamsMapStr.getBytes()));
        sb.append("&sign=").append(getSign(api, apiAccountId, apiKey, base64EnStr, timestamp));
        return sb.toString();
    }

    /**
     * 01接收马帮的主动通知
     *
     * @param mbReceiveDto
     * @throws Exception
     */
    @Override
    public void receiveMb(MbReceiveDto mbReceiveDto) throws Exception {
        String notify = mbReceiveDto.getNotify().replace(" ", "+");
        String userId = getUserId(mbReceiveDto.getSign(), mbReceiveDto.getNotify(), mbReceiveDto.getTimestamp());
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
        notifyStr = "orderChange";
//        codes.add("892213414595342");
        codes.add("892213617113432");
        this.dealNotify(codes, notifyStr, userId);
    }

    private String getUserId(String sign, String notify, int timestamp) throws Exception {
        String apiKey = sign.replace(notify, "").replace(String.valueOf(timestamp), "");
        SysUser user = new SysUser();
        user.setMbToken(apiKey);
        SysUser user1 = userMapper.selectUserByUser(user);
        if (ObjectUtils.isEmpty(user1)) {
            throw new Exception("apiKey系统中未找到匹配用户");
        }
        return user1.getUserId().toString();
    }

    /**
     * 02当有待处理订单提交到镖局物流平时，触发此通知
     *
     * @param codes
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void dealNotify(List<String> codes, String notify, String userId) throws Exception {
        //获取到codes
        String codeStr = String.join(",", codes);
        Map<String, String> param = new HashMap<>();
        param.put("codes", codeStr);
        String returnStr = getMbRes("api.biaoju.order.find", param);
        JSONObject jsonObject = JSON.parseObject(JSON.parseObject(returnStr).get("Data").toString());
        List<MbReturnDto> mbReturnDtos = JSON.parseArray(jsonObject.get("orders").toString(), MbReturnDto.class);
        if ("orderChange".equals(notify)) {
            List<String> existCodes = mbReturnDtoMapper.selectMbReturnDtoCodeListByCodes(codes);
            List<Parcel> parcelList = parcelMapper.selectParcelListByReferenceIn(codes);
            List<String> parcelCodesList =
                    CollectionUtils.isEmpty(parcelList) ? new ArrayList<>() : parcelList.stream().map(Parcel::getReference).collect(Collectors.toList());

            mbReturnDtos.forEach(
                    item -> {
                        item.setAddressBackStr(JSON.toJSONString(item.getAddressBack()));
                        item.setAddressPickupStr(JSON.toJSONString(item.getAddressPickup()));
                        item.setAddressReceiveStr(JSON.toJSONString(item.getAddressReceive()));
                        item.setExtendFieldsStr(JSON.toJSONString(item.getExtendFields()));
                        item.setItemListStr(JSON.toJSONString(item.getItemList()));
                    }
            );

            List<MbReturnDto> insertList = mbReturnDtos.stream().filter(item -> !existCodes.contains(item.getCode())).collect(Collectors.toList());
            List<MbReturnDto> updateList = mbReturnDtos.stream().filter(item -> existCodes.contains(item.getCode())).collect(Collectors.toList());
            List<MbReturnDto> insertDPDList = mbReturnDtos.stream().filter(item -> !parcelCodesList.contains(item.getCode())).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(insertList)) {
                mbReturnDtoMapper.batchInsert(insertList);
            }
            if (!CollectionUtils.isEmpty(updateList)) {
                mbReturnDtoMapper.batchUpdate(updateList);
            }
            List<String> errMsgList = new ArrayList<>();
            if (!CollectionUtils.isEmpty(insertDPDList)) {
                List<MbReturnDto> checkUserList = checkUser(userId, insertDPDList, errMsgList);
                List<MbReturnDto> correctList = checkCountryAndZip(checkUserList, errMsgList);
                if (CollectionUtils.isEmpty(correctList)) {
                    return;
                }
                Map<String, Package> codePackageMap = this.getPackageListByCodes(correctList.stream().map(MbReturnDto::getCode).collect(Collectors.toList()));
                List<Package> getPackagesByMbReturnDtos = this.getPackagesByMbReturnDtos(correctList, codePackageMap);
                this.insertPackages(getPackagesByMbReturnDtos);
                this.changeStatusToAccept(correctList.stream().map(MbReturnDto::getCode).collect(Collectors.toList()));
            }
        }
    }

    private List<MbReturnDto> checkUser(String userId, List<MbReturnDto> mbReturnDtos, List<String> errMsgList) {
        UserAuthorizationSys param = new UserAuthorizationSys();
        param.setCreateBy(userId);
        List<UserAuthorizationSys> userAuthorizationSys = userAuthorizationMapper.selectUserAuthorizationList(param);

        boolean errorFlag = false;
        Map<String, String> errorMap = new HashMap<>();
        List<MbReturnDto> correctInsertMbReturnDto = new ArrayList<>();

        if (CollectionUtils.isEmpty(userAuthorizationSys)) {
            for (MbReturnDto dto : mbReturnDtos) {
                customer customer = JSON.parseObject(dto.getCustomer(), customer.class);
                dto.setCreateBy(userId);
                dto.setUpdateBy(userId);
                errorFlag = true;
                String errSb = "customer, api_key: " + customer.getLogisticsKeys().getWishu().getApi_key() +
                        "不存在, 或者与api_secret不匹配, 请联系：" + phone;
                errorMap.put(dto.getCode(), errSb);
            }
        } else {
            Map<String, String> userTokenMap = userAuthorizationSys.stream().collect(toMap(
                    UserAuthorizationSys::getUserName, UserAuthorizationSys::getUserToken
            ));
            for (MbReturnDto dto : mbReturnDtos) {
                customer customer = JSON.parseObject(dto.getCustomer(), customer.class);
                dto.setCreateBy(userId);
                dto.setUpdateBy(userId);
                if (!userTokenMap.containsKey(customer.getLogisticsKeys().getWishu().getApi_key())
                        || !userTokenMap.get(customer.getLogisticsKeys().getWishu().getApi_key())
                        .equals(customer.getLogisticsKeys().getWishu().getApi_secret())) {
                    errorFlag = true;
                    String errSb = "customer, api_key: " + customer.getLogisticsKeys().getWishu().getApi_key() +
                            "不存在, 或者与api_secret不匹配, 请联系：" + phone;
                    errorMap.put(dto.getCode(), errSb);
                } else {
                    correctInsertMbReturnDto.add(dto);
                }
            }
        }
        if (errorFlag) {
            changeStatusToException(errorMap, errMsgList);
        }
        return correctInsertMbReturnDto;
    }

    private List<MbReturnDto> checkCountryAndZip(List<MbReturnDto> mbReturnDtos, List<String> errMsgList) {
        if (CollectionUtils.isEmpty(mbReturnDtos)) {
            return new ArrayList<>();
        }
        Map<String, String> checkCountryAndZip = mbReturnDtos.parallelStream().collect(toMap(
                MbReturnDto::getCode,
                item -> dpdServicesXMLClient.findPostalCode(item.getAddressReceive().getCountryCode(), item.getAddressReceive().getZipcode())
        ));
        if (CollectionUtils.isEmpty(checkCountryAndZip)
                || checkCountryAndZip.containsValue("NONEXISTING_POSTAL_CODE")
                || checkCountryAndZip.containsValue("NONEXISTING_COUNTRY_CODE")
                || checkCountryAndZip.containsValue("WRONG_POSTAL_PATTERN")) {
            changeStatusToException(checkCountryAndZip, errMsgList);
        }
        List<String> correctCodeList = new ArrayList<>();
        for (String code : checkCountryAndZip.keySet()) {
            if ("OK".equals(checkCountryAndZip.get(code))) {
                correctCodeList.add(code);
            }
        }
        return mbReturnDtos.stream().filter(item -> correctCodeList.contains(item.getCode())).collect(Collectors.toList());
    }

    /**
     * 03 根据马帮数据生成Package数据
     *
     * @param mbReturnDtos
     * @param codePackageMap
     * @return
     */
    private List<Package> getPackagesByMbReturnDtos(List<MbReturnDto> mbReturnDtos, Map<String, Package> codePackageMap) {
        if (CollectionUtils.isEmpty(mbReturnDtos)) {
            return new ArrayList<>();
        }
        return mbReturnDtos.stream().map(item -> {
            Package pac = !CollectionUtils.isEmpty(codePackageMap) && codePackageMap.containsKey(item.getCode()) ? codePackageMap.get(item.getCode()) : new Package();
            return getPackage(pac, item);
        }).collect(Collectors.toList());
    }

    /**
     * 04处理packages数据，包括数据库新增与对接DPD
     *
     * @param packages
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void insertPackages(List<Package> packages) throws Exception {

        if (CollectionUtils.isEmpty(packages)) {
            return;
        }
        BatchTaskHistory batchTaskHistory = new BatchTaskHistory();
        batchTaskHistory.setType("马帮导入");
        batchTaskHistory.setStatus("导入成功");
//        batchTaskHistory.setExcelUrl(documents.getId().toString());
        batchTaskHistory.setCreateUser(packages.get(0).getCreateUser());
        batchTaskHistory.setUpdateUser(packages.get(0).getUpdateUser());
        batchTaskHistory.setId(sequenceMapper.selectNextvalByName("bat_task_seq"));

        /**
         * 一系列处理
         */
        //生成id 并且更新
        Map<String, Sequence> nameMap = getSeqMap(packages.size());
        AddressSender addressSender = getSender(packages.get(0).getCreateUser());
        List<AddressReceiver> addressReceivers = new ArrayList<>();
        List<Services> servicesList = new ArrayList<>();
        List<Parcel> parcels = new ArrayList<>();
        for (Package packageVo : packages) {
            AddressReceiver addressReceiver = packageVo.getReceiver();
            addressReceiver.setCreateUser(packageVo.getCreateUser());
            addressReceiver.setUpdateUser(packageVo.getUpdateUser());
            addressReceiver.setId(getId(nameMap, "receiver_seq"));

            Services services = packageVo.getService();
            services.setCreateUser(packageVo.getCreateUser());
            services.setUpdateUser(packageVo.getUpdateUser());
            services.setId(getId(nameMap, "services_seq"));

            packageVo.setReceiverId(addressReceiver.getId());
            packageVo.setSenderId(addressSender.getId());
            packageVo.setServicesId(services.getId());
            packageVo.setBatchId(batchTaskHistory.getId());
            packageVo.setId(getId(nameMap, "package_seq"));
            Parcel parcel = packageVo.getParcels().get(0);
            parcel.setPackId(packageVo.getId());
            parcel.setCreateUser(packageVo.getCreateUser());
            parcel.setUpdateUser(packageVo.getUpdateUser());

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
        } catch (Exception e) {
            batchTaskHistory.setStatus("导入失败");
            batchTaskHistoryMapper.insertBatchTaskHistoryWithId(batchTaskHistory);
            e.printStackTrace();
            throw new Exception("马帮数据导入失败");
        }
    }

    private Package getPackage(Package pac, MbReturnDto mbReturnDto) {
        pac.setRemark(mbReturnDto.getRemark());
        pac.setCreateUser(StringUtils.isEmpty(pac.getCreateUser()) ? mbReturnDto.getCreateBy() : pac.getCreateUser());
        pac.setUpdateUser(StringUtils.isEmpty(pac.getUpdateUser()) ? mbReturnDto.getUpdateBy() : pac.getUpdateUser());
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

    private void getAddressReceiver(AddressReceiver addressReceiver, addressReceive addressReceive) {
        addressReceiver.setCountryCode(addressReceive.getCountryCode());
        addressReceiver.setCity(addressReceive.getCity());
        addressReceiver.setAddress(addressReceive.getProvince() + addressReceive.getCity() + addressReceive.getDistrict() + addressReceive.getDoorcode() + addressReceive.getStreet1());
        addressReceiver.setCompany(StringUtils.isEmpty(addressReceive.getCompanyStreet()) ? addressReceiver.getAddress() : addressReceive.getCompanyStreet());
        addressReceiver.setEmail(addressReceive.getEmail());
        addressReceiver.setName(addressReceive.getReceiver());
        addressReceiver.setPhone(StringUtils.isEmpty(addressReceive.getTelephone()) ? addressReceive.getMobile() : addressReceive.getTelephone());
        addressReceiver.setPostalCode(addressReceive.getZipcode());

    }

    private void getParcel(Parcel parcel, MbReturnDto mbReturnDto) {
        //单位的问题
        parcel.setSizeX(Integer.valueOf(mbReturnDto.getLength()));
        parcel.setSizeY(Integer.valueOf(mbReturnDto.getWidth()));
        parcel.setSizeZ(Integer.valueOf(mbReturnDto.getHeight()));
        parcel.setWeight(new BigDecimal(mbReturnDto.getWeightReal()).divide(new BigDecimal(1000)).setScale(2, BigDecimal.ROUND_HALF_UP));
        parcel.setCustomerData1(mbReturnDto.getProductNameEn());
        parcel.setReference(mbReturnDto.getCode());
//        waybill 运货单 ???
        parcel.setWaybill(mbReturnDto.getExpressChannelCode());
        parcel.setStatus(mbReturnDto.getStatus());
    }

    private void getServices(Services services, MbReturnDto mbReturnDto) {
        services.setCodAmount(mbReturnDto.getProductValue());
        services.setCodCurrency(mbReturnDto.getCurrencyCode());
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

    /**
     * 应该根据通知获取到用户信息
     *
     * @return
     */
    private AddressSender getSender(String userId) {
        AddressSender senderParam = new AddressSender();
        senderParam.setCreateUser(userId);
        return addressSenderMapper.selectAddressSenderList(senderParam).get(0);
    }

    /**
     * 04 将DPD中新增数据更新到马帮上
     *
     * @param codes
     */
    private void changeStatusToAccept(List<String> codes) {
        //查询更新后的数据
        Map<String, Package> codePackageMap = this.getPackageListByCodes(codes);
        for (String code : codePackageMap.keySet()) {
//            this.changeOne(code, codePackageMap.get(code));
        }
    }

    private void changeStatusToException(Map<String, String> checkCountryAndZip, List<String> errMsgList) {
        for (String code : checkCountryAndZip.keySet()) {
            if (!"OK".equals(checkCountryAndZip.get(code))) {
                MbException mbException = new MbException();
                mbException.setCode(code);
                mbException.setChangeStatus("changeStatus");
                mbException.setProcessMessage(checkCountryAndZip.get(code));

                Map<String, String> encodeParamsMap = new HashMap<>();
                encodeParamsMap.put("changeStatus", "exception");
                String enStr = net.arnx.jsonic.JSON.encode(mbException);
                errMsgList.add("code: " + code + "导入失败，失败原因：" + checkCountryAndZip.get(code));
                String res = HttpUtils.sendPost(url, getParamStr("api.biaoju.order.update", apiAccountId, apiKey, encodeParamsMap, enStr));
            }
        }
    }

    /**
     * 会发送多次post请求
     *
     * @param code
     * @param pac
     */
    private void changeOne(String code, Package pac) {
        MbAccept mbAccept = new MbAccept();
        mbAccept.setCode(code);
        mbAccept.setChangeStatus("accept");
        mbAccept.setExpressChannelCode(pac.getParcels().get(0).getWaybill());

//        本地现在是二进制文件 怎么传输？
//        mbAccept.setLabelPDFUrl();
        Map<String, String> encodeParamsMap = new HashMap<>();
        encodeParamsMap.put("changeStatus", "accept");
        String enStr = net.arnx.jsonic.JSON.encode(mbAccept);
        String res = HttpUtils.sendPost(url, getParamStr("api.biaoju.order.update", apiAccountId, apiKey, encodeParamsMap, enStr));
        //校验

    }

}
