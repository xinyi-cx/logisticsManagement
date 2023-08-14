package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.CodReport;
import com.ruoyi.system.domain.vo.ImportCodReportVo;
import com.ruoyi.system.domain.vo.PackageVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * COD报告Service接口
 * 
 *
 * @date 2023-05-02
 */
public interface ICodReportService 
{
    /**
     * 查询COD报告
     * 
     * @param id COD报告主键
     * @return COD报告
     */
    public CodReport selectCodReportById(Long id);

    /**
     * 查询COD报告列表
     * 
     * @param codReport COD报告
     * @return COD报告集合
     */
    public List<CodReport> selectCodReportList(CodReport codReport);

    /**
     * 新增COD报告
     * 
     * @param codReport COD报告
     * @return 结果
     */
    public int insertCodReport(CodReport codReport);

    public String importCodReport(MultipartFile file, List<ImportCodReportVo> codReports) throws Exception;

    /**
     * 修改COD报告
     * 
     * @param codReport COD报告
     * @return 结果
     */
    public int updateCodReport(CodReport codReport);

    /**
     * 批量删除COD报告
     * 
     * @param ids 需要删除的COD报告主键集合
     * @return 结果
     */
    public int deleteCodReportByIds(Long[] ids);

    /**
     * 删除COD报告信息
     * 
     * @param id COD报告主键
     * @return 结果
     */
    public int deleteCodReportById(Long id);
}
