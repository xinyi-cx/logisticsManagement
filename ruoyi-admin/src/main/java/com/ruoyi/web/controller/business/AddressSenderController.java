package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.AddressSender;
import com.ruoyi.system.service.IAddressSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 发件人信息Controller
 *
 *
 * @date 2022-01-02
 */
@RestController
@RequestMapping("/system/sender")
public class AddressSenderController extends BaseController {
    @Autowired
    private IAddressSenderService addressSenderService;

    /**
     * 查询发件人信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(AddressSender addressSender) {
        startPage();
        List<AddressSender> list = addressSenderService.selectAddressSenderList(addressSender);
        return getDataTable(list);
    }

    /**
     * 导出发件人信息列表
     */
    @Log(title = "发件人信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AddressSender addressSender) {
        List<AddressSender> list = addressSenderService.selectAddressSenderList(addressSender);
        ExcelUtil<AddressSender> util = new ExcelUtil<AddressSender>(AddressSender.class);
        util.exportExcel(response, list, "发件人信息数据");
    }

    /**
     * 获取发件人信息详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(addressSenderService.selectAddressSenderById(id));
    }

    /**
     * 新增发件人信息
     */
    @Log(title = "发件人信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AddressSender addressSender) {
        return toAjax(addressSenderService.insertAddressSender(addressSender));
    }

    /**
     * 修改发件人信息
     */
    @Log(title = "发件人信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AddressSender addressSender) throws Exception {
        return toAjax(addressSenderService.updateAddressSender(addressSender));
    }

    /**
     * 删除发件人信息
     */
    @Log(title = "发件人信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(addressSenderService.deleteAddressSenderByIds(ids));
    }
}
