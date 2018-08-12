package com.college.controller;

import com.college.entity.Role;
import com.college.entity.User;
import com.college.entity.UserRole;
import com.college.model.Resp;
import com.college.service.RoleService;
import com.college.service.UserRoleService;
import com.college.service.UserService;
import com.college.service.user.ApiUserService;
import com.college.utils.ImportExeclUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleService roleService;

    /**
     * 描述:通过传统方式form表单提交方式导入excel文件
     *
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "/api/file/importExcel.json", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Resp uploadExcel(HttpServletRequest request) throws Exception {
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
        return Resp.success();
    }

    /**
     * 导入表格数据 录入数据库
     *
     * @param list
     */
    private void importUser2DB(List<List<String>> list) {
        logger.info("ImportExcel-addUser user - start");
        if (list != null) {
            // 第一行是表头 无效信息不入库
            for (int i = 1; i < list.size(); i++) {
                System.out.print("第" + (i) + "行");
                List<String> cellList = list.get(i);
                User user = new User();
                String userName = "";
                String salt = "";
                salt = ApiUserService.getRandomSalt();
                String realName = "";
                String idCard = "";
                String roleName = "";
                Integer roleId = 3; // 默认角色是学生  -- 1：管理员 - 2：教师 -  3：学生
                int sex = 0;
                String sexStr;
                String school = "";
                String className = "";
                String classNo = "";
                String phone = "";
                String email = "";
                for (int j = 0; j < cellList.size(); j++) {
                    // System.out.print("    第" + (j + 1) + "列值：");
                    // 第一列 用户名
                    if (j == 0) {
                        userName = cellList.get(j);
                        if ("".equals(userName)) {
                            return;
                        }
                    }

                    if (j == 1) {
                        realName = cellList.get(j);
                    }

                    if (j == 2) {
                        idCard = cellList.get(j);
                    }


                    // 第四列 用户角色
                    if (j == 3) {
                        roleName = cellList.get(j).trim();
                        // 根据roleName 判断 角色id
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("roleName", roleName);
                        Role role = roleService.searchOne(map);
                        if (null != role.getId()) {
                            roleId = role.getId();
                        }
                    }
                    //性别
                    if (j == 4) {
                        sexStr = cellList.get(j).trim();
                        if (sexStr.equals("男")){
                            sex = 1;
                        }else if (sexStr.equals("女")){
                            sex = 0;
                        }else {
                            sex = 2;
                        }

                    }
                    //学号
                    if (j == 5) {
                        classNo = cellList.get(j);
                    }
                    //学校专业班级
                    if (j == 6) {
                        className = cellList.get(j);
                    }
                    //联系方式
                    if (j == 7) {
                        phone = cellList.get(j);
                    }
                    //邮箱
                    if (j == 8) {
                        email = cellList.get(j);
                    }
                    System.out.print("    " + cellList.get(j));
                }
                user.setUserName(userName);
                String password = idCard.substring(idCard.length()-6);
                password = ApiUserService.getEncryptedPassword(password, salt);
                user.setPassword(password);
                user.setSalt(salt);
                user.setRealName(realName);
                user.setIdCard(idCard);
                user.setStatus(true);
                user.setSex(sex);
                user.setPhone(phone);
                user.setCreateTime(new Date());
                user.setClassName(className);
                user.setClassNo(classNo);
                user.setSchool(school);
                user.setEmail(email);

                int userId = userService.insert(user);
                // 添加用户角色
                UserRole userRole = new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(roleId);
                userRole.setStatus(true);
                userRole.setCreateTime(new Date());
                userRoleService.insert(userRole);

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