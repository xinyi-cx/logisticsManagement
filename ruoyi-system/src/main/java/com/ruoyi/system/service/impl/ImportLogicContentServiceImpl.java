package com.ruoyi.system.service.impl;

import com.ruoyi.common.enums.SysWaybill;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.BatchTaskHistory;
import com.ruoyi.system.domain.Documents;
import com.ruoyi.system.domain.ImportLogicContent;
import com.ruoyi.system.domain.LogisticsInfo;
import com.ruoyi.system.domain.Parcel;
import com.ruoyi.system.domain.vo.ExportLogicContentCODVo;
import com.ruoyi.system.domain.vo.ExportLogicContentVo;
import com.ruoyi.system.mapper.*;
import com.ruoyi.system.service.IImportLogicContentService;
import com.ruoyi.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 导入查询物流Service业务层处理
 *
 *
 * @date 2022-11-20
 */
@Service
@Slf4j
public class ImportLogicContentServiceImpl implements IImportLogicContentService {
    @Autowired
    private ImportLogicContentMapper importLogicContentMapper;

    @Autowired
    private WaybillLRelMapper waybillLRelMapper;

    @Autowired
    private PackageMapper packageMapper;

    @Autowired
    private MbPackageMapper mbPackageMapper;

    @Autowired
    private MbParcelMapper mbParcelMapper;

    @Autowired
    private MbImportLogicContentMapper mbImportLogicContentMapper;

    @Autowired
    private PackRelLocalMapper packRelLocalMapper;

    @Autowired
    private RedirectRelMapper redirectRelMapper;

    @Autowired
    private ParcelMapper parcelMapper;

    @Autowired
    private MbReturnDtoMapper mbReturnDtoMapper;

    @Autowired
    private LogisticsInfoMapper logisticsInfoMapper;

    @Autowired
    private BatchTaskHistoryMapper batchTaskHistoryMapper;

    @Autowired
    private SequenceMapper sequenceMapper;

    @Autowired
    private DocumentsMapper documentsMapper;

    @Value("#{'${allJurisdictionUserNames}'.empty ? null : '${allJurisdictionUserNames}'.split(',')}")
    private List<String> allJurisdictionUserNames;

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 查询导入查询物流
     *
     * @param id 导入查询物流主键
     * @return 导入查询物流
     */
    @Override
    public ImportLogicContent selectImportLogicContentById(Long id) {
        return importLogicContentMapper.selectImportLogicContentById(id);
    }

    /**
     * 查询导入查询物流列表
     *
     * @param importLogicContent 导入查询物流
     * @return 导入查询物流
     */
    @Override
    public List<ImportLogicContent> selectImportLogicContentList(ImportLogicContent importLogicContent) {
        //对账
        if (!(SecurityUtils.isAdmin(SecurityUtils.getLoginUser().getUserId())
                || allJurisdictionUserNames.contains(SecurityUtils.getLoginUser().getUsername()))) {
            importLogicContent.setCreateBy(SecurityUtils.getLoginUser().getUserId().toString());
        }
        Map<Long, String> idFileNameMap = new HashMap<>();
        dealParam(importLogicContent, idFileNameMap);
        return importLogicContentMapper.selectImportLogicContentList(importLogicContent);
    }

    @Override
    public List<ImportLogicContent> selectImportLogicContentListNoReport(ImportLogicContent importLogicContent) {
        //对账
        if (!(SecurityUtils.isAdmin(SecurityUtils.getLoginUser().getUserId())
                || allJurisdictionUserNames.contains(SecurityUtils.getLoginUser().getUsername()))) {
            importLogicContent.setCreateBy(SecurityUtils.getLoginUser().getUserId().toString());
        }
        Map<Long, String> idFileNameMap = new HashMap<>();
        dealParam(importLogicContent, idFileNameMap);
        return importLogicContentMapper.selectImportLogicContentListNoReport(importLogicContent);
    }

