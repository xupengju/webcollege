package com.college.controller;

import com.college.contants.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
public class DownLoadServlet {
    private static Logger logger = LoggerFactory
            .getLogger(DownLoadServlet.class);

    /**
     * 文件下载
     *
     * @param fileName
     * @param request
     * @param response
     * @return
     * @Description:
     */
    @RequestMapping(Path.FILE_DOWN_FILE)
    public String downloadFile(@RequestParam("fileName") String fileName,
                               HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        if (fileName != null) {
            String realPath = request.getServletContext().getRealPath("");
            File file = new File(realPath, fileName);
            logger.info("FILE_DOWN_FILE realPath: {}", realPath);
            logger.info("FILE_DOWN_FILE file: {}", file);
            if (file.exists()) {
                String suffix = file.getName().substring(file.getName().lastIndexOf(".")).toLowerCase();
                logger.info("FILE_DOWN_FILE suffix: {}", suffix);
                if (".doc".equals(suffix) || ".docx".equals(suffix) || ".txt".equals(suffix) || ".pdf".equals(suffix)) {
                    response.setContentType("application/force-download");// 设置强制下载不打开
                    //response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("gbk"),"iso-8859-1") + "\"");    byte[] buffer = new byte[1024];
                    FileInputStream fis = null;
                    BufferedInputStream bis = null;
                    try {
                        fis = new FileInputStream(file);
                        bis = new BufferedInputStream(fis);
                        OutputStream os = response.getOutputStream();
                        int i = bis.read(buffer);
                        while (i != -1) {
                            os.write(buffer, 0, i);
                            i = bis.read(buffer);
                        }
                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                    } finally {
                        if (bis != null) {
                            try {
                                bis.close();
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                        if (fis != null) {
                            try {
                                fis.close();
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

        }
        return null;
    }


    /**
     * 构建下载类
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static ResponseEntity<byte[]> buildResponseEntity(File file) throws IOException {
        byte[] body = null;
        //获取文件
        InputStream is = new FileInputStream(file);
        body = new byte[is.available()];
        is.read(body);
        HttpHeaders headers = new HttpHeaders();
        //设置文件类型
        headers.add("Content-Disposition", "attchement;filename=" + file.getName());
        //设置Http状态码
        HttpStatus statusCode = HttpStatus.OK;
        //返回数据
        ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
        return entity;
    }
}