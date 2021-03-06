package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Package;
import com.ruoyi.system.domain.vo.ExportPackageVo;
import com.ruoyi.system.domain.vo.PackageVo;
import com.ruoyi.system.service.IPackageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("@ss.hasPermi('system:package:list')")
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
    @PreAuthorize("@ss.hasPermi('system:package:list')")
    @GetMapping("/listAll")
    public TableDataInfo list(PackageVo pkg)
    {
        startPage();
        List<PackageVo> list = packageService.selectPackageVoList(pkg);
        return getDataTable(list);
    }

    /**
     * 转寄面单页面查询面单列表
     */
    @PreAuthorize("@ss.hasPermi('system:package:list')")
    @GetMapping("/all")
    public List<PackageVo> all(PackageVo pkg)
    {
        return packageService.selectPackageVoList(pkg);
    }

    /**
     * 导出面单列表
     */
    @PreAuthorize("@ss.hasPermi('system:package:export')")
    @Log(title = "面单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PackageVo pkg)
    {
        List<PackageVo> list = packageService.selectPackageVoList(pkg);
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
    @PreAuthorize("@ss.hasPermi('system:package:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(packageService.selectPackageById(id));
    }

    /**
     * 新增面单
     */
    @PreAuthorize("@ss.hasPermi('system:package:add')")
    @Log(title = "面单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Package pkg)
    {
        return toAjax(packageService.insertPackage(pkg));
    }

    /**
     * 新增面单
     */
    @PreAuthorize("@ss.hasPermi('system:package:add')")
    @Log(title = "面单", businessType = BusinessType.INSERT)
    @PostMapping(value = "/addAll")
    public AjaxResult addAll(@RequestBody @Valid PackageVo pkg) throws Exception
    {
        return toAjax(packageService.insertPackage(pkg));
    }

    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response)
    {
        ExcelUtil<PackageVo> util = new ExcelUtil<PackageVo>(PackageVo.class);
        util.importTemplateExcel(response, "面单数据");
    }

    @PostMapping("/downloadFile/{id}")
    public void importTemplate(HttpServletResponse response, @PathVariable("id") Long id) throws Exception {
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
//        pkgId
        packageService.getPDFById(response, id);
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

    @Log(title = "面单导入", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('system:package:add')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file) throws Exception
    {
        ExcelUtil<PackageVo> util = new ExcelUtil<PackageVo>(PackageVo.class);
        List<PackageVo> packageVos = util.importExcel(file.getInputStream());
        packageService.importPackage(file, packageVos);
        return AjaxResult.success("导入成功");
    }

    /**
     * 修改面单
     */
    @PreAuthorize("@ss.hasPermi('system:package:edit')")
    @Log(title = "面单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PackageVo pkg)
    {
        return toAjax(packageService.updatePackage(pkg));
    }

    /**
     * 删除面单
     */
    @PreAuthorize("@ss.hasPermi('system:package:remove')")
    @Log(title = "面单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(packageService.deletePackageByIds(ids));
    }
}
