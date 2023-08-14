package com.ruoyi.system.service;

import com.ruoyi.system.domain.SyncHistoryDpd;

import java.util.List;

/**
 * 同步历史记录Service接口
 * 
 *
 * @date 2023-08-02
 */
public interface ISyncHistoryDpdService 
{
    /**
     * 查询同步历史记录
     * 
     * @param id 同步历史记录主键
     * @return 同步历史记录
     */
    public SyncHistoryDpd selectSyncHistoryDpdById(Long id);

    /**
     * 查询同步历史记录列表
     * 
     * @param syncHistoryDpd 同步历史记录
     * @return 同步历史记录集合
     */
    public List<SyncHistoryDpd> selectSyncHistoryDpdList(SyncHistoryDpd syncHistoryDpd);

    /**
     * 新增同步历史记录
     * 
     * @param syncHistoryDpd 同步历史记录
     * @return 结果
     */
    public int insertSyncHistoryDpd(SyncHistoryDpd syncHistoryDpd);

    /**
     * 修改同步历史记录
     * 
     * @param syncHistoryDpd 同步历史记录
     * @return 结果
     */
    public int updateSyncHistoryDpd(SyncHistoryDpd syncHistoryDpd);

    /**
     * 批量删除同步历史记录
     * 
     * @param ids 需要删除的同步历史记录主键集合
     * @return 结果
     */
    public int deleteSyncHistoryDpdByIds(Long[] ids);

    /**
     * 删除同步历史记录信息
     * 
     * @param id 同步历史记录主键
     * @return 结果
     */
    public int deleteSyncHistoryDpdById(Long id);

    public void delSyncFile();

    public void syncDpdFile(String filePath);
}
