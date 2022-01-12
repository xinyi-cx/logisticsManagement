package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Documents;

/**
 * 文件Mapper接口
 * 
 * @author ruoyi
 * @date 2022-01-12
 */
public interface DocumentsMapper 
{
    /**
     * 查询文件
     * 
     * @param id 文件主键
     * @return 文件
     */
    public Documents selectDocumentsById(Long id);

    /**
     * 查询文件列表
     * 
     * @param documents 文件
     * @return 文件集合
     */
    public List<Documents> selectDocumentsList(Documents documents);

    /**
     * 新增文件
     * 
     * @param documents 文件
     * @return 结果
     */
    public int insertDocuments(Documents documents);

    /**
     * 修改文件
     * 
     * @param documents 文件
     * @return 结果
     */
    public int updateDocuments(Documents documents);

    /**
     * 删除文件
     * 
     * @param id 文件主键
     * @return 结果
     */
    public int deleteDocumentsById(Long id);

    /**
     * 批量删除文件
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDocumentsByIds(Long[] ids);
}
