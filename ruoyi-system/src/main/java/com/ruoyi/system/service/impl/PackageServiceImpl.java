package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.enums.SysWaybill;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.DictUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.email.EmailUtil;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.DPDServicesExample.client.DPDInfoXMLClient;
import com.ruoyi.system.DPDServicesExample.client.DPDServicesXMLClient;
import com.ruoyi.system.domain.Package;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.domain.vo.ExportTwoPackageVo;
import com.ruoyi.system.domain.vo.PackageVo;
import com.ruoyi.system.dpdservices.DocumentGenerationResponseV1;
import com.ruoyi.system.dpdservices.ValidationInfoPGRV2;
import com.ruoyi.system.mapper.*;
import com.ruoyi.system.service.IPackageService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * 面单Service业务层处理
 *
 * @author ruoyi
 * @date 2022-01-02
 */
@Service
public class PackageServiceImpl implements IPackageService {
    @Autowired
    private PackageMapper packageMapper;

    @Autowired
    private RedirectPackageMapper redirectPackageMapper;

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
    private DocumentsMapper documentsMapper;

    @Autowired
    private PackagesGenerationResponseMapper packagesGenerationResponseMapper;

    @Autowired
    private DPDServicesXMLClient dpdServicesXMLClient;

    @Autowired
    private LogisticsInfoMapper logisticsInfoMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private PackageDpdMappingMapper packageDpdMappingMapper;

    @Autowired
    private RedirectRelMapper redirectRelMapper;

    @Autowired
    private ImportLogicContentMapper importLogicContentMapper;

    @Autowired
    private DPDInfoXMLClient dpdInfoXMLClient;

    /**
     * 查询面单
     *
     * @param id 面单主键
     * @return 面单
     */
    @Override
    public PackageVo selectPackageById(Long id) {
        Package pkg = packageMapper.selectPackageById(id);
        List<AddressSender> addressSenders = addressSenderMapper.selectAddressSenderByIdIn(Collections.singletonList(pkg.getSenderId()));
        Map<Long, AddressSender> addressSenderMap = addressSenders.stream().collect(toMap(AddressSender::getId, Function.identity()));
        List<AddressReceiver> addressReceivers = addressReceiverMapper.selectAddressReceiverByIdIn(Collections.singletonList(pkg.getReceiverId()));
        Map<Long, AddressReceiver> addressReceiverMap = addressReceivers.stream().collect(toMap(AddressReceiver::getId, Function.identity()));
        List<Services> servicesList = servicesMapper.selectServicesListByIdIn(Collections.singletonList(pkg.getServicesId()));
        Map<Long, Services> idServicesMap = servicesList.stream().collect(toMap(Services::getId, Function.identity()));
        List<Parcel> parcels = parcelMapper.selectParcelListByPackIdIn(Collections.singletonList(pkg.getId()));

        List<PackagesGenerationResponse> packagesGenerationResponses = packagesGenerationResponseMapper.selectPackagesGenerationResponseByPackIdIn(Collections.singletonList(pkg.getId()));
        Map<Long, PackagesGenerationResponse> packagesGenerationResponseMap = packagesGenerationResponses.stream().collect(toMap(PackagesGenerationResponse::getPackId, Function.identity()));

        return getPackageVo(new PackageVo(), pkg, addressSenderMap, addressReceiverMap, idServicesMap, parcels, packagesGenerationResponseMap, new HashMap<>(), new HashMap<>());
    }

    /**
     * @param dateStr
     * @return
     */
    @Override
    public Map getStatistics(String dateStr) {
        Date paramDate = new Date();
        if (!StringUtils.isEmpty(dateStr) && !"null".equals(dateStr) && dateStr.length() == 8) {
            paramDate = DateUtils.dateTime(DateUtils.YYYYMMDD, dateStr);
        }

        Package paramPackage = new Package();
        if (!StringUtils.isEmpty(dateStr) && !"null".equals(dateStr) && dateStr.length() == 6){
            //按照月度统计
            paramPackage.setParamMonth(dateStr);
        }else {
            paramPackage.setCreatedTime(paramDate);
        }
        if (!SecurityUtils.isAdmin(SecurityUtils.getLoginUser().getUserId())) {
            paramPackage.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
        }
//       直接查询物流表
        List<Package> packages = packageMapper.selectPackageList(paramPackage);
        if (CollectionUtils.isEmpty(packages)) {
            return new HashMap();
        }
//        List<PackagesGenerationResponse> packagesGenerationResponses =
//                packagesGenerationResponseMapper.selectPackagesGenerationResponseListByPacIds(packages.stream().map(Package::getId).collect(toList()));
        List<Parcel> parcels = parcelMapper.selectParcelListByPackIdIn(packages.stream().map(Package::getId).collect(toList()));
//        //创建时间
//        Map<String, List<PackagesGenerationResponse>> datePackageList = new HashMap<>();
//        packagesGenerationResponses.stream().forEach(item -> {
//                    String createtimeStr = DateUtils.parseDateToStr(DateUtils.YYYYMMDD, item.getCreatedTime());
//                    if (datePackageList.containsKey(createtimeStr)) {
//                        List<PackagesGenerationResponse> adds = datePackageList.get(createtimeStr);
//                        adds.add(item);
//                    } else {
//                        List<PackagesGenerationResponse> adds = new ArrayList<>();
//                        adds.add(item);
//                        datePackageList.put(createtimeStr, adds);
//                    }
//                }
//        );
//        for (String key : datePackageList.keySet()) {
//            //获取状态数量
//            Map<String, Long> collect = datePackageList.get(key).stream().collect(Collectors.groupingBy(PackagesGenerationResponse::getPkgStatus, Collectors.counting()));
//            //再和日期接在一起
//        }
//        if (CollectionUtils.isEmpty(packagesGenerationResponses)) {
//            return new HashMap();
//        }
//        Map<String, Long> collect = packagesGenerationResponses.stream().collect(Collectors.groupingBy(PackagesGenerationResponse::getPkgStatus, Collectors.counting()));
        if (CollectionUtils.isEmpty(parcels)) {
            return new HashMap();
        }
        Map<String, Long> collect = parcels.stream().collect(Collectors.groupingBy(Parcel::getStatus, Collectors.counting()));

        Map<String, List<String>> returnList = new HashMap<>();
        List<String> xAxisData = new ArrayList<>();
        List<String> seriesData = new ArrayList<>();
        for (String key : collect.keySet()) {
            xAxisData.add(DictUtils.getDictLabel("sys_waybill", key));
            seriesData.add(collect.get(key).toString());
        }
        returnList.put("xAxisData", xAxisData);
        returnList.put("seriesData", seriesData);
        return returnList;
    }

    @Override
    public Map getStatisticsAllUser(String dateStr){
        Date paramDate = new Date();
        if (!StringUtils.isEmpty(dateStr) && !"null".equals(dateStr) && dateStr.length() == 8) {
            paramDate = DateUtils.dateTime(DateUtils.YYYYMMDD, dateStr);
        }

        Package paramPackage = new Package();
        if (!StringUtils.isEmpty(dateStr) && !"null".equals(dateStr) && dateStr.length() == 6){
            //按照月度统计
            paramPackage.setParamMonth(dateStr);
        }else {
            paramPackage.setCreatedTime(paramDate);
        }
        //查看所有用户的
//        paramPackage.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
        List<Package> packages = packageMapper.selectPackageList(paramPackage);
        if (CollectionUtils.isEmpty(packages)) {
            return new HashMap();
        }
        List<Parcel> parcels = parcelMapper.selectParcelListByPackIdIn(packages.stream().map(Package::getId).collect(toList()));
        if (CollectionUtils.isEmpty(parcels)) {
            return new HashMap();
        }
        Map<String, Long> collect = parcels.stream().collect(Collectors.groupingBy(Parcel::getStatus, Collectors.counting()));
        Map<String, Long> userCountMap = parcels.stream().collect(Collectors.groupingBy(Parcel::getCreateUser, Collectors.counting()));
        List<SysUser> allUsers = sysUserMapper.selectUserList(new SysUser());
        Map<String, String> idNameMap = allUsers.stream().collect(toMap(item -> item.getUserId().toString(), SysUser::getName));

        Map<String, List<String>> returnList = new HashMap<>();
        List<String> xAxisData = new ArrayList<>();
        List<String> seriesData = new ArrayList<>();
        for (String key : collect.keySet()) {
            xAxisData.add(DictUtils.getDictLabel("sys_waybill", key));
            seriesData.add(collect.get(key).toString());
        }
        returnList.put("xAxisData", xAxisData);
        returnList.put("seriesData", seriesData);

        List<String> userXAxisData = new ArrayList<>();
        List<String> userSeriesData = new ArrayList<>();
        for (String key : userCountMap.keySet()) {
            userXAxisData.add(idNameMap.get(key));
            userSeriesData.add(collect.get(key).toString());
        }
        returnList.put("userXAxisData", userXAxisData);
        returnList.put("userSeriesData", userSeriesData);

        return returnList;
    }

