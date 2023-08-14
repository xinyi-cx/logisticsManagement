package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.UserFidRel;

import java.util.List;

/**
 * 用户fid对应关系Mapper接口
 *
 *
 * @date 2023-02-20
 */
public interface UserFidRelMapper {
    /**
     * 查询用户fid对应关系
     *
     * @param id 用户fid对应关系主键
     * @return 用户fid对应关系
     */
    public UserFidRel selectUserFidRelById(Long id);

    /**
     * 查询用户fid对应关系列表
     *
     * @param userFidRel 用户fid对应关系
     * @return 用户fid对应关系集合
     */
    public List<UserFidRel> selectUserFidRelList(UserFidRel userFidRel);

    public List<UserFidRel> selectUserFidRelListByEq(UserFidRel userFidRel);

    /**
     * 新增用户fid对应关系
     *
     * @param userFidRel 用户fid对应关系
     * @return 结果
     */
    public int insertUserFidRel(UserFidRel userFidRel);

    /**
     * 修改用户fid对应关系
     *
     * @param userFidRel 用户fid对应关系
     * @return 结果
     */
    public int updateUserFidRel(UserFidRel userFidRel);

    /**
     * 删除用户fid对应关系
     *
     * @param id 用户fid对应关系主键
     * @return 结果
     */
    public int deleteUserFidRelById(Long id);

    public int activeUserFidRelById(Long id);

    public int inActiveUserFidRelById(UserFidRel userFidRel);

    /**
     * 批量删除用户fid对应关系
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserFidRelByIds(Long[] ids);
}
