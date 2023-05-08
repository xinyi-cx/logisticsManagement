package com.ruoyi.web.controller.business;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.BatchTaskHistory;
import com.ruoyi.system.domain.vo.BatchTaskHistoryVo;
import com.ruoyi.system.service.IBatchTaskHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
    public TableDataInfo list(BatchTaskHistoryVo batchTaskHistoryVo)
    {
        startPage();
        List<BatchTaskHistory> list = batchTaskHistoryService.selectBatchTaskHistoryList(batchTaskHistoryVo);
        return getDataTable(list);
    }

    /**
     * 导出批量任务历史列表
     */
    @PreAuthorize("@ss.hasPermi('system:history:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, BatchTaskHistoryVo batchTaskHistory)
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
    @PostMapping
    public AjaxResult add(@RequestBody BatchTaskHistory batchTaskHistory)
    {
        return toAjax(batchTaskHistoryService.insertBatchTaskHistory(batchTaskHistory));
    }

    /**
     * 修改批量任务历史
     */
    @PreAuthorize("@ss.hasPermi('system:history:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody BatchTaskHistory batchTaskHistory) throws Exception
    {
        return toAjax(batchTaskHistoryService.updateBatchTaskHistory(batchTaskHistory));
    }

    /**
     * 删除批量任务历史
     */
    @PreAuthorize("@ss.hasPermi('system:history:remove')")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(batchTaskHistoryService.deleteBatchTaskHistoryByIds(ids));
    }

//    /**
//     * 根据id查看批量面单pdf
//     * @param response
//     * @param id
//     * @throws IOException
//     */
//    @PostMapping("/getPDFById/{id}")
//    public void getPDFById(HttpServletResponse response, @PathVariable("id") Long id) throws IOException {
//        // 批量的id
//        batchTaskHistoryService.getPDFById(response, id);
//    }
}
