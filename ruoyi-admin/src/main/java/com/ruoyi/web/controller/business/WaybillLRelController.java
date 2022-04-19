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
import com.ruoyi.system.domain.WaybillLRel;
import com.ruoyi.system.service.IWaybillLRelService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 物流单号和L关联Controller
 * 
 * @author ruoyi
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/system/rel")
public class WaybillLRelController extends BaseController
{
    @Autowired
    private IWaybillLRelService waybillLRelService;

    /**
     * 查询物流单号和L关联列表
     */
    @PreAuthorize("@ss.hasPermi('system:rel:list')")
    @GetMapping("/list")
    public TableDataInfo list(WaybillLRel waybillLRel)
    {
        startPage();
        List<WaybillLRel> list = waybillLRelService.selectWaybillLRelList(waybillLRel);
        return getDataTable(list);
    }

    /**
     * 导出物流单号和L关联列表
     */
    @PreAuthorize("@ss.hasPermi('system:rel:export')")
    @Log(title = "物流单号和L关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WaybillLRel waybillLRel)
    {
        List<WaybillLRel> list = waybillLRelService.selectWaybillLRelList(waybillLRel);
        ExcelUtil<WaybillLRel> util = new ExcelUtil<WaybillLRel>(WaybillLRel.class);
        util.exportExcel(response, list, "物流单号和L关联数据");
    }

    /**
     * 获取物流单号和L关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:rel:query')")
    @GetMapping(value = "/{waybill}")
    public AjaxResult getInfo(@PathVariable("waybill") String waybill)
    {
        return AjaxResult.success(waybillLRelService.selectWaybillLRelByWaybill(waybill));
    }

    /**
     * 新增物流单号和L关联
     */
    @PreAuthorize("@ss.hasPermi('system:rel:add')")
    @Log(title = "物流单号和L关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WaybillLRel waybillLRel)
    {
        return toAjax(waybillLRelService.insertWaybillLRel(waybillLRel));
    }

    /**
     * 修改物流单号和L关联
     */
    @PreAuthorize("@ss.hasPermi('system:rel:edit')")
    @Log(title = "物流单号和L关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WaybillLRel waybillLRel)
    {
        return toAjax(waybillLRelService.updateWaybillLRel(waybillLRel));
    }

    /**
     * 删除物流单号和L关联
     */
    @PreAuthorize("@ss.hasPermi('system:rel:remove')")
    @Log(title = "物流单号和L关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{waybills}")
    public AjaxResult remove(@PathVariable String[] waybills)
    {
        return toAjax(waybillLRelService.deleteWaybillLRelByWaybills(waybills));
    }
}
