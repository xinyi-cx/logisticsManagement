package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.system.domain.UserAuthorizationSys;
import com.ruoyi.system.mapper.UserAuthorizationMapper;
import com.ruoyi.system.service.IUserAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户授权信息Service业务层处理
 * 
 *
 * @date 2022-05-18
 */
@Service
public class UserAuthorizationServiceImpl implements IUserAuthorizationService 
{
    @Autowired
    private UserAuthorizationMapper userAuthorizationMapper;

    /**
     * 查询用户授权信息
     * 
     * @param userId 用户授权信息主键
     * @return 用户授权信息
     */
    @Override
    public UserAuthorizationSys selectUserAuthorizationByUserId(Long userId)
    {
        return userAuthorizationMapper.selectUserAuthorizationByUserId(userId);
    }

    /**
     * 查询用户授权信息列表
     * 
     * @param userAuthorizationSys 用户授权信息
     * @return 用户授权信息
     */
    @Override
    public List<UserAuthorizationSys> selectUserAuthorizationList(UserAuthorizationSys userAuthorizationSys)
    {
        userAuthorizationSys.setCreateBy(SecurityUtils.getLoginUser().getUserId().toString());
        return userAuthorizationMapper.selectUserAuthorizationList(userAuthorizationSys);
    }

    /**
     * 新增用户授权信息
     * 
     * @param userAuthorizationSys 用户授权信息
     * @return 结果
     */
    @Override
    public int insertUserAuthorization(UserAuthorizationSys userAuthorizationSys)
    {
        userAuthorizationSys.setCreateBy(SecurityUtils.getLoginUser().getUserId().toString());
        userAuthorizationSys.setUpdateBy(SecurityUtils.getLoginUser().getUserId().toString());
        userAuthorizationSys.setUserToken(IdUtils.fastUUID());
        return userAuthorizationMapper.insertUserAuthorization(userAuthorizationSys);
    }

    /**
     * 修改用户授权信息
     * 
     * @param userAuthorizationSys 用户授权信息
     * @return 结果
     */
    @Override
    public int updateUserAuthorization(UserAuthorizationSys userAuthorizationSys)
    {
        userAuthorizationSys.setUpdateBy(SecurityUtils.getLoginUser().getUserId().toString());
        return userAuthorizationMapper.updateUserAuthorization(userAuthorizationSys);
    }

    /**
     * 批量删除用户授权信息
     * 
     * @param userIds 需要删除的用户授权信息主键
     * @return 结果
     */
    @Override
    public int deleteUserAuthorizationByUserIds(Long[] userIds)
    {
        return userAuthorizationMapper.deleteUserAuthorizationByUserIds(userIds);
    }

    /**
     * 删除用户授权信息信息
     * 
     * @param userId 用户授权信息主键
     * @return 结果
     */
    @Override
    public int deleteUserAuthorizationByUserId(Long userId)
    {
        return userAuthorizationMapper.deleteUserAuthorizationByUserId(userId);
    }
}
