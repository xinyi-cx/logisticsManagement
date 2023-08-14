package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.PackagesGenerationResponse;

/**
 * 面单返回值Service接口
 * 
 *
 * @date 2022-01-02
 */
public interface IPackagesGenerationResponseService 
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
     * 批量删除面单返回值
     * 
     * @param ids 需要删除的面单返回值主键集合
     * @return 结果
     */
    public int deletePackagesGenerationResponseByIds(Long[] ids);

    /**
     * 删除面单返回值信息
     * 
     * @param id 面单返回值主键
     * @return 结果
     */
    public int deletePackagesGenerationResponseById(Long id);
}
