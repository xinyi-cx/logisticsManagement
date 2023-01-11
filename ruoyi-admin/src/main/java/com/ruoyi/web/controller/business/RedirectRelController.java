package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.RedirectRel;
import com.ruoyi.system.service.IRedirectRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 转寄关联关系Controller
 *
 * @author ruoyi
 * @date 2022-08-12
 */
@RestController
@RequestMapping("/system/redirectrel")
public class RedirectRelController extends BaseController {
    @Autowired
    private IRedirectRelService redirectRelService;

    /**
     * 查询转寄关联关系列表
     */
    @PreAuthorize("@ss.hasPermi('system:redirectrel:list')")
    @GetMapping("/list")
    public TableDataInfo list(RedirectRel redirectRel) {
        startPage();
        List<RedirectRel> list = redirectRelService.selectRedirectRelList(redirectRel);
        return getDataTable(list);
    }

    /**
     * 导出转寄关联关系列表
     */
    @PreAuthorize("@ss.hasPermi('system:redirectrel:export')")
    @Log(title = "转寄关联关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RedirectRel redirectRel) {
        List<RedirectRel> list = redirectRelService.selectRedirectRelList(redirectRel);
        ExcelUtil<RedirectRel> util = new ExcelUtil<RedirectRel>(RedirectRel.class);
        util.exportExcel(response, list, "转寄关联关系数据");
    }

    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) {
        ExcelUtil<RedirectRel> util = new ExcelUtil<RedirectRel>(RedirectRel.class);
        util.importTemplateExcel(response, "转寄关联关系数据");
    }

    @Log(title = "转寄关联关系导入", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('system:redirectrel:add')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file) throws Exception {
        ExcelUtil<RedirectRel> util = new ExcelUtil<RedirectRel>(RedirectRel.class);
        List<RedirectRel> redirectRelList = util.importExcel(file.getInputStream());
        return AjaxResult.success(redirectRelService.importRedirectRel(file, redirectRelList));
    }

    /**
     * 获取转寄关联关系详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:redirectrel:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(redirectRelService.selectRedirectRelById(id));
    }

    /**
     * 获取转寄关联关系详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:redirectrel:query')")
    @GetMapping(value = "/getInfoByNewPackageId/{newPackageId}")
    public AjaxResult getInfoByNewPackageId(@PathVariable("newPackageId") Long newPackageId) {
        return AjaxResult.success(redirectRelService.selectRedirectRelByNewPackageId(newPackageId));
    }

    /**
     * 新增转寄关联关系
     */
    @PreAuthorize("@ss.hasPermi('system:redirectrel:add')")
    @Log(title = "转寄关联关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RedirectRel redirectRel) {
        return toAjax(redirectRelService.insertRedirectRel(redirectRel));
    }

    /**
     * 修改转寄关联关系
     */
    @PreAuthorize("@ss.hasPermi('system:redirectrel:edit')")
    @Log(title = "转寄关联关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RedirectRel redirectRel) {
        return toAjax(redirectRelService.updateRedirectRel(redirectRel));
    }

    /**
     * 删除转寄关联关系
     */
    @PreAuthorize("@ss.hasPermi('system:redirectrel:remove')")
    @Log(title = "转寄关联关系", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(redirectRelService.deleteRedirectRelByIds(ids));
    }

    /**
     * 查询转寄关联关系物流信息
     */
    @GetMapping("/refresh")
    public String refresh(RedirectRel redirectRel) {
        startPage();
        List<RedirectRel> list = redirectRelService.selectRedirectRelList(redirectRel);
        return "success";
    }

}