    @Override
    public Map getStatisticsForParcel(String dateStr) {
        Date paramDate = new Date();
        if (!StringUtils.isEmpty(dateStr) && !"null".equals(dateStr)) {
            paramDate = DateUtils.dateTime(DateUtils.YYYYMMDD, dateStr);
        }

        Package paramPackage = new Package();
        paramPackage.setCreatedTime(paramDate);
        List<Package> packages = packageMapper.selectPackageList(paramPackage);
        if (CollectionUtils.isEmpty(packages)) {
            return new HashMap();
        }
        List<Parcel> parcels =
                parcelMapper.selectParcelListByPackIdIn(packages.stream().map(Package::getId).collect(toList()));
        if (CollectionUtils.isEmpty(parcels)) {
            return new HashMap();
        }
        Map<String, Long> collect = parcels.stream().collect(Collectors.groupingBy(Parcel::getStatus, Collectors.counting()));

        Map<String, List<String>> returnList = new HashMap<>();
        List<String> xAxisData = new ArrayList<>();
        List<String> seriesData = new ArrayList<>();
        for (String key : collect.keySet()) {
            xAxisData.add(key);
            seriesData.add(collect.get(key).toString());
        }
        returnList.put("xAxisData", xAxisData);
        returnList.put("seriesData", seriesData);
        return returnList;
    }

    /**
     * 查询面单列表
     *
     * @param pkg 面单
     * @return 面单
     */
    @Override
    public List<Package> selectPackageList(Package pkg) {
        return packageMapper.selectPackageList(pkg);
    }

    @Override
    public void getResponse(Long pkgId) throws IOException {
//        dpdServicesXMLClient.generateSpedLabels2(245977414, 232962721);
        dpdServicesXMLClient.generateProtocolByPackageId(pkgId);
        System.out.println("test");
    }

    @Value("${frontPath}")
    private String frontPath;

    @Override
    public void getTxtById(HttpServletResponse response, Long id) throws IOException {
        FileUtils.writeBytes(frontPath + "/error/" + id + ".txt", response.getOutputStream());
    }

    @Override
    public void getPDFById(HttpServletResponse response, Long pkgId) throws IOException {
        //查看pdf路径暂未知晓
        PackagesGenerationResponse packagesGenerationResponse = packagesGenerationResponseMapper.selectPackagesGenerationResponseByPackId(pkgId);
        Documents documents = documentsMapper.selectDocumentsByPackageId(packagesGenerationResponse.getPackageId());
        if (ObjectUtils.isEmpty(documents)) {
            //下载PDF并且存储
            DocumentGenerationResponseV1 ret = dpdServicesXMLClient.generateProtocolByPackageId(packagesGenerationResponse.getPackageId());
            Documents documentsInsert = new Documents();
            documentsInsert.setId(sequenceMapper.selectNextvalByName("doc_seq"));
            documentsInsert.setPackageId(packagesGenerationResponse.getPackageId());
            documentsInsert.setFileData(ret.getDocumentData());
            documentsInsert.setDocumentId(ret.getDocumentId());
            documentsInsert.setExtension("PDF");
            documentsInsert.setContentType("application/pdf");
            documentsInsert.setFileName("file");
            documentsInsert.setDisplayName(pkgId.toString() + ".pdf");
            documentsInsert.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
            documentsInsert.setUpdateUser(SecurityUtils.getLoginUser().getUserId().toString());
            documentsMapper.insertDocuments(documentsInsert);
            documents = documentsInsert;
        }
        getFileByDocuments(documents, response);
    }

    @Override
    public void getPDFByBatchId(HttpServletResponse response, Long batchId) throws IOException {
        BatchTaskHistory batchTaskHistory = batchTaskHistoryMapper.selectBatchTaskHistoryById(batchId);
        Documents paramDocuments = new Documents();
        paramDocuments.setSessionId(batchTaskHistory.getSessionId());
        List<Documents> documentsList = documentsMapper.selectDocumentsList(paramDocuments);
        Documents documents;
        if (CollectionUtils.isEmpty(documentsList)) {
            //下载PDF并且存储
            Package param = new Package();
            param.setBatchId(batchId);
            List<Package> packageList = packageMapper.selectPackageList(param);
            AddressReceiver addressReceiver = addressReceiverMapper.selectAddressReceiverById(packageList.get(0).getReceiverId());
            boolean plFlage = "PL".equalsIgnoreCase(addressReceiver.getCountryCode());
            DocumentGenerationResponseV1 ret = dpdServicesXMLClient.generateSpedLabelsBySessionId(batchTaskHistory.getSessionId(), plFlage);
            Documents documentsInsert = new Documents();
            documentsInsert.setId(sequenceMapper.selectNextvalByName("doc_seq"));
            documentsInsert.setSessionId(batchTaskHistory.getSessionId());
            documentsInsert.setFileData(ret.getDocumentData());
            documentsInsert.setDocumentId(ret.getDocumentId());
            documentsInsert.setExtension("PDF");
            documentsInsert.setContentType("application/pdf");
            documentsInsert.setFileName("file");
            documentsInsert.setDisplayName(batchId.toString() + ".pdf");
            documentsInsert.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
            documentsInsert.setUpdateUser(SecurityUtils.getLoginUser().getUserId().toString());
            documentsMapper.insertDocuments(documentsInsert);
            documents = documentsInsert;
        } else {
            documents = documentsList.stream().filter(item -> ObjectUtils.isEmpty(item.getPackageId())).collect(toList()).get(0);
        }
        getFileByDocuments(documents, response);
    }

