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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
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

    @Value("${frontPath}")
    private String frontPath;

    @Value("${mb.phone}")
    private String phone;

    @Value("${mb.postUserFlag}")
    private Boolean postUserFlag;

    @Value("${mb.senderUserId}")
    private Long senderUserId;

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

    @Autowired
    private MbMsgMapper mbMsgMapper;

    private void saveMbMsg(String code, String msgCode, String msg, String remark) {
        MbMsg mbMsg = new MbMsg();
        mbMsg.setCode(code);
        mbMsg.setMsgCode(msgCode);
        mbMsg.setMsg(msg);
        mbMsg.setRemark(remark);
        mbMsgMapper.insertMbMsg(mbMsg);
    }

    private Map<String, Package> getPackageListByCodes(List<String> pCodes) {
        List<Parcel> parcelList = parcelMapper.selectParcelListByReferenceIn(pCodes);
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

    private void test2() throws Exception {
        String url = "http://www.sandbox.i8956.com/interface/index.php";
        Map<String, String> encodeParamsMap = new HashMap<>();
        String enStr = net.arnx.jsonic.JSON.encode(encodeParamsMap);
        String res = HttpUtils.sendPost(url, getParamStr("api.biaoju.order.find" , (long) 29689, "2675166dc4b2242bf88c3ea25a452b3f" , encodeParamsMap, enStr));
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
     * 根据用户绑定的马帮数据  获取马帮结果
     *
     * @param methodName
     * @param encodeParamsMap
     * @param apiAccountId    用户绑定的apiAccountId
     * @param apiKey          用户绑定的apiKey
     * @return
     * @throws Exception
     */
    private String getMbRes(String methodName,
                            Map<String, String> encodeParamsMap,
                            Long apiAccountId,
                            String apiKey) throws Exception {
        String enStr = net.arnx.jsonic.JSON.encode(encodeParamsMap);
        String res = HttpUtils.sendPost(url, getParamStr(methodName, apiAccountId, apiKey, encodeParamsMap, enStr));
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isEmpty(res) || res.contains("ErrorCode")) {
            if (res.contains("ErrorCode")) {
                JSONObject jsonObject = JSON.parseObject(res);
                saveMbMsg("find" , jsonObject.get("ErrorCode").toString(), jsonObject.get("Data").toString(), "主动查找");
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
        log.info("receiveMb start");
        String notify = mbReceiveDto.getNotify().replace(" " , "+");
//        SysUser user1 = getUserId(mbReceiveDto.getSign(), mbReceiveDto.getNotify(), mbReceiveDto.getTimestamp());
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
//        notifyStr = "orderChange";
//        codes.add("892213414595342");
//        codes.add("892214828719791");
        List<String> errMsgList = new ArrayList<>();
//        if (!postUserFlag) {
        SysUser user1 = userMapper.selectUserById(senderUserId);
        dealNotify(codes, notifyStr, user1, errMsgList, postUserFlag, "马帮主动通知");
//        } else {
//            dealNotify(codes, notifyStr, null, errMsgList, postUserFlag, "马帮主动通知");
//        }
    }

    @Override
    public void changeAccept(MbImport mbImport) {
//        List<String> codes = new ArrayList<>();
//        codes.add(mbImport.getCode());
//        SysUser sysUser = userMapper.selectUserById(senderUserId);
//        changeStatusToAccept(codes, sysUser);
    }

    /**
     * 废弃- 因为md5加密无法解密
     *
     * @param sign
     * @param notify
     * @param timestamp
     * @return
     * @throws Exception
     */
    private SysUser getUserId(String sign, String notify, int timestamp) throws Exception {
        String apiKey = sign.replace("notify=" + notify, "").replace("&timestamp=" + timestamp, "");
        SysUser user = new SysUser();
        user.setApiKey(apiKey);
        SysUser user1 = userMapper.selectUserByUser(user);
        if (ObjectUtils.isEmpty(user1)) {
            throw new Exception("apiKey系统中未找到匹配用户");
        }
        return user1;
    }

    /**
     * 02当有待处理订单提交到镖局物流平时，触发此通知
     *
     * @param codes
     * @param notify
     * @param user1
     * @param errMsgList
     * @param checkAuthFlag
     * @param typeStr
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void dealNotify(List<String> codes,
                           String notify,
                           SysUser user1,
                           List<String> errMsgList,
                           Boolean checkAuthFlag,
                           String typeStr) throws Exception {
        //获取到codes
        String codeStr = String.join("," , codes);
        Map<String, String> param = new HashMap<>();
        param.put("codes" , codeStr);
        String returnStr = getMbRes("api.biaoju.order.find" , param,
                null == user1 ? apiAccountId : user1.getApiAccountId(), null == user1 ? apiKey : user1.getApiKey());
        JSONObject jsonObject = JSON.parseObject(JSON.parseObject(returnStr).get("Data").toString());
        List<MbReturnDto> mbReturnDtos = JSON.parseArray(jsonObject.get("orders").toString(), MbReturnDto.class);
        if ("orderChange".equals(notify)) {
            List<String> existCodes = mbReturnDtoMapper.selectMbReturnDtoCodeListByCodes(codes);
            List<Parcel> parcelList = parcelMapper.selectParcelListByReferenceIn(mbReturnDtos.stream().map(MbReturnDto::getPlatformTradeCode).collect(Collectors.toList()));
            List<String> parcelCodesList =
                    CollectionUtils.isEmpty(parcelList) ? new ArrayList<>() : parcelList.stream().map(Parcel::getReference).collect(Collectors.toList());

            mbReturnDtos.forEach(
                    item -> {
                        addressReceive addressReceive = item.getAddressReceive();
                        addressReceive.setZipcode(addressReceive.getZipcode().replace("-" , ""));
                        item.setAddressBackStr(JSON.toJSONString(item.getAddressBack()));
                        item.setAddressPickupStr(JSON.toJSONString(item.getAddressPickup()));
                        item.setAddressReceiveStr(JSON.toJSONString(item.getAddressReceive()));
                        item.setExtendFieldsStr(JSON.toJSONString(item.getExtendFields()));
                        item.setItemListStr(JSON.toJSONString(item.getItemList()));
                        if (null != user1) {
                            item.setCreateBy(user1.getUserId().toString());
                            item.setUpdateBy(user1.getUserId().toString());
                        }
                    }
            );

            List<MbReturnDto> insertList = mbReturnDtos.stream().filter(item -> !existCodes.contains(item.getCode())).collect(Collectors.toList());
            List<MbReturnDto> updateList = mbReturnDtos.stream().filter(item -> existCodes.contains(item.getCode())).collect(Collectors.toList());
            List<MbReturnDto> insertDPDList = mbReturnDtos.stream().filter(item -> !parcelCodesList.contains(item.getPlatformTradeCode())).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(insertList)) {
                mbReturnDtoMapper.batchInsert(insertList);
            }
            if (!CollectionUtils.isEmpty(updateList)) {
//                mbReturnDtoMapper.batchUpdate(updateList);
            }
            if (!CollectionUtils.isEmpty(insertDPDList)) {
                List<MbReturnDto> checkUserList;
                if (checkAuthFlag) {
                    checkUserList = checkUser(user1, insertDPDList, errMsgList);
                } else {
                    checkUserList = insertDPDList;
                }
                List<MbReturnDto> correctList = checkCountryAndZip(user1, checkUserList, errMsgList);
                if (CollectionUtils.isEmpty(correctList)) {
                    return;
                }
                Map<String, Package> codePackageMap = this.getPackageListByCodes(correctList.stream().map(MbReturnDto::getPlatformTradeCode).collect(Collectors.toList()));
                List<Package> getPackagesByMbReturnDtos = this.getPackagesByMbReturnDtos(correctList, codePackageMap);
                this.insertPackages(getPackagesByMbReturnDtos, typeStr);
                this.changeStatusToAccept(
                        correctList.stream().collect(toMap(MbReturnDto::getPlatformTradeCode, MbReturnDto::getCode)), user1);
            }
        }
    }

    private static final Logger log = LoggerFactory.getLogger(OuterServiceImpl.class);

    /**
     * 校验里面的customer数据 是否是咱们系统的客户
     *
     * @param user1
     * @param mbReturnDtos
     * @param errMsgList
     * @return
     */
    private List<MbReturnDto> checkUser(SysUser user1, List<MbReturnDto> mbReturnDtos, List<String> errMsgList) {
        log.info("checkUser start");
        UserAuthorizationSys param = new UserAuthorizationSys();
        List<UserAuthorizationSys> userAuthorizationSys = userAuthorizationMapper.selectUserAuthorizationList(param);

        boolean errorFlag = false;
        Map<String, String> errorMap = new HashMap<>();
        List<MbReturnDto> correctInsertMbReturnDto = new ArrayList<>();

        if (CollectionUtils.isEmpty(userAuthorizationSys)) {
            for (MbReturnDto dto : mbReturnDtos) {
                customer customer = JSON.parseObject(dto.getCustomer(), customer.class);
                errorFlag = true;
                String errSb = "customer, api_key: " + customer.getLogisticsKeys().getGrand_qingyi().getApi_key() +
                        "不存在, 或者与api_secret不匹配, 请联系：" + phone;
                errorMap.put(dto.getCode(), errSb);
            }
        } else {
            Map<String, UserAuthorizationSys> userTokenMap = userAuthorizationSys.stream().collect(toMap(
                    UserAuthorizationSys::getUserName, Function.identity()
            ));
            for (MbReturnDto dto : mbReturnDtos) {
                customer customer = JSON.parseObject(dto.getCustomer(), customer.class);
                if (!userTokenMap.containsKey(customer.getLogisticsKeys().getGrand_qingyi().getApi_key())
                        || !userTokenMap.get(customer.getLogisticsKeys().getGrand_qingyi().getApi_key()).getUserToken()
                        .equals(customer.getLogisticsKeys().getGrand_qingyi().getApi_secret())) {
                    errorFlag = true;
                    String errSb = "customer, api_key: " + customer.getLogisticsKeys().getGrand_qingyi().getApi_key() +
                            "不存在, 或者与api_secret不匹配, 请联系：" + phone;
                    errorMap.put(dto.getCode(), errSb);
                } else {
                    dto.setCreateBy(userTokenMap.get(customer.getLogisticsKeys().getGrand_qingyi().getApi_key()).getCreateBy());
                    dto.setUpdateBy(userTokenMap.get(customer.getLogisticsKeys().getGrand_qingyi().getApi_key()).getCreateBy());
                    correctInsertMbReturnDto.add(dto);
                }
            }
        }
        if (errorFlag) {
            changeStatusToException(errorMap, errMsgList, user1);
        }
        log.info("checkUser end" + JSONObject.toJSONString(correctInsertMbReturnDto));
        return correctInsertMbReturnDto;

    }

    /**
     * 马帮邮编校验
     *
     * @param user1
     * @param mbReturnDtos
     * @param errMsgList
     * @return
     */
    private List<MbReturnDto> checkCountryAndZip(SysUser user1, List<MbReturnDto> mbReturnDtos, List<String> errMsgList) {
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
            changeStatusToException(checkCountryAndZip, errMsgList, user1);
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
            Package pac = !CollectionUtils.isEmpty(codePackageMap) && codePackageMap.containsKey(item.getPlatformTradeCode())
                    ? codePackageMap.get(item.getPlatformTradeCode()) : new Package();
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
    public void insertPackages(List<Package> packages, String typeStr) throws Exception {

        if (CollectionUtils.isEmpty(packages)) {
            return;
        }
        BatchTaskHistory batchTaskHistory = new BatchTaskHistory();
        batchTaskHistory.setType(typeStr);
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
        //sender是用自己的还是他们填写的？
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
//        AddressSender addressSender =
//                ObjectUtils.isEmpty(pac.getSender()) ? new AddressSender() : pac.getSender();
        Services services =
                ObjectUtils.isEmpty(pac.getService()) ? new Services() : pac.getService();
        Parcel parcel =
                CollectionUtils.isEmpty(pac.getParcels()) ? new Parcel() : pac.getParcels().get(0);

//        getAddressSender(addressSender, mbReturnDto.getAddressPickup());
        getAddressReceiver(addressReceiver, mbReturnDto.getAddressReceive());
        getParcel(parcel, mbReturnDto);
        getServices(services, mbReturnDto);
        pac.setReceiver(addressReceiver);
        pac.setService(services);
        pac.setParcels(Arrays.asList(parcel));

        pac.setPostalCode(addressReceiver.getPostalCode());
        pac.setPhone(pac.getReceiver().getPhone());
        pac.setRef1("Whatsapp:+86 13726455903");
        pac.setRef2("qingguoqn@gmail.com");
        return pac;
    }

    private void getAddressReceiver(AddressReceiver addressReceiver, addressReceive addressReceive) {
        addressReceiver.setCountryCode(addressReceive.getCountryCode());
        addressReceiver.setCity(addressReceive.getCity());
        addressReceiver.setAddress(addressReceive.getStreet1());
        addressReceiver.setCompany(addressReceive.getReceiver());
        addressReceiver.setEmail(addressReceive.getEmail());
        addressReceiver.setName(addressReceive.getReceiver());
        addressReceiver.setPhone(StringUtils.isEmpty(addressReceive.getTelephone()) ? addressReceive.getMobile() : addressReceive.getTelephone());
        addressReceiver.setPostalCode(addressReceive.getZipcode());

    }

    private void getAddressSender(AddressSender addressSender, addressPickup addressPickup) {
        addressSender.setCountryCode(addressPickup.getCountryCode());
        addressSender.setCity(addressPickup.getCity());
        addressSender.setAddress(addressPickup.getProvince() + addressPickup.getCity() + addressPickup.getArea() + addressPickup.getAddress());
        addressSender.setCompany(addressPickup.getCompanyName());
        addressSender.setName(addressPickup.getContact());
        addressSender.setPhone(StringUtils.isEmpty(addressPickup.getTelephone()) ? addressPickup.getMobile() : addressPickup.getTelephone());
        addressSender.setPostalCode(addressPickup.getZipcode());
        addressSender.setFid((long) 1495);
    }

    private void getParcel(Parcel parcel, MbReturnDto mbReturnDto) {
        //单位的问题 不接受 xyz
//        parcel.setSizeX(Integer.valueOf(mbReturnDto.getLength()));
//        parcel.setSizeY(Integer.valueOf(mbReturnDto.getWidth()));
//        parcel.setSizeZ(Integer.valueOf(mbReturnDto.getHeight()));
        String weight = (StringUtils.isEmpty(mbReturnDto.getWeightReal()) || "0".equals(mbReturnDto.getWeightReal())) ? mbReturnDto.getWeightForcast() : mbReturnDto.getWeightReal();
        if (StringUtils.isEmpty(weight)) {
            weight = "1000";
        }
        parcel.setWeight(new BigDecimal(weight).divide(new BigDecimal(1000)).setScale(2, BigDecimal.ROUND_HALF_UP));
        parcel.setCustomerData1(mbReturnDto.getProductNameEn());
//        parcel.setReference(mbReturnDto.getCode());
        parcel.setReference(mbReturnDto.getPlatformTradeCode());
//        waybill 运货单 ???
        parcel.setWaybill(mbReturnDto.getExpressChannelCode());
        parcel.setStatus(mbReturnDto.getStatus());
    }

    private void getServices(Services services, MbReturnDto mbReturnDto) {
        services.setCodAmount(mbReturnDto.getProductValue());
        services.setCodCurrency(mbReturnDto.getCurrencyCode());
    }

    private static String[] SEQ_NAMES = {"receiver_seq" , "package_seq" , "services_seq"};

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
     * @param pCodeCodeMap
     */
    private void changeStatusToAccept(Map<String, String> pCodeCodeMap, SysUser user) {
        //查询更新后的数据
        Map<String, Package> codePackageMap = this.getPackageListByCodes(new ArrayList<>(pCodeCodeMap.keySet()));
        for (String pCode : codePackageMap.keySet()) {
            changeOne(pCodeCodeMap.get(pCode), codePackageMap.get(pCode), user);
        }
    }

    private void changeStatusToException(Map<String, String> checkCountryAndZip,
                                         List<String> errMsgList,
                                         SysUser user) {
        for (String code : checkCountryAndZip.keySet()) {
            if (!"OK".equals(checkCountryAndZip.get(code))) {
                MbException mbException = new MbException();
                mbException.setCode(code);
                mbException.setChangeStatus("exception");
                mbException.setProcessMessage(checkCountryAndZip.get(code));

                Map<String, String> encodeParamsMap = new HashMap<>();
                encodeParamsMap.put("changeStatus" , "exception");
                String enStr = net.arnx.jsonic.JSON.encode(mbException);
                errMsgList.add("code: " + code + "导入失败，失败原因：" + checkCountryAndZip.get(code));
                saveMbMsg(code + "sendBefore" , "mbException" , JSONObject.toJSONString(mbException), "");
                String res = HttpUtils.sendPost(url, getParamStr("api.biaoju.order.update" , null == user ? apiAccountId : user.getApiAccountId(), null == user ? apiKey : user.getApiKey(), encodeParamsMap, enStr));
                JSONObject jsonObject = JSON.parseObject(res);
                String exeStr = jsonObject.containsKey("Data") ? jsonObject.get("Data").toString() :
                        jsonObject.containsKey("Message") ? jsonObject.get("Message").toString() : "error";
                saveMbMsg(code + "exception" , jsonObject.get("ErrorCode").toString(), exeStr, mbException.toString());
            }
        }
    }

    @Value("${localIp}")
    private String localIp;

    /**
     * 会发送多次post请求
     *
     * @param code
     * @param pac
     */
    private void changeOne(String code, Package pac, SysUser user) {
        MbAccept mbAccept = new MbAccept();
        mbAccept.setCode(code);
        mbAccept.setChangeStatus("accept");
        mbAccept.setExpressChannelCode(pac.getParcels().get(0).getWaybill());
        mbAccept.setSupplierInnerCode(pac.getParcels().get(0).getWaybill());
        mbAccept.setLabelPDFUrl(localIp + pac.getParcels().get(0).getPackageId().toString() + ".pdf");

        saveMbMsg(code + "sendBefore" , "mbAccept" , JSONObject.toJSONString(mbAccept), "");

        //不为空就可以 用来拼接参数确定的
        Map<String, String> encodeParamsMap = new HashMap<>();
        encodeParamsMap.put("changeStatus" , "accept");
        String enStr = net.arnx.jsonic.JSON.encode(mbAccept);
        String res = HttpUtils.sendPost(url, getParamStr("api.biaoju.order.update" , null == user ? apiAccountId : user.getApiAccountId(), null == user ? apiKey : user.getApiKey(), encodeParamsMap, enStr));
        JSONObject jsonObject = JSON.parseObject(res);
        saveMbMsg(code + "accept" , jsonObject.get("ErrorCode").toString(), jsonObject.get("Data").toString(), mbAccept.toString());
    }

    @Override
    public List<String> importPackage(MultipartFile file,
                                      List<MbImport> mbImportList,
                                      SysUser sysUser,
                                      Boolean userFlag) throws Exception {
        if (CollectionUtils.isEmpty(mbImportList)) {
            return Arrays.asList("导入数据不能为空");
        }
        List<String> codes = mbImportList.stream().map(MbImport::getCode).collect(Collectors.toList());

        List<String> errMsgList = new ArrayList<>();
        dealNotify(codes, "orderChange" , sysUser, errMsgList, userFlag, "用户导入马帮信息");
        return errMsgList;
    }

    @Override
    public void getPDF(String pdfUrl, HttpServletResponse response) {
        String filePath = frontPath + "/" + pdfUrl;
        File file = new File(filePath);
        byte[] data;
        try {
            FileInputStream input = new FileInputStream(file);
            data = new byte[input.available()];
            input.read(data);
            response.getOutputStream().write(data);
            input.close();
        } catch (Exception e) {
            System.out.println("pdf文件错误");
        }
    }

    @Autowired
    private DpdMsgMapper dpdMsgMapper;

    @Override
    public void test() {
        DpdMsg dpdMsg = new DpdMsg();
        dpdMsg.setStatus("test");
        dpdMsg.setMsg("aaaaaaaa");
        dpdMsgMapper.insertDpdMsg(dpdMsg);
    }
}
