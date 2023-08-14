package com.ruoyi.system.service;

import com.ruoyi.system.domain.MbMsg;

import java.util.List;

/**
 * 马帮信息Service接口
 * 
 *
 * @date 2022-05-29
 */
public interface IMbMsgService 
{
    /**
     * 查询马帮信息
     * 
     * @param code 马帮信息主键
     * @return 马帮信息
     */
    public MbMsg selectMbMsgByCode(String code);

    /**
     * 查询马帮信息列表
     * 
     * @param mbMsg 马帮信息
     * @return 马帮信息集合
     */
    public List<MbMsg> selectMbMsgList(MbMsg mbMsg);

    /**
     * 新增马帮信息
     * 
     * @param mbMsg 马帮信息
     * @return 结果
     */
    public int insertMbMsg(MbMsg mbMsg);

    /**
     * 修改马帮信息
     * 
     * @param mbMsg 马帮信息
     * @return 结果
     */
    public int updateMbMsg(MbMsg mbMsg);

    /**
     * 批量删除马帮信息
     * 
     * @param codes 需要删除的马帮信息主键集合
     * @return 结果
     */
    public int deleteMbMsgByCodes(String[] codes);

    /**
     * 删除马帮信息信息
     * 
     * @param code 马帮信息主键
     * @return 结果
     */
    public int deleteMbMsgByCode(String code);
}
