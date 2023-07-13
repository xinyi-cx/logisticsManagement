package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.enums.SysWaybill;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.ImportLogicContent;
import com.ruoyi.system.domain.Parcel;
import com.ruoyi.system.domain.vo.*;
import com.ruoyi.system.service.IImportLogicContentService;
import com.ruoyi.system.service.IParcelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private IParcelService parcelService;

    /**
     * 查询导入查询物流列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ImportLogicContent importLogicContent) {
        startPage();
        List<ImportLogicContent> list = importLogicContentService.selectImportLogicContentList(importLogicContent);
        return getDataTable(list);
    }

    @GetMapping("/listNoReport")
    public TableDataInfo listNoReport(ImportLogicContent importLogicContent) {
        startPage();
        List<ImportLogicContent> list = importLogicContentService.selectImportLogicContentListNoReport(importLogicContent);
        return getDataTable(list);
    }

    @GetMapping("/listReport")
    public TableDataInfo listReport(ImportLogicContent importLogicContent) {
        startPage();
        List<ImportLogicContent> list = importLogicContentService.selectImportLogicContentListReport(importLogicContent);
        return getDataTable(list);
    }

    @PostMapping("/importTemplateNoGen")
    public void importTemplateNoGen(HttpServletResponse response)
    {
        ExcelUtil<ImportLogicContentTemplateVo> util = new ExcelUtil<ImportLogicContentTemplateVo>(ImportLogicContentTemplateVo.class);
        util.importTemplateExcel(response, "sheet1");
    }

    @PostMapping("/importTemplateForTj")
    public void importTemplateForTj(HttpServletResponse response)
    {
        ExcelUtil<ImportLogicContentTjTemplateVo> util = new ExcelUtil<ImportLogicContentTjTemplateVo>(ImportLogicContentTjTemplateVo.class);
        util.importTemplateExcel(response, "sheet1");
    }

    @Log(title = "批量更新退件物流", businessType = BusinessType.IMPORT)
    @PostMapping("/importLogicContentForTj")
    public AjaxResult importLogicContentForTj(MultipartFile file) throws Exception
    {
        ExcelUtil<ImportLogicContentTjTemplateVo> util = new ExcelUtil<ImportLogicContentTjTemplateVo>(ImportLogicContentTjTemplateVo.class);
        List<ImportLogicContentTjTemplateVo> importLogicContentTjTemplateVos = util.importExcel(file.getInputStream());
        List<ImportLogicContent> importLogicContents = new ArrayList<>();
        for (ImportLogicContentTjTemplateVo importLogicContentTjTemplateVo : importLogicContentTjTemplateVos) {
            ImportLogicContent importLogicContent = new ImportLogicContent();
            BeanUtils.copyProperties(importLogicContentTjTemplateVo, importLogicContent);
            importLogicContent.setStatus(SysWaybill.YTJ.getCode());
            importLogicContents.add(importLogicContent);
        }
        return AjaxResult.success(importLogicContentService.importLogicContentForTj(file, importLogicContents));
    }

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

    @Log(title = "导出", businessType = BusinessType.EXPORT)
    @PostMapping("/exportNoReport")
    public void exportNoReport(HttpServletResponse response, ImportLogicContent importLogicContent) {
        List<ExportLogicContentCODVo> list = importLogicContentService.exportImportLogicContentListNoReport(importLogicContent);
        ExcelUtil<ExportLogicContentCODVo> util = new ExcelUtil<ExportLogicContentCODVo>(ExportLogicContentCODVo.class);
        util.exportExcel(response, list, "sheet1");
    }

    @Log(title = "导出", businessType = BusinessType.EXPORT)
    @PostMapping("/exportReport")
    public void exportReport(HttpServletResponse response, ImportLogicContent importLogicContent) {
        List<ExportLogicContentCODVo> list = importLogicContentService.exportImportLogicContentListReport(importLogicContent);
        ExcelUtil<ExportLogicContentCODVo> util = new ExcelUtil<ExportLogicContentCODVo>(ExportLogicContentCODVo.class);
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

    @PreAuthorize("@ss.hasPermi('system:content:edit')")
    @Log(title = "修改物流状态", businessType = BusinessType.UPDATE)
    @PutMapping("/updateStatusByWeb")
    public AjaxResult updateStatusByWeb(@RequestBody ImportLogicContent importLogicContent) {
        return toAjax(importLogicContentService.updateStatusByWeb(importLogicContent));
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

    @GetMapping("/refreshToday")
    public String refreshToday(ImportLogicContent importLogicContent) {
        Parcel parcel = new Parcel();
        if (!(SecurityUtils.isAdmin(SecurityUtils.getLoginUser().getUserId()) || "Tracking".equals(SecurityUtils.getLoginUser().getUsername()))) {
            parcel.setCreateUser(SecurityUtils.getLoginUser().getUserId().toString());
        }
        parcel.setCreatedTime(DateUtils.getNowDate());
        parcelService.getParcelMsgTrans(parcel);

        return "正在获取物流信息，请稍后查看最新消息";
    }

    /**
     * 获取当前查询物流信息
     * @param importLogicContent
     * @return
     */
    @GetMapping("/refreshQuery")
    public String refreshQuery(ImportLogicContent importLogicContent) {
        List<ImportLogicContent> list = importLogicContentService.selectImportLogicContentList(importLogicContent);
        if (!CollectionUtils.isEmpty(list)){
            parcelService.getMsgByWaybills(list.stream().map(ImportLogicContent::getNewWaybill).collect(Collectors.toList()));
        }
        return "正在获取物流信息，请稍后查看最新消息";
    }

}
