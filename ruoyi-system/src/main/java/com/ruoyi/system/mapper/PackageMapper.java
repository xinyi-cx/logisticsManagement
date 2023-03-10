package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Package;

import java.util.List;

/**
 * 面单Mapper接口
 *
 * @author ruoyi
 * @date 2022-01-02
 */
public interface PackageMapper {
    /**
     * 查询面单
     *
     * @param id 面单主键
     * @return 面单
     */
    public Package selectPackageById(Long id);

    public List<Package> selectPackageByIdIn(List<Long> ids);

    /**
     * 查询面单
     *
     * @return id
     */
    public Long selectMaxId();

    /**
     * 查询面单列表
     *
     * @param pkg 面单
     * @return 面单集合
     */
    public List<Package> selectPackageList(Package pkg);

    public List<Package> selectPackageListForRel(Package pkg);

    public List<Package> selectPackageListForLocal(Package pkg);

    /**
     * 更细下载数量
     * @param ids
     * @return
     */
    public int updateDownloadNum(List<Long> ids);

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
    public int insertPackageWithId(Package pkg);

    /**
     * 批量添加
     * @param list 面单们
     * @return
     */
    public int batchInsert(List<Package> list);

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
