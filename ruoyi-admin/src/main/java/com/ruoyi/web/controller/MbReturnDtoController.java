package com.ruoyi.web.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.mb.MbReturnDto;
import com.ruoyi.system.service.IMbReturnDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 马帮订单信息Controller
 * 
 * @author ruoyi
 * @date 2022-05-16
 */
@RestController
@RequestMapping("/system/dto")
public class MbReturnDtoController extends BaseController
{
    @Autowired
    private IMbReturnDtoService mbReturnDtoService;

    /**
     * 查询马帮订单信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:dto:list')")
    @GetMapping("/list")
    public TableDataInfo list(MbReturnDto mbReturnDto)
    {
        startPage();
        List<MbReturnDto> list = mbReturnDtoService.selectMbReturnDtoList(mbReturnDto);
        return getDataTable(list);
    }

    /**
     * 导出马帮订单信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:dto:export')")
    @Log(title = "马帮订单信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MbReturnDto mbReturnDto)
    {
        List<MbReturnDto> list = mbReturnDtoService.selectMbReturnDtoList(mbReturnDto);
        ExcelUtil<MbReturnDto> util = new ExcelUtil<MbReturnDto>(MbReturnDto.class);
        util.exportExcel(response, list, "马帮订单信息数据");
    }

    /**
     * 获取马帮订单信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:dto:query')")
    @GetMapping(value = "/{code}")
    public AjaxResult getInfo(@PathVariable("code") String code)
    {
        return AjaxResult.success(mbReturnDtoService.selectMbReturnDtoByCode(code));
    }

    /**
     * 新增马帮订单信息
     */
    @PreAuthorize("@ss.hasPermi('system:dto:add')")
    @Log(title = "马帮订单信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MbReturnDto mbReturnDto)
    {
        return toAjax(mbReturnDtoService.insertMbReturnDto(mbReturnDto));
    }

    /**
     * 修改马帮订单信息
     */
    @PreAuthorize("@ss.hasPermi('system:dto:edit')")
    @Log(title = "马帮订单信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MbReturnDto mbReturnDto)
    {
        return toAjax(mbReturnDtoService.updateMbReturnDto(mbReturnDto));
    }

    /**
     * 删除马帮订单信息
     */
    @PreAuthorize("@ss.hasPermi('system:dto:remove')")
    @Log(title = "马帮订单信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{codes}")
    public AjaxResult remove(@PathVariable String[] codes)
    {
        return toAjax(mbReturnDtoService.deleteMbReturnDtoByCodes(codes));
    }
}
