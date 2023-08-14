package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 sequence
 * 
 *
 * @date 2022-01-10
 */
public class Sequence extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序列名称 */
    private String seqName;

    /** 当前值 */
    @Excel(name = "当前值")
    private Long currentVal;

    /** 跨度 */
    @Excel(name = "跨度")
    private Long incrementVal;

    public void setSeqName(String seqName) 
    {
        this.seqName = seqName;
    }

    public String getSeqName() 
    {
        return seqName;
    }
    public void setCurrentVal(Long currentVal) 
    {
        this.currentVal = currentVal;
    }

    public Long getCurrentVal() 
    {
        return currentVal;
    }
    public void setIncrementVal(Long incrementVal) 
    {
        this.incrementVal = incrementVal;
    }

    public Long getIncrementVal() 
    {
        return incrementVal;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("seqName", getSeqName())
            .append("currentVal", getCurrentVal())
            .append("incrementVal", getIncrementVal())
            .toString();
    }
}
