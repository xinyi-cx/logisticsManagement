package com.ruoyi.system.service;

import com.ruoyi.system.domain.mb.MbReturnDto;

import java.util.List;

/**
 * 马帮订单信息Service接口
 * 
 * @author ruoyi
 * @date 2022-05-16
 */
public interface IMbReturnDtoService 
{
    /**
     * 查询马帮订单信息
     * 
     * @param code 马帮订单信息主键
     * @return 马帮订单信息
     */
    public MbReturnDto selectMbReturnDtoByCode(String code);

    /**
     * 查询马帮订单信息列表
     * 
     * @param mbReturnDto 马帮订单信息
     * @return 马帮订单信息集合
     */
    public List<MbReturnDto> selectMbReturnDtoList(MbReturnDto mbReturnDto);

    /**
     * 批量删除马帮订单信息
     * 
     * @param codes 需要删除的马帮订单信息主键集合
     * @return 结果
     */
    public int deleteMbReturnDtoByCodes(String[] codes);

}
