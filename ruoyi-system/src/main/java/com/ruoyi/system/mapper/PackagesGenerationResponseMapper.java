package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.PackagesGenerationResponse;

import java.util.List;

/**
 * 面单返回值Mapper接口
 * 
 * @author ruoyi
 * @date 2022-01-02
 */
public interface PackagesGenerationResponseMapper 
{
    /**
     * 查询面单返回值
     * 
     * @param id 面单返回值主键
     * @return 面单返回值
     */
    public PackagesGenerationResponse selectPackagesGenerationResponseById(Long id);

    /**
     * 查询面单返回值列表
     * 
     * @param packagesGenerationResponse 面单返回值
     * @return 面单返回值集合
     */
    public List<PackagesGenerationResponse> selectPackagesGenerationResponseList(PackagesGenerationResponse packagesGenerationResponse);

    public List<PackagesGenerationResponse> selectPackagesGenerationResponseListByPacIds(List<Long> ids);

    /**
     * 新增面单返回值
     * 
     * @param packagesGenerationResponse 面单返回值
     * @return 结果
     */
    public int insertPackagesGenerationResponse(PackagesGenerationResponse packagesGenerationResponse);

    /**
     * 修改面单返回值
     * 
     * @param packagesGenerationResponse 面单返回值
     * @return 结果
     */
    public int updatePackagesGenerationResponse(PackagesGenerationResponse packagesGenerationResponse);

    /**
     * 删除面单返回值
     * 
     * @param id 面单返回值主键
     * @return 结果
     */
    public int deletePackagesGenerationResponseById(Long id);

    /**
     * 批量删除面单返回值
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePackagesGenerationResponseByIds(Long[] ids);
}
