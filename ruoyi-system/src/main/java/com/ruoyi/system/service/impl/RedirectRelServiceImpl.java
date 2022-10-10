package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.BatchTaskHistory;
import com.ruoyi.system.domain.Documents;
import com.ruoyi.system.domain.Parcel;
import com.ruoyi.system.domain.RedirectRel;
import com.ruoyi.system.mapper.*;
import com.ruoyi.system.service.IRedirectRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

/**
 * 转寄关联关系Service业务层处理
 *
 * @author ruoyi
 * @date 2022-08-12
 */
@Service
public class RedirectRelServiceImpl implements IRedirectRelService {
    @Autowired
    private RedirectRelMapper redirectRelMapper;

    @Autowired
    private ParcelMapper parcelMapper;

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

}
