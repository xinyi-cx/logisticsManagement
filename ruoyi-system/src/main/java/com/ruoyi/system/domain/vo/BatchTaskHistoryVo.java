package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 批量任务历史对象 batch_task_history
 * 
 * @author ruoyi
 * @date 2022-01-11
 */
public class BatchTaskHistoryVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long sessionId;

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

    /** 创建人 */
    @Excel(name = "创建人")
    private String createUser;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updateUser;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTimeStart;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTimeEnd;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Integer isDelete;

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Date getCreatedTimeStart() {
        return createdTimeStart;
    }

    public void setCreatedTimeStart(Date createdTimeStart) {
        this.createdTimeStart = createdTimeStart;
    }

    public Date getCreatedTimeEnd() {
        return createdTimeEnd;
    }

    public void setCreatedTimeEnd(Date createdTimeEnd) {
        this.createdTimeEnd = createdTimeEnd;
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
    public void setIsDelete(Integer isDelete)
    {
        this.isDelete = isDelete;
    }

    public Integer getIsDelete() 
    {
        return isDelete;
    }

}
