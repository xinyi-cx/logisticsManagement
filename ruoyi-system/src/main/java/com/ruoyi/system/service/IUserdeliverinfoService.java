package com.ruoyi.system.service;

import com.ruoyi.system.domain.mb.Userdeliverinfo;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 *
 * @author ruoyi
 * @date 2022-01-02
 */
public interface IUserdeliverinfoService {

    public List<Userdeliverinfo> selectUserdeliverinfoList(Userdeliverinfo userdeliverinfo);

    public int insertUserdeliverinfo(Userdeliverinfo userdeliverinfo);

}
