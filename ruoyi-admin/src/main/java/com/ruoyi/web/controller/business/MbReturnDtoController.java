package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.MbImport;
import com.ruoyi.system.domain.UserAuthorizationSys;
import com.ruoyi.system.domain.mb.MbReturnDto;
import com.ruoyi.system.service.IMbReturnDtoService;
import com.ruoyi.system.service.IOuterService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.IUserAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 马帮订单信息Controller
 *
 * @author ruoyi
 * @date 2022-05-16
 */
@RestController
@RequestMapping("/system/dto")
public class MbReturnDtoController extends BaseController {
    @Autowired
    private IMbReturnDtoService mbReturnDtoService;

    @Autowired
    private IOuterService outerService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private IUserAuthorizationService userAuthorizationService;

    @Value("${mb.importUserFlag}")
    private Boolean userFlag;

    /**
     * 查询马帮订单信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:dto:list')")
    @GetMapping("/list")
    public TableDataInfo list(MbReturnDto mbReturnDto) {
        startPage();
        List<MbReturnDto> list = mbReturnDtoService.selectMbReturnDtoList(mbReturnDto);
        return getDataTable(list);
    }

    /**
     * 导出马帮订单信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:dto:export')")
    @Log(title = "马帮订单信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MbReturnDto mbReturnDto) {
        List<MbReturnDto> list = mbReturnDtoService.selectMbReturnDtoList(mbReturnDto);
        ExcelUtil<MbReturnDto> util = new ExcelUtil<MbReturnDto>(MbReturnDto.class);
        util.exportExcel(response, list, "马帮订单信息数据");
    }

    /**
     * 获取马帮订单信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:dto:query')")
    @GetMapping(value = "/{code}")
    public AjaxResult getInfo(@PathVariable("code") String code) {
        return AjaxResult.success(mbReturnDtoService.selectMbReturnDtoByCode(code));
    }

    /**
     * 新增马帮订单信息
     */
    @PreAuthorize("@ss.hasPermi('system:dto:add')")
    @Log(title = "马帮订单信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MbReturnDto mbReturnDto) {
        return toAjax(mbReturnDtoService.insertMbReturnDto(mbReturnDto));
    }

    /**
     * 修改马帮订单信息
     */
    @PreAuthorize("@ss.hasPermi('system:dto:edit')")
    @Log(title = "马帮订单信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MbReturnDto mbReturnDto) {
        return toAjax(mbReturnDtoService.updateMbReturnDto(mbReturnDto));
    }

    /**
     * 删除马帮订单信息
     */
    @PreAuthorize("@ss.hasPermi('system:dto:remove')")
    @Log(title = "马帮订单信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{codes}")
    public AjaxResult remove(@PathVariable String[] codes) {
        return toAjax(mbReturnDtoService.deleteMbReturnDtoByCodes(codes));
    }

    /**
     * 下载马帮导入模板
     *
     * @param response
     */
    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) {
        ExcelUtil<MbImport> util = new ExcelUtil<MbImport>(MbImport.class);
        util.importTemplateExcel(response, "马帮数据");
    }

    @Log(title = "马帮导入", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file) throws Exception {
        ExcelUtil<MbImport> util = new ExcelUtil<MbImport>(MbImport.class);
        List<MbImport> mbImportList = util.importExcel(file.getInputStream());
        SysUser sysUser = sysUserService.selectUserById(SecurityUtils.getLoginUser().getUserId());
        if (StringUtils.isEmpty(sysUser.getApiKey()) || null == sysUser.getApiAccountId()){
            return AjaxResult.error("请先绑定马帮正确的apiAccountId, apiKey到此用户");
        }
        //自己导入暂时不验证授权
        if (userFlag){
            UserAuthorizationSys userAuthorizationSys = new UserAuthorizationSys();
            userAuthorizationSys.setCreateBy(sysUser.getUserId().toString());
            List<UserAuthorizationSys> userAuthorizationSys1 = userAuthorizationService.selectUserAuthorizationList(userAuthorizationSys);
            if (CollectionUtils.isEmpty(userAuthorizationSys1)){
                return AjaxResult.error("此用户未授权任何客户");
            }
        }
        List<String> errorMsg = outerService.importPackage(file, mbImportList, sysUser, userFlag);
        if (CollectionUtils.isEmpty(errorMsg)) {
            return AjaxResult.success("导入成功");
        } else {
            return AjaxResult.success(errorMsg.stream().collect(Collectors.joining(",")) +
                    "其余导入成功"
            );
        }

    }


}
