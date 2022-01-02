package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Parcel;

/**
 * 包裹Service接口
 * 
 * @author ruoyi
 * @date 2022-01-02
 */
public interface IParcelService 
{
    /**
     * 查询包裹
     * 
     * @param id 包裹主键
     * @return 包裹
     */
    public Parcel selectParcelById(Long id);

    /**
     * 查询包裹列表
     * 
     * @param parcel 包裹
     * @return 包裹集合
     */
    public List<Parcel> selectParcelList(Parcel parcel);

    /**
     * 新增包裹
     * 
     * @param parcel 包裹
     * @return 结果
     */
    public int insertParcel(Parcel parcel);

    /**
     * 修改包裹
     * 
     * @param parcel 包裹
     * @return 结果
     */
    public int updateParcel(Parcel parcel);

    /**
     * 批量删除包裹
     * 
     * @param ids 需要删除的包裹主键集合
     * @return 结果
     */
    public int deleteParcelByIds(Long[] ids);

    /**
     * 删除包裹信息
     * 
     * @param id 包裹主键
     * @return 结果
     */
    public int deleteParcelById(Long id);
}
