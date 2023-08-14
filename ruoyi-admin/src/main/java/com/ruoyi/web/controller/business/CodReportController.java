package com.ruoyi.web.controller.business;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.domain.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
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
import com.ruoyi.system.domain.CodReport;
import com.ruoyi.system.service.ICodReportService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import static java.util.stream.Collectors.toList;

/**
 * COD报告Controller
 * 
 *
 * @date 2023-05-02
 */
@RestController
@RequestMapping("/system/report")
public class CodReportController extends BaseController
{
    @Autowired
    private ICodReportService codReportService;

    /**
     * 查询COD报告列表
     */
    @PreAuthorize("@ss.hasPermi('system:report:list')")
    @GetMapping("/list")
    public TableDataInfo list(CodReport codReport)
    {
        startPage();
        List<CodReport> list = codReportService.selectCodReportList(codReport);
        return getDataTable(list);
    }

    /**
     * 导出COD报告列表
     */
    @PreAuthorize("@ss.hasPermi('system:report:export')")
    @Log(title = "COD报告", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CodReport codReport)
    {
        List<CodReport> list = codReportService.selectCodReportList(codReport);
        List<ExportCodReportVo> exportPackageVos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(list)){
//            packageService.updateDownloadNum(list.stream().map(PackageVo::getId).collect(Collectors.toList()));

            exportPackageVos = list.stream().map(item ->
                    {
                        ExportCodReportVo packageVo = new ExportCodReportVo();
                        BeanUtils.copyProperties(item, packageVo);
                        return packageVo;
                    }
            ).collect(toList());
        }
        ExcelUtil<ExportCodReportVo> util = new ExcelUtil<ExportCodReportVo>(ExportCodReportVo.class);
        util.exportExcel(response, exportPackageVos, "COD报告数据");
    }

    /**
     * 获取COD报告详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:report:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(codReportService.selectCodReportById(id));
    }

    /**
     * 新增COD报告
     */
    @PreAuthorize("@ss.hasPermi('system:report:add')")
    @Log(title = "COD报告", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CodReport codReport)
    {
        return toAjax(codReportService.insertCodReport(codReport));
    }

    /**
     * 修改COD报告
     */
    @PreAuthorize("@ss.hasPermi('system:report:edit')")
    @Log(title = "COD报告", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CodReport codReport)
    {
        return toAjax(codReportService.updateCodReport(codReport));
    }

    /**
     * 删除COD报告
     */
    @PreAuthorize("@ss.hasPermi('system:report:remove')")
    @Log(title = "COD报告", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(codReportService.deleteCodReportByIds(ids));
    }

    @Log(title = "本地报告导入", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file) throws Exception {
        ExcelUtil<ImportCodReportVo> util = new ExcelUtil<ImportCodReportVo>(ImportCodReportVo.class);
        List<ImportCodReportVo> importCodReportVos = util.importExcel(file.getInputStream());
        return AjaxResult.success(codReportService.importCodReport(file, importCodReportVos));
    }

//    @Log(title = "本地报告导入", businessType = BusinessType.IMPORT)
//    @PostMapping("/importDataCz")
//    public AjaxResult importDataCz(MultipartFile file) throws Exception {
//        ExcelUtil<LocalPackageCzVo> util = new ExcelUtil<LocalPackageCzVo>(LocalPackageCzVo.class);
//        List<LocalPackageCzVo> localPackageCzVos = util.importExcel(file.getInputStream());
//        List<PackageVo> packageVos = localPackageCzVos.stream().map(item ->
//                {
//                    PackageVo packageVo = new PackageVo();
//                    BeanUtils.copyProperties(item, packageVo);
//                    return packageVo;
//                }
//        ).collect(toList());
//        return AjaxResult.success(packageService.importPackage(file, packageVos));
//    }

    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) {
        ExcelUtil<ImportCodReportVo> util = new ExcelUtil<ImportCodReportVo>(ImportCodReportVo.class);
        util.importTemplateExcel(response, "本地报告数据");
    }
//
//    @PostMapping("/importTemplateCz")
//    public void importTemplateCz(HttpServletResponse response) {
//        ExcelUtil<LocalPackageCzVo> util = new ExcelUtil<LocalPackageCzVo>(LocalPackageCzVo.class);
//        util.importTemplateExcel(response, "本地报告数据");
//    }

//    /**
//     * 导出报告列表+关联关系
//     */
//    @Log(title = "报告", businessType = BusinessType.EXPORT)
//    @PostMapping("/exportLocalCz")
//    public void exportReCz(HttpServletResponse response, PackageVo pkg)
//    {
//        pkg.setExportFlag(1);
//        List<PackageVo> list = packageService.selectPackageVoList(pkg, "numRedisKey");
//        List<ExportLocalPackageCzVo> exportPackageVos = new ArrayList<>();
//        if (!CollectionUtils.isEmpty(list)){
//            packageService.updateDownloadNum(list.stream().map(PackageVo::getId).collect(Collectors.toList()));
//
//            exportPackageVos = list.stream().map(item ->
//                    {
//                        ExportLocalPackageCzVo packageVo = new ExportLocalPackageCzVo();
//                        BeanUtils.copyProperties(item, packageVo);
//                        return packageVo;
//                    }
//            ).collect(toList());
//        }
//        ExcelUtil<ExportLocalPackageCzVo> util = new ExcelUtil<ExportLocalPackageCzVo>(ExportLocalPackageCzVo.class);
//        util.exportExcel(response, exportPackageVos, "报告数据");
//    }
}
