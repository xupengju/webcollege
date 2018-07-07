package com.college.controller;

import com.college.entity.User;
import com.college.service.UserService;
import com.college.service.user.ApiUserService;
import com.college.utils.ImportExeclUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping
public class ImportExcelControl {
    // 日志
    private static Logger logger = LoggerFactory.getLogger(ImportExcelControl.class);
    // 用户serveice
    @Autowired
    private ApiUserService apiUserService;
    @Autowired
    private UserService userService;
    /**
     * 描述:通过传统方式form表单提交方式导入excel文件
     *
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "/api/file/importExcel.json", method = {RequestMethod.GET, RequestMethod.POST})
    public String uploadExcel(HttpServletRequest request) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        System.out.println("通过传统方式form表单提交方式导入excel文件!");
        InputStream in = null;
        List listob = null;
        MultipartFile file = multipartRequest.getFile("upfile");
        if (file.isEmpty()) {
            throw new Exception("文件不存在!");
        }
        in = file.getInputStream();
        ImportExeclUtil util = new ImportExeclUtil();
        List<List<String>> list = util.getBankListByExcel(in, file.getOriginalFilename());
        in.close();
        //该处可调用service相应方法进行数据保存到数据库中,现只对数据输出
        ImportExeclUtil.printTest(list);
        this.importUser2DB(list);
//        return "import result success";
        return "index";
    }

    /**
     * 导入表格数据 录入数据库
     * @param list
     */
    private void importUser2DB(List<List<String>>list){
        logger.info("ImportExcel-addUser user - start");
        if (list != null) {
            // 第一行是表头 无效信息不入库
            for (int i = 1; i < list.size(); i++) {
                System.out.print("第" + (i) + "行");
                List<String> cellList = list.get(i);
                User user = new User();
                String userName = "";
                String salt = "";
                salt = apiUserService.getRandomSalt();
                String password = "";
                String realName = "";
                String idCard = "";
                for (int j = 0; j < cellList.size(); j++) {
                    // System.out.print("    第" + (j + 1) + "列值：");
                    // 第一列 用户名
                    if (j == 0) {
                        userName = cellList.get(j);
                        if (userName.equals("") || userName == null){
                            return;
                        }
                    }
                    // 第二列 密码 自动初始化 身份ID 后6位
                    if (j == 1) {
//                        password = cellList.get(j);
                    }
                    // 第三列 真实姓名
                    if (j == 2) {
                        realName = cellList.get(j);
                    }
                    // 第四列 身份ID
                    if (j == 3) {
                        idCard = cellList.get(j);
                        // 密码 自动初始化 身份ID 后6位
                        password = idCard.substring(idCard.length() - 6);
                    }
                    System.out.print("    " + cellList.get(j));
                }
                System.out.println();
                boolean status = true;
//                String createTime = DateTimeUtil.getDateAndMinute();
                user.setUserName(userName);
                user.setPassword(password);
                user.setSalt(salt);
                user.setRealName(realName);
                user.setIdCard(idCard);
//                    user.setAdministrator(administrator);
                user.setStatus(status);
                user.setCreateTime(new Date());
                Integer userId = userService.insert(user);
                logger.info("ImportExcel-addUser userId :{}", userId);
            }
        }
        logger.info("ImportExcel-addUser user - end");
    }

    /**
     * 生成20位加密盐值
     * @return
     */
  /*  private String generateSaltStr() {
        Random ranGen = new SecureRandom();
        byte[] aesKey = new byte[20];
        ranGen.nextBytes(aesKey);
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < aesKey.length; i++) {
            String hex = Integer.toHexString(0xff & aesKey[i]);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }*/
    /**
     * 描述:通过 jquery.form.js 插件提供的ajax方式上传文件
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/api/file/ajaxUpload.json", method = {RequestMethod.GET, RequestMethod.POST})
    public void ajaxUploadExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        System.out.println("通过 jquery.form.js 提供的ajax方式上传文件!");
        InputStream in = null;
        List<String> listob = null;
        MultipartFile file = multipartRequest.getFile("upfile");
        if (file.isEmpty()) {
            throw new Exception("文件不存在!");
        }
        in = file.getInputStream();
        ImportExeclUtil util = new ImportExeclUtil();
        List<List<String>> list = util.getBankListByExcel(in, file.getOriginalFilename());
        //该处可调用service相应方法进行数据保存到数据库中,现只对数据输出
        ImportExeclUtil.printTest(list);
        this.importUser2DB(list);
        PrintWriter out = null;
        response.setCharacterEncoding("utf-8");//防止ajax接受到的中文信息乱码
        out = response.getWriter();
        out.print("文件导入成功!");
        out.flush();
        out.close();
    }
}