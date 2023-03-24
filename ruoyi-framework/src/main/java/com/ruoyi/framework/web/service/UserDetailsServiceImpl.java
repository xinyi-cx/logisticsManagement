package com.ruoyi.framework.web.service;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.UserStatus;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.UserFidRel;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.IUserFidRelService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户验证处理
 *
 * @author ruoyi
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private ISysUserService userService;

    @Autowired
    private IUserFidRelService userFidRelService;

    @Autowired
    private SysPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String val) throws UsernameNotFoundException
    {
        JSONObject jsonObject = JSONObject.parseObject(val);
        //更改为 customerName 登录
        String username = jsonObject.getString("username");
        String country = jsonObject.getString("country");
        UserFidRel userFidRelParam = new UserFidRel();
        userFidRelParam.setCustomerName(username);
        userFidRelParam.setCountry(country);
        userFidRelParam.setStatus("0");
        Long fid = null;
        List<UserFidRel> userFidRels = userFidRelService.selectUserFidRelList(userFidRelParam);
        if (CollectionUtils.isNotEmpty(userFidRels)){
            username = userFidRels.get(0).getUserName();
            fid = userFidRels.get(0).getFid();
        }
        SysUser user = null;
        if (StringUtils.isEmpty(country)){
            user = userService.selectUserByUserName(username);
        }else{
            SysUser paramUser = new SysUser();
            paramUser.setUserName(username);
            paramUser.setCountry(country);
            user = userService.selectUserByUser(paramUser);
        }
        if (StringUtils.isNull(user))
        {
            log.info("登录用户：{} 不存在.", username);
            throw new ServiceException("登录用户：" + username + " 不存在");
        }
        else if (UserStatus.DELETED.getCode().equals(user.getDelFlag()))
        {
            log.info("登录用户：{} 已被删除.", username);
            throw new ServiceException("对不起，您的账号：" + username + " 已被删除");
        }
        else if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            log.info("登录用户：{} 已被停用.", username);
            throw new ServiceException("对不起，您的账号：" + username + " 已停用");
        }

        return createLoginUser(user, fid);
    }

    public UserDetails createLoginUser(SysUser user, Long fid)
    {
        return new LoginUser(user.getUserId(), user.getDeptId(), fid, user, permissionService.getMenuPermission(user));
    }
}
