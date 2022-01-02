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
import com.ruoyi.system.domain.PackagesGenerationResponse;
import com.ruoyi.system.service.IPackagesGenerationResponseService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 面单返回值Controller
 * 
 * @author ruoyi
 * @date 2022-01-02
 */
@RestController
@RequestMapping("/system/response")
public class PackagesGenerationResponseController extends BaseController
{
    @Autowired
    private IPackagesGenerationResponseService packagesGenerationResponseService;

    /**
     * 查询面单返回值列表
     */
    @PreAuthorize("@ss.hasPermi('system:response:list')")
    @GetMapping("/list")
    public TableDataInfo list(PackagesGenerationResponse packagesGenerationResponse)
    {
        startPage();
        List<PackagesGenerationResponse> list = packagesGenerationResponseService.selectPackagesGenerationResponseList(packagesGenerationResponse);
        return getDataTable(list);
    }

    /**
     * 导出面单返回值列表
     */
    @PreAuthorize("@ss.hasPermi('system:response:export')")
    @Log(title = "面单返回值", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PackagesGenerationResponse packagesGenerationResponse)
    {
        List<PackagesGenerationResponse> list = packagesGenerationResponseService.selectPackagesGenerationResponseList(packagesGenerationResponse);
        ExcelUtil<PackagesGenerationResponse> util = new ExcelUtil<PackagesGenerationResponse>(PackagesGenerationResponse.class);
        util.exportExcel(response, list, "面单返回值数据");
    }

    /**
     * 获取面单返回值详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:response:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(packagesGenerationResponseService.selectPackagesGenerationResponseById(id));
    }

    /**
     * 新增面单返回值
     */
    @PreAuthorize("@ss.hasPermi('system:response:add')")
    @Log(title = "面单返回值", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PackagesGenerationResponse packagesGenerationResponse)
    {
        return toAjax(packagesGenerationResponseService.insertPackagesGenerationResponse(packagesGenerationResponse));
    }

    /**
     * 修改面单返回值
     */
    @PreAuthorize("@ss.hasPermi('system:response:edit')")
    @Log(title = "面单返回值", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PackagesGenerationResponse packagesGenerationResponse)
    {
        return toAjax(packagesGenerationResponseService.updatePackagesGenerationResponse(packagesGenerationResponse));
    }

    /**
     * 删除面单返回值
     */
    @PreAuthorize("@ss.hasPermi('system:response:remove')")
    @Log(title = "面单返回值", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(packagesGenerationResponseService.deletePackagesGenerationResponseByIds(ids));
    }
}
