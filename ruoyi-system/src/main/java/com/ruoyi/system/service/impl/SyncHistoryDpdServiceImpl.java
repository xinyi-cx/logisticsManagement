package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.system.domain.BatchTaskHistory;
import com.ruoyi.system.domain.SyncHistoryDpd;
import com.ruoyi.system.domain.busenum.ImportTypeEnum;
import com.ruoyi.system.mapper.SyncHistoryDpdMapper;
import com.ruoyi.system.service.IPackageService;
import com.ruoyi.system.service.ISyncHistoryDpdService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 同步历史记录Service业务层处理
 *
 *
 * @date 2023-08-02
 */
@Service
@Slf4j
public class SyncHistoryDpdServiceImpl implements ISyncHistoryDpdService {
    @Autowired
    private SyncHistoryDpdMapper syncHistoryDpdMapper;

    @Autowired
    private IPackageService packageService;

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

    @Override
    public void delSyncFile(){
        SyncHistoryDpd param = SyncHistoryDpd.builder().syncStatus("1").fileDeleteStatus("0").build();
        List<SyncHistoryDpd> needDelFile = syncHistoryDpdMapper.selectSyncHistoryDpdList(param);

        if (CollectionUtils.isEmpty(needDelFile)){
            return;
        }

        needDelFile.forEach(
                file -> FileUtils.deleteFile(file.getFilePath() + file.getFileName())
        );
        syncHistoryDpdMapper.updateSyncHistoryDpdToDel(needDelFile.stream().map(SyncHistoryDpd::getId).collect(Collectors.toList()));
    }

    /**
     * //获取表信息，获取所有已经执行过的文件
     *         // 获取所有文件  根据已经执行过的文件获取需要执行的文件
     *         // 生成执行记录 一个文件名字一条记录
     *         // 处理文件信息  try catch catch信息也需要存数据库
     *         // 删除文件
     * @param filePath
     */
    @Override
    public void syncDpdFile(String filePath) {
        log.info("start syncDpdFile");
        List<SyncHistoryDpd> syncHistoryDpds = syncHistoryDpdMapper.selectNoNeedSyncHistoryDpdList();
        List<String> fileNames = syncHistoryDpds.stream().map(SyncHistoryDpd::getFileName).collect(Collectors.toList());
        List<MultipartFile> files = FileUtils.getMultipartFilesByPath(filePath).stream()
                .filter(item -> !fileNames.contains(item.getOriginalFilename())).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(files)){
            log.info("start syncDpdFile empty");
            return;
        }
        ImportTypeEnum importTypeEnum;
        if (filePath.contains("local")){
            importTypeEnum = ImportTypeEnum.local;
        } else if (filePath.contains("ref")){
            importTypeEnum = ImportTypeEnum.ref;
        }else {
            importTypeEnum = ImportTypeEnum.original;
        }
        String country;
        if (filePath.contains("pl")){
            country = "pl";
        }else {
            country = "cz";
        }
        List<SyncHistoryDpd> syncHistoryDpds1 = files.stream().map(
                item -> genSyncHistoryDpd(item, importTypeEnum, country, filePath)
        ).collect(Collectors.toList());
        syncHistoryDpdMapper.batchInsert(syncHistoryDpds1);
        log.info("end syncDpdFile");
    }

    private SyncHistoryDpd genSyncHistoryDpd(MultipartFile file, ImportTypeEnum importTypeEnum, String country, String filePath) {
        String uuid = UUID.randomUUID().toString();
        log.info("start genSyncHistoryDpd uuid: {}", uuid);
        SyncHistoryDpd syncHistoryDpd = SyncHistoryDpd.builder()
                .messageId(uuid)
                .filePath(filePath)
                .fileName(file.getOriginalFilename())
                .fileDeleteStatus("0")
                .build();
        try {
            BatchTaskHistory taskHistory = packageService.importPackage(file, importTypeEnum, country);
            syncHistoryDpd.setBatId(taskHistory.getId());
            log.info("end genSyncHistoryDpd with no error uuid: {}", uuid);
        } catch (Exception e) {
            syncHistoryDpd.setErrorMsg(e.getMessage());
            syncHistoryDpd.setSyncStatus("0");
            log.info("end genSyncHistoryDpd with error uuid: {}", uuid);
        }finally {
            return syncHistoryDpd;
        }
    }


}
