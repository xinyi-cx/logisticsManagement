package com.ruoyi.system.service;

import com.ruoyi.system.domain.PackageDpdMapping;

import java.util.List;

/**
 * 面单dpd对应关系Service接口
 * 
 * @author ruoyi
 * @date 2022-07-27
 */
public interface IPackageDpdMappingService 
{
    /**
     * 查询面单dpd对应关系
     * 
     * @param id 面单dpd对应关系主键
     * @return 面单dpd对应关系
     */
    public PackageDpdMapping selectPackageDpdMappingById(Long id);

    /**
     * 查询面单dpd对应关系列表
     * 
     * @param packageDpdMapping 面单dpd对应关系
     * @return 面单dpd对应关系集合
     */
    public List<PackageDpdMapping> selectPackageDpdMappingList(PackageDpdMapping packageDpdMapping);

    /**
     * 新增面单dpd对应关系
     * 
     * @param packageDpdMapping 面单dpd对应关系
     * @return 结果
     */
    public int insertPackageDpdMapping(PackageDpdMapping packageDpdMapping);

    /**
     * 修改面单dpd对应关系
     * 
     * @param packageDpdMapping 面单dpd对应关系
     * @return 结果
     */
    public int updatePackageDpdMapping(PackageDpdMapping packageDpdMapping);

    /**
     * 批量删除面单dpd对应关系
     * 
     * @param ids 需要删除的面单dpd对应关系主键集合
     * @return 结果
     */
    public int deletePackageDpdMappingByIds(Long[] ids);

    /**
     * 删除面单dpd对应关系信息
     * 
     * @param id 面单dpd对应关系主键
     * @return 结果
     */
    public int deletePackageDpdMappingById(Long id);
}