    @Override
    public List<ImportLogicContent> selectImportLogicContentListReport(ImportLogicContent importLogicContent) {
        //对账
        if (!(SecurityUtils.isAdmin(SecurityUtils.getLoginUser().getUserId())
                || allJurisdictionUserNames.contains(SecurityUtils.getLoginUser().getUsername()))) {
            importLogicContent.setCreateBy(SecurityUtils.getLoginUser().getUserId().toString());
        }
        Map<Long, String> idFileNameMap = new HashMap<>();
        dealParam(importLogicContent, idFileNameMap);
        return importLogicContentMapper.selectImportLogicContentListReport(importLogicContent);
    }

    private void dealParam(ImportLogicContent importLogicContent, Map<Long, String> idFileNameMap){
        String userAuth = sysUserService.getLogisticsAuthority();
        if  ("local".equals(userAuth)){
            importLogicContent.setCreateBy(null);
            importLogicContent.setImportType("本地");
        } else if ("zj".equals(userAuth)){
            importLogicContent.setCreateBy(null);
            importLogicContent.setImportType("转寄");
        }else if ("zf".equals(userAuth)){
            importLogicContent.setCreateBy(null);
            importLogicContent.setImportType("直发");
        }
        if (StringUtils.isNotEmpty(importLogicContent.getCountry())) {
            importLogicContent.setCountry(importLogicContent.getCountry().toUpperCase());
        }
        if (StringUtils.isNotEmpty(importLogicContent.getClient())) {
            importLogicContent.setClient(importLogicContent.getClient().toUpperCase());
        }
//        if (StringUtils.isNotEmpty(importLogicContent.getFileName())){
//            BatchTaskHistoryVo batchTaskHistory = new BatchTaskHistoryVo();
//            batchTaskHistory.setFileName(importLogicContent.getFileName());
//            List<BatchTaskHistory> batchTaskHistories = batchTaskHistoryMapper.selectBatchTaskHistoryList(batchTaskHistory);
//            if (!CollectionUtils.isEmpty(batchTaskHistories)){
//                idFileNameMap = batchTaskHistories.stream().collect(Collectors.toMap(BatchTaskHistory::getId, BatchTaskHistory::getFileName));
//                importLogicContent.setBatchIds(new ArrayList<>(idFileNameMap.keySet()));
//            }
//        }
    }

    @Override
    public List<ExportLogicContentVo> exportImportLogicContentList(ImportLogicContent importLogicContent) {
        if (!(SecurityUtils.isAdmin(SecurityUtils.getLoginUser().getUserId())
                || allJurisdictionUserNames.contains(SecurityUtils.getLoginUser().getUsername()))) {
            importLogicContent.setCreateBy(SecurityUtils.getLoginUser().getUserId().toString());
        }
        Map<Long, String> idFileNameMap = new HashMap<>();
        dealParam(importLogicContent, idFileNameMap);
        List<ImportLogicContent> importLogicContents = importLogicContentMapper.selectImportLogicContentList(importLogicContent);

//        List<LogisticsInfo> logisticsInfos = logisticsInfoMapper.selectLogisticsInfoListByWaybillIn(
//                importLogicContents.stream().map(ImportLogicContent::getNewWaybill).collect(Collectors.toList())
//        );
//        Map<String, LogisticsInfo> waybillBeanMap = logisticsInfos.stream().collect(Collectors.toMap(LogisticsInfo::getWaybill, Function.identity()));
//        Map<String, LogisticsInfo> getZjStatus = getZjStatus(logisticsInfos);
//        Map<String, LogisticsInfo> getTjStatus = getTjStatus(logisticsInfos);

        List<ExportLogicContentVo> returnList = new ArrayList<>();
        for (ImportLogicContent logicContent : importLogicContents) {
            ExportLogicContentVo exportLogicContentVo = new ExportLogicContentVo();
            BeanUtils.copyProperties(logicContent, exportLogicContentVo);
//            String newWaybill = logicContent.getNewWaybill();
//            if (waybillBeanMap.containsKey(newWaybill)) {
//                LogisticsInfo logisticsInfo = waybillBeanMap.get(newWaybill);
//                exportLogicContentVo.setActivedDate(logisticsInfo.getActivationTime());
//                exportLogicContentVo.setStatus(logisticsInfo.getStatus());
//                exportLogicContentVo.setLastStatusDate(logisticsInfo.getLastTime());
//                if (getZjStatus.containsKey(newWaybill)){
//                    LogisticsInfo zjLogisticsInfo = getZjStatus.get(newWaybill);
//                    exportLogicContentVo.setStatus(zjLogisticsInfo.getStatus());
//                    exportLogicContentVo.setLastStatusDate(zjLogisticsInfo.getLastTime());
//                    exportLogicContentVo.setNewNumber(zjLogisticsInfo.getWaybill());
//                }
//                if (getTjStatus.containsKey(newWaybill)){
//                    LogisticsInfo tjLogisticsInfo = getTjStatus.get(newWaybill);
//                    exportLogicContentVo.setStatus(tjLogisticsInfo.getStatus());
//                    exportLogicContentVo.setLastStatusDate(tjLogisticsInfo.getLastTime());
//                    exportLogicContentVo.setReturnNumber(tjLogisticsInfo.getWaybill());
//                }
//            }
            returnList.add(exportLogicContentVo);
        }
        return returnList;
    }

