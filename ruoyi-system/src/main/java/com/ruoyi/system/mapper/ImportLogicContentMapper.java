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

    public ImportLogicContent selectImportLogicContentByNewWaybill(String newWaybill);

    public List<ImportLogicContent> selectImportLogicContentByNewWaybillIn(List<String> waybills);

    public List<ImportLogicContent> selectImportLogicContentByBatIdsIn(List<Long> batIds);

    /**
     * 查询导入查询物流列表
     * 
     * @param importLogicContent 导入查询物流
     * @return 导入查询物流集合
     */
    public List<ImportLogicContent> selectImportLogicContentList(ImportLogicContent importLogicContent);

    public List<ImportLogicContent> selectImportLogicContentListNoReport(ImportLogicContent importLogicContent);

    public List<ImportLogicContent> selectImportLogicContentListReport(ImportLogicContent importLogicContent);

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

    public int updateImportLogicContentForFileName(ImportLogicContent importLogicContent);

    public int updateImportLogicContentByWayBill(ImportLogicContent importLogicContent);

    /**
     * 更新转寄物流状态
     *
     * @param importLogicContents
     * @return
     */
    public int batchUpdateZjImportLogicContentByWayBill(List<ImportLogicContent> importLogicContents);

    public int updateImportLogicContentByWaybill(ImportLogicContent importLogicContent);

    public int batchUpdateImportLogicContentByWaybill(List<ImportLogicContent> importLogicContents);

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

    public int deleteImportLogicContentByPackIdsReal(Long[] ids);

    public int deleteImportLogicContentByPackIds(Long[] ids);

    public int batchInsert(List<ImportLogicContent> list);

}
