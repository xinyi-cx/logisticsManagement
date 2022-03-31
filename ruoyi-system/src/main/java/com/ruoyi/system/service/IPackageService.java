package com.ruoyi.system.service;

import com.ruoyi.system.domain.Package;
import com.ruoyi.system.domain.vo.PackageVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 面单Service接口
 * 
 * @author ruoyi
 * @date 2022-01-02
 */
public interface IPackageService 
{
    /**
     * 查询面单
     * 
     * @param id 面单主键
     * @return 面单
     */
    public PackageVo selectPackageById(Long id);

    /**
     * 获取echarts数据
     * @param id 批次id
     * @return
     */
    public Map getStatistics(Long id);

    /**
     * 查询面单列表
     * 
     * @param pkg 面单
     * @return 面单集合
     */
    public List<Package> selectPackageList(Package pkg);

    public void getResponse(Long pkgId) throws IOException;

    /**
     * 下载pdf
     * @param response
     * @param pkgId
     */
    public void getPDFById(HttpServletResponse response, Long pkgId) throws IOException;

    /**
     * 查询面单列表
     *
     * @param packageVo 面单
     * @return 面单集合
     */
    public List<PackageVo> selectPackageVoList(PackageVo packageVo);

    /**
     * 更细下载数量
     * @param ids
     * @return
     */
    public void updateDownloadNum(List<Long> ids);

  /**
     * 新增面单
     * 
     * @param pkg 面单
     * @return 结果
     */
    public int insertPackage(Package pkg);

    /**
     * 新增面单
     *
     * @param pkg 面单
     * @return 结果
     */
    public int insertPackage(PackageVo pkg);

    /**
     * 下载文件
     * @param response
     * @param id
     * @throws Exception
     */
    public void writeFile(HttpServletResponse response, Long id) throws Exception;
    /**
     * 导入面单
     *
     * @param packageVos 面单
     * @return 结果
     */
    public void importPackage(MultipartFile file, List<PackageVo> packageVos) throws IOException;

    /**
     * 修改面单
     * 
     * @param pkg 面单
     * @return 结果
     */
    public int updatePackage(PackageVo pkg);

    /**
     * 批量删除面单
     *
     * @param ids 需要删除的面单主键集合
     * @return 结果
     */
    public int deletePackageByIds(Long[] ids);

    /**
     * 删除面单信息
     * 
     * @param id 面单主键
     * @return 结果
     */
    public int deletePackageById(Long id);
}
