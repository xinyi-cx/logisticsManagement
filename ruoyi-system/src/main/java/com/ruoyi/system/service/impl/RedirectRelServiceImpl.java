package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.domain.vo.ExportRedirectRelVo;
import com.ruoyi.system.mapper.*;
import com.ruoyi.system.service.IRedirectRelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * 转寄关联关系Service业务层处理
 *
 *
 * @date 2022-08-12
 */
@Service
public class RedirectRelServiceImpl implements IRedirectRelService {
    @Autowired
    private RedirectRelMapper redirectRelMapper;

    @Autowired
    private ParcelMapper parcelMapper;

    @Autowired
    private LogisticsInfoMapper logisticsInfoMapper;

    @Autowired
    private SysDictDataMapper dictDataMapper;

    @Autowired
    private SequenceMapper sequenceMapper;

    @Autowired
    private DocumentsMapper documentsMapper;

    @Autowired
    private BatchTaskHistoryMapper batchTaskHistoryMapper;

    /**
     * 查询转寄关联关系
     *
     * @param id 转寄关联关系主键
     * @return 转寄关联关系
     */
    @Override
    public RedirectRel selectRedirectRelById(Long id) {
        return redirectRelMapper.selectRedirectRelById(id);
    }

    @Override
    public RedirectRel selectRedirectRelByNewPackageId(Long newPackageId){
        return redirectRelMapper.selectRedirectRelByNewPackageId(newPackageId);
    }

    /**
     * 查询转寄关联关系列表
     *
     * @param redirectRel 转寄关联关系
     * @return 转寄关联关系
     */
    @Override
    public List<RedirectRel> selectRedirectRelList(RedirectRel redirectRel) {
        return redirectRelMapper.selectRedirectRelList(redirectRel);
    }

    /**
     * 新增转寄关联关系
     *
     * @param redirectRel 转寄关联关系
     * @return 结果
     */
    @Override
    public int insertRedirectRel(RedirectRel redirectRel) {
        return redirectRelMapper.insertRedirectRel(redirectRel);
    }

    /**
     * 修改转寄关联关系
     *
     * @param redirectRel 转寄关联关系
     * @return 结果
     */
    @Override
    public int updateRedirectRel(RedirectRel redirectRel) {
        return redirectRelMapper.updateRedirectRel(redirectRel);
    }

    /**
     * 批量删除转寄关联关系
     *
     * @param ids 需要删除的转寄关联关系主键
     * @return 结果
     */
    @Override
    public int deleteRedirectRelByIds(Long[] ids) {
        return redirectRelMapper.deleteRedirectRelByIds(ids);
    }

