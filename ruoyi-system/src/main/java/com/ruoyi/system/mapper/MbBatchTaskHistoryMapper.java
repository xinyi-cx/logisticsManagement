package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.BatchTaskHistory;
import com.ruoyi.system.domain.vo.BatchTaskHistoryVo;

import java.util.List;

/**
 * 批量任务历史Mapper接口
 * 
 * @author ruoyi
 * @date 2022-01-03
 */
public interface MbBatchTaskHistoryMapper
{
    /**
     * 查询批量任务历史
     * 
     * @param id 批量任务历史主键
     * @return 批量任务历史
     */
    public BatchTaskHistory selectBatchTaskHistoryById(Long id);

    public List<BatchTaskHistory> selectBatchTaskHistoryByIdIn(List<Long> ids);

    /**
     * 查询批量任务历史列表
     * 
     * @param batchTaskHistoryVo 批量任务历史
     * @return 批量任务历史集合
     */
    public List<BatchTaskHistory> selectBatchTaskHistoryList(BatchTaskHistoryVo batchTaskHistoryVo);

    /**
     * 新增批量任务历史
     * 
     * @param batchTaskHistory 批量任务历史
     * @return 结果
     */
    public int insertBatchTaskHistoryWithId(BatchTaskHistory batchTaskHistory);

    /**
     * 修改批量任务历史
     * 
     * @param batchTaskHistory 批量任务历史
     * @return 结果
     */
    public int updateBatchTaskHistory(BatchTaskHistory batchTaskHistory);

    /**
     * 删除批量任务历史
     * 
     * @param id 批量任务历史主键
     * @return 结果
     */
    public int deleteBatchTaskHistoryById(Long id);

    /**
     * 批量删除批量任务历史
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBatchTaskHistoryByIds(Long[] ids);
}
