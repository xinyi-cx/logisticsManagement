package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.WaybillLRel;

/**
 * 物流单号和L关联Service接口
 * 
 * @author ruoyi
 * @date 2022-04-15
 */
public interface IWaybillLRelService 
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
     * 修改物流单号和L关联
     * 
     * @param waybillLRel 物流单号和L关联
     * @return 结果
     */
    public int updateWaybillLRel(WaybillLRel waybillLRel);

    /**
     * 批量删除物流单号和L关联
     * 
     * @param waybills 需要删除的物流单号和L关联主键集合
     * @return 结果
     */
    public int deleteWaybillLRelByWaybills(String[] waybills);

    /**
     * 删除物流单号和L关联信息
     * 
     * @param waybill 物流单号和L关联主键
     * @return 结果
     */
    public int deleteWaybillLRelByWaybill(String waybill);
}
