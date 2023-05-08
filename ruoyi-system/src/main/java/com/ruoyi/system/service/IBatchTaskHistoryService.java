package com.ruoyi.system.service;

import com.ruoyi.system.domain.BatchTaskHistory;
import com.ruoyi.system.domain.vo.BatchTaskHistoryVo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
    public int insertBatchTaskHistory(BatchTaskHistory batchTaskHistory);

    /**
     * 修改批量任务历史
     * 
     * @param batchTaskHistory 批量任务历史
     * @return 结果
     */
    public int updateBatchTaskHistory(BatchTaskHistory batchTaskHistory) throws Exception;

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

    /**
     * 下载pdf
     * @param response
     * @param id
     */
    public void getPDFById(HttpServletResponse response, Long id) throws IOException;

    public List<String> getWaybillsByBatchTaskHistoryId(Long id);

}