    @Override
    public long selectPackageVoListTotal(PackageVo packageVo){
        Long hisId = null;
        if (ObjectUtils.isNotEmpty(packageVo.getHisParam())) {
            hisId = Long.valueOf(packageVo.getHisParam().substring(3));
        }
        Package pkg = new Package();
        BeanUtils.copyProperties(packageVo, pkg);
        pkg.setBatchId(hisId);
        if ((!SecurityUtils.isAdmin(SecurityUtils.getLoginUser().getUserId())) && ObjectUtils.isEmpty(hisId)) {
            pkg.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
        }
        if (StringUtils.isNotEmpty(packageVo.getStatus())){
            Date paramDate = new Date();
            if (!StringUtils.isEmpty(packageVo.getDatStr()) && !"null".equals(packageVo.getDatStr()) && packageVo.getDatStr().length() == 8 ) {
                paramDate = DateUtils.dateTime(DateUtils.YYYYMMDD, packageVo.getDatStr());
            }
            if (!StringUtils.isEmpty(packageVo.getDatStr()) && !"null".equals(packageVo.getDatStr()) && packageVo.getDatStr().length() == 6){
                //按照月度统计
                pkg.setParamMonth(packageVo.getDatStr());
            }else {
                pkg.setCreatedTime(paramDate);
            }
        }
        List<Package> packagesAll = packageMapper.selectPackageList(pkg);
        if (CollectionUtils.isEmpty(packagesAll)) {
            return 0;
        }
        List<String> newWaybills = new ArrayList<>();
        //查询转寄
        if (ObjectUtils.isNotEmpty(packageVo.getOriginalId())) {
            RedirectRel redirectRelParam = new RedirectRel();
            redirectRelParam.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
            List<RedirectRel> redirectRelList = redirectRelMapper.selectRedirectRelList(redirectRelParam);
            if (CollectionUtils.isNotEmpty(redirectRelList)) {
                newWaybills = redirectRelList.stream().map(RedirectRel::getNewWaybill).collect(toList());
            }
//            RedirectPackage redirectPackage = new RedirectPackage();
//            redirectPackage.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
//            redirectPackage.setIsDelete(0);
//            List<Long> originalIds = redirectPackageMapper.selectRedirectPackageList(redirectPackage).stream().map(RedirectPackage::getOriginalId).collect(toList());
//            if (CollectionUtils.isEmpty(originalIds)) {
//                packages = packagesAll.stream().filter(item -> originalIds.contains(item.getId())).collect(toList());
//            }
        }
        List<Parcel> parcels;
        List<Parcel> allParcels = parcelMapper.selectParcelListByPackIdIn(packagesAll.stream().map(Package::getId).collect(toList()));
        if (ObjectUtils.isNotEmpty(packageVo.getOriginalId())){
            if (CollectionUtils.isEmpty(newWaybills)){
                return 0;
            }
            List<String> finalNewWaybills = newWaybills;
            parcels = allParcels.stream().filter(item -> finalNewWaybills.contains(item.getWaybill())).collect(toList());
        }else {
            parcels = allParcels;
        }
        List<Package> packages;
        List<Long> pkIds = parcels.stream().map(Parcel::getPackId).collect(toList());
        packages = packagesAll.stream().filter(item -> pkIds.contains(item.getId())).collect(toList());
        if (CollectionUtils.isEmpty(packages)) {
            return 0;
        }
        return packages.size();
    }

    /**
     * 查询面单列表
     *
     * @param packageVo 面单
     * @return 面单
     */
    @Override
    public List<PackageVo> selectPackageVoList(PackageVo packageVo) {
        Long hisId = null;
        Boolean sucFlag = null;
        if (ObjectUtils.isNotEmpty(packageVo.getHisParam())) {
            sucFlag = "000".equals(packageVo.getHisParam().substring(0, 3));
            hisId = Long.valueOf(packageVo.getHisParam().substring(3));
        }
        Package pkg = new Package();
        BeanUtils.copyProperties(packageVo, pkg);
        pkg.setBatchId(hisId);
        if ((!SecurityUtils.isAdmin(SecurityUtils.getLoginUser().getUserId())) && ObjectUtils.isEmpty(hisId)) {
            pkg.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
        }
        if (StringUtils.isNotEmpty(packageVo.getStatus())){
            Date paramDate = new Date();
            if (!StringUtils.isEmpty(packageVo.getDatStr()) && !"null".equals(packageVo.getDatStr()) && packageVo.getDatStr().length() == 8 ) {
                paramDate = DateUtils.dateTime(DateUtils.YYYYMMDD, packageVo.getDatStr());
            }
            if (!StringUtils.isEmpty(packageVo.getDatStr()) && !"null".equals(packageVo.getDatStr()) && packageVo.getDatStr().length() == 6){
                //按照月度统计
                pkg.setParamMonth(packageVo.getDatStr());
            }else {
                pkg.setCreatedTime(paramDate);
            }
        }
        List<Package> packagesAll = packageMapper.selectPackageList(pkg);
        if (CollectionUtils.isEmpty(packagesAll)) {
            return new ArrayList<>();
        }
        List<String> newWaybills = new ArrayList<>();
        Map<Long, List<RedirectRel>> idRedirectRelMap = new HashMap<>();
        //查询转寄
        if (ObjectUtils.isNotEmpty(packageVo.getOriginalId())) {
            RedirectRel redirectRelParam = new RedirectRel();
            if (!SecurityUtils.isAdmin(SecurityUtils.getLoginUser().getUserId())) {
                redirectRelParam.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
            }
            List<RedirectRel> redirectRelList = redirectRelMapper.selectRedirectRelList(redirectRelParam);
            if (CollectionUtils.isNotEmpty(redirectRelList)) {
                newWaybills = redirectRelList.stream().map(RedirectRel::getNewWaybill).collect(toList());
                idRedirectRelMap = redirectRelList.stream().collect(groupingBy(RedirectRel::getNewPackageId));
            }
//            RedirectPackage redirectPackage = new RedirectPackage();
//            redirectPackage.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
//            redirectPackage.setIsDelete(0);
//            List<Long> originalIds = redirectPackageMapper.selectRedirectPackageList(redirectPackage).stream().map(RedirectPackage::getOriginalId).collect(toList());
//            if (CollectionUtils.isEmpty(originalIds)) {
//                packages = packagesAll.stream().filter(item -> originalIds.contains(item.getId())).collect(toList());
//            }
        }
        List<Parcel> parcels;
        List<Parcel> selectParcels = parcelMapper.selectParcelListByPackIdIn(packagesAll.stream().map(Package::getId).collect(toList()));
        List<Parcel> allParcels;
        if (ObjectUtils.isNotEmpty(packageVo.getWaybill())){
            allParcels = selectParcels.stream().filter(item -> item.getWaybill().contains(packageVo.getWaybill())).collect(toList());
        } else {
            allParcels = selectParcels;
        }

        if (ObjectUtils.isNotEmpty(packageVo.getOriginalId())){
            if (CollectionUtils.isEmpty(newWaybills)){
                return new ArrayList<>();
            }
            List<String> finalNewWaybills = newWaybills;
            parcels = allParcels.stream().filter(item -> finalNewWaybills.contains(item.getWaybill())).collect(toList());
        }else {
            parcels = allParcels;
        }
        List<Package> packages;
        List<Long> pkIds = parcels.stream().map(Parcel::getPackId).collect(toList());
        packages = packagesAll.stream().filter(item -> pkIds.contains(item.getId())).collect(toList());
        if (CollectionUtils.isEmpty(packages)) {
            return new ArrayList<>();
        }
        List<AddressSender> addressSenders = addressSenderMapper.selectAddressSenderByIdIn(packages.stream().map(Package::getSenderId).collect(Collectors.toList()));
        Map<Long, AddressSender> addressSenderMap = addressSenders.stream().collect(toMap(AddressSender::getId, Function.identity()));
        List<AddressReceiver> addressReceivers = addressReceiverMapper.selectAddressReceiverByIdIn(packages.stream().map(Package::getReceiverId).collect(Collectors.toList()));
        Map<Long, AddressReceiver> addressReceiverMap = addressReceivers.stream().collect(toMap(AddressReceiver::getId, Function.identity()));
        List<Services> servicesList = servicesMapper.selectServicesListByIdIn(packages.stream().map(Package::getServicesId).collect(Collectors.toList()));
        Map<Long, Services> idServicesMap = servicesList.stream().collect(toMap(Services::getId, Function.identity()));

        List<PackagesGenerationResponse> packagesGenerationResponses = packagesGenerationResponseMapper.selectPackagesGenerationResponseByPackIdIn(packages.stream().map(Package::getId).collect(toList()));
        Map<Long, PackagesGenerationResponse> packagesGenerationResponseMap = packagesGenerationResponses.stream().collect(toMap(PackagesGenerationResponse::getPackId, Function.identity()));

        List<BatchTaskHistory> batchTaskHistories = batchTaskHistoryMapper.selectBatchTaskHistoryByIdIn(packages.stream().map(Package::getBatchId).filter(Objects::nonNull).collect(toList()));
        Map<Long, BatchTaskHistory> batchTaskHistoryMap = null;
        if (CollectionUtils.isNotEmpty(batchTaskHistories)){
            batchTaskHistoryMap = batchTaskHistories.stream().collect(toMap(BatchTaskHistory::getId, Function.identity()));
        }
        Map<Long, BatchTaskHistory> finalBatchTaskHistoryMap = batchTaskHistoryMap;

//        if (CollectionUtils.isNotEmpty(parcels)) {
//            orderListMap = getRedirectRel(parcels.stream().map(Parcel::getReference).collect(Collectors.toList()));
//        }
        Map<Long, List<RedirectRel>> finalOrderListMap = idRedirectRelMap;
        List<PackageVo> resultList = packages.stream().map(item -> this.getPackageVo(packageVo, item, addressSenderMap,
                addressReceiverMap, idServicesMap, parcels, packagesGenerationResponseMap, finalBatchTaskHistoryMap, finalOrderListMap))
                .filter(Objects::nonNull).collect(Collectors.toList());

        if (null == sucFlag) {
            return resultList;
        }

        if (sucFlag) {
            return resultList.stream().filter(item -> "OK".equals(item.getPackagesGenerationResponseStatus())).collect(toList());
        }
        return resultList.stream().filter(item -> !"OK".equals(item.getPackagesGenerationResponseStatus())).collect(toList());
    }

