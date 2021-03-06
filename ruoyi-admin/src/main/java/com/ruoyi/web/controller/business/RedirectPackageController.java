package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.vo.ExportPackageVo;
import com.ruoyi.system.domain.vo.PackageVo;
import com.ruoyi.system.domain.vo.REPackageVo;
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
 * 转寄面单原面单关联关系Controller
 *
 * @author ruoyi
 * @date 2022-01-17
 */
@RestController
@RequestMapping("/system/redirect")
public class RedirectPackageController extends BaseController
{
    @Autowired
    private IPackageService packageService;

    /**
     * 查询转寄面单原面单关联关系列表
     */
    @PreAuthorize("@ss.hasPermi('system:package:list')")
    @GetMapping("/list")
    public TableDataInfo list(PackageVo pkg)
    {
        startPage();
        List<PackageVo> list = packageService.selectPackageVoList(pkg);
        return getDataTable(list);
    }

    /**
     * 导出转寄面单原面单关联关系列表
     */
    @PreAuthorize("@ss.hasPermi('system:package:export')")
    @Log(title = "转寄面单原面单关联关系", businessType = BusinessType.EXPORT)
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
        util.exportExcel(response, exportPackageVos, "转寄面单原面单关联关系数据");
    }

    /**
     * 获取转寄面单原面单关联关系详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:package:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(packageService.selectPackageById(id));
    }

    /**
     * 新增转寄面单原面单关联关系
     */
    @PreAuthorize("@ss.hasPermi('system:package:add')")
    @Log(title = "转寄面单原面单关联关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PackageVo pkg) throws Exception
    {
        return toAjax(packageService.insertPackage(pkg));
    }

    /**
     * 修改转寄面单原面单关联关系
     */
    @PreAuthorize("@ss.hasPermi('system:package:edit')")
    @Log(title = "转寄面单原面单关联关系", businessType = BusinessType.UPDATE)
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

    @Log(title = "转寄面单导入", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('system:package:add')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file) throws Exception
    {
        ExcelUtil<REPackageVo> util = new ExcelUtil<REPackageVo>(REPackageVo.class);
        List<REPackageVo> rePackageVos = util.importExcel(file.getInputStream());
        List<PackageVo> packageVos = rePackageVos.stream().map(item ->
                {
                    PackageVo packageVo = new PackageVo();
                    BeanUtils.copyProperties(item, packageVo);
                    return packageVo;
                }
        ).collect(toList());
        packageService.importPackage(file, packageVos);
        return AjaxResult.success("导入成功");
    }

    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response)
    {
        ExcelUtil<REPackageVo> util = new ExcelUtil<REPackageVo>(REPackageVo.class);
        util.importTemplateExcel(response, "转寄面单数据");
    }

}
