package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 马帮信息对象 mb_msg
 * 
 * @author ruoyi
 * @date 2022-05-29
 */
public class MbMsg extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** code */
    @Excel(name = "code")
    private String code;

    /** 返回值 */
    @Excel(name = "返回值")
    private String msgCode;

    /** 信息 */
    @Excel(name = "信息")
    private String msg;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Integer isDelete;

    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setMsgCode(String msgCode) 
    {
        this.msgCode = msgCode;
    }

    public String getMsgCode() 
    {
        return msgCode;
    }
    public void setMsg(String msg) 
    {
        this.msg = msg;
    }

    public String getMsg() 
    {
        return msg;
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
            .append("code", getCode())
            .append("msgCode", getMsgCode())
            .append("msg", getMsg())
            .append("isDelete", getIsDelete())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
