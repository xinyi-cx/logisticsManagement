package com.ruoyi.web.controller.business;


import com.ruoyi.common.utils.MultpartFileToByte;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;

@Controller
@RequestMapping(value = "/")
public class MainController {

    //图片上传接口
//    public static String  upLoadFile(MultipartFile file, HttpServletRequest request) {
//        if (file == null) {
//            return  "文件不能为空";
//        }
//        String fileName = file.getOriginalFilename();
//        int index = fileName.lastIndexOf(".");
//        if (index < 0){
//            return  "文件格式不能为空";
//        } else {
//            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
//            if (",jpg,jpeg,png,pdf".indexOf(fileType) < 0){
//                return  "文件类型不合法，只支持jpg、jpeg、png和pdf格式的文件";
//            }
//        }
//
//
//        String path = "";
//        try {
//            String id = storeFile(file.getOriginalFilename(), file.getInputStream(), file.getContentType());
//            path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
//                    + request.getContextPath() + "/downFile?fileUrl=" + URLEncoder.encode(id, "UTF-8");
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println( "======================================  文件上传失败  =======================");
//        }
//        return path;
//    }

    //    d:/test/files/ 这里上传地址是写死的，正常开发是需要写到配置文件里面读取的
//    public static String storeFile(String fileName, InputStream inputStream, String contentType) throws Exception {
//        FileOutputStream out = null;
//        File file2 = null;
//        try {
//            file2 = new File( "d:/test/files/" + UuidUtil.get32UUID() + "/" + fileName);
//            if (!file2.getParentFile().exists()) {
//                file2.getParentFile().mkdirs();
//            }
//            boolean flag = file2.createNewFile();
//            if (!flag) {
//                throw new CannotCreateException("文件无法被创建");
//            }
//            out = new FileOutputStream(file2);
//            byte[] tmp = new byte[1024];
//            int i = -1;
//            while ((i = inputStream.read(tmp)) != -1) {
//                out.write(tmp, 0, i);
//            }
//            //return URLEncoder.encode(file2.getAbsolutePath(), "UTF-8");
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("======================================  文件上传失败  ================================");
//        } finally {
//            IOUtils.closeQuietly(out);
//            IOUtils.closeQuietly(inputStream);
//        }
//        return URLEncoder.encode(file2.getAbsolutePath(), "UTF-8");
//    }



    //图片回显查看接口
//    @RequestMapping(value="/downFile")
    @GetMapping("/downFile")
    public void viewPic(@RequestParam(value = "fileUrl") String fileUrl, HttpServletResponse response) {
        try {
            MultipartFile multipartFile = MultpartFileToByte.getMultipartFile(fileUrl);
            MultipartFile multipartFile2 = MultpartFileToByte.getMultipartFile(multipartFile.getBytes());
//            String path = URLDecoder.decode(fileUrl, "UTF-8");
//            File file = new File(path);
            response.addHeader("Content-Length", "" + multipartFile2.getSize());
            writeFile(fileUrl, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void writeFile(String id, HttpServletResponse response) {
        try {
            String name = id.substring(id.lastIndexOf(java.io.File.separator) + java.io.File.separator.length(), id.length());
            renderResponseFileHeader("name.xlsx", response);
            writeFile(id, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void writeFile(String path, OutputStream outputStream) throws Exception {
        InputStream in = null;
        try {
            MultipartFile multipartFile = MultpartFileToByte.getMultipartFile(path);
            MultipartFile multipartFile2 = MultpartFileToByte.getMultipartFile(multipartFile.getBytes());
            // 4.获取要下载的文件输入流
//            in = new FileInputStream(multipartFile2);
            in =  multipartFile2.getInputStream();
            int len = 0;
            // 5.创建数据缓冲区
            byte[] buffer = new byte[1024];
            // 6.通过response对象获取OutputStream流
            // 7.将FileInputStream流写入到buffer缓冲区
            while ((len = in.read(buffer)) > 0) {
                // 8.使用OutputStream将缓冲区的数据输出到客户端浏览器
                outputStream.write(buffer, 0, len);
            }
            IOUtils.closeQuietly(outputStream);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    public static void  renderResponseFileHeader(String fileName, HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition",
                "attachment; filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
    }


    //通过文件路径获取文件名称
    public static String getFileName(String path) throws Exception {
        path = URLDecoder.decode(path, "UTF-8");
        String name = path.substring(path.lastIndexOf(java.io.File.separator) + java.io.File.separator.length(), path.length());
        return name;
    }

//    //删除图片的同时也要删除服务端存储的图片，减少垃圾文件，优化硬盘空间
//    @RequestMapping(value = "/imgPath", produces = "application/json;charset=utf-8")
//    @ResponseBody
//    public ResponseJson detail(String imgPathString) {
//        //boolean file = FileUtils.deletePhysicalFile(imgPathString);
//        boolean file = deletePhysicalFile(imgPathString);
//        return new ResponseJson(Constants.CODE_SUCCESS, file);
//    }
//
//    //删除物理文件   //删除服务端存储的图片
//    public static boolean deletePhysicalFile(String fileUrl) {
//        if (fileUrl == null || fileUrl.length() == 0) return false;
//        String path = null;
//        boolean isDelete = false;
//        try {
//            String splitReg = "/downFile\\?fileUrl=";
//            String[] fileUrlStrs = fileUrl.split(splitReg);
//            path = fileUrlStrs[fileUrlStrs.length - 1];
//            path = URLDecoder.decode(path, "UTF-8");  //  UTF-8  编码反编译 解密
//            path = URLDecoder.decode(path, "UTF-8");  //  UTF-8  编码反编译 解密
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        File file = new File(path);
//        if (file.exists()) {
//            if (file.delete()) {
//                String filePath = file.getParent();
//                deleteEmptyDirectory(filePath);
//            }
//            isDelete = true;
//        }
//        return isDelete;
//    }
//    public static void deleteEmptyDirectory(String path){
//        File fileDir = new File(path);
//        if (fileDir.listFiles().length > 0)
//            return;
//        else {
//            fileDir.delete();
//        }
//    }

}