package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.AddressSender;
import com.ruoyi.system.mapper.AddressSenderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.UserFidRelMapper;
import com.ruoyi.system.domain.UserFidRel;
import com.ruoyi.system.service.IUserFidRelService;

/**
 * 用户fid对应关系Service业务层处理
 *
 * @author ruoyi
 * @date 2023-02-20
 */
@Service
public class UserFidRelServiceImpl implements IUserFidRelService {

    @Autowired
    private UserFidRelMapper userFidRelMapper;

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
        userFidRel.setCreateTime(DateUtils.getNowDate());
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
        UserFidRel userFidRel = userFidRelMapper.selectUserFidRelById(id);
        userFidRelMapper.activeUserFidRelById(id);
        userFidRelMapper.inActiveUserFidRelById(userFidRel.getUserId());
        AddressSender param = new AddressSender();
        param.setCreateUser(userFidRel.getUserId().toString());
        List<AddressSender> addressSenders = addressSenderMapper.selectAddressSenderList(param);
        AddressSender updateOne = addressSenders.get(0);
        updateOne.setFid(userFidRel.getFid());
        addressSenderMapper.updateAddressSender(updateOne);
        return 1;
    }


}
