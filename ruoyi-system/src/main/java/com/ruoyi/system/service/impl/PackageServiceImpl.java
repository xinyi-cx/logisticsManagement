package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.ruoyi.system.domain.*;
import com.ruoyi.system.domain.Package;
import com.ruoyi.system.domain.vo.PackageVo;
import com.ruoyi.system.mapper.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.service.IPackageService;
import org.springframework.transaction.annotation.Transactional;

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
    private AddressSenderMapper addressSenderMapper;

    @Autowired
    private AddressReceiverMapper addressReceiverMapper;

    @Autowired
    private BatchTaskHistoryMapper batchTaskHistoryMapper;

    @Autowired
    private SequenceMapper sequenceMapper;

    /**
     * 查询面单
     *
     * @param id 面单主键
     * @return 面单
     */
    @Override
    public Package selectPackageById(Long id) {
        return packageMapper.selectPackageById(id);
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
        BeanUtils.copyProperties(packageVo, pkg);
        List<Package> packages = packageMapper.selectPackageList(pkg);
        if (CollectionUtils.isEmpty(packages)){
            return new ArrayList<>();
        }
        List<AddressSender> addressSenders = addressSenderMapper.selectAddressSenderByIdIn(packages.stream().map(Package::getSenderId).collect(Collectors.toList()));
        Map<Long, AddressSender> addressSenderMap = addressSenders.stream().collect(toMap(AddressSender::getId, Function.identity()));
        List<AddressReceiver> addressReceivers = addressReceiverMapper.selectAddressReceiverByIdIn(packages.stream().map(Package::getReceiverId).collect(Collectors.toList()));
        Map<Long, AddressReceiver> addressReceiverMap = addressReceivers.stream().collect(toMap(AddressReceiver::getId, Function.identity()));

        return packages.stream().map(item -> this.getPackageVo(item, addressSenderMap, addressReceiverMap)).collect(Collectors.toList());
    }

    private PackageVo getPackageVo(Package pac, Map<Long, AddressSender> addressSenderMap, Map<Long, AddressReceiver> addressReceiverMap) {
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

        return packageVo;
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
        pkg.setCreatedTime(new Date());
        pkg.setUpdatedTime(new Date());
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
        return packageMapper.insertPackage(pac);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int importPackage(List<PackageVo> packageVos) {
        BatchTaskHistory batchTaskHistory = new BatchTaskHistory();
        batchTaskHistory.setStatus("上传成功");
        /**
         * 一系列处理
         */
        batchTaskHistoryMapper.insertBatchTaskHistory(batchTaskHistory);
        List<AddressSender> addressSenders = new ArrayList<>();
        List<AddressReceiver> addressReceivers = new ArrayList<>();
        List<Package> packages = new ArrayList<>();
        //生成id 并且更新
        Map<String, Sequence> nameMap = getSeqMap(packages.size());
        for (PackageVo packageVo : packageVos) {
            packageVo.setCreatedTime(new Date());
            packageVo.setUpdatedTime(new Date());
            AddressReceiver addressReceiver = getReceiver(packageVo, getId(nameMap, "receiver_seq"));
            AddressSender addressSender = getSender(packageVo, getId(nameMap, "send_seq"));

            Package pac = new Package();
            BeanUtils.copyProperties(packageVo, pac);
            pac.setReceiverId(addressReceiver.getId());
            pac.setSenderId(addressSender.getId());
            pac.setServicesId(1L);
            pac.setBatchId(batchTaskHistory.getId());

            addressSenders.add(addressSender);
            addressReceivers.add(addressReceiver);
            packages.add(pac);
        }

        addressSenderMapper.batchInsert(addressSenders);
        addressReceiverMapper.batchInsert(addressReceivers);
        return packageMapper.batchInsert(packages);
    }

    private Long getId(Map<String, Sequence> nameMap, String seqName) {
        Sequence sequence = nameMap.get(seqName);
        Long id = sequence.getCurrentVal() + sequence.getIncrementVal();
        sequence.setCurrentVal(id);
        return id;
    }

    private static String[] SEQ_NAMES = {"send_seq", "receiver_seq"};

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
        addressSender.setId(id);
        addressSender.setCreatedTime(new Date());
        addressSender.setUpdatedTime(new Date());
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
        addressReceiver.setId(id);
        addressReceiver.setPln(pkg.getPln());
        addressReceiver.setCreatedTime(new Date());
        addressReceiver.setUpdatedTime(new Date());
        return addressReceiver;
    }

    /**
     * 修改面单
     *
     * @param pkg 面单
     * @return 结果
     */
    @Override
    public int updatePackage(Package pkg) {
        return packageMapper.updatePackage(pkg);
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