    @Override
    public List<ExportLogicContentCODVo> exportImportLogicContentListReport(ImportLogicContent importLogicContent) {
        if (!(SecurityUtils.isAdmin(SecurityUtils.getLoginUser().getUserId())
                || allJurisdictionUserNames.contains(SecurityUtils.getLoginUser().getUsername()))) {
            importLogicContent.setCreateBy(SecurityUtils.getLoginUser().getUserId().toString());
        }
        Map<Long, String> idFileNameMap = new HashMap<>();
        dealParam(importLogicContent, idFileNameMap);
        List<ImportLogicContent> importLogicContents = importLogicContentMapper.selectImportLogicContentListReport(importLogicContent);

        return getExportLogicContentCODVos(importLogicContents);
    }

    @Override
    public List<ExportLogicContentCODVo> exportImportLogicContentListNoReport(ImportLogicContent importLogicContent) {
        if (!(SecurityUtils.isAdmin(SecurityUtils.getLoginUser().getUserId())
                || allJurisdictionUserNames.contains(SecurityUtils.getLoginUser().getUsername()))) {
            importLogicContent.setCreateBy(SecurityUtils.getLoginUser().getUserId().toString());
        }
        Map<Long, String> idFileNameMap = new HashMap<>();
        dealParam(importLogicContent, idFileNameMap);
        List<ImportLogicContent> importLogicContents = importLogicContentMapper.selectImportLogicContentListNoReport(importLogicContent);

        return getExportLogicContentCODVos(importLogicContents);
    }

    private List<ExportLogicContentCODVo> getExportLogicContentCODVos(List<ImportLogicContent> importLogicContents) {
        List<ExportLogicContentCODVo> returnList = new ArrayList<>();
        for (ImportLogicContent logicContent : importLogicContents) {
            ExportLogicContentCODVo exportLogicContentVo = new ExportLogicContentCODVo();
            BeanUtils.copyProperties(logicContent, exportLogicContentVo);
            returnList.add(exportLogicContentVo);
        }
        return returnList;
    }

    /**
     * 获取转寄后 状态的对应关系
     *
     * @param logisticsInfos
     * @return
     */
    private Map<String, LogisticsInfo> getZjStatus(List<LogisticsInfo> logisticsInfos) {
        List<LogisticsInfo> zjs = logisticsInfos.stream().filter(
                item -> SysWaybill.ZJ.getCode().equals(item.getStatus()) || SysWaybill.GP.getCode().equals(item.getStatus())).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(zjs)) {
            return new HashMap<>();
        }
        List<LogisticsInfo> zjHs = logisticsInfoMapper.selectLogisticsInfoListWithLInWaybills(
                zjs.stream().map(LogisticsInfo::getWaybill).collect(Collectors.toList())
        );
        if (CollectionUtils.isEmpty(zjHs)) {
            return new HashMap<>();
        }

