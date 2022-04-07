package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.LogisticsInfo;
import com.ruoyi.system.service.ILogisticsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 物流信息Controller
 * 
 * @author ruoyi
 * @date 2022-04-07
 */
@RestController
@RequestMapping("/system/info")
public class LogisticsInfoController extends BaseController
{
    @Autowired
    private ILogisticsInfoService logisticsInfoService;

    /**
     * 查询物流信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(LogisticsInfo logisticsInfo)
    {
        startPage();
        List<LogisticsInfo> list = logisticsInfoService.selectLogisticsInfoList(logisticsInfo);
        return getDataTable(list);
    }

    /**
     * 导出物流信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:export')")
    @Log(title = "物流信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, LogisticsInfo logisticsInfo)
    {
        List<LogisticsInfo> list = logisticsInfoService.selectLogisticsInfoList(logisticsInfo);
        ExcelUtil<LogisticsInfo> util = new ExcelUtil<LogisticsInfo>(LogisticsInfo.class);
        util.exportExcel(response, list, "物流信息数据");
    }

    /**
     * 获取物流信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(logisticsInfoService.selectLogisticsInfoById(id));
    }

    /**
     * 新增物流信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:add')")
    @Log(title = "物流信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LogisticsInfo logisticsInfo)
    {
        return toAjax(logisticsInfoService.insertLogisticsInfo(logisticsInfo));
    }

    /**
     * 修改物流信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:edit')")
    @Log(title = "物流信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LogisticsInfo logisticsInfo)
    {
        return toAjax(logisticsInfoService.updateLogisticsInfo(logisticsInfo));
    }

    /**
     * 删除物流信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:remove')")
    @Log(title = "物流信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(logisticsInfoService.deleteLogisticsInfoByIds(ids));
    }
}
