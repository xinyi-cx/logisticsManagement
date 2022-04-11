package com.ruoyi.system.service.impl;

import com.ruoyi.system.DPDServicesExample.client.DPDInfoXMLClient;
import com.ruoyi.system.domain.Parcel;
import com.ruoyi.system.mapper.ParcelMapper;
import com.ruoyi.system.service.IParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 包裹Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-01-02
 */
@Service
public class ParcelServiceImpl implements IParcelService 
{
    @Autowired
    private ParcelMapper parcelMapper;

    @Autowired
    private DPDInfoXMLClient dpdInfoXMLClient;

    /**
     * 查询包裹
     * 
     * @param id 包裹主键
     * @return 包裹
     */
    @Override
    public Parcel selectParcelById(Long id)
    {
        return parcelMapper.selectParcelById(id);
    }

    /**
     * 查询包裹列表
     * 
     * @param parcel 包裹
     * @return 包裹
     */
    @Override
    public List<Parcel> selectParcelList(Parcel parcel)
    {
        return parcelMapper.selectParcelList(parcel);
    }

    /**
     * 新增包裹
     * 
     * @param parcel 包裹
     * @return 结果
     */
    @Override
    public int insertParcel(Parcel parcel)
    {
        return parcelMapper.insertParcel(parcel);
    }

    /**
     * 修改包裹
     * 
     * @param parcel 包裹
     * @return 结果
     */
    @Override
    public int updateParcel(Parcel parcel)
    {
        return parcelMapper.updateParcel(parcel);
    }

    /**
     * 批量删除包裹
     * 
     * @param ids 需要删除的包裹主键
     * @return 结果
     */
    @Override
    public int deleteParcelByIds(Long[] ids)
    {
        return parcelMapper.deleteParcelByIds(ids);
    }

    /**
     * 删除包裹信息
     * 
     * @param id 包裹主键
     * @return 结果
     */
    @Override
    public int deleteParcelById(Long id)
    {
        return parcelMapper.deleteParcelById(id);
    }

    @Override
    public void getParcelMsg(){

    }


}
