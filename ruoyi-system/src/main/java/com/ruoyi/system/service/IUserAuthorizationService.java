package com.ruoyi.system.service;

import com.ruoyi.system.domain.UserAuthorization;

import java.util.List;

/**
 * 用户授权信息Service接口
 * 
 * @author ruoyi
 * @date 2022-05-18
 */
public interface IUserAuthorizationService 
{
    /**
     * 查询用户授权信息
     * 
     * @param userId 用户授权信息主键
     * @return 用户授权信息
     */
    public UserAuthorization selectUserAuthorizationByUserId(Long userId);

    /**
     * 查询用户授权信息列表
     * 
     * @param userAuthorization 用户授权信息
     * @return 用户授权信息集合
     */
    public List<UserAuthorization> selectUserAuthorizationList(UserAuthorization userAuthorization);

    /**
     * 新增用户授权信息
     * 
     * @param userAuthorization 用户授权信息
     * @return 结果
     */
    public int insertUserAuthorization(UserAuthorization userAuthorization);

    /**
     * 修改用户授权信息
     * 
     * @param userAuthorization 用户授权信息
     * @return 结果
     */
    public int updateUserAuthorization(UserAuthorization userAuthorization);

    /**
     * 批量删除用户授权信息
     * 
     * @param userIds 需要删除的用户授权信息主键集合
     * @return 结果
     */
    public int deleteUserAuthorizationByUserIds(Long[] userIds);

    /**
     * 删除用户授权信息信息
     * 
     * @param userId 用户授权信息主键
     * @return 结果
     */
    public int deleteUserAuthorizationByUserId(Long userId);
}
