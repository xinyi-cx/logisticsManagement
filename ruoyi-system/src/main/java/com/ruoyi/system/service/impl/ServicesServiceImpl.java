package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ServicesMapper;
import com.ruoyi.system.domain.Services;
import com.ruoyi.system.service.IServicesService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-01-02
 */
@Service
public class ServicesServiceImpl implements IServicesService 
{
    @Autowired
    private ServicesMapper servicesMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public Services selectServicesById(Long id)
    {
        return servicesMapper.selectServicesById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param services 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Services> selectServicesList(Services services)
    {
        return servicesMapper.selectServicesList(services);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param services 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertServices(Services services)
    {
        return servicesMapper.insertServices(services);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param services 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateServices(Services services)
    {
        return servicesMapper.updateServices(services);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteServicesByIds(Long[] ids)
    {
        return servicesMapper.deleteServicesByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteServicesById(Long id)
    {
        return servicesMapper.deleteServicesById(id);
    }
}
