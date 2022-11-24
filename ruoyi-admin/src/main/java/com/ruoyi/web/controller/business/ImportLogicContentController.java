package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.ImportLogicContent;
import com.ruoyi.system.domain.vo.ExportLogicContentVo;
import com.ruoyi.system.service.IImportLogicContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 导入查询物流Controller
 *
 * @author ruoyi
 * @date 2022-11-20
 */
@RestController
@RequestMapping("/system/content")
public class ImportLogicContentController extends BaseController {
    @Autowired
    private IImportLogicContentService importLogicContentService;

    /**
     * 查询导入查询物流列表
     */
    @PreAuthorize("@ss.hasPermi('system:content:list')")
    @GetMapping("/list")
    public TableDataInfo list(ImportLogicContent importLogicContent) {
        startPage();
        List<ImportLogicContent> list = importLogicContentService.selectImportLogicContentList(importLogicContent);
        return getDataTable(list);
    }

//    @Log(title = "面单导入", businessType = BusinessType.IMPORT)
//    @PreAuthorize("@ss.hasPermi('system:package:add')")
//    @PostMapping("/importData")
//    public AjaxResult importData(MultipartFile file) throws Exception
//    {
//        ExcelUtil<ImportLogicContentVo> util = new ExcelUtil<ImportLogicContentVo>(ImportLogicContentVo.class);
//        List<ImportLogicContentVo> importLogicContentVos = util.importExcel(file.getInputStream());
//        return AjaxResult.success(importLogicContentService.importPackage(file, importLogicContentVos));
//    }

    /**
     * 导出导入查询物流列表
     */
    @Log(title = "导出", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ImportLogicContent importLogicContent) {
        List<ExportLogicContentVo> list = importLogicContentService.exportImportLogicContentList(importLogicContent);
        ExcelUtil<ExportLogicContentVo> util = new ExcelUtil<ExportLogicContentVo>(ExportLogicContentVo.class);
        util.exportExcel(response, list, "sheet1");
    }

    /**
     * 获取导入查询物流详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:content:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(importLogicContentService.selectImportLogicContentById(id));
    }

    /**
     * 新增导入查询物流
     */
    @PreAuthorize("@ss.hasPermi('system:content:add')")
    @Log(title = "导入查询物流", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ImportLogicContent importLogicContent) {
        return toAjax(importLogicContentService.insertImportLogicContent(importLogicContent));
    }

    /**
     * 修改导入查询物流
     */
    @PreAuthorize("@ss.hasPermi('system:content:edit')")
    @Log(title = "导入查询物流", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ImportLogicContent importLogicContent) {
        return toAjax(importLogicContentService.updateImportLogicContent(importLogicContent));
    }

    /**
     * 删除导入查询物流
     */
    @PreAuthorize("@ss.hasPermi('system:content:remove')")
    @Log(title = "导入查询物流", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(importLogicContentService.deleteImportLogicContentByIds(ids));
    }
}
