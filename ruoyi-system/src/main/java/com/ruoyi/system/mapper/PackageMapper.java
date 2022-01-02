package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Package;

/**
 * 面单Mapper接口
 * 
 * @author ruoyi
 * @date 2022-01-02
 */
public interface PackageMapper 
{
    /**
     * 查询面单
     * 
     * @param id 面单主键
     * @return 面单
     */
    public Package selectPackageById(Long id);

    /**
     * 查询面单列表
     * 
     * @param pkg 面单
     * @return 面单集合
     */
    public List<Package> selectPackageList(Package pkg);

    /**
     * 新增面单
     * 
     * @param pkg 面单
     * @return 结果
     */
    public int insertPackage(Package pkg);

    /**
     * 修改面单
     * 
     * @param pkg 面单
     * @return 结果
     */
    public int updatePackage(Package pkg);

    /**
     * 删除面单
     * 
     * @param id 面单主键
     * @return 结果
     */
    public int deletePackageById(Long id);

    /**
     * 批量删除面单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePackageByIds(Long[] ids);
}