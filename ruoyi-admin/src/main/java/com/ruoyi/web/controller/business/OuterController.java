package com.ruoyi.web.controller.business;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.system.domain.mb.MbReceiveDto;
import com.ruoyi.system.service.IOuterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public void export(MbReceiveDto mbReceiveDto) throws Exception {
        outerService.receiveMb(mbReceiveDto);
    }

}
