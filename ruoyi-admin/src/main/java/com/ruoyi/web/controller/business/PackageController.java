package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.system.domain.Package;
import com.ruoyi.system.domain.busenum.ImportTypeEnum;
import com.ruoyi.system.domain.vo.*;
import com.ruoyi.system.service.IPackageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * 面单Controller
 * 
 * @author ruoyi
 * @date 2022-01-02
 */
@RestController
@RequestMapping("/system/package")
public class PackageController extends BaseController
{
    @Autowired
    private IPackageService packageService;

    /**
     * 查询面单列表
     */
    @GetMapping("/list")
    public TableDataInfo list(Package pkg)
    {
        startPage();
        List<Package> list = packageService.selectPackageList(pkg);
        return getDataTable(list);
    }

    /**
     * 查询面单列表
     */
    @GetMapping("/getResponse/{id}")
    public void getResponse(@PathVariable("id") Long id) throws IOException {
        packageService.getResponse(id);
    }

    /**
     * 查询面单列表
     */
    @GetMapping("/listAll")
    public TableDataInfo list(PackageVo pkg)
    {
        startPage();
        String numRedisKey = IdUtils.fastSimpleUUID();
        List<PackageVo> list = packageService.selectPackageVoList(pkg, numRedisKey);
        return getDataTable(list, packageService.selectPackageVoListTotal(pkg, numRedisKey));
    }

    /**
     * 转寄面单页面查询面单列表
     */
    @GetMapping("/all")
    public List<PackageVo> all(PackageVo pkg)
    {
        return packageService.selectPackageVoList(pkg, "numRedisKey");
    }

    /**
     * 导出面单列表
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response, PackageVo pkg)
    {
        pkg.setExportFlag(1);
        List<PackageVo> list = packageService.selectPackageVoList(pkg, "numRedisKey");
        List<ExportPackageVo> exportPackageVos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(list)){
            packageService.updateDownloadNum(list.stream().map(PackageVo::getId).collect(Collectors.toList()));

            exportPackageVos = list.stream().map(item ->
                    {
                        ExportPackageVo packageVo = new ExportPackageVo();
                        BeanUtils.copyProperties(item, packageVo);
                        return packageVo;
                    }
            ).collect(toList());
        }
        ExcelUtil<ExportPackageVo> util = new ExcelUtil<ExportPackageVo>(ExportPackageVo.class);
        util.exportExcel(response, exportPackageVos, "面单数据");
    }

    /**
     * 导出面单列表
     */
    @PostMapping("/exportTwo")
    public void exportTwo(HttpServletResponse response, PackageVo pkg)
    {
        pkg.setExportFlag(1);
        List<PackageVo> list = packageService.selectPackageVoList(pkg, "numRedisKey");
        List<ExportTwoPackageVo> exportPackageVos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(list)){
            packageService.updateDownloadNum(list.stream().map(PackageVo::getId).collect(Collectors.toList()));

            exportPackageVos = list.stream().map(item ->
                    {
                        ExportTwoPackageVo packageVo = new ExportTwoPackageVo();
                        BeanUtils.copyProperties(item, packageVo);
                        return packageVo;
                    }
            ).collect(toList());
        }
        ExcelUtil<ExportTwoPackageVo> util = new ExcelUtil<ExportTwoPackageVo>(ExportTwoPackageVo.class);
        util.exportExcel(response, exportPackageVos, "面单数据");
    }

    /**
     * 导出面单列表
     */
    @PostMapping("/exportCz")
    public void exportCz(HttpServletResponse response, PackageVo pkg)
    {
        pkg.setExportFlag(1);
        List<PackageVo> list = packageService.selectPackageVoList(pkg, "numRedisKey");
        List<ExportPackageCzVo> exportPackageVos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(list)){
            packageService.updateDownloadNum(list.stream().map(PackageVo::getId).collect(Collectors.toList()));

            exportPackageVos = list.stream().map(item ->
                    {
                        ExportPackageCzVo packageVo = new ExportPackageCzVo();
                        BeanUtils.copyProperties(item, packageVo);
                        return packageVo;
                    }
            ).collect(toList());
        }
        ExcelUtil<ExportPackageCzVo> util = new ExcelUtil<ExportPackageCzVo>(ExportPackageCzVo.class);
        util.exportExcel(response, exportPackageVos, "面单数据");
    }
