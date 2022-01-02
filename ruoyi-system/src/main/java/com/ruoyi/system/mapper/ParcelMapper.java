package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Parcel;

/**
 * 包裹Mapper接口
 * 
 * @author ruoyi
 * @date 2022-01-02
 */
public interface ParcelMapper 
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
     * 删除包裹
     * 
     * @param id 包裹主键
     * @return 结果
     */
    public int deleteParcelById(Long id);

    /**
     * 批量删除包裹
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteParcelByIds(Long[] ids);
}
