package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.ImportLogicContent;

import java.util.List;

/**
 * 导入查询物流Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-20
 */
public interface ImportLogicContentMapper 
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
     * 删除导入查询物流
     * 
     * @param id 导入查询物流主键
     * @return 结果
     */
    public int deleteImportLogicContentById(Long id);

    /**
     * 批量删除导入查询物流
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteImportLogicContentByIds(Long[] ids);

    public int batchInsert(List<ImportLogicContent> list);

}
