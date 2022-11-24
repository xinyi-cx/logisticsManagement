package com.ruoyi.system.service.impl;

import com.ruoyi.common.enums.SysWaybill;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.ImportLogicContent;
import com.ruoyi.system.domain.LogisticsInfo;
import com.ruoyi.system.domain.vo.ExportLogicContentVo;
import com.ruoyi.system.mapper.ImportLogicContentMapper;
import com.ruoyi.system.mapper.LogisticsInfoMapper;
import com.ruoyi.system.service.IImportLogicContentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 导入查询物流Service业务层处理
 *
 * @author ruoyi
 * @date 2022-11-20
 */
@Service
public class ImportLogicContentServiceImpl implements IImportLogicContentService {
    @Autowired
    private ImportLogicContentMapper importLogicContentMapper;

    @Autowired
    private LogisticsInfoMapper logisticsInfoMapper;

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
        return importLogicContentMapper.selectImportLogicContentList(importLogicContent);
    }

    @Override
    public List<ExportLogicContentVo> exportImportLogicContentList(ImportLogicContent importLogicContent) {
        List<ImportLogicContent> importLogicContents = importLogicContentMapper.selectImportLogicContentList(importLogicContent);

        List<LogisticsInfo> logisticsInfos = logisticsInfoMapper.selectLogisticsInfoListByWaybillIn(
                importLogicContents.stream().map(ImportLogicContent::getNewWaybill).collect(Collectors.toList())
        );
        Map<String, LogisticsInfo> waybillBeanMap = logisticsInfos.stream().collect(Collectors.toMap(LogisticsInfo::getWaybill, Function.identity()));
        Map<String, LogisticsInfo> getZjStatus = getZjStatus(logisticsInfos);
        Map<String, LogisticsInfo> getTjStatus = getTjStatus(logisticsInfos);

        List<ExportLogicContentVo> returnList = new ArrayList<>();
        for (ImportLogicContent logicContent : importLogicContents) {
            ExportLogicContentVo exportLogicContentVo = new ExportLogicContentVo();
            BeanUtils.copyProperties(logicContent, exportLogicContentVo);
            String newWaybill = logicContent.getNewWaybill();
            if (waybillBeanMap.containsKey(newWaybill)) {
                LogisticsInfo logisticsInfo = waybillBeanMap.get(newWaybill);
                exportLogicContentVo.setActivedDate(logisticsInfo.getActivationTime());
                exportLogicContentVo.setStatus(logisticsInfo.getStatus());
                exportLogicContentVo.setLastStatusDate(logisticsInfo.getLastTime());
                if (getZjStatus.containsKey(newWaybill)){
                    LogisticsInfo zjLogisticsInfo = getZjStatus.get(newWaybill);
                    exportLogicContentVo.setStatus(zjLogisticsInfo.getStatus());
                    exportLogicContentVo.setLastStatusDate(zjLogisticsInfo.getLastTime());
                    exportLogicContentVo.setNewNumber(zjLogisticsInfo.getWaybill());
                }
                if (getTjStatus.containsKey(newWaybill)){
                    LogisticsInfo tjLogisticsInfo = getZjStatus.get(newWaybill);
                    exportLogicContentVo.setStatus(tjLogisticsInfo.getStatus());
                    exportLogicContentVo.setLastStatusDate(tjLogisticsInfo.getLastTime());
                    exportLogicContentVo.setReturnNumber(tjLogisticsInfo.getWaybill());
                }
                returnList.add(exportLogicContentVo);
            }
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

        return zjHs.stream().collect(Collectors.toMap(LogisticsInfo::getPackageReference, Function.identity()));
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

        return zjHs.stream().collect(Collectors.toMap(LogisticsInfo::getPackageReference, Function.identity()));
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

    /**
     * 批量删除导入查询物流
     *
     * @param ids 需要删除的导入查询物流主键
     * @return 结果
     */
    @Override
    public int deleteImportLogicContentByIds(Long[] ids) {
        return importLogicContentMapper.deleteImportLogicContentByIds(ids);
    }

    /**
     * 删除导入查询物流信息
     *
     * @param id 导入查询物流主键
     * @return 结果
     */
    @Override
    public int deleteImportLogicContentById(Long id) {
        return importLogicContentMapper.deleteImportLogicContentById(id);
    }
}
