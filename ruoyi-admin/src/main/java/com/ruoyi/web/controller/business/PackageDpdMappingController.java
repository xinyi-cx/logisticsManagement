package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.PackageDpdMapping;
import com.ruoyi.system.service.IPackageDpdMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 面单dpd对应关系Controller
 *
 *
 * @date 2022-07-27
 */
@RestController
@RequestMapping("/system/mapping")
public class PackageDpdMappingController extends BaseController {
    @Autowired
    private IPackageDpdMappingService packageDpdMappingService;

    /**
     * 查询面单dpd对应关系列表
     */
    @PreAuthorize("@ss.hasPermi('system:mapping:list')")
    @GetMapping("/list")
    public TableDataInfo list(PackageDpdMapping packageDpdMapping) {
        startPage();
        List<PackageDpdMapping> list = packageDpdMappingService.selectPackageDpdMappingList(packageDpdMapping);
        return getDataTable(list);
    }

    /**
     * 导出面单dpd对应关系列表
     */
    @PreAuthorize("@ss.hasPermi('system:mapping:export')")
    @Log(title = "面单dpd对应关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PackageDpdMapping packageDpdMapping) {
        List<PackageDpdMapping> list = packageDpdMappingService.selectPackageDpdMappingList(packageDpdMapping);
        ExcelUtil<PackageDpdMapping> util = new ExcelUtil<PackageDpdMapping>(PackageDpdMapping.class);
        util.exportExcel(response, list, "面单dpd对应关系数据");
    }

    /**
     * 获取面单dpd对应关系详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:mapping:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(packageDpdMappingService.selectPackageDpdMappingById(id));
    }

    /**
     * 新增面单dpd对应关系
     */
    @PreAuthorize("@ss.hasPermi('system:mapping:add')")
    @Log(title = "面单dpd对应关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PackageDpdMapping packageDpdMapping) {
        return toAjax(packageDpdMappingService.insertPackageDpdMapping(packageDpdMapping));
    }

    /**
     * 修改面单dpd对应关系
     */
    @PreAuthorize("@ss.hasPermi('system:mapping:edit')")
    @Log(title = "面单dpd对应关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PackageDpdMapping packageDpdMapping) {
        return toAjax(packageDpdMappingService.updatePackageDpdMapping(packageDpdMapping));
    }

    /**
     * 删除面单dpd对应关系
     */
    @PreAuthorize("@ss.hasPermi('system:mapping:remove')")
    @Log(title = "面单dpd对应关系", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(packageDpdMappingService.deletePackageDpdMappingByIds(ids));
    }
}
