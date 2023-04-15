package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.DPDServicesExample.client.DPDServicesXMLClient;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.domain.Package;
import com.ruoyi.system.domain.vo.BatchTaskHistoryVo;
import com.ruoyi.system.dpdservices.DocumentGenerationResponseV1;
import com.ruoyi.system.mapper.*;
import com.ruoyi.system.service.IBatchTaskHistoryService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private LogisticsInfoMapper logisticsInfoMapper;

    @Autowired
    private PackageMapper packageMapper;

    @Autowired
    private ParcelMapper parcelMapper;

    @Autowired
    private ImportLogicContentMapper importLogicContentMapper;

    @Autowired
    private PackRelLocalMapper packRelLocalMapper;

    @Autowired
    private RedirectRelMapper redirectRelMapper;

    @Autowired
    private DocumentsMapper documentsMapper;

    @Autowired
    private DPDServicesXMLClient dpdServicesXMLClient;

    @Autowired
    private SequenceMapper sequenceMapper;

    @Autowired
    private AddressReceiverMapper addressReceiverMapper;

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
        if (!SecurityUtils.isAdmin(SecurityUtils.getLoginUser().getUserId())) {
            batchTaskHistoryVo.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
        }
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
        return batchTaskHistoryMapper.insertBatchTaskHistoryWithId(batchTaskHistory);
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
     * @param hisIds 需要删除的批量任务历史主键
     * @return 结果
     */
    @Override
    public int deleteBatchTaskHistoryByIds(Long[] hisIds)
    {
        List<Package> packages = packageMapper.selectPackageByBatchIdIn(Arrays.asList(hisIds));
        if (CollectionUtils.isEmpty(packages)){

            List<ImportLogicContent> importLogicContents = importLogicContentMapper.selectImportLogicContentByBatIdsIn(Arrays.asList(hisIds));
            List<String> waybills = importLogicContents.stream().map(ImportLogicContent::getNewWaybill).collect(Collectors.toList());
            parcelMapper.deleteParcelByWaybillssReal(waybills);
            importLogicContentMapper.deleteImportLogicContentByIds(
                    importLogicContents.stream().map(ImportLogicContent::getId).collect(Collectors.toList()).toArray(new Long[importLogicContents.size()]));
        }else {
            Long[] ids = packages.stream().map(Package::getId).collect(Collectors.toList()).toArray(new Long[packages.size()]);

            parcelMapper.deleteParcelByPackIdsReal(ids);
            importLogicContentMapper.deleteImportLogicContentByPackIdsReal(ids);
            packageMapper.deletePackageByIdsReal(ids);
            packRelLocalMapper.deletePackRelLocalByOldPackageIds(ids);
            redirectRelMapper.deleteRedirectRelByNewPackageIds(ids);
        }
        return batchTaskHistoryMapper.deleteBatchTaskHistoryByIds(hisIds);
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

    @Override
    public void getPDFById(HttpServletResponse response, Long id) throws IOException {
        BatchTaskHistory batchTaskHistory = batchTaskHistoryMapper.selectBatchTaskHistoryById(id);
        Documents documents = documentsMapper.selectDocumentsBySessionId(batchTaskHistory.getSessionId());
        if (ObjectUtils.isEmpty(documents)) {
            //下载PDF并且存储
            Package param = new Package();
            param.setBatchId(id);
            List<Package> packageList = packageMapper.selectPackageList(param);
            AddressReceiver addressReceiver = addressReceiverMapper.selectAddressReceiverById(packageList.get(0).getReceiverId());
            boolean plFlag = "PL".equalsIgnoreCase(addressReceiver.getCountryCode());
            DocumentGenerationResponseV1 ret = dpdServicesXMLClient.generateSpedLabelsBySessionId(batchTaskHistory.getSessionId(), plFlag);
            Documents documentsInsert = new Documents();
            documentsInsert.setId(sequenceMapper.selectNextvalByName("doc_seq"));
            documentsInsert.setSessionId(batchTaskHistory.getSessionId());
            documentsInsert.setFileData(ret.getDocumentData());
            documentsInsert.setDocumentId(ret.getDocumentId());
            documentsInsert.setExtension("PDF");
            documentsInsert.setContentType("application/pdf");
            documentsInsert.setFileName("file");
            documentsInsert.setDisplayName(batchTaskHistory.getSessionId().toString() + ".pdf");
            documentsInsert.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
            documentsInsert.setUpdateUser(SecurityUtils.getLoginUser().getUserId().toString());
            documentsMapper.insertDocuments(documentsInsert);
            documents = documentsInsert;
        }
        getFileByDocuments(documents, response);
    }

    private void getFileByDocuments(Documents document, HttpServletResponse response) throws IOException {
        InputStream fis = null;
        OutputStream toClient = null;
        try {
            byte[] documentByte = document.getFileData();
            fis = new ByteArrayInputStream(documentByte);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);

            response.setContentType(document.getContentType());
            response.setCharacterEncoding("utf-8");
            toClient = new BufferedOutputStream(response.getOutputStream());
            toClient.write(buffer);
            toClient.flush();
        } finally {
            IOUtils.closeQuietly(fis);
            IOUtils.closeQuietly(toClient);
        }
    }

    @Override
    public List<String> getWaybillsByBatchTaskHistoryId(Long id){
        BatchTaskHistory batchTaskHistory = batchTaskHistoryMapper.selectBatchTaskHistoryById(id);

        Package param = new Package();
        param.setBatchId(batchTaskHistory.getId());
        List<Package> packages = packageMapper.selectPackageList(param);
        if (CollectionUtils.isEmpty(packages)){
            return new ArrayList<>();
        }
        List<Parcel> parcels = parcelMapper.selectParcelListByPackIdIn(packages.stream().map(Package::getId).collect(Collectors.toList()));
        if (CollectionUtils.isEmpty(parcels)){
            return new ArrayList<>();
        }

        return (parcels.stream().map(Parcel::getWaybill).collect(Collectors.toList()));
    }

}
