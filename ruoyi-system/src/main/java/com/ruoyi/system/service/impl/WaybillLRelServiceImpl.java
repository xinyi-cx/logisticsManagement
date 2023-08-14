package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WaybillLRelMapper;
import com.ruoyi.system.domain.WaybillLRel;
import com.ruoyi.system.service.IWaybillLRelService;

/**
 * 物流单号和L关联Service业务层处理
 * 
 *
 * @date 2022-04-15
 */
@Service
public class WaybillLRelServiceImpl implements IWaybillLRelService 
{
    @Autowired
    private WaybillLRelMapper waybillLRelMapper;

    /**
     * 查询物流单号和L关联
     * 
     * @param waybill 物流单号和L关联主键
     * @return 物流单号和L关联
     */
    @Override
    public WaybillLRel selectWaybillLRelByWaybill(String waybill)
    {
        return waybillLRelMapper.selectWaybillLRelByWaybill(waybill);
    }

    /**
     * 查询物流单号和L关联列表
     * 
     * @param waybillLRel 物流单号和L关联
     * @return 物流单号和L关联
     */
    @Override
    public List<WaybillLRel> selectWaybillLRelList(WaybillLRel waybillLRel)
    {
        return waybillLRelMapper.selectWaybillLRelList(waybillLRel);
    }

    /**
     * 新增物流单号和L关联
     * 
     * @param waybillLRel 物流单号和L关联
     * @return 结果
     */
    @Override
    public int insertWaybillLRel(WaybillLRel waybillLRel)
    {
        return waybillLRelMapper.insertWaybillLRel(waybillLRel);
    }

    /**
     * 修改物流单号和L关联
     * 
     * @param waybillLRel 物流单号和L关联
     * @return 结果
     */
    @Override
    public int updateWaybillLRel(WaybillLRel waybillLRel)
    {
        return waybillLRelMapper.updateWaybillLRel(waybillLRel);
    }

    /**
     * 批量删除物流单号和L关联
     * 
     * @param waybills 需要删除的物流单号和L关联主键
     * @return 结果
     */
    @Override
    public int deleteWaybillLRelByWaybills(String[] waybills)
    {
        return waybillLRelMapper.deleteWaybillLRelByWaybills(waybills);
    }

    /**
     * 删除物流单号和L关联信息
     * 
     * @param waybill 物流单号和L关联主键
     * @return 结果
     */
    @Override
    public int deleteWaybillLRelByWaybill(String waybill)
    {
        return waybillLRelMapper.deleteWaybillLRelByWaybill(waybill);
    }
}
