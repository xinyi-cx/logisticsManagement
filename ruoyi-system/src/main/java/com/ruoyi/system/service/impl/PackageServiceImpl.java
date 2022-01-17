package com.ruoyi.system.service.impl;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.ruoyi.common.utils.MultpartFileToByte;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.domain.Package;
import com.ruoyi.system.domain.vo.PackageVo;
import com.ruoyi.system.mapper.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.service.IPackageService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

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
    private BatchTaskHistoryMapper batchTaskHistoryMapper;

    @Autowired
    private SequenceMapper sequenceMapper;

    @Autowired
    private ParcelMapper parcelMapper;

    @Autowired
    private DocumentsMapper documentsMapper;

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

        return getPackageVo(pkg, addressSenderMap, addressReceiverMap, parcels);
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

    /**
     * 查询面单列表
     *
     * @param packageVo 面单
     * @return 面单
     */
    @Override
    public List<PackageVo> selectPackageVoList(PackageVo packageVo) {
        Package pkg = new Package();
        pkg.setCreateUser(SecurityUtils.getLoginUser().getUsername());
        BeanUtils.copyProperties(packageVo, pkg);
        List<Package> packagesAll = packageMapper.selectPackageList(pkg);
        List<Package> packages = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(packageVo.getOriginalId())){
            RedirectPackage redirectPackage = new RedirectPackage();
            redirectPackage.setCreateUser(SecurityUtils.getLoginUser().getUsername());
            redirectPackage.setIsDelete(0);
            List<Long> originalIds = redirectPackageMapper.selectRedirectPackageList(redirectPackage).stream().map(RedirectPackage::getOriginalId).collect(toList());
            if (CollectionUtils.isEmpty(originalIds)) {
                packages = packagesAll.stream().filter(item -> originalIds.contains(item.getId())).collect(toList());
            }
        }else {
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

        return packages.stream().map(item -> this.getPackageVo(item, addressSenderMap, addressReceiverMap, parcels)).collect(Collectors.toList());
    }

    private PackageVo getPackageVo(Package pac,
                                   Map<Long, AddressSender> addressSenderMap,
                                   Map<Long, AddressReceiver> addressReceiverMap,
                                   List<Parcel> parcels) {
        PackageVo packageVo = new PackageVo();
        BeanUtils.copyProperties(pac, packageVo);
        packageVo.setSenderAddress(addressSenderMap.get(pac.getSenderId()).getAddress());
        packageVo.setSenderCity(addressSenderMap.get(pac.getSenderId()).getCity());
        packageVo.setSenderCompany(addressSenderMap.get(pac.getSenderId()).getCompany());
        packageVo.setSenderCountryCode(addressSenderMap.get(pac.getSenderId()).getCountryCode());
        packageVo.setSenderEmail(addressSenderMap.get(pac.getSenderId()).getEmail());
        packageVo.setSenderFid(addressSenderMap.get(pac.getSenderId()).getFid());
        packageVo.setSenderName(addressSenderMap.get(pac.getSenderId()).getName());
        packageVo.setSenderPhone(addressSenderMap.get(pac.getSenderId()).getPhone());
        packageVo.setSenderPostalCode(addressSenderMap.get(pac.getSenderId()).getPostalCode());

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
        return packageVo;
    }

    /**
     * 更细下载数量
     * @param ids
     * @return
     */
    @Override
    public void updateDownloadNum(List<Long> ids){
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
        //生成id
        AddressReceiver addressReceiver = getReceiver(pkg, sequenceMapper.selectNextvalByName("receiver_seq"));
        AddressSender addressSender = getSender(pkg, sequenceMapper.selectNextvalByName("send_seq"));
        addressSenderMapper.insertAddressSenderWithId(addressSender);
        addressReceiverMapper.insertAddressReceiverWithId(addressReceiver);
        pac.setReceiverId(addressReceiver.getId());
        pac.setSenderId(addressSender.getId());
        pac.setServicesId(1L);
        pac.setId(sequenceMapper.selectNextvalByName("package_seq"));
        pac.setCreateUser(SecurityUtils.getLoginUser().getUsername());
        pac.setUpdateUser(SecurityUtils.getLoginUser().getUsername());
        packageMapper.insertPackageWithId(pac);
        if (ObjectUtils.isNotEmpty(pkg.getOriginalId())){
            RedirectPackage redirectPackage = new RedirectPackage();
            redirectPackage.setId(pac.getId());
            redirectPackage.setOriginalId(pkg.getOriginalId());
            redirectPackage.setCreateUser(SecurityUtils.getLoginUser().getUsername());
            redirectPackage.setUpdateUser(SecurityUtils.getLoginUser().getUsername());
            redirectPackageMapper.insertRedirectPackage(redirectPackage);
        }
        //一对多暂时还未确定
        Parcel parcel = new Parcel();
        BeanUtils.copyProperties(pkg, parcel);
        parcel.setPackId(pac.getId());
        parcel.setCreateUser(SecurityUtils.getLoginUser().getUsername());
        parcel.setUpdateUser(SecurityUtils.getLoginUser().getUsername());
//        接口返回
//        parcel.setPackageId();
        return parcelMapper.insertParcel(parcel);
    }

    @Override
    public void writeFile(HttpServletResponse response, Long id) throws Exception {
        InputStream fis = null;
        OutputStream toClient = null;
        try {
            BatchTaskHistory batchTaskHistory = batchTaskHistoryMapper.selectBatchTaskHistoryById(id);
            Documents document = documentsMapper.selectDocumentsById(Long.valueOf(batchTaskHistory.getExcelUrl()));
            byte[] documentByte = document.getFileData();
            fis = new ByteArrayInputStream(documentByte);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);

            response.setContentType(document.getContentType());
            response.setCharacterEncoding("utf-8");
            toClient = new BufferedOutputStream(response.getOutputStream());
            toClient.write(buffer);
            toClient.flush();
            batchTaskHistory.setDownloadNum(batchTaskHistory.getDownloadNum()+1);
            batchTaskHistoryMapper.updateBatchTaskHistory(batchTaskHistory);
        } finally {
            IOUtils.closeQuietly(fis);
            IOUtils.closeQuietly(toClient);
        }
    }

    public Documents getDocuments(MultipartFile file) throws IOException {
        Documents documents = new Documents();
        documents.setFileData(file.getBytes());
        documents.setFileName(file.getName());
        documents.setContentType(file.getContentType());
        documents.setFileSize(file.getSize());
        documents.setDisplayName(file.getOriginalFilename());
        documents.setCreateUser(SecurityUtils.getLoginUser().getUsername());
        documents.setUpdateUser(SecurityUtils.getLoginUser().getUsername());
        documentsMapper.insertDocuments(documents);
        return documents;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int importPackage(MultipartFile file, List<PackageVo> packageVos) throws IOException {
        Documents documents = getDocuments(file);
        BatchTaskHistory batchTaskHistory = new BatchTaskHistory();
        batchTaskHistory.setStatus("上传成功");
        batchTaskHistory.setExcelUrl(documents.getId().toString());
        batchTaskHistory.setCreateUser(SecurityUtils.getLoginUser().getUsername());
        batchTaskHistory.setUpdateUser(SecurityUtils.getLoginUser().getUsername());
        /**
         * 一系列处理
         */
        batchTaskHistoryMapper.insertBatchTaskHistory(batchTaskHistory);
        List<AddressSender> addressSenders = new ArrayList<>();
        List<AddressReceiver> addressReceivers = new ArrayList<>();
        List<Package> packages = new ArrayList<>();
        List<Parcel> parcels = new ArrayList<>();
        //生成id 并且更新
        Map<String, Sequence> nameMap = getSeqMap(packages.size());
        for (PackageVo packageVo : packageVos) {
            AddressReceiver addressReceiver = getReceiver(packageVo, getId(nameMap, "receiver_seq"));
            AddressSender addressSender = getSender(packageVo, getId(nameMap, "send_seq"));

            Package pac = new Package();
            BeanUtils.copyProperties(packageVo, pac);
            pac.setReceiverId(addressReceiver.getId());
            pac.setSenderId(addressSender.getId());
            pac.setServicesId(1L);
            pac.setBatchId(batchTaskHistory.getId());
            pac.setId(getId(nameMap, "package_seq"));
            pac.setCreateUser(SecurityUtils.getLoginUser().getUsername());
            pac.setUpdateUser(SecurityUtils.getLoginUser().getUsername());
            Parcel parcel = new Parcel();
            BeanUtils.copyProperties(packageVo, parcel);
            parcel.setPackId(pac.getId());
            parcel.setCreateUser(SecurityUtils.getLoginUser().getUsername());
            parcel.setUpdateUser(SecurityUtils.getLoginUser().getUsername());
//            parcel.setPackId("");

            addressSenders.add(addressSender);
            addressReceivers.add(addressReceiver);
            packages.add(pac);
            parcels.add(parcel);
        }

        addressSenderMapper.batchInsert(addressSenders);
        addressReceiverMapper.batchInsert(addressReceivers);
        packageMapper.batchInsert(packages);
        return parcelMapper.batchInsert(parcels);
    }

    private Long getId(Map<String, Sequence> nameMap, String seqName) {
        Sequence sequence = nameMap.get(seqName);
        Long id = sequence.getCurrentVal() + sequence.getIncrementVal();
        sequence.setCurrentVal(id);
        return id;
    }

    private static String[] SEQ_NAMES = {"send_seq", "receiver_seq", "package_seq"};

    private Map<String, Sequence> getSeqMap(int addNum) {
        List<Sequence> sequences = sequenceMapper.selectSequenceList(new Sequence());
        Map<String, Sequence> nameMap = sequences.stream().collect(toMap(Sequence::getSeqName, Function.identity()));
        for (String seqName : SEQ_NAMES) {
            if (nameMap.containsKey(seqName)) {
                Sequence sequence = nameMap.get(seqName);
                sequence.setCurrentVal(sequence.getCurrentVal() + addNum * sequence.getIncrementVal());
                sequenceMapper.updateSequence(sequence);
            }
        }
        return nameMap;
    }


    private AddressSender getSender(PackageVo pkg, Long id) {
        AddressSender addressSender = new AddressSender();
        addressSender.setAddress(pkg.getSenderAddress());
        addressSender.setCity(pkg.getSenderCity());
        addressSender.setCompany(pkg.getSenderCompany());
        addressSender.setCountryCode(pkg.getSenderCountryCode());
        addressSender.setEmail(pkg.getSenderEmail());
        addressSender.setFid(pkg.getSenderFid());
        addressSender.setName(pkg.getSenderName());
        addressSender.setPhone(pkg.getSenderPhone());
        addressSender.setPostalCode(pkg.getSenderPostalCode());
        if (ObjectUtils.isNotEmpty(id)) {
            addressSender.setId(id);
            addressSender.setCreateUser(SecurityUtils.getLoginUser().getUsername());
        }
        addressSender.setUpdateUser(SecurityUtils.getLoginUser().getUsername());
        return addressSender;
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
            addressReceiver.setCreateUser(SecurityUtils.getLoginUser().getUsername());
        }
        addressReceiver.setUpdateUser(SecurityUtils.getLoginUser().getUsername());
        return addressReceiver;
    }

    /**
     * 修改面单
     *
     * @param pkg 面单
     * @return 结果
     */
    @Override
    public int updatePackage(PackageVo pkg) {
        pkg.setUpdateUser(SecurityUtils.getLoginUser().getUsername());
        Package pac = packageMapper.selectPackageById(pkg.getId());
        BeanUtils.copyProperties(pkg, pac, "id", "createdTime");

        AddressReceiver addressReceiver = getReceiver(pkg, null);
        addressReceiver.setId(pac.getReceiverId());
        AddressSender addressSender = getSender(pkg, null);
        addressSender.setId(pac.getSenderId());
        addressSenderMapper.updateAddressSender(addressSender);
        addressReceiverMapper.updateAddressReceiver(addressReceiver);
//        pac.setServicesId(1L);
        Parcel parcel = new Parcel();
        parcel.setPackId(pac.getId());
        List<Parcel> parcels = parcelMapper.selectParcelList(parcel);
        BeanUtils.copyProperties(pkg, parcels.get(0), "id", "createdTime");
        parcelMapper.updateParcel(parcels.get(0));
        if (ObjectUtils.isNotEmpty(pkg.getOriginalId())){
            RedirectPackage redirectPackage = new RedirectPackage();
            redirectPackage.setId(pac.getId());
            redirectPackage.setOriginalId(pkg.getOriginalId());
            redirectPackage.setUpdateUser(SecurityUtils.getLoginUser().getUsername());
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
