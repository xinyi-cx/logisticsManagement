package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.PackRelLocal;

/**
 * 本地关联关系Service接口
 * 
 *
 * @date 2023-03-08
 */
public interface IPackRelLocalService 
{
    /**
     * 查询本地关联关系
     * 
     * @param id 本地关联关系主键
     * @return 本地关联关系
     */
    public PackRelLocal selectPackRelLocalById(Long id);

    /**
     * 查询本地关联关系列表
     * 
     * @param packRelLocal 本地关联关系
     * @return 本地关联关系集合
     */
    public List<PackRelLocal> selectPackRelLocalList(PackRelLocal packRelLocal);

    /**
     * 新增本地关联关系
     * 
     * @param packRelLocal 本地关联关系
     * @return 结果
     */
    public int insertPackRelLocal(PackRelLocal packRelLocal);

    /**
     * 修改本地关联关系
     * 
     * @param packRelLocal 本地关联关系
     * @return 结果
     */
    public int updatePackRelLocal(PackRelLocal packRelLocal);

    /**
     * 批量删除本地关联关系
     * 
     * @param ids 需要删除的本地关联关系主键集合
     * @return 结果
     */
    public int deletePackRelLocalByIds(Long[] ids);

    /**
     * 删除本地关联关系信息
     * 
     * @param id 本地关联关系主键
     * @return 结果
     */
    public int deletePackRelLocalById(Long id);
}
