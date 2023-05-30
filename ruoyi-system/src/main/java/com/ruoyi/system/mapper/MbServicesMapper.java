package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Services;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2022-01-02
 */
public interface MbServicesMapper
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

    public List<Services> selectServicesListByIdIn(List<Long> ids);

    /**
     * 新增【请填写功能名称】
     * 
     * @param services 【请填写功能名称】
     * @return 结果
     */
    public int insertServices(Services services);

    public int insertServicesWithId(Services services);

    public int batchInsert(List<Services> servicesList);

    /**
     * 修改【请填写功能名称】
     * 
     * @param services 【请填写功能名称】
     * @return 结果
     */
    public int updateServices(Services services);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteServicesById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteServicesByIds(Long[] ids);

    public int insertInToWithOutMb();

    public int deleteMbData();

}
