package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.BatchTaskHistory;
import com.ruoyi.system.domain.vo.BatchTaskHistoryVo;
import com.ruoyi.system.mapper.BatchTaskHistoryMapper;
import com.ruoyi.system.service.IBatchTaskHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 批量任务历史Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-01-03
 */
@Service
public class BatchTaskHistoryServiceImpl implements IBatchTaskHistoryService 
{
    @Autowired
    private BatchTaskHistoryMapper batchTaskHistoryMapper;

    /**
     * 查询批量任务历史
     * 
     * @param id 批量任务历史主键
     * @return 批量任务历史
     */
    @Override
    public BatchTaskHistory selectBatchTaskHistoryById(Long id)
    {
        return batchTaskHistoryMapper.selectBatchTaskHistoryById(id);
    }

    /**
     * 查询批量任务历史列表
     * 
     * @param batchTaskHistoryVo 批量任务历史
     * @return 批量任务历史
     */
    @Override
    public List<BatchTaskHistory> selectBatchTaskHistoryList(BatchTaskHistoryVo batchTaskHistoryVo)
    {
        return batchTaskHistoryMapper.selectBatchTaskHistoryList(batchTaskHistoryVo);
    }

    /**
     * 新增批量任务历史
     * 
     * @param batchTaskHistory 批量任务历史
     * @return 结果
     */
    @Override
    public int insertBatchTaskHistory(BatchTaskHistory batchTaskHistory)
    {
        return batchTaskHistoryMapper.insertBatchTaskHistory(batchTaskHistory);
    }

    /**
     * 修改批量任务历史
     * 
     * @param batchTaskHistory 批量任务历史
     * @return 结果
     */
    @Override
    public int updateBatchTaskHistory(BatchTaskHistory batchTaskHistory)
    {
        return batchTaskHistoryMapper.updateBatchTaskHistory(batchTaskHistory);
    }

    /**
     * 批量删除批量任务历史
     * 
     * @param ids 需要删除的批量任务历史主键
     * @return 结果
     */
    @Override
    public int deleteBatchTaskHistoryByIds(Long[] ids)
    {
        return batchTaskHistoryMapper.deleteBatchTaskHistoryByIds(ids);
    }

    /**
     * 删除批量任务历史信息
     * 
     * @param id 批量任务历史主键
     * @return 结果
     */
    @Override
    public int deleteBatchTaskHistoryById(Long id)
    {
        return batchTaskHistoryMapper.deleteBatchTaskHistoryById(id);
    }
}
