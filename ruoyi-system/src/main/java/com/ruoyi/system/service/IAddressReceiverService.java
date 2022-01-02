package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.AddressReceiver;

/**
 * 收货人信息Service接口
 * 
 * @author ruoyi
 * @date 2022-01-02
 */
public interface IAddressReceiverService 
{
    /**
     * 查询收货人信息
     * 
     * @param id 收货人信息主键
     * @return 收货人信息
     */
    public AddressReceiver selectAddressReceiverById(Long id);

    /**
     * 查询收货人信息列表
     * 
     * @param addressReceiver 收货人信息
     * @return 收货人信息集合
     */
    public List<AddressReceiver> selectAddressReceiverList(AddressReceiver addressReceiver);

    /**
     * 新增收货人信息
     * 
     * @param addressReceiver 收货人信息
     * @return 结果
     */
    public int insertAddressReceiver(AddressReceiver addressReceiver);

    /**
     * 修改收货人信息
     * 
     * @param addressReceiver 收货人信息
     * @return 结果
     */
    public int updateAddressReceiver(AddressReceiver addressReceiver);

    /**
     * 批量删除收货人信息
     * 
     * @param ids 需要删除的收货人信息主键集合
     * @return 结果
     */
    public int deleteAddressReceiverByIds(Long[] ids);

    /**
     * 删除收货人信息信息
     * 
     * @param id 收货人信息主键
     * @return 结果
     */
    public int deleteAddressReceiverById(Long id);
}
