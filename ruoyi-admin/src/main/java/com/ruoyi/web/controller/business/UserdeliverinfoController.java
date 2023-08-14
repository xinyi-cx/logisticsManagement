package com.ruoyi.web.controller.business;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.domain.mb.Userdeliverinfo;
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
import com.ruoyi.system.service.IUserdeliverinfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * user deliver infomation tableController
 * 
 *
 * @date 2023-06-29
 */
@RestController
@RequestMapping("/system/userdeliverinfo")
public class UserdeliverinfoController extends BaseController
{
    @Autowired
    private IUserdeliverinfoService userdeliverinfoService;

    /**
     * 查询user deliver infomation table列表
     */
    @PreAuthorize("@ss.hasPermi('system:userdeliverinfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(Userdeliverinfo userdeliverinfo)
    {
        startPage();
        List<Userdeliverinfo> list = userdeliverinfoService.selectUserdeliverinfoList(userdeliverinfo);
        return getDataTable(list);
    }

    /**
     * 导出user deliver infomation table列表
     */
    @PreAuthorize("@ss.hasPermi('system:userdeliverinfo:export')")
    @Log(title = "user deliver infomation table", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Userdeliverinfo userdeliverinfo)
    {
        List<Userdeliverinfo> list = userdeliverinfoService.selectUserdeliverinfoList(userdeliverinfo);
        ExcelUtil<Userdeliverinfo> util = new ExcelUtil<Userdeliverinfo>(Userdeliverinfo.class);
        util.exportExcel(response, list, "user deliver infomation table数据");
    }


    /**
     * 新增user deliver infomation table
     */
    @PreAuthorize("@ss.hasPermi('system:userdeliverinfo:add')")
    @Log(title = "user deliver infomation table", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Userdeliverinfo userdeliverinfo)
    {
        return toAjax(userdeliverinfoService.insertUserdeliverinfo(userdeliverinfo));
    }

}
