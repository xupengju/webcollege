package com.college.controller;

import com.college.contants.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.UUID;

/**
 * @author Milo on 2018/4/22.
 * @description
 */
@Controller
@RequestMapping
public class UploadController {
    private static Logger logger = LoggerFactory
            .getLogger(UploadController.class);

    /**
     * 上传图片
     *
     * @param request
     * @param response
     * @param file
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @RequestMapping(value = Path.FILE_UPLOAD)
    @ResponseBody
    public String uploadImage(HttpServletRequest request,
                              HttpServletResponse response,
                              @RequestParam(value = "file") MultipartFile file)
            throws IllegalStateException, IOException {

        logger.info("uploadImage :{}", "");
        HttpSession session = request.getSession();
        // fileName唯一性
        String path = session.getServletContext().getRealPath("/");
        // 原始文件名
        String originalFileName = file.getOriginalFilename();
        // 获取图片后缀
        String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
        // 生成图片存储的名称，UUID 避免相同图片名冲突，并加上图片后缀
        String fileName = UUID.randomUUID().toString() + suffix;
        File targetFile = new File(path, fileName);

        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        /**
         * MultipartFile接口中定义了如下很多有用的方法。
         使用getSize()方法获得文件长度，以此决定允许上传的文件大小。
         使用isEmpty()方法判断上传文件是否为空文件，以此决定是否拒绝空文件。
         使用getInputStream()方法将文件读取为java.io.InputStream流对象。
         使用getContentType()方法获得文件类型，以此决定允许上传的文件类型。
         使用transferTo（dest）方法将上传文件写到服务器上指定的文件
         */
        file.transferTo(targetFile);
        return request.getContextPath() + "/" + fileName;
    }


    /*
     * 采用file.Transto 来保存上传的文件
     */
    @RequestMapping(Path.FILE_UPLOAD_FILE1)
    @ResponseBody
    public String fileUpload2(@RequestParam("file") CommonsMultipartFile file) throws IOException {
        long startTime = System.currentTimeMillis();
        System.out.println("fileName：" + file.getOriginalFilename());
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")).toLowerCase();
        // 生成图片存储的名称，UUID 避免相同图片名冲突，并加上图片后缀
        String fileName = UUID.randomUUID().toString() + suffix;
        if (".doc".equals(suffix) || ".docx".equals(suffix) || ".txt".equals(suffix) || ".pdf".equals(suffix)){
            String path = "/data/wwwroot/default/" + fileName ;

            File newFile = new File(path);
            //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
            file.transferTo(newFile);
            long endTime = System.currentTimeMillis();
            System.out.println("方法二的运行时间：" + String.valueOf(endTime - startTime) + "ms");
            return path;
        }

        return "format error";
    }

    /*
     *采用spring提供的上传文件的方法
     */
    @RequestMapping(Path.FILE_UPLOAD_FILE2)
    @ResponseBody
    public String springUpload(HttpServletRequest request) throws IllegalStateException, IOException {
        long startTime = System.currentTimeMillis();
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        String path = "";
        if (multipartResolver.isMultipart(request)) {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //获取multiRequest 中所有的文件名
            Iterator iter = multiRequest.getFileNames();

            while (iter.hasNext()) {
                //一次遍历所有文件
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                if (file != null) {
                    path = "/data/wwwroot/default/" + file.getOriginalFilename();
                    //上传
                    file.transferTo(new File(path));
                }

            }

        }
        long endTime = System.currentTimeMillis();
        System.out.println("方法三的运行时间：" + String.valueOf(endTime - startTime) + "ms");
        return path;
    }
}