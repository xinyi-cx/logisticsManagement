package com.ruoyi.system.service;

import com.ruoyi.system.domain.ImportLogicContent;
import com.ruoyi.system.domain.vo.ExportLogicContentVo;

import java.util.List;

/**
 * 导入查询物流Service接口
 * 
 * @author ruoyi
 * @date 2022-11-20
 */
public interface IImportLogicContentService 
{
    /**
     * 查询导入查询物流
     * 
     * @param id 导入查询物流主键
     * @return 导入查询物流
     */
    public ImportLogicContent selectImportLogicContentById(Long id);

    /**
     * 查询导入查询物流列表
     * 
     * @param importLogicContent 导入查询物流
     * @return 导入查询物流集合
     */
    public List<ImportLogicContent> selectImportLogicContentList(ImportLogicContent importLogicContent);

    public List<ExportLogicContentVo> exportImportLogicContentList(ImportLogicContent importLogicContent);

    /**
     * 新增导入查询物流
     * 
     * @param importLogicContent 导入查询物流
     * @return 结果
     */
    public int insertImportLogicContent(ImportLogicContent importLogicContent);

    /**
     * 修改导入查询物流
     * 
     * @param importLogicContent 导入查询物流
     * @return 结果
     */
    public int updateImportLogicContent(ImportLogicContent importLogicContent);

    /**
     * 批量删除导入查询物流
     * 
     * @param ids 需要删除的导入查询物流主键集合
     * @return 结果
     */
    public int deleteImportLogicContentByIds(Long[] ids);

    /**
     * 删除导入查询物流信息
     * 
     * @param id 导入查询物流主键
     * @return 结果
     */
    public int deleteImportLogicContentById(Long id);
}