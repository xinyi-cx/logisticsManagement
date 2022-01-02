package com.ruoyi.web.controller.business;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.system.domain.AddressReceiver;
import com.ruoyi.system.service.IAddressReceiverService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 收货人信息Controller
 * 
 * @author ruoyi
 * @date 2022-01-02
 */
@RestController
@RequestMapping("/system/receiver")
public class AddressReceiverController extends BaseController
{
    @Autowired
    private IAddressReceiverService addressReceiverService;

    /**
     * 查询收货人信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:receiver:list')")
    @GetMapping("/list")
    public TableDataInfo list(AddressReceiver addressReceiver)
    {
        startPage();
        List<AddressReceiver> list = addressReceiverService.selectAddressReceiverList(addressReceiver);
        return getDataTable(list);
    }

    /**
     * 导出收货人信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:receiver:export')")
    @Log(title = "收货人信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AddressReceiver addressReceiver)
    {
        List<AddressReceiver> list = addressReceiverService.selectAddressReceiverList(addressReceiver);
        ExcelUtil<AddressReceiver> util = new ExcelUtil<AddressReceiver>(AddressReceiver.class);
        util.exportExcel(response, list, "收货人信息数据");
    }

    /**
     * 获取收货人信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:receiver:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(addressReceiverService.selectAddressReceiverById(id));
    }

    /**
     * 新增收货人信息
     */
    @PreAuthorize("@ss.hasPermi('system:receiver:add')")
    @Log(title = "收货人信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AddressReceiver addressReceiver)
    {
        return toAjax(addressReceiverService.insertAddressReceiver(addressReceiver));
    }

    /**
     * 修改收货人信息
     */
    @PreAuthorize("@ss.hasPermi('system:receiver:edit')")
    @Log(title = "收货人信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AddressReceiver addressReceiver)
    {
        return toAjax(addressReceiverService.updateAddressReceiver(addressReceiver));
    }

    /**
     * 删除收货人信息
     */
    @PreAuthorize("@ss.hasPermi('system:receiver:remove')")
    @Log(title = "收货人信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(addressReceiverService.deleteAddressReceiverByIds(ids));
    }
}
