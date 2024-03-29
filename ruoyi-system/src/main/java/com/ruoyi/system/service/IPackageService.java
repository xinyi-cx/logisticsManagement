package com.ruoyi.system.service;

import com.ruoyi.system.domain.BatchTaskHistory;
import com.ruoyi.system.domain.Package;
import com.ruoyi.system.domain.busenum.ImportTypeEnum;
import com.ruoyi.system.domain.vo.PackageVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 面单Service接口
 * 
 *
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
     * @param dateStr 批次 时间 格式为 yyyymmdd
     * @return
     */
    public Map getStatistics(String dateStr);

    public Map getStatisticsAllUser(String dateStr);

    /**
     * 获取物流状态的echarts数据
     * @param dateStr
     * @return
     */
    public Map getStatisticsForParcel(String dateStr);

    /**
     * 查询面单列表
     * 
     * @param pkg 面单
     * @return 面单集合
     */
    public List<Package> selectPackageList(Package pkg);

    public void getResponse(Long pkgId) throws IOException;

    /**
     * 下载导入错误信息
     * @param response
     * @param id
     * @throws IOException
     */
    public void getTxtById(HttpServletResponse response, Long id) throws IOException;

    /**
     * 下载pdf
     * @param response
     * @param pkgId
     */
    public void getPDFById(HttpServletResponse response, Long pkgId) throws IOException;

    public void getPDFByBatchId(HttpServletResponse response, Long batchId) throws IOException;

    /**
     * 查询面单列表
     *
     * @param packageVo 面单
     * @return 面单集合
     */
    public List<PackageVo> selectPackageVoList(PackageVo packageVo, String numRedisKey);

    /**
     * 查询面单列表
     *
     * @param packageVo 面单
     * @return 面单集合
     */
    public long selectPackageVoListTotal(PackageVo packageVo, String numRedisKey);

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
    public int insertPackage(PackageVo pkg) throws Exception;

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
    public String importPackage(MultipartFile file, List<PackageVo> packageVos, ImportTypeEnum importTypeEnum) throws Exception;

    public BatchTaskHistory importPackage(MultipartFile file, ImportTypeEnum importTypeEnum, String country) throws Exception;

    public String importPackageForNoGen(MultipartFile file, List<PackageVo> packageVos) throws Exception;

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

    public void testSendEmailFile();
}
