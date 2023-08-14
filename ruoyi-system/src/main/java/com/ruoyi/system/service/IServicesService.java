package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Services;

/**
 * 【请填写功能名称】Service接口
 * 
 *
 * @date 2022-01-02
 */
public interface IServicesService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public Services selectServicesById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param services 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Services> selectServicesList(Services services);

    /**
     * 新增【请填写功能名称】
     * 
     * @param services 【请填写功能名称】
     * @return 结果
     */
    public int insertServices(Services services);

    /**
     * 修改【请填写功能名称】
     * 
     * @param services 【请填写功能名称】
     * @return 结果
     */
    public int updateServices(Services services);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteServicesByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteServicesById(Long id);
}
