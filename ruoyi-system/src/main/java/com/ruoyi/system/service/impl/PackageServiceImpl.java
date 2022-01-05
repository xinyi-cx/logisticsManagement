package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.system.domain.*;
import com.ruoyi.system.domain.Package;
import com.ruoyi.system.domain.vo.PackageVo;
import com.ruoyi.system.mapper.AddressReceiverMapper;
import com.ruoyi.system.mapper.AddressSenderMapper;
import com.ruoyi.system.mapper.BatchTaskHistoryMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.PackageMapper;
import com.ruoyi.system.service.IPackageService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 面单Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-01-02
 */
@Service
public class PackageServiceImpl implements IPackageService 
{
    @Autowired
    private PackageMapper packageMapper;

    @Autowired
    private AddressSenderMapper addressSenderMapper;

    @Autowired
    private AddressReceiverMapper addressReceiverMapper;

    @Autowired
    private BatchTaskHistoryMapper batchTaskHistoryMapper;

    /**
     * 查询面单
     * 
     * @param id 面单主键
     * @return 面单
     */
    @Override
    public Package selectPackageById(Long id)
    {
        return packageMapper.selectPackageById(id);
    }

    /**
     * 查询面单列表
     * 
     * @param pkg 面单
     * @return 面单
     */
    @Override
    public List<Package> selectPackageList(Package pkg)
    {
        return packageMapper.selectPackageList(pkg);
    }

    /**
     * 新增面单
     * 
     * @param pkg 面单
     * @return 结果
     */
    @Override
    public int insertPackage(Package pkg)
    {
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
    public int insertPackage(PackageVo pkg)
    {
        Package pac = new Package();
        BeanUtils.copyProperties(pkg,pac);
        //生成id
        AddressReceiver addressReceiver = getReceiver(pkg, null);
        AddressSender addressSender = getSender(pkg, null);
        addressSenderMapper.insertAddressSenderWithId(addressSender);
        addressReceiverMapper.insertAddressReceiverWithId(addressReceiver);
        pac.setReceiverId(addressReceiver.getId());
        pac.setSenderId(addressSender.getId());
        pac.setServicesId(1L);
        packageMapper.insertPackage(pac);
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int importPackage(List<PackageVo> packageVos){
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
        for (PackageVo packageVo : packageVos) {
            AddressReceiver addressReceiver = getReceiver(packageVo, null);
            AddressSender addressSender = getSender(packageVo, null);

            Package pac = new Package();
            BeanUtils.copyProperties(packageVo,pac);
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

    private AddressSender getSender(PackageVo pkg, Long id){
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
        return addressSender;
    }

    private AddressReceiver getReceiver(PackageVo pkg, Long id){
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
        return addressReceiver;
    }

    /**
     * 修改面单
     * 
     * @param pkg 面单
     * @return 结果
     */
    @Override
    public int updatePackage(Package pkg)
    {
        return packageMapper.updatePackage(pkg);
    }

    /**
     * 批量删除面单
     * 
     * @param ids 需要删除的面单主键
     * @return 结果
     */
    @Override
    public int deletePackageByIds(Long[] ids)
    {
        return packageMapper.deletePackageByIds(ids);
    }

    /**
     * 删除面单信息
     * 
     * @param id 面单主键
     * @return 结果
     */
    @Override
    public int deletePackageById(Long id)
    {
        return packageMapper.deletePackageById(id);
    }
}
