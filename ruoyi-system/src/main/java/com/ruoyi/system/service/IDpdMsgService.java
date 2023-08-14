package com.ruoyi.system.service;

import com.ruoyi.system.domain.DpdMsg;

import java.util.List;

/**
 * DPD返回值Service接口
 * 
 *
 * @date 2022-06-23
 */
public interface IDpdMsgService 
{
    /**
     * 查询DPD返回值
     * 
     * @param id DPD返回值主键
     * @return DPD返回值
     */
    public DpdMsg selectDpdMsgById(Long id);

    /**
     * 查询DPD返回值列表
     * 
     * @param dpdMsg DPD返回值
     * @return DPD返回值集合
     */
    public List<DpdMsg> selectDpdMsgList(DpdMsg dpdMsg);

    /**
     * 批量删除DPD返回值
     * 
     * @param ids 需要删除的DPD返回值主键集合
     * @return 结果
     */
    public int deleteDpdMsgByIds(Long[] ids);

}
