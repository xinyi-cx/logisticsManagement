package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.RedirectPackage;

import java.util.List;

/**
 * 转寄面单原面单关联关系Mapper接口
 * 
 *
 * @date 2022-01-17
 */
public interface RedirectPackageMapper 
{
    /**
     * 查询转寄面单原面单关联关系
     * 
     * @param id 转寄面单原面单关联关系主键
     * @return 转寄面单原面单关联关系
     */
    public RedirectPackage selectRedirectPackageById(Long id);

    /**
     * 查询转寄面单原面单关联关系列表
     * 
     * @param redirectPackage 转寄面单原面单关联关系
     * @return 转寄面单原面单关联关系集合
     */
    public List<RedirectPackage> selectRedirectPackageList(RedirectPackage redirectPackage);

    /**
     * 新增转寄面单原面单关联关系
     * 
     * @param redirectPackage 转寄面单原面单关联关系
     * @return 结果
     */
    public int insertRedirectPackage(RedirectPackage redirectPackage);

    public int batchInsert(List<RedirectPackage> list);

    /**
     * 修改转寄面单原面单关联关系
     * 
     * @param redirectPackage 转寄面单原面单关联关系
     * @return 结果
     */
    public int updateRedirectPackage(RedirectPackage redirectPackage);

    /**
     * 删除转寄面单原面单关联关系
     * 
     * @param id 转寄面单原面单关联关系主键
     * @return 结果
     */
    public int deleteRedirectPackageById(Long id);

    /**
     * 批量删除转寄面单原面单关联关系
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRedirectPackageByIds(Long[] ids);
}
