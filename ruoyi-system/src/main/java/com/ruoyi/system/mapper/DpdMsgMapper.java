package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.DpdMsg;

import java.util.List;

/**
 * DPD返回值Mapper接口
 *
 *
 * @date 2022-06-23
 */
public interface DpdMsgMapper {
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
     * 新增DPD返回值
     *
     * @param dpdMsg DPD返回值
     * @return 结果
     */
    public int insertDpdMsg(DpdMsg dpdMsg);

    /**
     * 修改DPD返回值
     *
     * @param dpdMsg DPD返回值
     * @return 结果
     */
    public int updateDpdMsg(DpdMsg dpdMsg);

    /**
     * 删除DPD返回值
     *
     * @param id DPD返回值主键
     * @return 结果
     */
    public int deleteDpdMsgById(Long id);

    /**
     * 批量删除DPD返回值
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDpdMsgByIds(Long[] ids);
}
