package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.PackageDpdMapping;
import com.ruoyi.system.mapper.PackageDpdMappingMapper;
import com.ruoyi.system.service.IPackageDpdMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 面单dpd对应关系Service业务层处理
 *
 *
 * @date 2022-07-27
 */
@Service
public class PackageDpdMappingServiceImpl implements IPackageDpdMappingService {
    @Autowired
    private PackageDpdMappingMapper packageDpdMappingMapper;

    /**
     * 查询面单dpd对应关系
     *
     * @param id 面单dpd对应关系主键
     * @return 面单dpd对应关系
     */
    @Override
    public PackageDpdMapping selectPackageDpdMappingById(Long id) {
        return packageDpdMappingMapper.selectPackageDpdMappingById(id);
    }

    /**
     * 查询面单dpd对应关系列表
     *
     * @param packageDpdMapping 面单dpd对应关系
     * @return 面单dpd对应关系
     */
    @Override
    public List<PackageDpdMapping> selectPackageDpdMappingList(PackageDpdMapping packageDpdMapping) {
        return packageDpdMappingMapper.selectPackageDpdMappingList(packageDpdMapping);
    }

    /**
     * 新增面单dpd对应关系
     *
     * @param packageDpdMapping 面单dpd对应关系
     * @return 结果
     */
    @Override
    public int insertPackageDpdMapping(PackageDpdMapping packageDpdMapping) {
        return packageDpdMappingMapper.insertPackageDpdMapping(packageDpdMapping);
    }

    /**
     * 修改面单dpd对应关系
     *
     * @param packageDpdMapping 面单dpd对应关系
     * @return 结果
     */
    @Override
    public int updatePackageDpdMapping(PackageDpdMapping packageDpdMapping) {
        return packageDpdMappingMapper.updatePackageDpdMapping(packageDpdMapping);
    }

    /**
     * 批量删除面单dpd对应关系
     *
     * @param ids 需要删除的面单dpd对应关系主键
     * @return 结果
     */
    @Override
    public int deletePackageDpdMappingByIds(Long[] ids) {
        return packageDpdMappingMapper.deletePackageDpdMappingByIds(ids);
    }

    /**
     * 删除面单dpd对应关系信息
     *
     * @param id 面单dpd对应关系主键
     * @return 结果
     */
    @Override
    public int deletePackageDpdMappingById(Long id) {
        return packageDpdMappingMapper.deletePackageDpdMappingById(id);
    }
}
