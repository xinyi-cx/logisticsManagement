package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.WaybillLRel;

import java.util.List;

/**
 * 物流单号和L关联Mapper接口
 * 
 * @author ruoyi
 * @date 2022-04-15
 */
public interface WaybillLRelMapper 
{
    /**
     * 查询物流单号和L关联
     * 
     * @param waybill 物流单号和L关联主键
     * @return 物流单号和L关联
     */
    public WaybillLRel selectWaybillLRelByWaybill(String waybill);

    /**
     * 查询物流单号和L关联列表
     * 
     * @param waybillLRel 物流单号和L关联
     * @return 物流单号和L关联集合
     */
    public List<WaybillLRel> selectWaybillLRelList(WaybillLRel waybillLRel);

    /**
     * 新增物流单号和L关联
     * 
     * @param waybillLRel 物流单号和L关联
     * @return 结果
     */
    public int insertWaybillLRel(WaybillLRel waybillLRel);

    /**
     * 批量添加
     * @param list
     * @return
     */
    public int batchInsert(List<WaybillLRel> list);

    /**
     * 修改物流单号和L关联
     * 
     * @param waybillLRel 物流单号和L关联
     * @return 结果
     */
    public int updateWaybillLRel(WaybillLRel waybillLRel);

    public int batchUpdateWaybillLRel(List<WaybillLRel> waybillLRels);

    /**
     * 删除物流单号和L关联
     * 
     * @param waybill 物流单号和L关联主键
     * @return 结果
     */
    public int deleteWaybillLRelByWaybill(String waybill);

    /**
     * 批量删除物流单号和L关联
     * 
     * @param waybills 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWaybillLRelByWaybills(String[] waybills);

    public int deleteNoSyncRel();

    public int deleteWaybillLRelByWaybillsRel(List<String> waybills);
}
