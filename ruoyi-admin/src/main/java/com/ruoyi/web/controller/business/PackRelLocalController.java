package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.PackRelLocal;
import com.ruoyi.system.domain.vo.*;
import com.ruoyi.system.service.IPackRelLocalService;
import com.ruoyi.system.service.IPackageService;
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

import static java.util.stream.Collectors.toList;

/**
 * 本地关联关系Controller
 *
 * @author ruoyi
 * @date 2023-03-08
 */
@RestController
@RequestMapping("/system/local")
public class PackRelLocalController extends BaseController {
    @Autowired
    private IPackRelLocalService packRelLocalService;

    @Autowired
    private IPackageService packageService;

    /**
     * 查询本地关联关系列表
     */
    @PreAuthorize("@ss.hasPermi('system:local:list')")
    @GetMapping("/list")
    public TableDataInfo list(PackRelLocal packRelLocal) {
        startPage();
        List<PackRelLocal> list = packRelLocalService.selectPackRelLocalList(packRelLocal);
        return getDataTable(list);
    }

    /**
     * 导出本地关联关系列表
     */
    @PreAuthorize("@ss.hasPermi('system:local:export')")
    @Log(title = "本地关联关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PackRelLocal packRelLocal) {
        List<PackRelLocal> list = packRelLocalService.selectPackRelLocalList(packRelLocal);
        ExcelUtil<PackRelLocal> util = new ExcelUtil<PackRelLocal>(PackRelLocal.class);
        util.exportExcel(response, list, "本地关联关系数据");
    }

    /**
     * 获取本地关联关系详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:local:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(packRelLocalService.selectPackRelLocalById(id));
    }

    /**
     * 新增本地关联关系
     */
    @PreAuthorize("@ss.hasPermi('system:local:add')")
    @Log(title = "本地关联关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PackRelLocal packRelLocal) {
        return toAjax(packRelLocalService.insertPackRelLocal(packRelLocal));
    }

    /**
     * 修改本地关联关系
     */
    @PreAuthorize("@ss.hasPermi('system:local:edit')")
    @Log(title = "本地关联关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PackRelLocal packRelLocal) {
        return toAjax(packRelLocalService.updatePackRelLocal(packRelLocal));
    }

    /**
     * 删除本地关联关系
     */
    @PreAuthorize("@ss.hasPermi('system:local:remove')")
    @Log(title = "本地关联关系", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(packRelLocalService.deletePackRelLocalByIds(ids));
    }

    @Log(title = "本地面单导入", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file) throws Exception {
        ExcelUtil<LocalPackageVo> util = new ExcelUtil<LocalPackageVo>(LocalPackageVo.class);
        List<LocalPackageVo> localPackageVos = util.importExcel(file.getInputStream());
        List<PackageVo> packageVos = localPackageVos.stream().map(item ->
                {
                    PackageVo packageVo = new PackageVo();
                    BeanUtils.copyProperties(item, packageVo);
                    return packageVo;
                }
        ).collect(toList());
        return AjaxResult.success(packageService.importPackage(file, packageVos));
    }

    @Log(title = "本地面单导入", businessType = BusinessType.IMPORT)
    @PostMapping("/importDataCz")
    public AjaxResult importDataCz(MultipartFile file) throws Exception {
        ExcelUtil<LocalPackageCzVo> util = new ExcelUtil<LocalPackageCzVo>(LocalPackageCzVo.class);
        List<LocalPackageCzVo> localPackageCzVos = util.importExcel(file.getInputStream());
        List<PackageVo> packageVos = localPackageCzVos.stream().map(item ->
                {
                    PackageVo packageVo = new PackageVo();
                    BeanUtils.copyProperties(item, packageVo);
                    return packageVo;
                }
        ).collect(toList());
        return AjaxResult.success(packageService.importPackage(file, packageVos));
    }

    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) {
        ExcelUtil<LocalPackageVo> util = new ExcelUtil<LocalPackageVo>(LocalPackageVo.class);
        util.importTemplateExcel(response, "本地面单数据");
    }

    @PostMapping("/importTemplateCz")
    public void importTemplateCz(HttpServletResponse response) {
        ExcelUtil<LocalPackageCzVo> util = new ExcelUtil<LocalPackageCzVo>(LocalPackageCzVo.class);
        util.importTemplateExcel(response, "本地面单数据");
    }

    /**
     * 转寄面单导出
     * 导出面单列表+关联关系
     */
    @Log(title = "面单", businessType = BusinessType.EXPORT)
    @PostMapping("/exportLocal")
    public void exportRe(HttpServletResponse response, PackageVo pkg)
    {
        pkg.setExportFlag(1);
        List<PackageVo> list = packageService.selectPackageVoList(pkg, "numRedisKey");
        List<ExportLocalPackageVo> exportPackageVos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(list)){
            packageService.updateDownloadNum(list.stream().map(PackageVo::getId).collect(Collectors.toList()));

            exportPackageVos = list.stream().map(item ->
                    {
                        ExportLocalPackageVo packageVo = new ExportLocalPackageVo();
                        BeanUtils.copyProperties(item, packageVo);
                        return packageVo;
                    }
            ).collect(toList());
        }
        ExcelUtil<ExportLocalPackageVo> util = new ExcelUtil<ExportLocalPackageVo>(ExportLocalPackageVo.class);
        util.exportExcel(response, exportPackageVos, "面单数据");
    }

    /**
     * 导出面单列表+关联关系
     */
    @Log(title = "面单", businessType = BusinessType.EXPORT)
    @PostMapping("/exportLocalCz")
    public void exportReCz(HttpServletResponse response, PackageVo pkg)
    {
        pkg.setExportFlag(1);
        List<PackageVo> list = packageService.selectPackageVoList(pkg, "numRedisKey");
        List<ExportLocalPackageCzVo> exportPackageVos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(list)){
            packageService.updateDownloadNum(list.stream().map(PackageVo::getId).collect(Collectors.toList()));

            exportPackageVos = list.stream().map(item ->
                    {
                        ExportLocalPackageCzVo packageVo = new ExportLocalPackageCzVo();
                        BeanUtils.copyProperties(item, packageVo);
                        return packageVo;
                    }
            ).collect(toList());
        }
        ExcelUtil<ExportLocalPackageCzVo> util = new ExcelUtil<ExportLocalPackageCzVo>(ExportLocalPackageCzVo.class);
        util.exportExcel(response, exportPackageVos, "面单数据");
    }

}
