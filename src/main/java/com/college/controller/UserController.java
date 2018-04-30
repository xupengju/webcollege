package com.college.controller;

import com.college.contants.AppCode;
import com.college.contants.Path;
import com.college.entity.User;
import com.college.model.*;
import com.college.service.UserService;
import com.college.service.user.ApiUserService;
import com.github.pagehelper.Page;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Milo on 2018/3/24.
 * @description
 */
@RestController
public class UserController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private ApiUserService apiUserService;


    @RequestMapping(value = Path.USER_LIST)
    @ResponseBody
    public Map<String, Object> studentList(@RequestParam(value = "pagenum", defaultValue = "1") int pageNum,
                                           @RequestParam(value = "pagesize", defaultValue = "10") int pageSize,
                                           @RequestParam(value = "username", required = false) String userName
    ) {
        Map<String, Object> params = Maps.newHashMap();
        // FIXME: 2018/4/7 根据登录的用户 ID  过滤应该展示的用户列表
        if (null != userName) {
            params.put("userName", userName);
        }
        Page<User> page = userService.searchPageList(pageNum, pageSize, params);
        Map<String, Object> resultMap = Maps.newHashMap();
        logger.info(" UserController --> students :{}", page.getResult());
        resultMap.put("result", page.getResult());
        resultMap.put("pages", page.getPages());
        resultMap.put("startrow", page.getStartRow());
        resultMap.put("endrow", page.getEndRow());
        resultMap.put("pagesize", page.getPageSize());
        resultMap.put("pagenum", page.getPageNum());
        resultMap.put("total", page.getTotal());
        return resultMap;
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @RequestMapping(Path.USER_ADD)
    @ResponseBody
    public Object addUser(@ModelAttribute("user") UserModel user) {
        logger.info("addUser user :{}", user);
        Integer userId = apiUserService.createUser(user);
        logger.info("addUser userId :{}", userId);
        return null != userId ? Resp.success(userId) : Resp.error(AppCode._10003);
    }

    /**
     * 修改用户
     *
     * @param user
     */
    @RequestMapping(Path.USER_UPDATE)
    public Object update(UserModel user) {
        boolean result = apiUserService.update(user, getCurrentId());
        return result ? Resp.success() : Resp.error(AppCode._10006);
    }

    /**
     * 修改密码
     *
     * @return
     */
    @RequestMapping(Path.USER_PASSWORD_UPDATE)
    public Object updatePassword(@RequestParam(value = "password", required = false, defaultValue = "") String password,
                                 @RequestParam(value = "newPassword", required = false, defaultValue = "") String newPassword) {
        boolean result = apiUserService.updatePassword(getCurrentId(), password, newPassword);
        return result ? Resp.success() : Resp.error(AppCode._10005);
    }

    /**
     * 查找用户
     *
     * @param id
     * @return
     */
    @RequestMapping(Path.USER_FIND)
    public Object findUser(@RequestParam(value = "id", required = true, defaultValue = "0") Long id) {
        User user = apiUserService.getUser(id);
        return null == user ? Resp.error() : Resp.success(user);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @RequestMapping(Path.USER_GET)
    public Object getUser(@RequestParam(value = "userId", defaultValue = "0") Integer userId) {
        UserModel userModel = new UserModel();
        User user = apiUserService.getUser(Long.valueOf(userId));
        if (user != null) {
            userModel.setUserName(user.getUserName());
            userModel.setId(user.getId());
            userModel.setRealName(user.getRealName());
        }
        return Resp.success(userModel);
    }

    /**
     * 添加用户角色
     *
     * @param userId
     * @param roleId
     * @return
     */
    @RequestMapping(Path.USER_ROLE_UPDATE)
    public Object updateUserRole(@RequestParam(value = "userId", required = false, defaultValue = "0") Integer userId,
                                 @RequestParam(value = "roleId", required = false, defaultValue = "0") Integer roleId) {
        apiUserService.updateUserRole(userId, roleId);
        return Resp.success();
    }

    /**
     * 获取用户角色
     *
     * @return
     */
    @RequestMapping(Path.USER_ROLE_GET)
    public Object getUserRole(@RequestParam(value = "userId", required = false, defaultValue = "0") Integer userId) {
        int roleId = apiUserService.getUserRole(userId);
        return Resp.success(roleId);
    }

    /**
     * 获取用户菜单树
     *
     * @return
     */
    @RequestMapping(Path.USER_PERMISSION)
    public Object getUserPermission() {
        int userId = getCurrentId();
        List<ResourceModel> resourceModelList = apiUserService.getUserPermissionTree(userId);
        return Resp.success(resourceModelList);
    }

}