    private PackageVo getPackageVo(PackageVo paramPackageVo,
                                   Package pac,
                                   Map<Long, AddressSender> addressSenderMap,
                                   Map<Long, AddressReceiver> addressReceiverMap,
                                   Map<Long, Services> idServicesMap,
                                   List<Parcel> parcels,
                                   Map<Long, PackagesGenerationResponse> packagesGenerationResponseMap,
                                   Map<Long, BatchTaskHistory> batchTaskHistoryMap,
                                   Map<Long, List<RedirectRel>> orderListMap) {
        if (CollectionUtils.isNotEmpty(paramPackageVo.getIds()) && !paramPackageVo.getIds().contains(pac.getId())){
            return null;
        }
        PackageVo packageVo = new PackageVo();
        BeanUtils.copyProperties(pac, packageVo);

        packageVo.setReceiverAddress(addressReceiverMap.get(pac.getReceiverId()).getAddress());
        packageVo.setReceiverCity(addressReceiverMap.get(pac.getReceiverId()).getCity());
        packageVo.setReceiverCompany(addressReceiverMap.get(pac.getReceiverId()).getCompany());
        packageVo.setReceiverCountryCode(addressReceiverMap.get(pac.getReceiverId()).getCountryCode());
        packageVo.setReceiverEmail(addressReceiverMap.get(pac.getReceiverId()).getEmail());
        packageVo.setReceiverName(addressReceiverMap.get(pac.getReceiverId()).getName());
        packageVo.setReceiverPhone(addressReceiverMap.get(pac.getReceiverId()).getPhone());
        packageVo.setReceiverPostalCode(addressReceiverMap.get(pac.getReceiverId()).getPostalCode());
        packageVo.setPln(addressReceiverMap.get(pac.getReceiverId()).getPln());

        packageVo.setShowSenderName(addressSenderMap.get(pac.getSenderId()).getName());

        if (idServicesMap.containsKey(pac.getServicesId())){
            packageVo.setPln(new BigDecimal(idServicesMap.get(pac.getServicesId()).getCodAmount()));
        }

        List<Parcel> parcelList = parcels.stream().filter(item -> item.getPackId().equals(pac.getId())).collect(toList());
        if (CollectionUtils.isNotEmpty(parcelList)) {
            Parcel parcel = parcelList.get(0);
            if (StringUtils.isNotEmpty(paramPackageVo.getStatus()) && !StringUtils.equals(parcel.getStatus(), paramPackageVo.getStatus())) {
                return null;
            }
            BeanUtils.copyProperties(parcel, packageVo, "id", "createdTime", "updatedTime", "createUser", "updateUser");
        } else {
            return null;
        }
        PackagesGenerationResponse packagesGenerationResponse = packagesGenerationResponseMap.get(pac.getId());
        if (ObjectUtils.isNotEmpty(packagesGenerationResponse)) {
            BeanUtils.copyProperties(packagesGenerationResponse, packageVo, "id", "createdTime", "updatedTime", "createUser", "updateUser", "status", "packId");
            packageVo.setPackagesGenerationResponseStatus(packagesGenerationResponse.getStatus());
        }

        if (ObjectUtils.isNotEmpty(batchTaskHistoryMap) && batchTaskHistoryMap.containsKey(pac.getBatchId())) {
            BatchTaskHistory batchTaskHistory = batchTaskHistoryMap.get(pac.getBatchId());
            if (ObjectUtils.isNotEmpty(batchTaskHistory)) {
                packageVo.setSource(batchTaskHistory.getType());
            }
        } else {
            packageVo.setSource("新增");
        }

        if (null != orderListMap &&  orderListMap.containsKey(pac.getId())){
            List<RedirectRel> redirectRelList = orderListMap.get(pac.getId());
            List<String> backOrders = redirectRelList.stream().map(RedirectRel::getBackOrder).distinct().collect(toList());
            packageVo.setBackOrder(String.join("&", backOrders));
            packageVo.setNewOrder(packageVo.getReference());
            packageVo.setNewWaybill(packageVo.getWaybill());
            packageVo.setOldWaybill(redirectRelList.get(0).getOldWaybill());
        }

        return packageVo;
    }

    /**
     * 更细下载数量
     *
     * @param ids
     * @return
     */
    @Override
    public void updateDownloadNum(List<Long> ids) {
        packageMapper.updateDownloadNum(ids);
    }

    /**
     * 新增面单
     *
     * @param pkg 面单
     * @return 结果
     */
    @Override
    public int insertPackage(Package pkg) {
        return packageMapper.insertPackage(pkg);
    }

    /**
     * 新增面单
     *
     * @param pkg 面单
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertPackage(PackageVo pkg) throws Exception {
        Package pac = new Package();
        BeanUtils.copyProperties(pkg, pac);
        AddressReceiver addressReceiver = getReceiver(pkg, sequenceMapper.selectNextvalByName("receiver_seq"));
        AddressSender addressSender = getSender();
        Services services = getServices(pkg, sequenceMapper.selectNextvalByName("services_seq"));

        pac.setReceiverId(addressReceiver.getId());
        pac.setSenderId(addressSender.getId());
        pac.setServicesId(services.getId());
        pac.setId(sequenceMapper.selectNextvalByName("package_seq"));
        pac.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
        pac.setUpdateUser(SecurityUtils.getLoginUser().getUserId().toString());

        if (ObjectUtils.isNotEmpty(pkg.getOriginalId())) {
            RedirectPackage redirectPackage = new RedirectPackage();
            redirectPackage.setId(pac.getId());
            redirectPackage.setOriginalId(pkg.getOriginalId());
            redirectPackage.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
            redirectPackage.setUpdateUser(SecurityUtils.getLoginUser().getUserId().toString());
            redirectPackageMapper.insertRedirectPackage(redirectPackage);
        }
        Parcel parcel = new Parcel();
        BeanUtils.copyProperties(pkg, parcel);
        parcel.setPackId(pac.getId());
        parcel.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
        parcel.setUpdateUser(SecurityUtils.getLoginUser().getUserId().toString());

        List<Parcel> parcels = new ArrayList<>();
        parcels.add(parcel);

        pac.setReceiver(addressReceiver);
        pac.setSender(addressSender);
        pac.setService(services);
        pac.setParcels(parcels);
        List<Package> packages = new ArrayList<>();
        packages.add(pac);

        List<PackagesGenerationResponse> returnResponses = dpdServicesXMLClient.generatePackagesNumberByBusiness(packages);

        addressReceiverMapper.insertAddressReceiverWithId(addressReceiver);
        servicesMapper.insertServicesWithId(services);
        parcelMapper.insertParcel(parcel);
        packageMapper.insertPackageWithId(pac);
        packagesGenerationResponseMapper.batchInsert(returnResponses);
        return 1;
    }

    /**
     * 下载excel
     *
     * @param response
     * @param id
     * @throws Exception
     */
    @Override
    public void writeFile(HttpServletResponse response, Long id) throws Exception {
        BatchTaskHistory batchTaskHistory = batchTaskHistoryMapper.selectBatchTaskHistoryById(id);
        batchTaskHistory.setDownloadNum(batchTaskHistory.getDownloadNum()+1);
        batchTaskHistoryMapper.updateBatchTaskHistory(batchTaskHistory);
        Documents document = documentsMapper.selectDocumentsById(Long.valueOf(batchTaskHistory.getExcelUrl()));
        getFileByDocuments(document, response);
    }

