package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.LogisticsInfo;

import java.util.List;

/**
 * 物流信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-04-07
 */
public interface LogisticsInfoMapper 
{
    /**
     * 查询物流信息
     * 
     * @param id 物流信息主键
     * @return 物流信息
     */
    public LogisticsInfo selectLogisticsInfoById(Long id);

    /**
     * 查询物流信息列表
     * 
     * @param logisticsInfo 物流信息
     * @return 物流信息集合
     */
    public List<LogisticsInfo> selectLogisticsInfoList(LogisticsInfo logisticsInfo);

    public List<LogisticsInfo> selectLogisticsInfoListNeedDeal(LogisticsInfo logisticsInfo);

    /**
     * 新增物流信息
     * 
     * @param logisticsInfo 物流信息
     * @return 结果
     */
    public int insertLogisticsInfo(LogisticsInfo logisticsInfo);

    /**
     * 批量添加
     * @param list 物流信息们
     * @return
     */
    public int batchInsert(List<LogisticsInfo> list);

    /**
     * 修改物流信息
     * 
     * @param logisticsInfo 物流信息
     * @return 结果
     */
    public int updateLogisticsInfo(LogisticsInfo logisticsInfo);

    /**
     * 删除物流信息
     * 
     * @param id 物流信息主键
     * @return 结果
     */
    public int deleteLogisticsInfoById(Long id);

    /**
     * 批量删除物流信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLogisticsInfoByIds(Long[] ids);

    public int deleteLogisticsInfoByWaybills(List<String> waybills);

    public int updateRedirectNumByWaybills(List<String> waybills);

    public int updateRedirectNumByIds(List<Long> ids);

    public List<LogisticsInfo> selectLogisticsInfoListByPackIdIn(List<Long> ids);

    public List<LogisticsInfo> selectLogisticsInfoListByWaybillIn(List<String> waybills);

    public List<LogisticsInfo> selectLogisticsInfoListWithLInWaybills(List<String> waybills);

}
