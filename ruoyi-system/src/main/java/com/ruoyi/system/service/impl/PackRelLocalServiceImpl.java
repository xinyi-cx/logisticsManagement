package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.PackRelLocalMapper;
import com.ruoyi.system.domain.PackRelLocal;
import com.ruoyi.system.service.IPackRelLocalService;

/**
 * 本地关联关系Service业务层处理
 * 
 *
 * @date 2023-03-08
 */
@Service
public class PackRelLocalServiceImpl implements IPackRelLocalService 
{
    @Autowired
    private PackRelLocalMapper packRelLocalMapper;

    /**
     * 查询本地关联关系
     * 
     * @param id 本地关联关系主键
     * @return 本地关联关系
     */
    @Override
    public PackRelLocal selectPackRelLocalById(Long id)
    {
        return packRelLocalMapper.selectPackRelLocalById(id);
    }

    /**
     * 查询本地关联关系列表
     * 
     * @param packRelLocal 本地关联关系
     * @return 本地关联关系
     */
    @Override
    public List<PackRelLocal> selectPackRelLocalList(PackRelLocal packRelLocal)
    {
        return packRelLocalMapper.selectPackRelLocalList(packRelLocal);
    }

    /**
     * 新增本地关联关系
     * 
     * @param packRelLocal 本地关联关系
     * @return 结果
     */
    @Override
    public int insertPackRelLocal(PackRelLocal packRelLocal)
    {
        return packRelLocalMapper.insertPackRelLocal(packRelLocal);
    }

    /**
     * 修改本地关联关系
     * 
     * @param packRelLocal 本地关联关系
     * @return 结果
     */
    @Override
    public int updatePackRelLocal(PackRelLocal packRelLocal)
    {
        return packRelLocalMapper.updatePackRelLocal(packRelLocal);
    }

    /**
     * 批量删除本地关联关系
     * 
     * @param ids 需要删除的本地关联关系主键
     * @return 结果
     */
    @Override
    public int deletePackRelLocalByIds(Long[] ids)
    {
        return packRelLocalMapper.deletePackRelLocalByIds(ids);
    }

    /**
     * 删除本地关联关系信息
     * 
     * @param id 本地关联关系主键
     * @return 结果
     */
    @Override
    public int deletePackRelLocalById(Long id)
    {
        return packRelLocalMapper.deletePackRelLocalById(id);
    }
}
