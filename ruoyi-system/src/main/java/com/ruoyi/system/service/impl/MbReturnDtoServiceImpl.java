package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.mb.MbReturnDto;
import com.ruoyi.system.mapper.MbReturnDtoMapper;
import com.ruoyi.system.service.IMbReturnDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 马帮订单信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-05-16
 */
@Service
public class MbReturnDtoServiceImpl implements IMbReturnDtoService 
{
    @Autowired
    private MbReturnDtoMapper mbReturnDtoMapper;

    /**
     * 查询马帮订单信息
     * 
     * @param code 马帮订单信息主键
     * @return 马帮订单信息
     */
    @Override
    public MbReturnDto selectMbReturnDtoByCode(String code)
    {
        return mbReturnDtoMapper.selectMbReturnDtoByCode(code);
    }

    /**
     * 查询马帮订单信息列表
     * 
     * @param mbReturnDto 马帮订单信息
     * @return 马帮订单信息
     */
    @Override
    public List<MbReturnDto> selectMbReturnDtoList(MbReturnDto mbReturnDto)
    {
        return mbReturnDtoMapper.selectMbReturnDtoList(mbReturnDto);
    }

    /**
     * 新增马帮订单信息
     * 
     * @param mbReturnDto 马帮订单信息
     * @return 结果
     */
    @Override
    public int insertMbReturnDto(MbReturnDto mbReturnDto)
    {
        mbReturnDto.setCreateTime(DateUtils.getNowDate());
        return mbReturnDtoMapper.insertMbReturnDto(mbReturnDto);
    }

    /**
     * 修改马帮订单信息
     * 
     * @param mbReturnDto 马帮订单信息
     * @return 结果
     */
    @Override
    public int updateMbReturnDto(MbReturnDto mbReturnDto)
    {
        mbReturnDto.setUpdateTime(DateUtils.getNowDate());
        return mbReturnDtoMapper.updateMbReturnDto(mbReturnDto);
    }

    /**
     * 批量删除马帮订单信息
     * 
     * @param codes 需要删除的马帮订单信息主键
     * @return 结果
     */
    @Override
    public int deleteMbReturnDtoByCodes(String[] codes)
    {
        return mbReturnDtoMapper.deleteMbReturnDtoByCodes(codes);
    }

    /**
     * 删除马帮订单信息信息
     * 
     * @param code 马帮订单信息主键
     * @return 结果
     */
    @Override
    public int deleteMbReturnDtoByCode(String code)
    {
        return mbReturnDtoMapper.deleteMbReturnDtoByCode(code);
    }
}