    private void getFileByDocuments(Documents document, HttpServletResponse response) throws IOException {
        InputStream fis = null;
        OutputStream toClient = null;
        try {
            byte[] documentByte = document.getFileData();
            fis = new ByteArrayInputStream(documentByte);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);

            response.setContentType(document.getContentType());
            response.setCharacterEncoding("utf-8");
            toClient = new BufferedOutputStream(response.getOutputStream());
            toClient.write(buffer);
            toClient.flush();
        } finally {
            IOUtils.closeQuietly(fis);
            IOUtils.closeQuietly(toClient);
        }
    }

    public Documents getDocuments(MultipartFile file) throws IOException {
        Documents documents = new Documents();
        documents.setId(sequenceMapper.selectNextvalByName("doc_seq"));
        documents.setFileData(file.getBytes());
        documents.setFileName(file.getName());
        documents.setContentType(file.getContentType());
        documents.setFileSize(file.getSize());
        documents.setDisplayName(file.getOriginalFilename());
        documents.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
        documents.setUpdateUser(SecurityUtils.getLoginUser().getUserId().toString());
        documentsMapper.insertDocuments(documents);
        return documents;
    }

    private boolean checkForRe(List<PackageVo> packageVos) {
        List<String> oldWaybills = packageVos.stream().map(PackageVo::getOldWaybill).filter(Objects::nonNull).distinct().collect(toList());
        if (CollectionUtils.isEmpty(oldWaybills) || CollectionUtils.isEmpty(packageVos) || oldWaybills.size() != packageVos.size()) {
            return false;
        }
        List<Parcel> parcels = parcelMapper.selectParcelListByWaybillIn(oldWaybills);
        return CollectionUtils.isNotEmpty(parcels) && parcels.size() == oldWaybills.size();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String importPackage(MultipartFile file, List<PackageVo> packageVos) throws Exception {
        String fileName = file.getOriginalFilename();
        List<String> listFile = Arrays.asList(fileName.split(" "));
        if (CollectionUtils.isEmpty(listFile) || listFile.size()<6 || !(
                "resend".equalsIgnoreCase(listFile.get(1)) || "local".equalsIgnoreCase(listFile.get(1)) || "original".equalsIgnoreCase(listFile.get(1))) ){
            return "文件命名错误";
        }
        Documents documents = getDocuments(file);
        BatchTaskHistory batchTaskHistory = new BatchTaskHistory();
        batchTaskHistory.setType("面单导入");
        batchTaskHistory.setStatus("上传成功");
        batchTaskHistory.setExcelUrl(documents.getId().toString());
        batchTaskHistory.setFileName(documents.getDisplayName());
        batchTaskHistory.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
        batchTaskHistory.setUpdateUser(SecurityUtils.getLoginUser().getUserId().toString());
        batchTaskHistory.setId(sequenceMapper.selectNextvalByName("bat_task_seq"));

        //国家邮编校验
//        List<String> checkCountryAndZip = checkCountryAndZip(packageVos);
//        if (org.springframework.util.CollectionUtils.isEmpty(checkCountryAndZip)
//                || checkCountryAndZip.contains("NONEXISTING_POSTAL_CODE")
//                || checkCountryAndZip.contains("NONEXISTING_COUNTRY_CODE")
//                || checkCountryAndZip.contains("WRONG_POSTAL_PATTERN")) {
////            if (org.springframework.util.CollectionUtils.isEmpty(checkCountryAndZip)){
//                throw new Exception("邮编或国家代码验证失败");
////            }
////            StringBuilder sb = new StringBuilder();
////            for (String key : checkCountryAndZip.keySet()) {
////                sb.append("code: ").append(key)
////                        .append(", 邮编或国家代码验证失败, 失败信息为: ").append(checkCountryAndZip.get(key)).append("\n");
////            }
////            throw new Exception(sb.toString());
//        }

        Boolean checkWeight = checkWeight(packageVos);
        if (checkWeight){
            throw new Exception("重量不得大于10");
        }

        Set<String> oldWaybills = packageVos.stream().map(PackageVo::getOldWaybill).filter(Objects::nonNull).collect(Collectors.toSet());
        boolean reflag =  oldWaybills.isEmpty();

        if(!reflag){
            batchTaskHistory.setType("转寄面单导入");
//            boolean checkForReFlag = checkForRe(packageVos);
//            if (!checkForReFlag){
//                batchTaskHistory.setStatus("上传失败");
//                batchTaskHistoryMapper.insertBatchTaskHistoryWithId(batchTaskHistory);
//                throw new Exception("原面单物流单号不能为空，或者原面单物流单号不正确");
//            }
        }

        /**
         * 一系列处理
         */
        //生成id 并且更新
        Map<String, Sequence> nameMap = getSeqMap(packageVos.size());
        AddressSender addressSender = getSender();
        List<Package> packages = new ArrayList<>();
        List<RedirectPackage> redirectPackages = new ArrayList<>();
        List<ImportLogicContent> importLogicContents = new ArrayList<>();
        Map<Long, RedirectRel> idRedirectRelMap = new HashMap<>();
        for (PackageVo packageVo : packageVos) {
            AddressReceiver addressReceiver = getReceiver(packageVo, getId(nameMap, "receiver_seq"));
            SysUser sysUser = sysUserMapper.selectUserById(SecurityUtils.getLoginUser().getUserId());
            if (addressReceiver.getCountryCode().equalsIgnoreCase("PL")) {
                if (!(sysUser.getCountry().equalsIgnoreCase("CN") || sysUser.getCountry().equalsIgnoreCase("PL"))) {
                    return "导入失败，收件人国家与当前登陆用户所在国家不一致";
                }
            } else {
                if (!(sysUser.getCountry().equalsIgnoreCase(addressReceiver.getCountryCode()))) {
                    return "导入失败，收件人国家与当前登陆用户所在国家不一致";
                }
            }

            Services services = getServices(packageVo, getId(nameMap, "services_seq"));

            Package pac = new Package();
            BeanUtils.copyProperties(packageVo, pac);
            pac.setReceiverId(addressReceiver.getId());
            pac.setSenderId(addressSender.getId());
            pac.setServicesId(services.getId());
            pac.setBatchId(batchTaskHistory.getId());
            pac.setId(getId(nameMap, "package_seq"));
            pac.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
            pac.setUpdateUser(SecurityUtils.getLoginUser().getUserId().toString());
            Parcel parcel = new Parcel();
            BeanUtils.copyProperties(packageVo, parcel);
            parcel.setPackId(pac.getId());
            parcel.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
            parcel.setUpdateUser(SecurityUtils.getLoginUser().getUserId().toString());

            pac.setService(services);
            pac.setReceiver(addressReceiver);
            List<Parcel> parcels1 = new ArrayList<>();
            parcels1.add(parcel);
            pac.setParcels(parcels1);
            pac.setSender(addressSender);

            if(!reflag){
                RedirectPackage redirectPackage = new RedirectPackage();
                redirectPackage.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
                redirectPackage.setUpdateUser(SecurityUtils.getLoginUser().getUserId().toString());
//                redirectPackage.setOriginalId(packageVo.getOriginalId());
                redirectPackage.setId(pac.getId());
                redirectPackages.add(redirectPackage);

                RedirectRel redirectRel = new RedirectRel();
                BeanUtils.copyProperties(packageVo, redirectRel);
                redirectRel.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
                redirectRel.setUpdateUser(SecurityUtils.getLoginUser().getUserId().toString());
                redirectRel.setNewPackageId(pac.getId());
                redirectRel.setCountryCode(addressReceiver.getCountryCode());
                idRedirectRelMap.put(pac.getId(), redirectRel);
            }

            List<String> list = Arrays.asList(documents.getDisplayName().split(" "));
            //导入信息新增
            ImportLogicContent importLogicContent = new ImportLogicContent();
//            BeanUtils.copyProperties(packageVo, importLogicContent, "id");
            importLogicContent.setBatchId(batchTaskHistory.getId());
            importLogicContent.setDocumentFileId(documents.getId());
            importLogicContent.setPackId(pac.getId());
            importLogicContent.setRecipientName(addressReceiver.getName());
            importLogicContent.setRecipientEmail(addressReceiver.getEmail());
            importLogicContent.setRecipientPhone(addressReceiver.getPhone());
            importLogicContent.setValuePlnCod(packageVo.getPln().toString());
            importLogicContent.setWeightKg(packageVo.getWeight().toString());
//            importLogicContent.setClient(addressSender.getName());
//            importLogicContent.setCountry(addressSender.getCountryCode());
            importLogicContent.setClient(list.get(2));
            String type =  getType(list.get(1));
            importLogicContent.setCountry(type.equals("本地")?list.get(4):list.get(3));
            importLogicContent.setImportType(type);
            String box = type.equals("本地")?list.get(6):list.get(5);
            importLogicContent.setNeedBox(box.contains("box") ? "Y" : "N");
            importLogicContent.setCreateBy(SecurityUtils.getLoginUser().getUserId().toString());
            importLogicContent.setUpdateBy(SecurityUtils.getLoginUser().getUserId().toString());
            importLogicContent.setStatus(SysWaybill.WJH.getCode());
            importLogicContent.setLoginid(SecurityUtils.getLoginUser().getUsername());
            importLogicContent.setOrderNumber(packageVo.getReference());
            importLogicContent.setDescription(packageVo.getCustomerData1());
            importLogicContent.setCreateDate(DateUtils.getDate2());
            pac.setImportLogicContent(importLogicContent);
            packages.add(pac);

        }
        try {
            List<PackagesGenerationResponse> returnResponses = dpdServicesXMLClient.generatePackagesNumberByBusiness(packages);

            List<AddressReceiver> addressReceivers = new ArrayList<>();
            List<Services> servicesList = new ArrayList<>();
            List<Parcel> parcels = new ArrayList<>();
            List<RedirectRel> redirectRels = new ArrayList<>();
            for (Package pac : packages) {
                addressReceivers.add(pac.getReceiver());
                servicesList.add(pac.getService());
                parcels.addAll(pac.getParcels());
                importLogicContents.add(pac.getImportLogicContent());
                if(!reflag && idRedirectRelMap.containsKey(pac.getId())){
                    RedirectRel redirectRel = idRedirectRelMap.get(pac.getId());
                    redirectRel.setNewWaybill(pac.getImportLogicContent().getNewWaybill());
                    redirectRels.add(redirectRel);
                }
            }
            batchTaskHistory.setSessionId(returnResponses.get(0).getSessionId());
            batchTaskHistory.setSuccessNum((int) returnResponses.stream().filter(item -> "OK".equals(item.getPkgStatus())).count());
            batchTaskHistory.setFailNum((int) returnResponses.stream().filter(item -> !"OK".equals(item.getPkgStatus())).count());

            packageMapper.batchInsert(packages);
            addressReceiverMapper.batchInsert(addressReceivers);
            servicesMapper.batchInsert(servicesList);
            parcelMapper.batchInsert(parcels);
            packagesGenerationResponseMapper.batchInsert(returnResponses);
            importLogicContentMapper.batchInsert(importLogicContents);
            if(!reflag){
                dealForRedirect(new ArrayList<String>(oldWaybills));
                redirectPackageMapper.batchInsert(redirectPackages);
                redirectRelMapper.batchInsert(redirectRels);
            }
            StringBuilder returnStrBuf = new StringBuilder();
            returnStrBuf.append("面单导入成功，成功")
                    .append(batchTaskHistory.getSuccessNum()).append("条，失败")
                    .append(batchTaskHistory.getFailNum()).append("条。\n");
            boolean errFlag = false;
            Map<String, String> fileNameMap = getFileNameMap("PL");
            List<String> errorMsgList = new ArrayList<>();
            for (int i = 0; i < returnResponses.size(); i++) {
                //错误信息处理
                if (!"OK".equals(returnResponses.get(i).getPkgStatus())){
                    errFlag = true;
                    StringBuilder errorStrBuf = new StringBuilder();
                    errorStrBuf.append("第").append(i+1).append("条失败，失败原因：");
                    List<ValidationInfoPGRV2> validationInfoPGRV2s = returnResponses.get(i).getValidationInfoPGRV2List();
                    for (int validNum = 0; validNum < validationInfoPGRV2s.size(); validNum++) {
                        ValidationInfoPGRV2 valid = validationInfoPGRV2s.get(validNum);
                        if (StringUtils.isNotEmpty(valid.getInfo())){
                            errorStrBuf.append(validNum+1);
                            if (StringUtils.isNotEmpty(valid.getFieldNames()) && fileNameMap.containsKey(valid.getFieldNames())){
                                errorStrBuf.append("字段：").append(fileNameMap.get(valid.getFieldNames())).append("。");
                            }
                            if (!errorStrBuf.toString().contains(valid.getInfo())){
                                errorStrBuf.append("原因：").append(valid.getInfo());
                            }
                        }
                    }
                    errorMsgList.add(errorStrBuf.toString());
                }
            }
            if (errFlag){
//                returnStrBuf.append(errorStrBuf);
//                下载文件前端也需要处理
                returnStrBuf.append("失败原因请去批量任务历史页面查看：").append(batchTaskHistory.getId());
                FileUtils.createTxtFile(errorMsgList, frontPath + "/error/", batchTaskHistory.getId().toString());
            }
            return returnStrBuf.toString();
        }catch (Exception e){
            batchTaskHistory.setStatus("上传失败");
            e.printStackTrace();
            throw new Exception("上传失败");
        }finally {
            batchTaskHistoryMapper.insertBatchTaskHistoryWithId(batchTaskHistory);
        }
    }

    @Override
//    @Transactional(rollbackFor = Exception.class)
    public String importPackageForNoGen(MultipartFile file, List<PackageVo> packageVosOri) throws Exception {
        if (CollectionUtils.isEmpty(packageVosOri)){
            return "导入数据不能为空";
        }
        String fileName = file.getOriginalFilename();
        List<String> list = Arrays.asList(fileName.split(" "));
        if (CollectionUtils.isEmpty(list) || list.size()<6 || !(
                "resend".equalsIgnoreCase(list.get(1)) || "local".equalsIgnoreCase(list.get(1)) || "original".equalsIgnoreCase(list.get(1))) ){
            return "文件命名错误";
        }

        Documents documents = getDocuments(file);
        BatchTaskHistory batchTaskHistory = new BatchTaskHistory();
        batchTaskHistory.setType("物流信息导入");
        batchTaskHistory.setStatus("上传成功");
        batchTaskHistory.setExcelUrl(documents.getId().toString());
        batchTaskHistory.setFileName(documents.getDisplayName());
        batchTaskHistory.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
        batchTaskHistory.setUpdateUser(SecurityUtils.getLoginUser().getUserId().toString());
        batchTaskHistory.setId(sequenceMapper.selectNextvalByName("bat_task_seq"));

        List<String> wldhs = packageVosOri.stream().map(PackageVo::getNewWaybill).collect(toList());

        List<ImportLogicContent> czImportLogicContents = importLogicContentMapper.selectImportLogicContentByNewWaybillIn(wldhs);

        List<PackageVo> packageVos;
        if (CollectionUtils.isEmpty(czImportLogicContents)){
            packageVos = packageVosOri;
        }else {
            List<String> czwldhs = czImportLogicContents.stream().map(ImportLogicContent::getNewWaybill).collect(toList());
            packageVos = packageVosOri.stream().filter(item -> !czwldhs.contains(item.getNewWaybill())).collect(toList());
        }

        if (CollectionUtils.isEmpty(packageVos)){
            return "导入成功";
        }
        /**
         * 一系列处理
         */
        //生成id 并且更新
        Map<String, Sequence> nameMap = getSeqMap(packageVos.size());
        AddressSender addressSender = getSender();
        List<Package> packages = new ArrayList<>();
        List<ImportLogicContent> importLogicContents = new ArrayList<>();
        for (PackageVo packageVo : packageVos) {
            AddressReceiver addressReceiver = getReceiver(packageVo, getId(nameMap, "receiver_seq"));
            Services services = getServices(packageVo, getId(nameMap, "services_seq"));

            Package pac = new Package();
            BeanUtils.copyProperties(packageVo, pac);
            pac.setReceiverId(addressReceiver.getId());
            pac.setSenderId(addressSender.getId());
            pac.setServicesId(services.getId());
            pac.setBatchId(batchTaskHistory.getId());
            pac.setId(getId(nameMap, "package_seq"));
            pac.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
            pac.setUpdateUser(SecurityUtils.getLoginUser().getUserId().toString());
            Parcel parcel = new Parcel();
            BeanUtils.copyProperties(packageVo, parcel);
            parcel.setPackId(pac.getId());
            parcel.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
            parcel.setUpdateUser(SecurityUtils.getLoginUser().getUserId().toString());
            parcel.setStatus(SysWaybill.WJH.getCode());
            if (ObjectUtils.isEmpty(packageVo.getWaybill())) {
                parcel.setWaybill(packageVo.getNewWaybill());
            }

            pac.setService(services);
            pac.setReceiver(addressReceiver);
            List<Parcel> parcels1 = new ArrayList<>();
            parcels1.add(parcel);
            pac.setParcels(parcels1);
            pac.setSender(addressSender);
            packages.add(pac);

            //            DPD Resend Wolin pol 20221101 23box exoort xls
//            DPD Resend Wolin pol 20221101 23 exoort xls
            //导入信息新增
            ImportLogicContent importLogicContent = new ImportLogicContent();
            BeanUtils.copyProperties(packageVo, importLogicContent, "id");
            importLogicContent.setBatchId(batchTaskHistory.getId());
            importLogicContent.setDocumentFileId(documents.getId());
            importLogicContent.setPackId(pac.getId());
            importLogicContent.setClient(list.get(2));
            String type =  getType(list.get(1));
            importLogicContent.setCountry(type.equals("本地")?list.get(4):list.get(3));
            importLogicContent.setImportType(type);
            String box = type.equals("本地")?list.get(6):list.get(5);
            importLogicContent.setNeedBox(box.contains("box") ? "Y" : "N");
            importLogicContent.setCreateBy(SecurityUtils.getLoginUser().getUserId().toString());
            importLogicContent.setUpdateBy(SecurityUtils.getLoginUser().getUserId().toString());
            importLogicContent.setStatus(SysWaybill.WJH.getCode());
            importLogicContents.add(importLogicContent);

            batchTaskHistory.setType(StringUtils.isEmpty(importLogicContent.getImportType()) ? batchTaskHistory.getType() : importLogicContent.getImportType());
        }
        try {

            List<AddressReceiver> addressReceivers = new ArrayList<>();
            List<Services> servicesList = new ArrayList<>();
            List<Parcel> parcels = new ArrayList<>();
            for (Package pac : packages) {
                addressReceivers.add(pac.getReceiver());
                servicesList.add(pac.getService());
                parcels.addAll(pac.getParcels());
            }

//            packageMapper.batchInsert(packages);
//            addressReceiverMapper.batchInsert(addressReceivers);
//            servicesMapper.batchInsert(servicesList);
            parcelMapper.batchInsert(parcels);
            importLogicContentMapper.batchInsert(importLogicContents);
            batchTaskHistory.setSuccessNum(importLogicContents.size());
            batchTaskHistory.setFailNum(0);
            // 异步查询一下物流信息
            dpdInfoXMLClient.getLogic(parcels);
            return "物流信息导入成功，请稍后查询最新物流信息";
//                    +
//                    batchTaskHistory.getSuccessNum() + "条，失败" +
//                    batchTaskHistory.getFailNum() + "条。\n";
        }catch (Exception e){
            batchTaskHistory.setStatus("上传失败");
            e.printStackTrace();
            throw new Exception("上传失败");
        }finally {
            batchTaskHistoryMapper.insertBatchTaskHistoryWithId(batchTaskHistory);
        }
    }

    private String getType(String engType){
//        业务会有resend local original三种
        if ("local".equalsIgnoreCase(engType)){
            return "本地";
        }

        if ("resend".equalsIgnoreCase(engType)){
            return "转寄";
        }

        return "直发";
    }

//    public void getLogic(List<Parcel> parcels){
//        parcels.parallelStream().forEach(item -> dpdInfoXMLClient.getEventsForOneWaybill(item));
//    }

    private Map<String, String> getFileNameMap(String countryCode) {
        PackageDpdMapping packageDpdMapping = new PackageDpdMapping();
        packageDpdMapping.setCountryCode(countryCode);
        List<PackageDpdMapping> packageDpdMappings = packageDpdMappingMapper.selectPackageDpdMappingList(packageDpdMapping);
        if (CollectionUtils.isEmpty(packageDpdMappings)) {
            return new HashMap<>();
        }
        return packageDpdMappings.stream().collect(toMap(PackageDpdMapping::getDpdField, PackageDpdMapping::getImportName, (o1, o2) -> o1));
    }

    private List<String> checkCountryAndZip(List<PackageVo> packageVos){
        return packageVos.parallelStream()
                .map(item -> dpdServicesXMLClient.findPostalCode(item.getReceiverCountryCode(), item.getReceiverPostalCode()))
                .collect(toList());
    }

    private Boolean checkWeight(List<PackageVo> packageVos){
        for (PackageVo packageVo : packageVos) {
            if (packageVo.getWeight().compareTo(BigDecimal.TEN) == 1){
                return true;
            }
        }
        return false;
    }

    private void dealForRedirect(List<String> oldWaybills){
        List<LogisticsInfo> logisticsInfos = logisticsInfoMapper.selectLogisticsInfoListByWaybillIn(oldWaybills);
        if (CollectionUtils.isNotEmpty(logisticsInfos)){
            logisticsInfoMapper.updateRedirectNumByIds(logisticsInfos.stream().map(LogisticsInfo::getId).collect(toList()));
        }
    }

    private Long getId(Map<String, Sequence> nameMap, String seqName) {
        Sequence sequence = nameMap.get(seqName);
        Long id = sequence.getCurrentVal() + sequence.getIncrementVal();
        sequence.setCurrentVal(id);
        return id;
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


    private AddressSender getSender() {
        AddressSender senderParam = new AddressSender();
        senderParam.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
        return addressSenderMapper.selectAddressSenderList(senderParam).get(0);
//        AddressSender addressSender = new AddressSender();
//        addressSender.setAddress(pkg.getSenderAddress());
//        addressSender.setCity(pkg.getSenderCity());
//        addressSender.setCompany(pkg.getSenderCompany());
//        addressSender.setCountryCode(pkg.getSenderCountryCode());
//        addressSender.setEmail(pkg.getSenderEmail());
//        addressSender.setFid(pkg.getSenderFid());
//        addressSender.setName(pkg.getSenderName());
//        addressSender.setPhone(pkg.getSenderPhone());
//        addressSender.setPostalCode(pkg.getSenderPostalCode());
//        if (ObjectUtils.isNotEmpty(id)) {
//            addressSender.setId(id);
//            addressSender.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
//        }
//        addressSender.setUpdateUser(SecurityUtils.getLoginUser().getUserId().toString());
//        return addressSender;
    }

    private AddressReceiver getReceiver(PackageVo pkg, Long id) {
        AddressReceiver addressReceiver = new AddressReceiver();
        addressReceiver.setAddress(pkg.getReceiverAddress());
        addressReceiver.setCity(pkg.getReceiverCity());
        addressReceiver.setCompany(pkg.getReceiverName());
        addressReceiver.setCountryCode(pkg.getReceiverCountryCode());
        addressReceiver.setEmail(pkg.getReceiverEmail());
        addressReceiver.setName(pkg.getReceiverName());
        addressReceiver.setPhone(pkg.getReceiverPhone());
        String postalCode = StringUtils.isEmpty(pkg.getReceiverPostalCode()) ? "":
                pkg.getReceiverPostalCode().replace("-", "").replace("_", "").replace(" ", "");
        addressReceiver.setPostalCode(postalCode);
        addressReceiver.setPln(pkg.getPln());
        if (ObjectUtils.isNotEmpty(id)) {
            addressReceiver.setId(id);
            addressReceiver.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
        }
        addressReceiver.setUpdateUser(SecurityUtils.getLoginUser().getUserId().toString());
        return addressReceiver;
    }

    private Services getServices(PackageVo pkg, Long id) {
        Services services = new Services();
        services.setCodAmount(ObjectUtils.isEmpty(pkg.getPln()) ? "0" : pkg.getPln().toString());
        if (ObjectUtils.isEmpty(pkg.getCodCurrency())){
            services.setCodCurrency("PLN");
        } else {
            services.setCodCurrency(pkg.getCodCurrency());
        }

        if (ObjectUtils.isNotEmpty(id)) {
            services.setId(id);
            services.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
        }
        services.setUpdateUser(SecurityUtils.getLoginUser().getUserId().toString());
        return services;
    }

    /**
     * 修改面单
     *
     * @param pkg 面单
     * @return 结果
     */
    @Override
    public int updatePackage(PackageVo pkg) {
        pkg.setUpdateUser(SecurityUtils.getLoginUser().getUserId().toString());
        Package pac = packageMapper.selectPackageById(pkg.getId());
        BeanUtils.copyProperties(pkg, pac, "id", "createdTime");

        AddressReceiver addressReceiver = getReceiver(pkg, null);
        addressReceiver.setId(pac.getReceiverId());
        AddressSender addressSender = getSender();
        addressSender.setId(pac.getSenderId());
//        addressSenderMapper.updateAddressSender(addressSender);
        addressReceiverMapper.updateAddressReceiver(addressReceiver);
//        pac.setServicesId(1L);
        Parcel parcel = new Parcel();
        parcel.setPackId(pac.getId());
        List<Parcel> parcels = parcelMapper.selectParcelList(parcel);
        BeanUtils.copyProperties(pkg, parcels.get(0), "id", "createdTime");
        parcelMapper.updateParcel(parcels.get(0));
        if (ObjectUtils.isNotEmpty(pkg.getOriginalId())) {
            RedirectPackage redirectPackage = new RedirectPackage();
            redirectPackage.setId(pac.getId());
            redirectPackage.setOriginalId(pkg.getOriginalId());
            redirectPackage.setUpdateUser(SecurityUtils.getLoginUser().getUserId().toString());
            redirectPackageMapper.updateRedirectPackage(redirectPackage);
        }
        return packageMapper.updatePackage(pac);
    }

    /**
     * 批量删除面单
     *
     * @param ids 需要删除的面单主键
     * @return 结果
     */
    @Override
    public int deletePackageByIds(Long[] ids) {
        packageMapper.deletePackageByIds(ids);
        importLogicContentMapper.deleteImportLogicContentByPackIds(ids);
        parcelMapper.deleteParcelByPackIds(ids);
        return 1;
    }

    /**
     * 删除面单信息
     *
     * @param id 面单主键
     * @return 结果
     */
    @Override
    public int deletePackageById(Long id) {
        return packageMapper.deletePackageById(id);
    }

    private Map<String, List<RedirectRel>> getRedirectRel(List<String> orders){
        List<RedirectRel> redirectRelList = redirectRelMapper.selectByNewOrderIn(orders);
        if (CollectionUtils.isNotEmpty(redirectRelList)){
            return redirectRelList.stream().collect(groupingBy(RedirectRel::getNewOrder));
        }
        return new HashMap<>();
    }

    @Autowired
    private EmailUtil emailUtil;

    @Value("#{'${dpd.email.to}'.split(',')}")
    private List<String> to;

    @Value("#{'${dpd.email.cc}'.split(',')}")
    private List<String> cc;

    @Override
    public void testSendEmailFile(){
        // 测试文本邮件发送（无附件）
//        String to = "1097700731@qq.com"; // 这是个假邮箱，写成你自己的邮箱地址就可以
        String title = "文本邮件发送测试";
        String content = "文本邮件发送测试";
        PackageVo packageVo = new PackageVo();
        packageVo.setNewWaybill("aaaaa");
        File file = sendEmail("test.xlsx", Arrays.asList(packageVo));
        emailUtil.sendMessageCarryFile(to.toArray(new String[0]), cc.toArray(new String[0]), title, content, file);
    }

    private File getPdfForEmail(BatchTaskHistory batchTaskHistory) throws IOException {
        Documents documentsExcel = documentsMapper.selectDocumentsById(Long.valueOf(batchTaskHistory.getExcelUrl()));
        StringBuilder pdfNameSb = new StringBuilder();
        pdfNameSb.append(documentsExcel.getDisplayName().replace(".xlsx","")
                .replace(".xls", ""));
//        Resenging request: 20220815  Wolin to CZech 12 parcels【need box】;
        boolean boxFlag = pdfNameSb.toString().contains("box");
        StringBuilder titleSb = new StringBuilder();
        titleSb.append("Resenging request:").append(
                pdfNameSb.toString().replace("export", "parcels")
                        .replace("box", ""));
        if (boxFlag){
            titleSb.append("【need box】");
        }
        Documents documents = documentsMapper.selectDocumentsBySessionId(batchTaskHistory.getSessionId());

        return getFileByDocuments(pdfNameSb.toString() + " labels.pdf", documents);
    }

    private File getFileByDocuments(String fileUrl, Documents document) throws IOException {
        String filePath = frontPath + "/" + fileUrl;
        File file = new File(filePath);
        InputStream fis = null;
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            byte[] documentByte = document.getFileData();
            fis = new ByteArrayInputStream(documentByte);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);

            fileOutputStream.write(buffer);
            fileOutputStream.flush();
            return file;
        } finally {
            IOUtils.closeQuietly(fis);
            fileOutputStream.close();
        }
    }

    private File sendEmail(String fileUrl, List<PackageVo> packageVos){
        String filePath = frontPath + "/" + fileUrl;
        File file = new File(filePath);
        List<ExportTwoPackageVo> exportPackageVos = null;
        if (CollectionUtils.isNotEmpty(packageVos)){
            exportPackageVos = packageVos.stream().map(item ->
                    {
                        ExportTwoPackageVo packageVo = new ExportTwoPackageVo();
                        BeanUtils.copyProperties(item, packageVo);
                        return packageVo;
                    }
            ).collect(toList());
        }
        ExcelUtil<ExportTwoPackageVo> util = new ExcelUtil<ExportTwoPackageVo>(ExportTwoPackageVo.class);
        util.exportExcel(file, exportPackageVos, "面单数据");
        return file;
    }

}
