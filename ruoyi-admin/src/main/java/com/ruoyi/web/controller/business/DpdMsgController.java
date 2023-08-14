package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.DpdMsg;
import com.ruoyi.system.service.IDpdMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * DPD返回值Controller
 * 
 *
 * @date 2022-06-23
 */
@RestController
@RequestMapping("/system/dpd-msg")
public class DpdMsgController extends BaseController
{
    @Autowired
    private IDpdMsgService dpdMsgService;

    /**
     * 查询DPD返回值列表
     */
    @PreAuthorize("@ss.hasPermi('system:msg:list')")
    @GetMapping("/list")
    public TableDataInfo list(DpdMsg dpdMsg)
    {
        startPage();
        List<DpdMsg> list = dpdMsgService.selectDpdMsgList(dpdMsg);
        return getDataTable(list);
    }

    /**
     * 导出DPD返回值列表
     */
    @PreAuthorize("@ss.hasPermi('system:msg:export')")
    @Log(title = "DPD返回值", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DpdMsg dpdMsg)
    {
        List<DpdMsg> list = dpdMsgService.selectDpdMsgList(dpdMsg);
        ExcelUtil<DpdMsg> util = new ExcelUtil<DpdMsg>(DpdMsg.class);
        util.exportExcel(response, list, "DPD返回值数据");
    }

    /**
     * 获取DPD返回值详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:msg:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(dpdMsgService.selectDpdMsgById(id));
    }

    /**
     * 删除DPD返回值
     */
    @PreAuthorize("@ss.hasPermi('system:msg:remove')")
    @Log(title = "DPD返回值", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(dpdMsgService.deleteDpdMsgByIds(ids));
    }
}
