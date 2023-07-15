package com.ruoyi.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.system.domain.mb.Userdeliverinfo;
import com.ruoyi.system.service.IUserdeliverinfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserdeliverinfoServicempl implements IUserdeliverinfoService {

    @Value("${sys.url}")
    private String url;

    private String insertUserDeliverInfo(Userdeliverinfo userdeliverinfo) {
        String res = HttpUtils.sendPostWithBody(url + "/api/userdeliverinfocommit", userdeliverinfo);
        StringBuilder sb = new StringBuilder();
        JSONObject jsonObject = JSON.parseObject(res);
        return res;
    }

    private List<Userdeliverinfo> getUserDeliverInfo(Userdeliverinfo userdeliverinfo) {
        String res = HttpUtils.sendGet(url + "/api/userdeliverinfolist", getParamStr(userdeliverinfo.getBegintime(), userdeliverinfo.getEndtime()));
        return JSON.parseArray(res, Userdeliverinfo.class);
    }

    private String getParamStr(String begintime,
                               String endtime) {
        String nowStr = DateUtils.getDate();
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotEmpty(begintime)) {
            sb.append("begintime=").append(begintime);
        } else {
            sb.append("begintime=").append(nowStr);
        }
        if (StringUtils.isNotEmpty(endtime)) {
            sb.append("&endtime=").append(endtime);
        } else {
            sb.append("&endtime=").append(nowStr);
        }

        log.info("getParamStr ++++++ " + sb.toString());
        return sb.toString();
    }

    @Override
    public List<Userdeliverinfo> selectUserdeliverinfoList(Userdeliverinfo userdeliverinfo) {
        return getUserDeliverInfo(userdeliverinfo);
    }

    @Override
    public int insertUserdeliverinfo(Userdeliverinfo userdeliverinfo) {
        insertUserDeliverInfo(userdeliverinfo);
        return 1;
    }

}
