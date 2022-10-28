package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.LogisticsInfo;
import com.ruoyi.system.domain.Parcel;
import com.ruoyi.system.service.IBatchTaskHistoryService;
import com.ruoyi.system.service.ILogisticsInfoService;
import com.ruoyi.system.service.IParcelService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 物流信息Controller
 *
 * @author ruoyi
 * @date 2022-04-07
 */
@RestController
@RequestMapping("/system/info")
public class LogisticsInfoController extends BaseController {
    @Autowired
    private ILogisticsInfoService logisticsInfoService;

    @Autowired
    private IBatchTaskHistoryService batchTaskHistoryService;

    @Autowired
    private IParcelService parcelService;

    /**
     * 查询物流信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(LogisticsInfo logisticsInfo) {
        startPage();
        if (ObjectUtils.isNotEmpty(logisticsInfo.getHisParam())) {
            List<String> batchWList = batchTaskHistoryService.getWaybillsByBatchTaskHistoryId(logisticsInfo.getHisParam());
            List<LogisticsInfo> batchList = logisticsInfoService.selectLogisticsInfoListByWaybillIn(batchWList);
            if (!CollectionUtils.isEmpty(batchList)) {
                logisticsInfo.setIds(batchList.stream().map(LogisticsInfo::getId).collect(Collectors.toList()));
            } else {
                return getDataTable(new ArrayList<>());
            }
        }
        List<LogisticsInfo> list = logisticsInfoService.selectLogisticsInfoList(logisticsInfo);
        return getDataTable(list);
    }

    /**
     * 查询物流信息列表
     */
    @GetMapping("/refresh")
    public String refresh(LogisticsInfo logisticsInfo) {
        if (ObjectUtils.isNotEmpty(logisticsInfo.getHisParam())) {
            List<String> batchWList = batchTaskHistoryService.getWaybillsByBatchTaskHistoryId(logisticsInfo.getHisParam());
            List<LogisticsInfo> batchList = logisticsInfoService.selectLogisticsInfoListByWaybillIn(batchWList);
            if (!CollectionUtils.isEmpty(batchList)) {
                logisticsInfo.setIds(batchList.stream().map(LogisticsInfo::getId).collect(Collectors.toList()));
            }
        }
        parcelService.getParcelMsgByLogisticsInfo(logisticsInfo);
        parcelService.getParcelMsg(new Parcel());

        return "已经刷新物流，请稍后查看最新消息";
    }

    /**
     * 导出物流信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:export')")
    @Log(title = "物流信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, LogisticsInfo logisticsInfo) {
        List<LogisticsInfo> list = logisticsInfoService.selectLogisticsInfoList(logisticsInfo);
        ExcelUtil<LogisticsInfo> util = new ExcelUtil<LogisticsInfo>(LogisticsInfo.class);
        util.exportExcel(response, list, "物流信息数据");
    }

    /**
     * 获取物流信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(logisticsInfoService.selectLogisticsInfoById(id));
    }

    /**
     * 查询物流信息列表
     */
    @GetMapping("/refreshById/{id}")
    public String refreshById(@PathVariable("id") Long id) {
        parcelService.getParcelMsgByLogisticsInfoId(id);
        return "已经刷新物流，请查看最新消息";
    }

    /**
     * 新增物流信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:add')")
    @Log(title = "物流信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LogisticsInfo logisticsInfo) {
        return toAjax(logisticsInfoService.insertLogisticsInfo(logisticsInfo));
    }

    /**
     * 修改物流信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:edit')")
    @Log(title = "物流信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LogisticsInfo logisticsInfo) {
        return toAjax(logisticsInfoService.updateLogisticsInfo(logisticsInfo));
    }

    /**
     * 删除物流信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:remove')")
    @Log(title = "物流信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(logisticsInfoService.deleteLogisticsInfoByIds(ids));
    }
}
