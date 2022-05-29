package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.domain.MbImport;
import com.ruoyi.system.domain.mb.MbReceiveDto;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 收货人信息Service接口
 * 
 * @author ruoyi
 * @date 2022-01-02
 */
public interface IOuterService
{

    /**
     * 接收马帮的主动通知
     *
     * @param mbReceiveDto
     * @throws Exception
     */
    public void receiveMb(MbReceiveDto mbReceiveDto) throws Exception;

    public void changeAccept(MbImport mbImport);

    public void getPDF(String pdfUrl, HttpServletResponse response);

    public List<String> importPackage(MultipartFile file, List<MbImport> mbImportList, SysUser sysUser, Boolean userFlag) throws Exception;

}
