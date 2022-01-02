package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AddressReceiverMapper;
import com.ruoyi.system.domain.AddressReceiver;
import com.ruoyi.system.service.IAddressReceiverService;

/**
 * 收货人信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-01-02
 */
@Service
public class AddressReceiverServiceImpl implements IAddressReceiverService 
{
    @Autowired
    private AddressReceiverMapper addressReceiverMapper;

    /**
     * 查询收货人信息
     * 
     * @param id 收货人信息主键
     * @return 收货人信息
     */
    @Override
    public AddressReceiver selectAddressReceiverById(Long id)
    {
        return addressReceiverMapper.selectAddressReceiverById(id);
    }

    /**
     * 查询收货人信息列表
     * 
     * @param addressReceiver 收货人信息
     * @return 收货人信息
     */
    @Override
    public List<AddressReceiver> selectAddressReceiverList(AddressReceiver addressReceiver)
    {
        return addressReceiverMapper.selectAddressReceiverList(addressReceiver);
    }

    /**
     * 新增收货人信息
     * 
     * @param addressReceiver 收货人信息
     * @return 结果
     */
    @Override
    public int insertAddressReceiver(AddressReceiver addressReceiver)
    {
        return addressReceiverMapper.insertAddressReceiver(addressReceiver);
    }

    /**
     * 修改收货人信息
     * 
     * @param addressReceiver 收货人信息
     * @return 结果
     */
    @Override
    public int updateAddressReceiver(AddressReceiver addressReceiver)
    {
        return addressReceiverMapper.updateAddressReceiver(addressReceiver);
    }

    /**
     * 批量删除收货人信息
     * 
     * @param ids 需要删除的收货人信息主键
     * @return 结果
     */
    @Override
    public int deleteAddressReceiverByIds(Long[] ids)
    {
        return addressReceiverMapper.deleteAddressReceiverByIds(ids);
    }

    /**
     * 删除收货人信息信息
     * 
     * @param id 收货人信息主键
     * @return 结果
     */
    @Override
    public int deleteAddressReceiverById(Long id)
    {
        return addressReceiverMapper.deleteAddressReceiverById(id);
    }
}
