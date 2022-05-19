package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.UserAuthorization;
import com.ruoyi.system.service.IUserAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户授权信息Controller
 * 
 * @author ruoyi
 * @date 2022-05-18
 */
@RestController
@RequestMapping("/system/authorization")
public class UserAuthorizationController extends BaseController
{
    @Autowired
    private IUserAuthorizationService userAuthorizationService;

    /**
     * 查询用户授权信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:authorization:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserAuthorization userAuthorization)
    {
        startPage();
        List<UserAuthorization> list = userAuthorizationService.selectUserAuthorizationList(userAuthorization);
        return getDataTable(list);
    }

    /**
     * 导出用户授权信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:authorization:export')")
    @Log(title = "用户授权信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserAuthorization userAuthorization)
    {
        List<UserAuthorization> list = userAuthorizationService.selectUserAuthorizationList(userAuthorization);
        ExcelUtil<UserAuthorization> util = new ExcelUtil<UserAuthorization>(UserAuthorization.class);
        util.exportExcel(response, list, "用户授权信息数据");
    }

    /**
     * 获取用户授权信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:authorization:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return AjaxResult.success(userAuthorizationService.selectUserAuthorizationByUserId(userId));
    }

    /**
     * 新增用户授权信息
     */
    @PreAuthorize("@ss.hasPermi('system:authorization:add')")
    @Log(title = "用户授权信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserAuthorization userAuthorization)
    {
        return toAjax(userAuthorizationService.insertUserAuthorization(userAuthorization));
    }

    /**
     * 修改用户授权信息
     */
    @PreAuthorize("@ss.hasPermi('system:authorization:edit')")
    @Log(title = "用户授权信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserAuthorization userAuthorization)
    {
        return toAjax(userAuthorizationService.updateUserAuthorization(userAuthorization));
    }

    /**
     * 删除用户授权信息
     */
    @PreAuthorize("@ss.hasPermi('system:authorization:remove')")
    @Log(title = "用户授权信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(userAuthorizationService.deleteUserAuthorizationByUserIds(userIds));
    }
}
