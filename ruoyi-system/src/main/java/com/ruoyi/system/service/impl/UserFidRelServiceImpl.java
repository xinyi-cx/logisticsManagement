package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.AddressSender;
import com.ruoyi.system.domain.UserFidRel;
import com.ruoyi.system.mapper.AddressSenderMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.UserFidRelMapper;
import com.ruoyi.system.service.IUserFidRelService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户fid对应关系Service业务层处理
 *
 *
 * @date 2023-02-20
 */
@Service
public class UserFidRelServiceImpl implements IUserFidRelService {

    @Autowired
    private UserFidRelMapper userFidRelMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private AddressSenderMapper addressSenderMapper;

    /**
     * 查询用户fid对应关系
     *
     * @param id 用户fid对应关系主键
     * @return 用户fid对应关系
     */
    @Override
    public UserFidRel selectUserFidRelById(Long id) {
        return userFidRelMapper.selectUserFidRelById(id);
    }

    /**
     * 查询用户fid对应关系列表
     *
     * @param userFidRel 用户fid对应关系
     * @return 用户fid对应关系
     */
    @Override
    public List<UserFidRel> selectUserFidRelList(UserFidRel userFidRel) {
        return userFidRelMapper.selectUserFidRelList(userFidRel);
    }

    /**
     * 新增用户fid对应关系
     *
     * @param userFidRel 用户fid对应关系
     * @return 结果
     */
    @Override
    public int insertUserFidRel(UserFidRel userFidRel) {
        SysUser sysUser = sysUserMapper.selectUserById(userFidRel.getUserId());
        userFidRel.setCreateTime(DateUtils.getNowDate());
        userFidRel.setUserName(sysUser.getUserName());
//        userFidRel.setCustomerName(sysUser.getCustomerName());
        userFidRel.setNickName(sysUser.getNickName());
        userFidRel.setCountry(sysUser.getCountry());
        userFidRel.setStatus("0");
        AddressSender senderParam = new AddressSender();
        senderParam.setCreateUser(sysUser.getUserId().toString());
        AddressSender returnSender = addressSenderMapper.selectAddressSenderList(senderParam).get(0);
        if (ObjectUtils.isNotEmpty(returnSender)){
            userFidRel.setFid(returnSender.getFid());
        }
        return userFidRelMapper.insertUserFidRel(userFidRel);
    }

    /**
     * 修改用户fid对应关系
     *
     * @param userFidRel 用户fid对应关系
     * @return 结果
     */
    @Override
    public int updateUserFidRel(UserFidRel userFidRel) {
        userFidRel.setUpdateTime(DateUtils.getNowDate());
        return userFidRelMapper.updateUserFidRel(userFidRel);
    }

    /**
     * 批量删除用户fid对应关系
     *
     * @param ids 需要删除的用户fid对应关系主键
     * @return 结果
     */
    @Override
    public int deleteUserFidRelByIds(Long[] ids) {
        return userFidRelMapper.deleteUserFidRelByIds(ids);
    }

    /**
     * 删除用户fid对应关系信息
     *
     * @param id 用户fid对应关系主键
     * @return 结果
     */
    @Override
    public int deleteUserFidRelById(Long id) {
        return userFidRelMapper.deleteUserFidRelById(id);
    }

    @Override
    public int activeUserFidRelById(Long id) {
//        UserFidRel userFidRel = userFidRelMapper.selectUserFidRelById(id);
//        userFidRelMapper.inActiveUserFidRelById(userFidRel);
        userFidRelMapper.activeUserFidRelById(id);
//        AddressSender param = new AddressSender();
//        param.setCreateUser(userFidRel.getUserId().toString());
//        List<AddressSender> addressSenders = addressSenderMapper.selectAddressSenderList(param);
//        AddressSender updateOne = addressSenders.get(0);
//        updateOne.setFid(userFidRel.getFid());
//        addressSenderMapper.updateAddressSender(updateOne);
        return 1;
    }


}
