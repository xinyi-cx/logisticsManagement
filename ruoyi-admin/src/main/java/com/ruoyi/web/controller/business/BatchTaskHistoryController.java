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
import com.ruoyi.system.domain.BatchTaskHistory;
import com.ruoyi.system.service.IBatchTaskHistoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 批量任务历史Controller
 * 
 * @author ruoyi
 * @date 2022-01-03
 */
@RestController
@RequestMapping("/system/history")
public class BatchTaskHistoryController extends BaseController
{
    @Autowired
    private IBatchTaskHistoryService batchTaskHistoryService;

    /**
     * 查询批量任务历史列表
     */
    @PreAuthorize("@ss.hasPermi('system:history:list')")
    @GetMapping("/list")
    public TableDataInfo list(BatchTaskHistory batchTaskHistory)
    {
        startPage();
        List<BatchTaskHistory> list = batchTaskHistoryService.selectBatchTaskHistoryList(batchTaskHistory);
        return getDataTable(list);
    }

    /**
     * 导出批量任务历史列表
     */
    @PreAuthorize("@ss.hasPermi('system:history:export')")
    @Log(title = "批量任务历史", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BatchTaskHistory batchTaskHistory)
    {
        List<BatchTaskHistory> list = batchTaskHistoryService.selectBatchTaskHistoryList(batchTaskHistory);
        ExcelUtil<BatchTaskHistory> util = new ExcelUtil<BatchTaskHistory>(BatchTaskHistory.class);
        util.exportExcel(response, list, "批量任务历史数据");
    }

    /**
     * 获取批量任务历史详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:history:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(batchTaskHistoryService.selectBatchTaskHistoryById(id));
    }

    /**
     * 新增批量任务历史
     */
    @PreAuthorize("@ss.hasPermi('system:history:add')")
    @Log(title = "批量任务历史", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BatchTaskHistory batchTaskHistory)
    {
        return toAjax(batchTaskHistoryService.insertBatchTaskHistory(batchTaskHistory));
    }

    /**
     * 修改批量任务历史
     */
    @PreAuthorize("@ss.hasPermi('system:history:edit')")
    @Log(title = "批量任务历史", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BatchTaskHistory batchTaskHistory)
    {
        return toAjax(batchTaskHistoryService.updateBatchTaskHistory(batchTaskHistory));
    }

    /**
     * 删除批量任务历史
     */
    @PreAuthorize("@ss.hasPermi('system:history:remove')")
    @Log(title = "批量任务历史", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(batchTaskHistoryService.deleteBatchTaskHistoryByIds(ids));
    }
}