    /**
     * 删除转寄关联关系信息
     *
     * @param id 转寄关联关系主键
     * @return 结果
     */
    @Override
    public int deleteRedirectRelById(Long id) {
        return redirectRelMapper.deleteRedirectRelById(id);
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
    public String importRedirectRel(MultipartFile file, List<RedirectRel> redirectRelList) throws Exception {

        Documents documents = getDocuments(file);
        BatchTaskHistory batchTaskHistory = new BatchTaskHistory();
        batchTaskHistory.setType("转寄关联关系导入");
        batchTaskHistory.setStatus("上传成功");
        batchTaskHistory.setExcelUrl(documents.getId().toString());
        batchTaskHistory.setFileName(documents.getFileName());
        batchTaskHistory.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
        batchTaskHistory.setUpdateUser(SecurityUtils.getLoginUser().getUserId().toString());
        batchTaskHistory.setId(sequenceMapper.selectNextvalByName("bat_task_seq"));

        if (CollectionUtils.isEmpty(redirectRelList)) {
            batchTaskHistory.setStatus("上传失败");
            batchTaskHistoryMapper.insertBatchTaskHistoryWithId(batchTaskHistory);
            return "导入失败";
        }

        List<Parcel> newParcelList = parcelMapper.selectParcelListByReferenceIn(redirectRelList.stream().map(RedirectRel::getNewOrder).collect(Collectors.toList()));
        if (CollectionUtils.isEmpty(newParcelList)) {
            batchTaskHistory.setStatus("上传失败");
            batchTaskHistory.setRemark("导入失败，未发现新物流单号");
            batchTaskHistoryMapper.insertBatchTaskHistoryWithId(batchTaskHistory);
            return "导入失败，未发现新物流单号";
        }
        Map<String, Parcel> orderParcelMap = newParcelList.stream().collect(toMap(Parcel::getReference, Function.identity()));

        List<RedirectRel> insertRedirectRelList = new ArrayList<>();
        boolean errFlag = false;
        List<String> errorList = new ArrayList<>();
        for (RedirectRel redirectRel : redirectRelList) {
            if (orderParcelMap.containsKey(redirectRel.getNewOrder())) {
                redirectRel.setCountryCode("PL");
                redirectRel.setNewWaybill(orderParcelMap.get(redirectRel.getNewOrder()).getWaybill());
                redirectRel.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
                redirectRel.setUpdateUser(SecurityUtils.getLoginUser().getUserId().toString());
                insertRedirectRelList.add(redirectRel);
            } else {
                errorList.add(redirectRel.getNewOrder());
                errFlag = true;
            }
        }

        int result = redirectRelMapper.batchInsert(insertRedirectRelList);
        if (result > 0) {
            // 导入成功后发送邮件？
            StringBuilder sb = new StringBuilder();
            sb.append("导入成功，成功")
                    .append(result)
                    .append("条");
            if (errFlag) {
                sb.append("导入失败原因：未找到新物流单号：").append(String.join("，", errorList));
            }
            batchTaskHistory.setRemark(sb.toString());
            batchTaskHistoryMapper.insertBatchTaskHistoryWithId(batchTaskHistory);
            return sb.toString();
        }
        batchTaskHistory.setStatus("上传失败");
        batchTaskHistoryMapper.insertBatchTaskHistoryWithId(batchTaskHistory);
        return "导入失败";
    }

    /**
     * 导出转寄物流信息
     * @param waybills
     * @return
     */
    @Override
    public List<ExportRedirectRelVo> exportRedirectRelVo(List<String> waybills) {
        if (CollectionUtils.isEmpty(waybills)) {
            return new ArrayList<>();
        }
        List<RedirectRel> redirectRelList = redirectRelMapper.selectByNewWaybillIn(waybills);
//        List<String> waybills = redirectRelList.stream().map(RedirectRel::getNewWaybill).filter(StringUtils::isNotEmpty).collect(Collectors.toList());
        List<LogisticsInfo> logisticsInfos = logisticsInfoMapper.selectLogisticsInfoListByWaybillIn(waybills);
        Map<String, List<LogisticsInfo>> waybillListMap = logisticsInfos.stream().collect(groupingBy(LogisticsInfo::getWaybill));
        List<SysDictData> sysDictData = dictDataMapper.selectDictDataByType("sys_waybill");
        Map<String, String> dict = sysDictData.stream().collect(toMap(SysDictData::getDictValue, SysDictData::getDictLabel));

        List<ExportRedirectRelVo> returnList = new ArrayList<>();
        for (RedirectRel redirectRel : redirectRelList) {
            String waybill = redirectRel.getNewWaybill();
            ExportRedirectRelVo exportRedirectRelVo = new ExportRedirectRelVo();
            BeanUtils.copyProperties(redirectRel, exportRedirectRelVo);
            if (waybillListMap.containsKey(waybill)) {
                List<LogisticsInfo> logisticsInfos1 = waybillListMap.get(waybill);
                LogisticsInfo zjLogisticsInfo = logisticsInfos1.stream().sorted(Comparator.comparing(LogisticsInfo::getLastTime).reversed()).collect(toList()).get(0);
                exportRedirectRelVo.setLastTime(zjLogisticsInfo.getLastTime());
                exportRedirectRelVo.setLastMsg(zjLogisticsInfo.getLastMsg());
                exportRedirectRelVo.setStatus(dict.get(zjLogisticsInfo.getStatus()));
            } else {
                exportRedirectRelVo.setLastMsg("为查询到物流信息");
            }
            returnList.add(exportRedirectRelVo);
        }
        return returnList;
    }

    @Override
    public List<ExportRedirectRelVo> exportWithZj(List<LogisticsInfo> logisticsInfos){
        if (CollectionUtils.isEmpty(logisticsInfos)){
            return new ArrayList<>();
        }
        return exportRedirectRelVo(logisticsInfos.stream().map(LogisticsInfo::getWaybill).collect(toList()));
    }

}
