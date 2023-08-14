package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.PackagesGenerationResponseMapper;
import com.ruoyi.system.domain.PackagesGenerationResponse;
import com.ruoyi.system.service.IPackagesGenerationResponseService;

/**
 * 面单返回值Service业务层处理
 * 
 *
 * @date 2022-01-02
 */
@Service
public class PackagesGenerationResponseServiceImpl implements IPackagesGenerationResponseService 
{
    @Autowired
    private PackagesGenerationResponseMapper packagesGenerationResponseMapper;

    /**
     * 查询面单返回值
     * 
     * @param id 面单返回值主键
     * @return 面单返回值
     */
    @Override
    public PackagesGenerationResponse selectPackagesGenerationResponseById(Long id)
    {
        return packagesGenerationResponseMapper.selectPackagesGenerationResponseById(id);
    }

    /**
     * 查询面单返回值列表
     * 
     * @param packagesGenerationResponse 面单返回值
     * @return 面单返回值
     */
    @Override
    public List<PackagesGenerationResponse> selectPackagesGenerationResponseList(PackagesGenerationResponse packagesGenerationResponse)
    {
        return packagesGenerationResponseMapper.selectPackagesGenerationResponseList(packagesGenerationResponse);
    }

    /**
     * 新增面单返回值
     * 
     * @param packagesGenerationResponse 面单返回值
     * @return 结果
     */
    @Override
    public int insertPackagesGenerationResponse(PackagesGenerationResponse packagesGenerationResponse)
    {
        return packagesGenerationResponseMapper.insertPackagesGenerationResponse(packagesGenerationResponse);
    }

    /**
     * 修改面单返回值
     * 
     * @param packagesGenerationResponse 面单返回值
     * @return 结果
     */
    @Override
    public int updatePackagesGenerationResponse(PackagesGenerationResponse packagesGenerationResponse)
    {
        return packagesGenerationResponseMapper.updatePackagesGenerationResponse(packagesGenerationResponse);
    }

    /**
     * 批量删除面单返回值
     * 
     * @param ids 需要删除的面单返回值主键
     * @return 结果
     */
    @Override
    public int deletePackagesGenerationResponseByIds(Long[] ids)
    {
        return packagesGenerationResponseMapper.deletePackagesGenerationResponseByIds(ids);
    }

    /**
     * 删除面单返回值信息
     * 
     * @param id 面单返回值主键
     * @return 结果
     */
    @Override
    public int deletePackagesGenerationResponseById(Long id)
    {
        return packagesGenerationResponseMapper.deletePackagesGenerationResponseById(id);
    }
}
