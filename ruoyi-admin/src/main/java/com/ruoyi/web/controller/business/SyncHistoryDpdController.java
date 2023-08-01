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
import com.ruoyi.system.domain.SyncHistoryDpd;
import com.ruoyi.system.service.ISyncHistoryDpdService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 同步历史记录Controller
 *
 * @author ruoyi
 * @date 2023-08-02
 */
@RestController
@RequestMapping("/system/dpd")
public class SyncHistoryDpdController extends BaseController {
    @Autowired
    private ISyncHistoryDpdService syncHistoryDpdService;

    /**
     * 查询同步历史记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:dpd:list')")
    @GetMapping("/list")
    public TableDataInfo list(SyncHistoryDpd syncHistoryDpd) {
        startPage();
        List<SyncHistoryDpd> list = syncHistoryDpdService.selectSyncHistoryDpdList(syncHistoryDpd);
        return getDataTable(list);
    }

    /**
     * 导出同步历史记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:dpd:export')")
    @Log(title = "同步历史记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SyncHistoryDpd syncHistoryDpd) {
        List<SyncHistoryDpd> list = syncHistoryDpdService.selectSyncHistoryDpdList(syncHistoryDpd);
        ExcelUtil<SyncHistoryDpd> util = new ExcelUtil<SyncHistoryDpd>(SyncHistoryDpd.class);
        util.exportExcel(response, list, "同步历史记录数据");
    }

    /**
     * 获取同步历史记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:dpd:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(syncHistoryDpdService.selectSyncHistoryDpdById(id));
    }

    /**
     * 新增同步历史记录
     */
    @PreAuthorize("@ss.hasPermi('system:dpd:add')")
    @Log(title = "同步历史记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SyncHistoryDpd syncHistoryDpd) {
        return toAjax(syncHistoryDpdService.insertSyncHistoryDpd(syncHistoryDpd));
    }

    /**
     * 修改同步历史记录
     */
    @PreAuthorize("@ss.hasPermi('system:dpd:edit')")
    @Log(title = "同步历史记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SyncHistoryDpd syncHistoryDpd) {
        return toAjax(syncHistoryDpdService.updateSyncHistoryDpd(syncHistoryDpd));
    }

    /**
     * 删除同步历史记录
     */
    @PreAuthorize("@ss.hasPermi('system:dpd:remove')")
    @Log(title = "同步历史记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(syncHistoryDpdService.deleteSyncHistoryDpdByIds(ids));
    }
}
