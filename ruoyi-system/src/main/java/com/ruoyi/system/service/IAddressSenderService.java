package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.AddressSender;

/**
 * 发件人信息Service接口
 * 
 * @author ruoyi
 * @date 2022-01-02
 */
public interface IAddressSenderService 
{
    /**
     * 查询发件人信息
     * 
     * @param id 发件人信息主键
     * @return 发件人信息
     */
    public AddressSender selectAddressSenderById(Long id);

    /**
     * 查询发件人信息列表
     * 
     * @param addressSender 发件人信息
     * @return 发件人信息集合
     */
    public List<AddressSender> selectAddressSenderList(AddressSender addressSender);

    /**
     * 新增发件人信息
     * 
     * @param addressSender 发件人信息
     * @return 结果
     */
    public int insertAddressSender(AddressSender addressSender);

    /**
     * 修改发件人信息
     * 
     * @param addressSender 发件人信息
     * @return 结果
     */
    public int updateAddressSender(AddressSender addressSender);

    /**
     * 批量删除发件人信息
     * 
     * @param ids 需要删除的发件人信息主键集合
     * @return 结果
     */
    public int deleteAddressSenderByIds(Long[] ids);

    /**
     * 删除发件人信息信息
     * 
     * @param id 发件人信息主键
     * @return 结果
     */
    public int deleteAddressSenderById(Long id);
}
