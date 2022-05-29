package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.MbMsg;

import java.util.List;

/**
 * 马帮信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-05-29
 */
public interface MbMsgMapper 
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
     * 删除马帮信息
     * 
     * @param code 马帮信息主键
     * @return 结果
     */
    public int deleteMbMsgByCode(String code);

    /**
     * 批量删除马帮信息
     * 
     * @param codes 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMbMsgByCodes(String[] codes);
}
