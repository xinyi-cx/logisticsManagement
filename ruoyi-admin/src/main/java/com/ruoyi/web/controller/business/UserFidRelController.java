package com.ruoyi.web.controller.business;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.SysRole;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.UserFidRel;
import com.ruoyi.system.service.IUserFidRelService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户fid对应关系Controller
 *
 *
 * @date 2023-02-20
 */
@RestController
@RequestMapping("/system/fidrel")
public class UserFidRelController extends BaseController {
    @Autowired
    private IUserFidRelService userFidRelService;

    /**
     * 查询用户fid对应关系列表
     */
    @PreAuthorize("@ss.hasPermi('system:fidrel:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserFidRel userFidRel) {
        startPage();
        List<UserFidRel> list = userFidRelService.selectUserFidRelList(userFidRel);
        return getDataTable(list);
    }

    /**
     * 导出用户fid对应关系列表
     */
    @PreAuthorize("@ss.hasPermi('system:fidrel:export')")
    @Log(title = "用户fid对应关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserFidRel userFidRel) {
        List<UserFidRel> list = userFidRelService.selectUserFidRelList(userFidRel);
        ExcelUtil<UserFidRel> util = new ExcelUtil<UserFidRel>(UserFidRel.class);
        util.exportExcel(response, list, "用户fid对应关系数据");
    }

    /**
     * 获取用户fid对应关系详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:fidrel:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(userFidRelService.selectUserFidRelById(id));
    }

    /**
     * 新增用户fid对应关系
     */
    @PreAuthorize("@ss.hasPermi('system:fidrel:add')")
    @Log(title = "用户fid对应关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserFidRel userFidRel) {
        return toAjax(userFidRelService.insertUserFidRel(userFidRel));
    }

    /**
     * 修改用户fid对应关系
     */
    @PreAuthorize("@ss.hasPermi('system:fidrel:edit')")
    @Log(title = "用户fid对应关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserFidRel userFidRel) {
        return toAjax(userFidRelService.updateUserFidRel(userFidRel));
    }

    /**
     * 删除用户fid对应关系
     */
    @PreAuthorize("@ss.hasPermi('system:fidrel:remove')")
    @Log(title = "用户fid对应关系", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(userFidRelService.deleteUserFidRelByIds(ids));
    }

    @Log(title = "激活", businessType = BusinessType.UPDATE)
    @PutMapping("/active/{id}")
    public AjaxResult active(@PathVariable("id") Long id) {
        return toAjax(userFidRelService.activeUserFidRelById(id));
    }
}
