package com.ruoyi.system.service;

import com.ruoyi.system.domain.AddressReceiver;

/**
 * 收货人信息Service接口
 * 
 * @author ruoyi
 * @date 2022-01-02
 */
public interface IOuterService
{
    /**
     * 查询收货人信息
     * 
     * @param id 收货人信息主键
     * @return 收货人信息
     */
    public AddressReceiver selectAddressReceiverById(Long id);

}
