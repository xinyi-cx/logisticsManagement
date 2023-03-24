package com.ruoyi.web.controller.business;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.DPDinfo.pl.com.dpd.dpdinfoservices.events.Exception_Exception;
import com.ruoyi.system.domain.MbImport;
import com.ruoyi.system.domain.mb.MbReceiveDto;
import com.ruoyi.system.service.IOuterService;
import com.ruoyi.system.service.IPackageService;
import com.ruoyi.system.service.IParcelService;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/receiveMb")
public class OuterController extends BaseController {
    @Autowired
    private IOuterService outerService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private IPackageService packageService;

    @Autowired
    private IParcelService parcelService;

    /**
     * test
     */
    @GetMapping("/test")
    public void test() {
        outerService.test();
    }

    @GetMapping("/testSendEmail")
    public void testSendEmail() {
        packageService.testSendEmailFile();
    }

    /**
     * 接收马帮的主动通知
     *
     * @param mbReceiveDto
     * @throws Exception
     */
    @PostMapping
    public void receiveMb(MbReceiveDto mbReceiveDto) throws Exception {
        outerService.receiveMb(mbReceiveDto);
    }

    /**
     * 接收马帮的主动通知
     *
     * @return
     */
    @PostMapping("/getPDF")
    public void getPDF(MbImport mbImport, HttpServletResponse response) {
        outerService.getPDF(mbImport.getPdfUrl(), response);
    }

//    @PostMapping("/changeAccept")
//    public void changeAccept(MbImport mbImport) {
//        outerService.changeAccept(mbImport);
//    }

    /**
     * 获取用户列表
     */
    @PostMapping("/getUser")
    public AjaxResult getUserForLogin(@RequestBody SysUser user) {
        List<String> list = userService.getUserForLogin(user);
        return AjaxResult.success(CollectionUtils.isEmpty(list) ? new ArrayList<>() : list);
    }

    @GetMapping(value = "/getParcelMsgById/{id}")
    public void getParcelMsgById(@PathVariable("id") Long id) {
        parcelService.getParcelMsgById(id);
    }

    @GetMapping(value = "/getParcelMsgByLogisticsInfoId/{id}")
    public void getParcelMsgByLogisticsInfoId(@PathVariable("id") Long id) {
        parcelService.getParcelMsgByLogisticsInfoId(id);
    }

    @GetMapping(value = "/getStringByWaybill/{waybill}")
    public String getStringByWaybill(@PathVariable("waybill") String waybill) throws Exception_Exception {
        return parcelService.getStringByWaybill(waybill);
    }

}
