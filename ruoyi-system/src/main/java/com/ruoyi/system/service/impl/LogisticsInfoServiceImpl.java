package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.LogisticsInfo;
import com.ruoyi.system.domain.Sequence;
import com.ruoyi.system.mapper.LogisticsInfoMapper;
import com.ruoyi.system.mapper.SequenceMapper;
import com.ruoyi.system.service.ILogisticsInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

/**
 * 物流信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-07
 */
@Service
public class LogisticsInfoServiceImpl implements ILogisticsInfoService 
{
    @Autowired
    private LogisticsInfoMapper logisticsInfoMapper;

    @Autowired
    private SequenceMapper sequenceMapper;

    /**
     * 查询物流信息
     * 
     * @param id 物流信息主键
     * @return 物流信息
     */
    @Override
    public LogisticsInfo selectLogisticsInfoById(Long id)
    {
        return logisticsInfoMapper.selectLogisticsInfoById(id);
    }

    /**
     * 查询物流信息列表
     * 
     * @param logisticsInfo 物流信息
     * @return 物流信息
     */
    @Override
    public List<LogisticsInfo> selectLogisticsInfoList(LogisticsInfo logisticsInfo)
    {
        if (!SecurityUtils.isAdmin(SecurityUtils.getLoginUser().getUserId())) {
            logisticsInfo.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
        }

        return logisticsInfoMapper.selectLogisticsInfoList(logisticsInfo);
    }

    /**
     * 新增物流信息
     * 
     * @param logisticsInfo 物流信息
     * @return 结果
     */
    @Override
    public int insertLogisticsInfo(LogisticsInfo logisticsInfo)
    {
        logisticsInfo.setId(sequenceMapper.selectNextvalByName("wl_seq"));
        logisticsInfo.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
        logisticsInfo.setUpdateUser(SecurityUtils.getLoginUser().getUserId().toString());
        return logisticsInfoMapper.insertLogisticsInfo(logisticsInfo);
    }

    @Override
    public int batchInsert(List<LogisticsInfo> logisticsInfos)
    {
        Map<String, Sequence> nameMap = getSeqMap(logisticsInfos.size());
        for (LogisticsInfo logisticsInfo : logisticsInfos) {
            logisticsInfo.setId(getId(nameMap, "wl_seq"));
            logisticsInfo.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
            logisticsInfo.setUpdateUser(SecurityUtils.getLoginUser().getUserId().toString());
        }
        return logisticsInfoMapper.batchInsert(logisticsInfos);
    }

    private Long getId(Map<String, Sequence> nameMap, String seqName) {
        Sequence sequence = nameMap.get(seqName);
        Long id = sequence.getCurrentVal() + sequence.getIncrementVal();
        sequence.setCurrentVal(id);
        return id;
    }

    private static String[] SEQ_NAMES = {"wl_seq"};

    private Map<String, Sequence> getSeqMap(int addNum) {
        List<Sequence> sequences = sequenceMapper.selectSequenceList(new Sequence());
        Map<String, Sequence> nameMap = sequences.stream().collect(toMap(Sequence::getSeqName, Function.identity()));
        for (String seqName : SEQ_NAMES) {
            if (nameMap.containsKey(seqName)) {
                Sequence sequence = new Sequence();
                BeanUtils.copyProperties(nameMap.get(seqName), sequence);
                sequence.setCurrentVal(sequence.getCurrentVal() + addNum * sequence.getIncrementVal());
                sequenceMapper.updateSequence(sequence);
            }
        }
        return nameMap;
    }
    /**
     * 修改物流信息
     * 
     * @param logisticsInfo 物流信息
     * @return 结果
     */
    @Override
    public int updateLogisticsInfo(LogisticsInfo logisticsInfo)
    {
        return logisticsInfoMapper.updateLogisticsInfo(logisticsInfo);
    }

    /**
     * 批量删除物流信息
     * 
     * @param ids 需要删除的物流信息主键
     * @return 结果
     */
    @Override
    public int deleteLogisticsInfoByIds(Long[] ids)
    {
        return logisticsInfoMapper.deleteLogisticsInfoByIds(ids);
    }

    /**
     * 删除物流信息信息
     * 
     * @param id 物流信息主键
     * @return 结果
     */
    @Override
    public int deleteLogisticsInfoById(Long id)
    {
        return logisticsInfoMapper.deleteLogisticsInfoById(id);
    }
}
