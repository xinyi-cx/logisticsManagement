package com.ruoyi.web.controller.business;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.system.domain.Package;
import com.ruoyi.system.service.IPackageService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 面单Controller
 * 
 * @author ruoyi
 * @date 2022-01-02
 */
@RestController
@RequestMapping("/system/package")
public class PackageController extends BaseController
{
    @Autowired
    private IPackageService packageService;

    /**
     * 查询面单列表
     */
    @PreAuthorize("@ss.hasPermi('system:package:list')")
    @GetMapping("/list")
    public TableDataInfo list(Package pkg)
    {
        startPage();
        List<Package> list = packageService.selectPackageList(pkg);
        return getDataTable(list);
    }

    /**
     * 导出面单列表
     */
    @PreAuthorize("@ss.hasPermi('system:package:export')")
    @Log(title = "面单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Package pkg)
    {
        List<Package> list = packageService.selectPackageList(pkg);
        ExcelUtil<Package> util = new ExcelUtil<Package>(Package.class);
        util.exportExcel(response, list, "面单数据");
    }

    /**
     * 获取面单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:package:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(packageService.selectPackageById(id));
    }

    /**
     * 新增面单
     */
    @PreAuthorize("@ss.hasPermi('system:package:add')")
    @Log(title = "面单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Package pkg)
    {
        return toAjax(packageService.insertPackage(pkg));
    }

    /**
     * 修改面单
     */
    @PreAuthorize("@ss.hasPermi('system:package:edit')")
    @Log(title = "面单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Package pkg)
    {
        return toAjax(packageService.updatePackage(pkg));
    }

    /**
     * 删除面单
     */
    @PreAuthorize("@ss.hasPermi('system:package:remove')")
    @Log(title = "面单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(packageService.deletePackageByIds(ids));
    }
}
