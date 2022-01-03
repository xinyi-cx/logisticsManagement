package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.AddressReceiver;
import com.ruoyi.system.domain.AddressSender;
import com.ruoyi.system.domain.Parcel;
import com.ruoyi.system.domain.vo.PackageVo;
import com.ruoyi.system.mapper.AddressReceiverMapper;
import com.ruoyi.system.mapper.AddressSenderMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.PackageMapper;
import com.ruoyi.system.domain.Package;
import com.ruoyi.system.service.IPackageService;

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
    public int insertPackage(PackageVo pkg)
    {
        Package pac = new Package();
        BeanUtils.copyProperties(pkg,pac);
        pac.setReceiverId(getReceiver(pkg).getId());
        pac.setSenderId(getSender(pkg).getId());
        pac.setServicesId(1L);
        packageMapper.insertPackage(pac);
        return 0;
    }

    private AddressSender getSender(PackageVo pkg){
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

        addressSender.setId((long) addressSenderMapper.insertAddressSender(addressSender));
        return addressSender;
    }

    private AddressReceiver getReceiver(PackageVo pkg){
        AddressReceiver addressReceiver = new AddressReceiver();
        addressReceiver.setAddress(pkg.getReceiverAddress());
        addressReceiver.setCity(pkg.getReceiverCity());
        addressReceiver.setCompany(pkg.getReceiverCompany());
        addressReceiver.setCountryCode(pkg.getReceiverCountryCode());
        addressReceiver.setEmail(pkg.getReceiverEmail());
        addressReceiver.setName(pkg.getReceiverName());
        addressReceiver.setPhone(pkg.getReceiverPhone());
        addressReceiver.setPostalCode(pkg.getReceiverPostalCode());

        addressReceiver.setId((long) addressReceiverMapper.insertAddressReceiver(addressReceiver));
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
