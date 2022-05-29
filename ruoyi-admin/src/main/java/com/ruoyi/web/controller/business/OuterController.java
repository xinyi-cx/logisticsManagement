package com.ruoyi.web.controller.business;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.system.domain.MbImport;
import com.ruoyi.system.domain.mb.MbReceiveDto;
import com.ruoyi.system.service.IOuterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/receiveMb")
public class OuterController extends BaseController {
    @Autowired
    private IOuterService outerService;

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
     * @return
     */
    @PostMapping("/getPDF")
    public void getPDF(MbImport mbImport, HttpServletResponse response) {
        outerService.getPDF(mbImport.getPdfUrl(), response);
    }

    @PostMapping("/changeAccept")
    public void changeAccept(MbImport mbImport) {
        outerService.changeAccept(mbImport);
    }

}
