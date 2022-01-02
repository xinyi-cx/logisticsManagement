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
import com.ruoyi.system.domain.Parcel;
import com.ruoyi.system.service.IParcelService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 包裹Controller
 * 
 * @author ruoyi
 * @date 2022-01-02
 */
@RestController
@RequestMapping("/system/parcel")
public class ParcelController extends BaseController
{
    @Autowired
    private IParcelService parcelService;

    /**
     * 查询包裹列表
     */
    @PreAuthorize("@ss.hasPermi('system:parcel:list')")
    @GetMapping("/list")
    public TableDataInfo list(Parcel parcel)
    {
        startPage();
        List<Parcel> list = parcelService.selectParcelList(parcel);
        return getDataTable(list);
    }

    /**
     * 导出包裹列表
     */
    @PreAuthorize("@ss.hasPermi('system:parcel:export')")
    @Log(title = "包裹", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Parcel parcel)
    {
        List<Parcel> list = parcelService.selectParcelList(parcel);
        ExcelUtil<Parcel> util = new ExcelUtil<Parcel>(Parcel.class);
        util.exportExcel(response, list, "包裹数据");
    }

    /**
     * 获取包裹详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:parcel:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(parcelService.selectParcelById(id));
    }

    /**
     * 新增包裹
     */
    @PreAuthorize("@ss.hasPermi('system:parcel:add')")
    @Log(title = "包裹", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Parcel parcel)
    {
        return toAjax(parcelService.insertParcel(parcel));
    }

    /**
     * 修改包裹
     */
    @PreAuthorize("@ss.hasPermi('system:parcel:edit')")
    @Log(title = "包裹", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Parcel parcel)
    {
        return toAjax(parcelService.updateParcel(parcel));
    }

    /**
     * 删除包裹
     */
    @PreAuthorize("@ss.hasPermi('system:parcel:remove')")
    @Log(title = "包裹", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(parcelService.deleteParcelByIds(ids));
    }
}
