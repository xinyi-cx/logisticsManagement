package com.ruoyi.web.controller.business;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.AddressReceiver;
import com.ruoyi.system.service.IAddressReceiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/system/outer")
public class OuterController extends BaseController
{
    @Autowired
    private IAddressReceiverService addressReceiverService;

    /**
     * 查询收货人信息列表
     */
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
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(addressReceiverService.selectAddressReceiverById(id));
    }

    /**
     * 新增收货人信息
     */
    @PostMapping
    public AjaxResult add(@RequestBody AddressReceiver addressReceiver)
    {
        return toAjax(addressReceiverService.insertAddressReceiver(addressReceiver));
    }

    /**
     * 修改收货人信息
     */
    @PutMapping
    public AjaxResult edit(@RequestBody AddressReceiver addressReceiver)
    {
        return toAjax(addressReceiverService.updateAddressReceiver(addressReceiver));
    }

    /**
     * 删除收货人信息
     */
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(addressReceiverService.deleteAddressReceiverByIds(ids));
    }
}
