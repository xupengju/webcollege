package com.college.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Milo on 2018/4/22.
 * @description
 */
@Controller
@RequestMapping(value = "upload")
public class UploadController {

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
    @RequestMapping(value = "/uploadimage", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String uploadImage(HttpServletRequest request,
                              HttpServletResponse response,
                              @RequestParam(value = "file") MultipartFile file)
            throws IllegalStateException, IOException {
        HttpSession session = request.getSession();
        // fileName唯一性
        String path = session.getServletContext().getRealPath("/media/temporaryupload");
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
        return request.getContextPath() + "/media/temporaryupload/" + fileName;
    }
}
