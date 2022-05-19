package com.ruoyi.system.service;

import com.ruoyi.system.domain.mb.MbReceiveDto;

/**
 * 收货人信息Service接口
 * 
 * @author ruoyi
 * @date 2022-01-02
 */
public interface IOuterService
{

    /**
     * 接收马帮的主动通知
     *
     * @param mbReceiveDto
     * @throws Exception
     */
    public void receiveMb(MbReceiveDto mbReceiveDto) throws Exception;

}
