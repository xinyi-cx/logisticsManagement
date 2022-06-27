package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.DPDServicesExample.client.DPDServicesXMLClient;
import com.ruoyi.system.domain.BatchTaskHistory;
import com.ruoyi.system.domain.Documents;
import com.ruoyi.system.domain.vo.BatchTaskHistoryVo;
import com.ruoyi.system.dpdservices.DocumentGenerationResponseV1;
import com.ruoyi.system.mapper.BatchTaskHistoryMapper;
import com.ruoyi.system.mapper.DocumentsMapper;
import com.ruoyi.system.mapper.SequenceMapper;
import com.ruoyi.system.service.IBatchTaskHistoryService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
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

    @Autowired
    private DocumentsMapper documentsMapper;

    @Autowired
    private DPDServicesXMLClient dpdServicesXMLClient;

    @Autowired
    private SequenceMapper sequenceMapper;

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

    @Override
    public void getPDFById(HttpServletResponse response, Long id) throws IOException {
        BatchTaskHistory batchTaskHistory = batchTaskHistoryMapper.selectBatchTaskHistoryById(id);
        Documents documents = documentsMapper.selectDocumentsBySessionId(batchTaskHistory.getSessionId());
        if (ObjectUtils.isEmpty(documents)) {
            //下载PDF并且存储
            DocumentGenerationResponseV1 ret = dpdServicesXMLClient.generateSpedLabelsBySessionId(batchTaskHistory.getSessionId());
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

}
