package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Package;
import com.ruoyi.system.domain.vo.PackageVo;

/**
 * 面单Service接口
 * 
 * @author ruoyi
 * @date 2022-01-02
 */
public interface IPackageService 
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
     * 查询面单列表
     *
     * @param packageVo 面单
     * @return 面单集合
     */
    public List<PackageVo> selectPackageVoList(PackageVo packageVo);

    /**
     * 新增面单
     * 
     * @param pkg 面单
     * @return 结果
     */
    public int insertPackage(Package pkg);

    /**
     * 新增面单
     *
     * @param pkg 面单
     * @return 结果
     */
    public int insertPackage(PackageVo pkg);

    /**
     * 导入面单
     *
     * @param packageVos 面单
     * @return 结果
     */
    public int importPackage(List<PackageVo> packageVos);

    /**
     * 修改面单
     * 
     * @param pkg 面单
     * @return 结果
     */
    public int updatePackage(Package pkg);

    /**
     * 批量删除面单
     *
     * @param ids 需要删除的面单主键集合
     * @return 结果
     */
    public int deletePackageByIds(Long[] ids);

    /**
     * 删除面单信息
     * 
     * @param id 面单主键
     * @return 结果
     */
    public int deletePackageById(Long id);
}
