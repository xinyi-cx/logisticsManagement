package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.PackageMapper;
import com.ruoyi.system.domain.Package;
import com.ruoyi.system.service.IPackageService;

/**
 * 面单Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-01-02
 */
@Service
public class PackageServiceImpl implements IPackageService 
{
    @Autowired
    private PackageMapper packageMapper;

    /**
     * 查询面单
     * 
     * @param id 面单主键
     * @return 面单
     */
    @Override
    public Package selectPackageById(Long id)
    {
        return packageMapper.selectPackageById(id);
    }

    /**
     * 查询面单列表
     * 
     * @param pkg 面单
     * @return 面单
     */
    @Override
    public List<Package> selectPackageList(Package pkg)
    {
        return packageMapper.selectPackageList(pkg);
    }

    /**
     * 新增面单
     * 
     * @param pkg 面单
     * @return 结果
     */
    @Override
    public int insertPackage(Package pkg)
    {
        return packageMapper.insertPackage(pkg);
    }

    /**
     * 修改面单
     * 
     * @param pkg 面单
     * @return 结果
     */
    @Override
    public int updatePackage(Package pkg)
    {
        return packageMapper.updatePackage(pkg);
    }

    /**
     * 批量删除面单
     * 
     * @param ids 需要删除的面单主键
     * @return 结果
     */
    @Override
    public int deletePackageByIds(Long[] ids)
    {
        return packageMapper.deletePackageByIds(ids);
    }

    /**
     * 删除面单信息
     * 
     * @param id 面单主键
     * @return 结果
     */
    @Override
    public int deletePackageById(Long id)
    {
        return packageMapper.deletePackageById(id);
    }
}
