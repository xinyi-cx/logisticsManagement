package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.DPDServicesExample.client.DPDServicesXMLClient;
import com.ruoyi.system.domain.AddressSender;
import com.ruoyi.system.mapper.AddressSenderMapper;
import com.ruoyi.system.service.IAddressSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 发件人信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-01-02
 */
@Service
public class AddressSenderServiceImpl implements IAddressSenderService 
{
    @Autowired
    private AddressSenderMapper addressSenderMapper;

    @Autowired
    private DPDServicesXMLClient dpdServicesXMLClient;

    /**
     * 查询发件人信息
     * 
     * @param id 发件人信息主键
     * @return 发件人信息
     */
    @Override
    public AddressSender selectAddressSenderById(Long id)
    {
        if (id == 0){
            AddressSender addressSender = new AddressSender();
            addressSender.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
            List<AddressSender> addressSenders = addressSenderMapper.selectAddressSenderList(addressSender);
            return addressSenders.get(0);
        }
        return addressSenderMapper.selectAddressSenderById(id);
    }

    /**
     * 查询发件人信息列表
     * 
     * @param addressSender 发件人信息
     * @return 发件人信息
     */
    @Override
    public List<AddressSender> selectAddressSenderList(AddressSender addressSender)
    {
        return addressSenderMapper.selectAddressSenderList(addressSender);
    }

    /**
     * 新增发件人信息
     * 
     * @param addressSender 发件人信息
     * @return 结果
     */
    @Override
    public int insertAddressSender(AddressSender addressSender)
    {
        return addressSenderMapper.insertAddressSender(addressSender);
    }

    /**
     * 修改发件人信息
     * 
     * @param addressSender 发件人信息
     * @return 结果
     */
    @Override
    public int updateAddressSender(AddressSender addressSender) throws Exception {
        checkCountryZipCode(addressSender);
        return addressSenderMapper.updateAddressSender(addressSender);
    }

    private void checkCountryZipCode(AddressSender addressSender) throws Exception {
        String status = dpdServicesXMLClient.findPostalCode(addressSender.getCountryCode(), addressSender.getPostalCode());
        if (!"OK".equals(status)) {
            throw new Exception("邮编错误：" + status);
        }
    }

    /**
     * 批量删除发件人信息
     * 
     * @param ids 需要删除的发件人信息主键
     * @return 结果
     */
    @Override
    public int deleteAddressSenderByIds(Long[] ids)
    {
        return addressSenderMapper.deleteAddressSenderByIds(ids);
    }

    /**
     * 删除发件人信息信息
     * 
     * @param id 发件人信息主键
     * @return 结果
     */
    @Override
    public int deleteAddressSenderById(Long id)
    {
        return addressSenderMapper.deleteAddressSenderById(id);
    }
}
