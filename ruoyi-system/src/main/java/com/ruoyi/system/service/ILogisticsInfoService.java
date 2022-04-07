package com.ruoyi.system.service;

import com.ruoyi.system.domain.LogisticsInfo;

import java.util.List;

/**
 * 物流信息Service接口
 * 
 * @author ruoyi
 * @date 2022-04-07
 */
public interface ILogisticsInfoService 
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

    /**
     * 新增物流信息
     * 
     * @param logisticsInfo 物流信息
     * @return 结果
     */
    public int insertLogisticsInfo(LogisticsInfo logisticsInfo);

    public int batchInsert(List<LogisticsInfo> logisticsInfos);

    /**
     * 修改物流信息
     * 
     * @param logisticsInfo 物流信息
     * @return 结果
     */
    public int updateLogisticsInfo(LogisticsInfo logisticsInfo);

    /**
     * 批量删除物流信息
     * 
     * @param ids 需要删除的物流信息主键集合
     * @return 结果
     */
    public int deleteLogisticsInfoByIds(Long[] ids);

    /**
     * 删除物流信息信息
     * 
     * @param id 物流信息主键
     * @return 结果
     */
    public int deleteLogisticsInfoById(Long id);
}
