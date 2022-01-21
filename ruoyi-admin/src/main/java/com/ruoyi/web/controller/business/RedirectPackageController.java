package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.vo.PackageVo;
import com.ruoyi.system.service.IPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 转寄面单原面单关联关系Controller
 *
 * @author ruoyi
 * @date 2022-01-17
 */
@RestController
@RequestMapping("/system/redirect")
public class RedirectPackageController extends BaseController
{
    @Autowired
    private IPackageService packageService;

    /**
     * 查询转寄面单原面单关联关系列表
     */
    @PreAuthorize("@ss.hasPermi('system:package:list')")
    @GetMapping("/list")
    public TableDataInfo list(PackageVo pkg)
    {
        startPage();
        List<PackageVo> list = packageService.selectPackageVoList(pkg);
        return getDataTable(list);
    }

    /**
     * 导出转寄面单原面单关联关系列表
     */
    @PreAuthorize("@ss.hasPermi('system:package:export')")
    @Log(title = "转寄面单原面单关联关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PackageVo pkg)
    {
        List<PackageVo> list = packageService.selectPackageVoList(pkg);
        ExcelUtil<PackageVo> util = new ExcelUtil<PackageVo>(PackageVo.class);
        util.exportExcel(response, list, "转寄面单原面单关联关系数据");
    }

    /**
     * 获取转寄面单原面单关联关系详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:package:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(packageService.selectPackageById(id));
    }

    /**
     * 新增转寄面单原面单关联关系
     */
    @PreAuthorize("@ss.hasPermi('system:package:add')")
    @Log(title = "转寄面单原面单关联关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PackageVo pkg)
    {
        return toAjax(packageService.insertPackage(pkg));
    }

    /**
     * 修改转寄面单原面单关联关系
     */
    @PreAuthorize("@ss.hasPermi('system:package:edit')")
    @Log(title = "转寄面单原面单关联关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PackageVo pkg)
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
