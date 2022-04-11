package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.DPDServicesExample.client.DPDServicesXMLClient;
import com.ruoyi.system.domain.Package;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.domain.vo.PackageVo;
import com.ruoyi.system.dpdservices.DocumentGenerationResponseV1;
import com.ruoyi.system.mapper.*;
import com.ruoyi.system.service.IPackageService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

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
        List<Parcel> parcels = parcelMapper.selectParcelListByPackIdIn(Collections.singletonList(pkg.getId()));

        List<PackagesGenerationResponse> packagesGenerationResponses = packagesGenerationResponseMapper.selectPackagesGenerationResponseByPackIdIn(Collections.singletonList(pkg.getId()));
        Map<Long, PackagesGenerationResponse> packagesGenerationResponseMap = packagesGenerationResponses.stream().collect(toMap(PackagesGenerationResponse::getPackId, Function.identity()));

        return getPackageVo(pkg, addressSenderMap, addressReceiverMap, parcels, packagesGenerationResponseMap);
    }

    /**
     * @param dateStr
     * @return
     */
    @Override
    public Map getStatistics(String dateStr) {
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
        List<PackagesGenerationResponse> packagesGenerationResponses =
                packagesGenerationResponseMapper.selectPackagesGenerationResponseListByPacIds(packages.stream().map(Package::getId).collect(toList()));
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
        if (CollectionUtils.isEmpty(packagesGenerationResponses)) {
            return new HashMap();
        }
        Map<String, Long> collect = packagesGenerationResponses.stream().collect(Collectors.groupingBy(PackagesGenerationResponse::getPkgStatus, Collectors.counting()));

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
            DocumentGenerationResponseV1 ret = dpdServicesXMLClient.generateSpedLabelsBySessionId(batchTaskHistory.getSessionId());
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
            documents = documentsList.stream().filter(item -> ObjectUtils.isEmpty(item.getSessionId())).collect(toList()).get(0);
        }
        getFileByDocuments(documents, response);
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
        pkg.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
        List<Package> packagesAll = packageMapper.selectPackageList(pkg);
        List<Package> packages = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(packageVo.getOriginalId())) {
            RedirectPackage redirectPackage = new RedirectPackage();
            redirectPackage.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
            redirectPackage.setIsDelete(0);
            List<Long> originalIds = redirectPackageMapper.selectRedirectPackageList(redirectPackage).stream().map(RedirectPackage::getOriginalId).collect(toList());
            if (CollectionUtils.isEmpty(originalIds)) {
                packages = packagesAll.stream().filter(item -> originalIds.contains(item.getId())).collect(toList());
            }
        } else {
            packages = packagesAll;
        }
        if (CollectionUtils.isEmpty(packages)) {
            return new ArrayList<>();
        }
        List<AddressSender> addressSenders = addressSenderMapper.selectAddressSenderByIdIn(packages.stream().map(Package::getSenderId).collect(Collectors.toList()));
        Map<Long, AddressSender> addressSenderMap = addressSenders.stream().collect(toMap(AddressSender::getId, Function.identity()));
        List<AddressReceiver> addressReceivers = addressReceiverMapper.selectAddressReceiverByIdIn(packages.stream().map(Package::getReceiverId).collect(Collectors.toList()));
        Map<Long, AddressReceiver> addressReceiverMap = addressReceivers.stream().collect(toMap(AddressReceiver::getId, Function.identity()));
        List<Parcel> parcels = parcelMapper.selectParcelListByPackIdIn(packages.stream().map(Package::getId).collect(toList()));

        List<PackagesGenerationResponse> packagesGenerationResponses = packagesGenerationResponseMapper.selectPackagesGenerationResponseByPackIdIn(packages.stream().map(Package::getId).collect(toList()));
        Map<Long, PackagesGenerationResponse> packagesGenerationResponseMap = packagesGenerationResponses.stream().collect(toMap(PackagesGenerationResponse::getPackId, Function.identity()));
        List<PackageVo> resultList = packages.stream().map(item -> this.getPackageVo(item, addressSenderMap, addressReceiverMap, parcels, packagesGenerationResponseMap)).collect(Collectors.toList());
        if (null == sucFlag) {
            return resultList;
        }

        if (sucFlag) {
            return resultList.stream().filter(item -> "OK".equals(item.getStatus())).collect(toList());
        }
        return resultList.stream().filter(item -> "FAIL".equals(item.getStatus())).collect(toList());
    }

    private PackageVo getPackageVo(Package pac,
                                   Map<Long, AddressSender> addressSenderMap,
                                   Map<Long, AddressReceiver> addressReceiverMap,
                                   List<Parcel> parcels,
                                   Map<Long, PackagesGenerationResponse> packagesGenerationResponseMap) {
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

        List<Parcel> parcelList = parcels.stream().filter(item -> item.getPackId().equals(pac.getId())).collect(toList());
        if (CollectionUtils.isNotEmpty(parcelList)) {
            Parcel parcel = parcelList.get(0);
            BeanUtils.copyProperties(parcel, packageVo, "id", "createdTime", "updatedTime", "createUser", "updateUser");
        }
        PackagesGenerationResponse packagesGenerationResponse = packagesGenerationResponseMap.get(pac.getId());
        if (ObjectUtils.isNotEmpty(packagesGenerationResponse)) {
            BeanUtils.copyProperties(packagesGenerationResponse, packageVo, "id", "createdTime", "updatedTime", "createUser", "updateUser", "status", "packId");
            packageVo.setPackagesGenerationResponseStatus(packagesGenerationResponse.getStatus());
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
    public int insertPackage(PackageVo pkg) {
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
        //一对多暂时还未确定 一对一 不存在一对多
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

    private boolean checkForRe(List<PackageVo> packageVos){
        List<Long> originalIds = packageVos.stream().map(PackageVo::getOriginalId).filter(Objects::nonNull).distinct().collect(toList());
        if(CollectionUtils.isEmpty(originalIds) || CollectionUtils.isEmpty(packageVos) || originalIds.size()!=packageVos.size()){
            return false;
        }
        List<Package> packages = packageMapper.selectPackageByIdIn(originalIds);
        return CollectionUtils.isNotEmpty(packages) && packages.size()==originalIds.size();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importPackage(MultipartFile file, List<PackageVo> packageVos) throws Exception {
        Documents documents = getDocuments(file);
        BatchTaskHistory batchTaskHistory = new BatchTaskHistory();
        batchTaskHistory.setType("面单导入");
        batchTaskHistory.setStatus("上传成功");
        batchTaskHistory.setExcelUrl(documents.getId().toString());
        batchTaskHistory.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
        batchTaskHistory.setUpdateUser(SecurityUtils.getLoginUser().getUserId().toString());
        batchTaskHistory.setId(sequenceMapper.selectNextvalByName("bat_task_seq"));

        Set<Long> originalIds = packageVos.stream().map(PackageVo::getOriginalId).filter(Objects::nonNull).collect(Collectors.toSet());
        boolean reflag =  originalIds.isEmpty();

        if(!reflag){
            batchTaskHistory.setType("转寄面单导入");
            boolean checkForReFlag = checkForRe(packageVos);
            if (!checkForReFlag){
                batchTaskHistory.setStatus("上传失败");
                batchTaskHistoryMapper.insertBatchTaskHistoryWithId(batchTaskHistory);
                throw new Exception("原面单号不能为空，或者原面单号不正确");
            }
        }

        /**
         * 一系列处理
         */
        //生成id 并且更新
        Map<String, Sequence> nameMap = getSeqMap(packageVos.size());
        AddressSender addressSender = getSender();
        List<Package> packages = new ArrayList<>();
        List<RedirectPackage> redirectPackages = new ArrayList<>();
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

            pac.setService(services);
            pac.setReceiver(addressReceiver);
            List<Parcel> parcels1 = new ArrayList<>();
            parcels1.add(parcel);
            pac.setParcels(parcels1);
            pac.setSender(addressSender);
            packages.add(pac);
            if(!reflag){
                RedirectPackage redirectPackage = new RedirectPackage();
                redirectPackage.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
                redirectPackage.setUpdateUser(SecurityUtils.getLoginUser().getUserId().toString());
                redirectPackage.setOriginalId(packageVo.getOriginalId());
                redirectPackage.setId(pac.getId());
                redirectPackages.add(redirectPackage);
            }
        }
        try {
            List<PackagesGenerationResponse> returnResponses = dpdServicesXMLClient.generatePackagesNumberByBusiness(packages);

            List<AddressReceiver> addressReceivers = new ArrayList<>();
            List<Services> servicesList = new ArrayList<>();
            List<Parcel> parcels = new ArrayList<>();
            for (Package pac : packages) {
                addressReceivers.add(pac.getReceiver());
                servicesList.add(pac.getService());
                parcels.addAll(pac.getParcels());
            }
            batchTaskHistory.setSessionId(returnResponses.get(0).getSessionId());
            batchTaskHistoryMapper.insertBatchTaskHistoryWithId(batchTaskHistory);
            packageMapper.batchInsert(packages);
            addressReceiverMapper.batchInsert(addressReceivers);
            servicesMapper.batchInsert(servicesList);
            parcelMapper.batchInsert(parcels);
            packagesGenerationResponseMapper.batchInsert(returnResponses);
            if(!reflag){
                redirectPackageMapper.batchInsert(redirectPackages);
            }
        }catch (Exception e){
            batchTaskHistory.setStatus("上传失败");
            batchTaskHistoryMapper.insertBatchTaskHistoryWithId(batchTaskHistory);
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
        addressReceiver.setCompany(pkg.getReceiverCompany());
        addressReceiver.setCountryCode(pkg.getReceiverCountryCode());
        addressReceiver.setEmail(pkg.getReceiverEmail());
        addressReceiver.setName(pkg.getReceiverName());
        addressReceiver.setPhone(pkg.getReceiverPhone());
        addressReceiver.setPostalCode(pkg.getReceiverPostalCode());
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
        services.setCodAmount(pkg.getPln().toString());
        services.setCodCurrency("PLN");
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
        return packageMapper.deletePackageByIds(ids);
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
}
