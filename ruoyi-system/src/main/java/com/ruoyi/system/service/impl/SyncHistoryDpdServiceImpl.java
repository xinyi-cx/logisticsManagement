package com.ruoyi.system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.common.utils.file.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SyncHistoryDpdMapper;
import com.ruoyi.system.domain.SyncHistoryDpd;
import com.ruoyi.system.service.ISyncHistoryDpdService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 同步历史记录Service业务层处理
 *
 * @author ruoyi
 * @date 2023-08-02
 */
@Service
public class SyncHistoryDpdServiceImpl implements ISyncHistoryDpdService {
    @Autowired
    private SyncHistoryDpdMapper syncHistoryDpdMapper;

    /**
     * 查询同步历史记录
     *
     * @param id 同步历史记录主键
     * @return 同步历史记录
     */
    @Override
    public SyncHistoryDpd selectSyncHistoryDpdById(Long id) {
        return syncHistoryDpdMapper.selectSyncHistoryDpdById(id);
    }

    /**
     * 查询同步历史记录列表
     *
     * @param syncHistoryDpd 同步历史记录
     * @return 同步历史记录
     */
    @Override
    public List<SyncHistoryDpd> selectSyncHistoryDpdList(SyncHistoryDpd syncHistoryDpd) {
        return syncHistoryDpdMapper.selectSyncHistoryDpdList(syncHistoryDpd);
    }

    /**
     * 新增同步历史记录
     *
     * @param syncHistoryDpd 同步历史记录
     * @return 结果
     */
    @Override
    public int insertSyncHistoryDpd(SyncHistoryDpd syncHistoryDpd) {
        return syncHistoryDpdMapper.insertSyncHistoryDpd(syncHistoryDpd);
    }

    /**
     * 修改同步历史记录
     *
     * @param syncHistoryDpd 同步历史记录
     * @return 结果
     */
    @Override
    public int updateSyncHistoryDpd(SyncHistoryDpd syncHistoryDpd) {
        return syncHistoryDpdMapper.updateSyncHistoryDpd(syncHistoryDpd);
    }

    /**
     * 批量删除同步历史记录
     *
     * @param ids 需要删除的同步历史记录主键
     * @return 结果
     */
    @Override
    public int deleteSyncHistoryDpdByIds(Long[] ids) {
        return syncHistoryDpdMapper.deleteSyncHistoryDpdByIds(ids);
    }

    /**
     * 删除同步历史记录信息
     *
     * @param id 同步历史记录主键
     * @return 结果
     */
    @Override
    public int deleteSyncHistoryDpdById(Long id) {
        return syncHistoryDpdMapper.deleteSyncHistoryDpdById(id);
    }


    private void tttt() {
        //获取表信息，获取所有已经执行过的文件
        // 获取所有文件  根据已经执行过的文件获取需要执行的文件
        // 生成执行记录 一个文件名字一条记录
        // 处理文件信息  try catch catch信息也需要存数据库
        // 删除文件
        List<SyncHistoryDpd> syncHistoryDpds = syncHistoryDpdMapper.selectNoNeedSyncHistoryDpdList();
        List<String> fileNames = syncHistoryDpds.stream().map(SyncHistoryDpd::getFileName).collect(Collectors.toList());
        List<MultipartFile> files = FileUtils.getMultipartFilesByPath("");





    }


}
