package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Sequence;

import java.util.List;
import java.util.Map;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2022-01-10
 */
public interface SequenceMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param seqName 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public Sequence selectSequenceBySeqName(String seqName);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param sequence 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Sequence> selectSequenceList(Sequence sequence);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param seqName 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public Long selectNextvalByName(String seqName);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param seqName 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public Long selectCurrvalByName(String seqName);

    public Long selectNextvalWithSizeByName(Map paramMap);

    /**
     * 新增【请填写功能名称】
     * 
     * @param sequence 【请填写功能名称】
     * @return 结果
     */
    public int insertSequence(Sequence sequence);

    /**
     * 修改【请填写功能名称】
     * 
     * @param sequence 【请填写功能名称】
     * @return 结果
     */
    public int updateSequence(Sequence sequence);

    /**
     * 删除【请填写功能名称】
     * 
     * @param seqName 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteSequenceBySeqName(String seqName);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param seqNames 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSequenceBySeqNames(String[] seqNames);
}
