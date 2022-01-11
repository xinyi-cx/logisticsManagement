package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 批量任务历史对象 batch_task_history
 * 
 * @author ruoyi
 * @date 2022-01-11
 */
public class BatchTaskHistory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    public BatchTaskHistory(){
        this.downloadNum = 0;
        this.failNum = 0;
        this.successNum = 0;
        this.createdTime = new Date();
        this.updatedTime = new Date();
    }

    /** 主键 */
    private Long id;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 成功面单数 */
    @Excel(name = "成功面单数")
    private Integer successNum;

    /** 失败面单数 */
    @Excel(name = "失败面单数")
    private Integer failNum;

    /** 下载次数 */
    @Excel(name = "下载次数")
    private Integer downloadNum;

    /** 原始excel路径 */
    @Excel(name = "原始excel路径")
    private String excelUrl;

    /** excel内容 */
    @Excel(name = "excel内容")
    private String excelContent;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createUser;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updateUser;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedTime;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Integer isDelete;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setSuccessNum(Integer successNum) 
    {
        this.successNum = successNum;
    }

    public Integer getSuccessNum() 
    {
        return successNum;
    }
    public void setFailNum(Integer failNum) 
    {
        this.failNum = failNum;
    }

    public Integer getFailNum() 
    {
        return failNum;
    }
    public void setDownloadNum(Integer downloadNum) 
    {
        this.downloadNum = downloadNum;
    }

    public Integer getDownloadNum() 
    {
        return downloadNum;
    }
    public void setExcelUrl(String excelUrl) 
    {
        this.excelUrl = excelUrl;
    }

    public String getExcelUrl() 
    {
        return excelUrl;
    }
    public void setExcelContent(String excelContent) 
    {
        this.excelContent = excelContent;
    }

    public String getExcelContent() 
    {
        return excelContent;
    }
    public void setCreateUser(String createUser) 
    {
        this.createUser = createUser;
    }

    public String getCreateUser() 
    {
        return createUser;
    }
    public void setUpdateUser(String updateUser) 
    {
        this.updateUser = updateUser;
    }

    public String getUpdateUser() 
    {
        return updateUser;
    }
    public void setCreatedTime(Date createdTime) 
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() 
    {
        return createdTime;
    }
    public void setUpdatedTime(Date updatedTime) 
    {
        this.updatedTime = updatedTime;
    }

    public Date getUpdatedTime() 
    {
        return updatedTime;
    }
    public void setIsDelete(Integer isDelete) 
    {
        this.isDelete = isDelete;
    }

    public Integer getIsDelete() 
    {
        return isDelete;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("type", getType())
            .append("status", getStatus())
            .append("successNum", getSuccessNum())
            .append("failNum", getFailNum())
            .append("downloadNum", getDownloadNum())
            .append("excelUrl", getExcelUrl())
            .append("excelContent", getExcelContent())
            .append("createUser", getCreateUser())
            .append("updateUser", getUpdateUser())
            .append("createdTime", getCreatedTime())
            .append("updatedTime", getUpdatedTime())
            .append("isDelete", getIsDelete())
            .toString();
    }
}
