package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.RedirectRel;

import java.util.List;

/**
 * 转寄关联关系Mapper接口
 * 
 * @author ruoyi
 * @date 2022-08-12
 */
public interface RedirectRelMapper 
{
    /**
     * 查询转寄关联关系
     * 
     * @param id 转寄关联关系主键
     * @return 转寄关联关系
     */
    public RedirectRel selectRedirectRelById(Long id);

    /**
     * 查询转寄关联关系列表
     * 
     * @param redirectRel 转寄关联关系
     * @return 转寄关联关系集合
     */
    public List<RedirectRel> selectRedirectRelList(RedirectRel redirectRel);

    /**
     * 新增转寄关联关系
     * 
     * @param redirectRel 转寄关联关系
     * @return 结果
     */
    public int insertRedirectRel(RedirectRel redirectRel);

    /**
     * 修改转寄关联关系
     * 
     * @param redirectRel 转寄关联关系
     * @return 结果
     */
    public int updateRedirectRel(RedirectRel redirectRel);

    /**
     * 删除转寄关联关系
     * 
     * @param id 转寄关联关系主键
     * @return 结果
     */
    public int deleteRedirectRelById(Long id);

    /**
     * 批量删除转寄关联关系
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRedirectRelByIds(Long[] ids);


    /**
     * 批量添加
     * @param list 面单们
     * @return
     */
    public int batchInsert(List<RedirectRel> list);

    public List<RedirectRel> selectByNewOrderIn(List<String> newOrders);

    public List<RedirectRel> selectByOldWaybillIn(List<String> oldWaybills);

}
