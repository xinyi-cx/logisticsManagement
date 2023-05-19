package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Parcel;

import java.util.List;

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

    public List<Parcel> selectParcelListNeedDeal(Parcel parcel);

    public List<Parcel> selectParcelListByPackIdIn(List<Long> ids);

    public List<Parcel> selectParcelListByWaybillIn(List<String> waybills);

    public List<Parcel> selectParcelListByReferenceIn(List<String> references);

    /**
     * 新增包裹
     * 
     * @param parcel 包裹
     * @return 结果
     */
    public int insertParcel(Parcel parcel);

    /**
     * 批量添加
     * @param list
     * @return
     */
    public int batchInsert(List<Parcel> list);

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

    public int deleteParcelByPackIds(Long[] ids);

    public int deleteParcelByPackIdsReal(Long[] ids);

    public int deleteParcelByWaybillssReal(List<String> waybills);

    public int deleteParcelByWlNull();

    public List<Parcel> selectParcelListWithNull();

    /**
     * 批量更新
     * @param list
     * @return
     */
    public int batchUpdate(List<Parcel> list);

    public int syncStatus();

    public int deleteNoSyncRel();

}
