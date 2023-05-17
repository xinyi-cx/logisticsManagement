package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.BatchTaskHistory;
import com.ruoyi.system.domain.CodReport;
import com.ruoyi.system.domain.Documents;
import com.ruoyi.system.domain.vo.ImportCodReportVo;
import com.ruoyi.system.mapper.BatchTaskHistoryMapper;
import com.ruoyi.system.mapper.CodReportMapper;
import com.ruoyi.system.mapper.DocumentsMapper;
import com.ruoyi.system.mapper.SequenceMapper;
import com.ruoyi.system.service.ICodReportService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * COD报告Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-05-02
 */
@Service
public class CodReportServiceImpl implements ICodReportService 
{
    @Autowired
    private CodReportMapper codReportMapper;

    @Autowired
    private SequenceMapper sequenceMapper;

    @Autowired
    private DocumentsMapper documentsMapper;

    @Autowired
    private BatchTaskHistoryMapper batchTaskHistoryMapper;

    /**
     * 查询COD报告
     * 
     * @param id COD报告主键
     * @return COD报告
     */
    @Override
    public CodReport selectCodReportById(Long id)
    {
        return codReportMapper.selectCodReportById(id);
    }

    /**
     * 查询COD报告列表
     * 
     * @param codReport COD报告
     * @return COD报告
     */
    @Override
    public List<CodReport> selectCodReportList(CodReport codReport)
    {
        return codReportMapper.selectCodReportList(codReport);
    }

    /**
     * 新增COD报告
     * 
     * @param codReport COD报告
     * @return 结果
     */
    @Override
    public int insertCodReport(CodReport codReport)
    {
        codReport.setCreateTime(DateUtils.getNowDate());
        return codReportMapper.insertCodReport(codReport);
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
    @Transactional(rollbackFor = Exception.class)
    public String importCodReport(MultipartFile file, List<ImportCodReportVo> importCodReportVos) throws Exception {
//        if (CollectionUtils.isNotEmpty(importCodReportVos) && importCodReportVos.size() > 300) {
//            return "单次导入最多300条";
//        }
//        String fileName = file.getOriginalFilename();
//        List<String> listFile = Arrays.asList(fileName.split(" "));
//        if (CollectionUtils.isEmpty(listFile) || listFile.size()<6 || !(
//                "resend".equalsIgnoreCase(listFile.get(1)) || "local".equalsIgnoreCase(listFile.get(1)) || "original".equalsIgnoreCase(listFile.get(1))) ){
//            return "文件命名错误";
//        }
        Documents documents = getDocuments(file);
        BatchTaskHistory batchTaskHistory = new BatchTaskHistory();
        batchTaskHistory.setType("COD报告导入");
        batchTaskHistory.setStatus("上传成功");
        batchTaskHistory.setExcelUrl(documents.getId().toString());
        batchTaskHistory.setFileName(documents.getDisplayName());
        batchTaskHistory.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
        batchTaskHistory.setUpdateUser(SecurityUtils.getLoginUser().getUserId().toString());
        batchTaskHistory.setId(sequenceMapper.selectNextvalByName("bat_task_seq"));

        List<CodReport> all = codReportMapper.selectCodReportList(new CodReport());
        List<String> exWaybills = all.stream().map(CodReport::getWaybill).collect(Collectors.toList());

        List<CodReport> codReportList = new ArrayList<>();
        for (ImportCodReportVo importCodReportVo : importCodReportVos) {
            CodReport codReport = new CodReport();
            BeanUtils.copyProperties(importCodReportVo, codReport);

            codReport.setDocumentFileId(documents.getId());
            codReport.setBatchId(batchTaskHistory.getId());
            Date goodsEffectiveDeliveryDate = new Date(codReport.getGoodsEffectiveDeliveryDate());
            Date waybillPostingDate = new Date(codReport.getWaybillPostingDate());
            codReport.setGoodsEffectiveDeliveryDate(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, goodsEffectiveDeliveryDate));
            codReport.setWaybillPostingDate(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, waybillPostingDate));

            List<String> waybills = Arrays.asList(importCodReportVo.getWaybill().replace(" ","").split(","));
            String waybill = waybills.get(waybills.size()-1);
            if (CollectionUtils.isNotEmpty(exWaybills) && exWaybills.contains(waybill)){
                continue;
            }
            codReport.setWaybill(waybill);
            codReport.setCreateBy(SecurityUtils.getLoginUser().getUserId().toString());
            codReport.setUpdateBy(SecurityUtils.getLoginUser().getUserId().toString());
            codReportList.add(codReport);
        }
        try {
            StringBuilder returnStrBuf = new StringBuilder();
            if (CollectionUtils.isNotEmpty(codReportList)){
                codReportMapper.batchInsert(codReportList);
                batchTaskHistory.setSuccessNum(codReportList.size());
            }
            returnStrBuf.append("报告导入成功，成功")
                    .append(batchTaskHistory.getSuccessNum()).append("条。\n");
            return returnStrBuf.toString();
        }catch (Exception e){
            batchTaskHistory.setStatus("上传失败");
            e.printStackTrace();
            throw new Exception("上传失败");
        }finally {
            batchTaskHistoryMapper.insertBatchTaskHistoryWithId(batchTaskHistory);
        }
    }

    /**
     * 修改COD报告
     * 
     * @param codReport COD报告
     * @return 结果
     */
    @Override
    public int updateCodReport(CodReport codReport)
    {
        codReport.setUpdateTime(DateUtils.getNowDate());
        return codReportMapper.updateCodReport(codReport);
    }

    /**
     * 批量删除COD报告
     * 
     * @param ids 需要删除的COD报告主键
     * @return 结果
     */
    @Override
    public int deleteCodReportByIds(Long[] ids)
    {
        return codReportMapper.deleteCodReportByIds(ids);
    }

    /**
     * 删除COD报告信息
     * 
     * @param id COD报告主键
     * @return 结果
     */
    @Override
    public int deleteCodReportById(Long id)
    {
        return codReportMapper.deleteCodReportById(id);
    }
}
