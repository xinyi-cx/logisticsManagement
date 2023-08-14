package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.DpdMsg;
import com.ruoyi.system.mapper.DpdMsgMapper;
import com.ruoyi.system.service.IDpdMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DPD返回值Service业务层处理
 * 
 *
 * @date 2022-06-23
 */
@Service
public class DpdMsgServiceImpl implements IDpdMsgService 
{
    @Autowired
    private DpdMsgMapper dpdMsgMapper;

    /**
     * 查询DPD返回值
     * 
     * @param id DPD返回值主键
     * @return DPD返回值
     */
    @Override
    public DpdMsg selectDpdMsgById(Long id)
    {
        return dpdMsgMapper.selectDpdMsgById(id);
    }

    /**
     * 查询DPD返回值列表
     * 
     * @param dpdMsg DPD返回值
     * @return DPD返回值
     */
    @Override
    public List<DpdMsg> selectDpdMsgList(DpdMsg dpdMsg)
    {
        return dpdMsgMapper.selectDpdMsgList(dpdMsg);
    }

    /**
     * 批量删除DPD返回值
     * 
     * @param ids 需要删除的DPD返回值主键
     * @return 结果
     */
    @Override
    public int deleteDpdMsgByIds(Long[] ids)
    {
        return dpdMsgMapper.deleteDpdMsgByIds(ids);
    }

}
