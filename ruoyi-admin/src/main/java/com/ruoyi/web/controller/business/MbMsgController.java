package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.MbMsg;
import com.ruoyi.system.service.IMbMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 马帮信息Controller
 * 
 *
 * @date 2022-05-29
 */
@RestController
@RequestMapping("/system/msg")
public class MbMsgController extends BaseController
{
    @Autowired
    private IMbMsgService mbMsgService;

    /**
     * 查询马帮信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:msg:list')")
    @GetMapping("/list")
    public TableDataInfo list(MbMsg mbMsg)
    {
        startPage();
        List<MbMsg> list = mbMsgService.selectMbMsgList(mbMsg);
        return getDataTable(list);
    }

    /**
     * 导出马帮信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:msg:export')")
    @Log(title = "马帮信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MbMsg mbMsg)
    {
        List<MbMsg> list = mbMsgService.selectMbMsgList(mbMsg);
        ExcelUtil<MbMsg> util = new ExcelUtil<MbMsg>(MbMsg.class);
        util.exportExcel(response, list, "马帮信息数据");
    }

    /**
     * 获取马帮信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:msg:query')")
    @GetMapping(value = "/{code}")
    public AjaxResult getInfo(@PathVariable("code") String code)
    {
        return AjaxResult.success(mbMsgService.selectMbMsgByCode(code));
    }

    /**
     * 新增马帮信息
     */
    @PreAuthorize("@ss.hasPermi('system:msg:add')")
    @Log(title = "马帮信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MbMsg mbMsg)
    {
        return toAjax(mbMsgService.insertMbMsg(mbMsg));
    }

    /**
     * 修改马帮信息
     */
    @PreAuthorize("@ss.hasPermi('system:msg:edit')")
    @Log(title = "马帮信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MbMsg mbMsg)
    {
        return toAjax(mbMsgService.updateMbMsg(mbMsg));
    }

    /**
     * 删除马帮信息
     */
    @PreAuthorize("@ss.hasPermi('system:msg:remove')")
    @Log(title = "马帮信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{codes}")
    public AjaxResult remove(@PathVariable String[] codes)
    {
        return toAjax(mbMsgService.deleteMbMsgByCodes(codes));
    }
}
