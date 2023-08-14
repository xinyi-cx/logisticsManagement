package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.MbMsgMapper;
import com.ruoyi.system.domain.MbMsg;
import com.ruoyi.system.service.IMbMsgService;

/**
 * 马帮信息Service业务层处理
 * 
 *
 * @date 2022-05-29
 */
@Service
public class MbMsgServiceImpl implements IMbMsgService 
{
    @Autowired
    private MbMsgMapper mbMsgMapper;

    /**
     * 查询马帮信息
     * 
     * @param code 马帮信息主键
     * @return 马帮信息
     */
    @Override
    public MbMsg selectMbMsgByCode(String code)
    {
        return mbMsgMapper.selectMbMsgByCode(code);
    }

    /**
     * 查询马帮信息列表
     * 
     * @param mbMsg 马帮信息
     * @return 马帮信息
     */
    @Override
    public List<MbMsg> selectMbMsgList(MbMsg mbMsg)
    {
        return mbMsgMapper.selectMbMsgList(mbMsg);
    }

    /**
     * 新增马帮信息
     * 
     * @param mbMsg 马帮信息
     * @return 结果
     */
    @Override
    public int insertMbMsg(MbMsg mbMsg)
    {
        mbMsg.setCreateTime(DateUtils.getNowDate());
        return mbMsgMapper.insertMbMsg(mbMsg);
    }

    /**
     * 修改马帮信息
     * 
     * @param mbMsg 马帮信息
     * @return 结果
     */
    @Override
    public int updateMbMsg(MbMsg mbMsg)
    {
        mbMsg.setUpdateTime(DateUtils.getNowDate());
        return mbMsgMapper.updateMbMsg(mbMsg);
    }

    /**
     * 批量删除马帮信息
     * 
     * @param codes 需要删除的马帮信息主键
     * @return 结果
     */
    @Override
    public int deleteMbMsgByCodes(String[] codes)
    {
        return mbMsgMapper.deleteMbMsgByCodes(codes);
    }

    /**
     * 删除马帮信息信息
     * 
     * @param code 马帮信息主键
     * @return 结果
     */
    @Override
    public int deleteMbMsgByCode(String code)
    {
        return mbMsgMapper.deleteMbMsgByCode(code);
    }
}
