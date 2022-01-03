package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.BatchTaskHistory;

/**
 * 批量任务历史Service接口
 * 
 * @author ruoyi
 * @date 2022-01-03
 */
public interface IBatchTaskHistoryService 
{
    /**
     * 查询批量任务历史
     * 
     * @param id 批量任务历史主键
     * @return 批量任务历史
     */
    public BatchTaskHistory selectBatchTaskHistoryById(Long id);

    /**
     * 查询批量任务历史列表
     * 
     * @param batchTaskHistory 批量任务历史
     * @return 批量任务历史集合
     */
    public List<BatchTaskHistory> selectBatchTaskHistoryList(BatchTaskHistory batchTaskHistory);

    /**
     * 新增批量任务历史
     * 
     * @param batchTaskHistory 批量任务历史
     * @return 结果
     */
    public int insertBatchTaskHistory(BatchTaskHistory batchTaskHistory);

    /**
     * 修改批量任务历史
     * 
     * @param batchTaskHistory 批量任务历史
     * @return 结果
     */
    public int updateBatchTaskHistory(BatchTaskHistory batchTaskHistory);

    /**
     * 批量删除批量任务历史
     * 
     * @param ids 需要删除的批量任务历史主键集合
     * @return 结果
     */
    public int deleteBatchTaskHistoryByIds(Long[] ids);

    /**
     * 删除批量任务历史信息
     * 
     * @param id 批量任务历史主键
     * @return 结果
     */
    public int deleteBatchTaskHistoryById(Long id);
}