        return zjHs.stream().collect(Collectors.toMap(LogisticsInfo::getPackageReference, Function.identity(),  (o1, o2) -> o1 ));
    }

    /**
     * 获取退件后 状态的对应关系
     *
     * @param logisticsInfos
     * @return
     */
    private Map<String, LogisticsInfo> getTjStatus(List<LogisticsInfo> logisticsInfos) {
        List<LogisticsInfo> zjs = logisticsInfos.stream().filter(
                item -> SysWaybill.YTJ.getCode().equals(item.getStatus())).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(zjs)) {
            return new HashMap<>();
        }
        List<LogisticsInfo> zjHs = logisticsInfoMapper.selectLogisticsInfoListWithLInWaybills(
                zjs.stream().map(LogisticsInfo::getWaybill).collect(Collectors.toList())
        );
        if (CollectionUtils.isEmpty(zjHs)) {
            return new HashMap<>();
        }

        return zjHs.stream().collect(Collectors.toMap(LogisticsInfo::getPackageReference, Function.identity(),  (o1, o2) -> o1));
    }

    /**
     * 新增导入查询物流
     *
     * @param importLogicContent 导入查询物流
     * @return 结果
     */
    @Override
    public int insertImportLogicContent(ImportLogicContent importLogicContent) {
        importLogicContent.setCreateTime(DateUtils.getNowDate());
        return importLogicContentMapper.insertImportLogicContent(importLogicContent);
    }

    /**
     * 修改导入查询物流
     *
     * @param importLogicContent 导入查询物流
     * @return 结果
     */
    @Override
    public int updateImportLogicContent(ImportLogicContent importLogicContent) {
        importLogicContent.setUpdateTime(DateUtils.getNowDate());
        return importLogicContentMapper.updateImportLogicContent(importLogicContent);
    }

    @Override
    @Transactional
    public int updateStatusByWeb(ImportLogicContent importLogicContent) {
        Parcel parcel = new Parcel();
        parcel.setStatus(importLogicContent.getStatus());
        parcel.setWaybill(importLogicContent.getNewWaybill());
        parcelMapper.updateParcel(parcel);
        return importLogicContentMapper.updateStatusByWeb(importLogicContent);
    }


    /**
     * 批量删除导入查询物流
     *
     * @param ids 需要删除的导入查询物流主键
     * @return 结果
     */
    @Override
    public int deleteImportLogicContentByIds(Long[] ids) {
        List<ImportLogicContent> importLogicContents = importLogicContentMapper.selectImportLogicContentByIds(ids);
        List<Long> packIds = importLogicContents
                .stream().map(ImportLogicContent::getPackId).collect(Collectors.toList());
        Long[] packIdArays = packIds.toArray(new Long[0]);

        parcelMapper.deleteParcelByPackIdsReal(packIdArays);
        waybillLRelMapper.deleteWaybillLRelByWaybillsRel(importLogicContents.stream().map(ImportLogicContent::getNewWaybill).collect(Collectors.toList()));
        packageMapper.deletePackageByIdsReal(packIdArays);
        packRelLocalMapper.deletePackRelLocalByOldPackageIds(packIdArays);
        redirectRelMapper.deleteRedirectRelByNewPackageIds(packIdArays);
        deleteMbReturnDtoByPackageIds(ids, packIdArays);
        log.info("end deleteMbReturnDtoByPackageIds");
        return importLogicContentMapper.deleteImportLogicContentByIds(ids);
    }

    @Async
    int deleteMbReturnDtoByPackageIds(Long[] ids, Long[] packIdArays){
        log.info("start deleteMbReturnDtoByPackageIds");
        mbParcelMapper.deleteParcelByPackIdsReal(packIdArays);
        mbPackageMapper.deletePackageByIdsReal(packIdArays);
        mbImportLogicContentMapper.deleteImportLogicContentByIds(ids);
        return mbReturnDtoMapper.deleteMbReturnDtoByPackageIds(packIdArays);
    }

    /**
     * 批量更新退件信息
     * @param file
     * @param importLogicContents
     * @return
     * @throws Exception
     */
    @Override
    public String importLogicContentForTj(MultipartFile file, List<ImportLogicContent> importLogicContents) throws Exception {
        if (org.apache.commons.collections4.CollectionUtils.isEmpty(importLogicContents)) {
            return "导入数据不能为空";
        }
//        if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(importLogicContents) && importLogicContents.size() > 300) {
//            return "单次导入最多300条";
//        }
        Documents documents = getDocuments(file);
        BatchTaskHistory batchTaskHistory = new BatchTaskHistory();
        batchTaskHistory.setType("退件物流导入");
        batchTaskHistory.setStatus("导入成功");
        batchTaskHistory.setExcelUrl(documents.getId().toString());
        batchTaskHistory.setFileName(documents.getDisplayName());
        batchTaskHistory.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
        batchTaskHistory.setUpdateUser(SecurityUtils.getLoginUser().getUserId().toString());
        batchTaskHistory.setId(sequenceMapper.selectNextvalByName("bat_task_seq"));

        importLogicContentMapper.batchUpdateImportLogicContentByWaybill(importLogicContents);
        batchTaskHistoryMapper.insertBatchTaskHistoryWithId(batchTaskHistory);
        return "更新退件物流信息成功";
    }

    public Documents getDocuments(MultipartFile file) throws IOException {
        Documents documents = new Documents();
        documents.setId(sequenceMapper.selectNextvalByName("doc_seq"));
        documents.setFileData(file.getBytes());
        documents.setFileName(file.getName());
        documents.setContentType(file.getContentType());
        documents.setFileSize(file.getSize());
        documents.setDisplayName(file.getOriginalFilename());
        documents.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
        documents.setUpdateUser(SecurityUtils.getLoginUser().getUserId().toString());
        documentsMapper.insertDocuments(documents);
        return documents;
    }

    @Override
    public void backupImportLogicContent(){

    }

    @Override
    public Map<String, String> getStateStatistics(){
        Map<String, Object> params = new HashMap<>();
        String updateBeginTime = DateUtils.dateTimeNow(DateUtils.YYYY_MM_DD);
        log.info("getStateStatistics updateBeginTime {}", updateBeginTime);
        params.put("updateBeginTime", updateBeginTime);
        ImportLogicContent importLogicContent = new ImportLogicContent();
        importLogicContent.setParams(params);
        List<ImportLogicContent> importLogicContents = importLogicContentMapper.selectImportLogicContentList(importLogicContent);

        StringBuffer content = new StringBuffer();
        Map<String, Long> statusNumMap = importLogicContents.stream().collect(Collectors.groupingBy(ImportLogicContent::getStatus, Collectors.counting()));
        content.append("物流信息状态统计：").append("\r\n").append("统计时间：").append(updateBeginTime).append("\r\n");
        for (SysWaybill sysWaybill : SysWaybill.values()) {
            if (statusNumMap.containsKey(sysWaybill.getCode())){
                content.append("物流状态：")
                        .append(sysWaybill.getInfo())
                        .append(",当日更新数量：")
                        .append(statusNumMap.get(sysWaybill.getCode()))
                        .append(";")
                        .append("\r\n");
            }
        }
        Map<String, String> mailMpa = new HashMap<>();
        mailMpa.put("content", content.toString());
        mailMpa.put("title", "物流状态变更通知");
        return mailMpa;
    }


}