//    ExportRePackageVo

    /**
     * 转寄面单导出
     * 导出面单列表+关联关系
     */
    @PostMapping("/exportRe")
    public void exportRe(HttpServletResponse response, PackageVo pkg)
    {
        pkg.setExportFlag(1);
        List<PackageVo> list = packageService.selectPackageVoList(pkg, "numRedisKey");
        List<ExportRePackageVo> exportPackageVos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(list)){
            packageService.updateDownloadNum(list.stream().map(PackageVo::getId).collect(Collectors.toList()));

            exportPackageVos = list.stream().map(item ->
                    {
                        ExportRePackageVo packageVo = new ExportRePackageVo();
                        BeanUtils.copyProperties(item, packageVo);
                        return packageVo;
                    }
            ).collect(toList());
        }
        ExcelUtil<ExportRePackageVo> util = new ExcelUtil<ExportRePackageVo>(ExportRePackageVo.class);
        util.exportExcel(response, exportPackageVos, "面单数据");
    }

    /**
     * 导出面单列表+关联关系
     */
    @PostMapping("/exportReCz")
    public void exportReCz(HttpServletResponse response, PackageVo pkg)
    {
        pkg.setExportFlag(1);
        List<PackageVo> list = packageService.selectPackageVoList(pkg, "numRedisKey");
        List<ExportRePackageCzVo> exportPackageVos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(list)){
            packageService.updateDownloadNum(list.stream().map(PackageVo::getId).collect(Collectors.toList()));

            exportPackageVos = list.stream().map(item ->
                    {
                        ExportRePackageCzVo packageVo = new ExportRePackageCzVo();
                        BeanUtils.copyProperties(item, packageVo);
                        return packageVo;
                    }
            ).collect(toList());
        }
        ExcelUtil<ExportRePackageCzVo> util = new ExcelUtil<ExportRePackageCzVo>(ExportRePackageCzVo.class);
        util.exportExcel(response, exportPackageVos, "面单数据");
    }

    /**
     * 根据批次
     * @param dateStr
     * @return
     */
    @GetMapping(value = "/statistics/{dateStr}")
    public AjaxResult getStatistics(@PathVariable("dateStr") String dateStr)
    {
        return AjaxResult.success(packageService.getStatistics(dateStr));
    }

    /**
     * 根据批次
     * @param dateStr
     * @return
     */
    @GetMapping(value = "/statisticsAllUser/{dateStr}")
    public AjaxResult getStatisticsAllUser(@PathVariable("dateStr") String dateStr)
    {
        return AjaxResult.success(packageService.getStatisticsAllUser(dateStr));
    }

    /**
     * 获取面单详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(packageService.selectPackageById(id));
    }

    /**
     * 新增面单
     */
    @PostMapping
    public AjaxResult add(@RequestBody Package pkg)
    {
        return toAjax(packageService.insertPackage(pkg));
    }

    /**
     * 新增面单
     */
    @PostMapping(value = "/addAll")
    public AjaxResult addAll(@RequestBody @Valid PackageVo pkg) throws Exception
    {
        return toAjax(packageService.insertPackage(pkg));
    }

    /**
     * 导出模板
     * @param response
     */
    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response)
    {
        ExcelUtil<PackageVo> util = new ExcelUtil<PackageVo>(PackageVo.class);
        util.importTemplateExcel(response, "面单数据");
    }

    /**
     * 导出模板
     * @param response
     */
    @PostMapping("/importTemplateCz")
    public void importTemplateCz(HttpServletResponse response)
    {
        ExcelUtil<PackageCzVo> util = new ExcelUtil<PackageCzVo>(PackageCzVo.class);
        util.importTemplateExcel(response, "面单数据");
    }

    @PostMapping("/downloadFile/{id}")
    public void downloadFile(HttpServletResponse response, @PathVariable("id") Long id) throws Exception {
        packageService.writeFile(response, id);
    }

    /**
     * 根据id查看面单pdf
     * @param response
     * @param id
     * @throws IOException
     */
    @PostMapping("/getPDFById/{id}")
    public void getPDFById(HttpServletResponse response, @PathVariable("id") Long id) throws IOException {
        packageService.getPDFById(response, id);
    }

    /**
     * 下载导入错误信息
     * @param response
     * @param id
     * @throws IOException
     */
    @PostMapping("/getTxtById/{id}")
    public void getTxtById(HttpServletResponse response, @PathVariable("id") Long id) throws IOException {
        packageService.getTxtById(response, id);
    }

    /**
     * 根据批量id查看面单pdf
     * @param response
     * @param id
     * @throws IOException
     */
    @PostMapping("/getPDFByBatchId/{id}")
    public void getPDFByBatchId(HttpServletResponse response, @PathVariable("id") Long id) throws IOException {
        packageService.getPDFByBatchId(response, id);
    }

    @Log(title = "导入物流", businessType = BusinessType.IMPORT)
    @PostMapping("/importPackageForNoGen")
    public AjaxResult importPackageForNoGen(MultipartFile file) throws Exception
    {
        ExcelUtil<PackageNoGenVo> util = new ExcelUtil<PackageNoGenVo>(PackageNoGenVo.class);
        List<PackageNoGenVo> packageCzVoList = util.importExcel(file.getInputStream());
        List<PackageVo> packageVos = new ArrayList<>();
        for (PackageNoGenVo packageNoGenVo : packageCzVoList) {
            PackageVo packageVo = new PackageVo();
            BeanUtils.copyProperties(packageNoGenVo, packageVo);
            packageVos.add(packageVo);
        }
        return AjaxResult.success(packageService.importPackageForNoGen(file, packageVos));
    }

    @Log(title = "面单导入-波兰", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file) throws Exception
    {
        ExcelUtil<PackageVo> util = new ExcelUtil<PackageVo>(PackageVo.class);
        List<PackageVo> packageVos = util.importExcel(file.getInputStream());
        return AjaxResult.success(packageService.importPackage(file, packageVos, ImportTypeEnum.original));
    }

    @Log(title = "面单导入-其他国家", businessType = BusinessType.IMPORT)
    @PostMapping("/importDataCz")
    public AjaxResult importDataCz(MultipartFile file) throws Exception
    {
        ExcelUtil<PackageCzVo> util = new ExcelUtil<PackageCzVo>(PackageCzVo.class);
        List<PackageCzVo> packageCzVoList = util.importExcel(file.getInputStream());
        List<PackageVo> packageVos = new ArrayList<>();
        for (PackageCzVo packageCzVo : packageCzVoList) {
            PackageVo packageVo = new PackageVo();
            BeanUtils.copyProperties(packageCzVo, packageVo);
            packageVos.add(packageVo);
        }
        return AjaxResult.success(packageService.importPackage(file, packageVos, ImportTypeEnum.original));
    }

    /**
     * 修改面单
     */
    @PutMapping
    public AjaxResult edit(@RequestBody PackageVo pkg)
    {
        return toAjax(packageService.updatePackage(pkg));
    }

    /**
     * 删除面单
     */
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(packageService.deletePackageByIds(ids));
    }
}
