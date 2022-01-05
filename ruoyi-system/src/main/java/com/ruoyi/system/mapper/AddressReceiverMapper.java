package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.AddressReceiver;

/**
 * 收货人信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-01-02
 */
public interface AddressReceiverMapper 
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
     * 查询收货人信息列表
     *
     * @param
     * @return 收货人信息集合
     */
    public List<AddressReceiver> selectAddressReceiverByIdIn(List<Long> ids);

    /**
     * 新增收货人信息
     * 
     * @param addressReceiver 收货人信息
     * @return 结果
     */
    public int insertAddressReceiver(AddressReceiver addressReceiver);

    /**
     * 新增收货人信息
     *
     * @param addressReceiver 收货人信息
     * @return 结果
     */
    public int insertAddressReceiverWithId(AddressReceiver addressReceiver);

    /**
     * 批量添加
     * @param list
     * @return
     */
    public int batchInsert(List<AddressReceiver> list);

    /**
     * 修改收货人信息
     * 
     * @param addressReceiver 收货人信息
     * @return 结果
     */
    public int updateAddressReceiver(AddressReceiver addressReceiver);

    /**
     * 删除收货人信息
     * 
     * @param id 收货人信息主键
     * @return 结果
     */
    public int deleteAddressReceiverById(Long id);

    /**
     * 批量删除收货人信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAddressReceiverByIds(Long[] ids);
}
