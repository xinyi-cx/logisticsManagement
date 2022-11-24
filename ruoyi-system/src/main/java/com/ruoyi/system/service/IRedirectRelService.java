package com.ruoyi.system.service;

import com.ruoyi.system.domain.LogisticsInfo;
import com.ruoyi.system.domain.RedirectRel;
import com.ruoyi.system.domain.vo.ExportRedirectRelVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 转寄关联关系Service接口
 * 
 * @author ruoyi
 * @date 2022-08-12
 */
public interface IRedirectRelService 
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
     * 批量删除转寄关联关系
     * 
     * @param ids 需要删除的转寄关联关系主键集合
     * @return 结果
     */
    public int deleteRedirectRelByIds(Long[] ids);

    /**
     * 删除转寄关联关系信息
     * 
     * @param id 转寄关联关系主键
     * @return 结果
     */
    public int deleteRedirectRelById(Long id);

    public String importRedirectRel(MultipartFile file, List<RedirectRel> redirectRelList) throws Exception;

    /**
     * 导出转寄物流信息
     * @param waybills
     * @return
     */
    public List<ExportRedirectRelVo> exportRedirectRelVo(List<String> waybills);

    public List<ExportRedirectRelVo> exportWithZj(List<LogisticsInfo> logisticsInfos);

}
