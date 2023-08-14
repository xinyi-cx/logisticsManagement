package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 同步历史记录对象 sync_history_dpd
 * 
 *
 * @date 2023-08-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class SyncHistoryDpd extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 批量任务历史Id */
    @Excel(name = "批量任务历史Id")
    private Long batId;

    /** 信息id */
    @Excel(name = "信息id")
    private String messageId;

    /** 文件名称 */
    @Excel(name = "文件名称")
    private String fileName;

    @Excel(name = "文件路径")
    private String filePath;

    /** 同步状态 1-同步成功 0-同步失败 2-部分成功 */
    @Excel(name = "同步状态 1-同步成功 0-同步失败 2-部分成功")
    private String syncStatus;

    /** 文件是否删除 0-未删除 1-已经删除 */
    @Excel(name = "文件是否删除 0-未删除 1-已经删除")
    private String fileDeleteStatus;

    /** 错误信息 */
    @Excel(name = "错误信息")
    private String errorMsg;

    /** 同步时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "同步时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date syncTime;

}
