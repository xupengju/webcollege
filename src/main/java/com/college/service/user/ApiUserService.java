package com.college.service.user;

import com.college.entity.Permission;
import com.college.entity.RolePermission;
import com.college.entity.User;
import com.college.entity.UserRole;
import com.college.model.UserModel;
import com.college.service.PermissionService;
import com.college.service.RolePermissionService;
import com.college.service.UserRoleService;
import com.college.service.UserService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Milo on 2018/4/6.
 * @description
 */
@Service
public class ApiUserService {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private UserRoleService userRoleService;
    /**
     * 试图以给定用户名、密码进行登录
     * 如果登录成功，返回该用户的实例
     * 如果登录失败，返回 null
     *
     * @param loginName
     * @param password
     * @return
     */
    public User attempLogin(String loginName, String password) {
        Map<String, Object> paramsMap = Maps.newHashMap();
        paramsMap.put("userName", loginName);
        paramsMap.put("status", true);
        User user = userService.searchOne(paramsMap);
        if (null != user) {
            if (!user.getPassword().equals(getEncryptedPassword(password, user.getSalt()))) {
                return null;
            }
        }
        return user;
    }

    /**
     * 创建用户
     *
     * @param user
     * @return
     */
    public Integer createUser(UserModel user) {
        Map<String, Object> paramsMap = Maps.newHashMap();
        paramsMap.put("userName", user.getUserName());
        User newUser = userService.searchOne(paramsMap);
        if (null != newUser) {
            return null;
        }

        newUser = new User();
        BeanUtils.copyProperties(user, newUser);
        // 设置用户密码
        String salt = getRandomSalt();
        newUser.setPassword(getEncryptedPassword(user.getPassword(), salt));
        newUser.setSalt(salt);
        newUser.setStatus(true);
        newUser.setAdministrator(false);
        newUser.setCreateTime(new Date());
        newUser.setUpdateTime(new Date());
        Integer userId = userService.insert(newUser);
        return userId;
    }

    /**
     * 查找用户
     *
     * @param id
     * @return
     */
    public User getUser(Long id) {
        User user = userService.get(id);
        return user;
    }

    /**
     * 修改用户
     *
     * @param user
     */
    public boolean update(UserModel user, Integer currentId) {
        User newUser = userService.get(new Long(user.getId()));
        if (null != newUser) {
            if (Boolean.FALSE.equals(user.getStatus())) {
                // 冻结用户-> 管理员不能更改
//                if(newUser.getAdministrator()){
//                    throw new CollegeException(AppCode._50017);
//                }

                // 冻结用户-> 当前用户不能更改
                if (newUser.getId().equals(currentId)) {
                    //  throw new CollegeException(AppCode._50018);
                }
            }

            if (newUser.getUserName().trim().equals(user.getUserName().trim())) {
                // 登录名未修改,直接修改剩余项
                BeanUtils.copyProperties(user, newUser);
                newUser.setUpdateTime(null);
                userService.update(newUser);
                return true;
            }

            // 判断是否存在相同用户名的(不包含本身)
            Map<String, Object> paramsMap = Maps.newHashMap();
            paramsMap.put("userName", user.getUserName());
            paramsMap.put("status", true);
            List<User> userList = userService.findListByParams(paramsMap);
            if (CollectionUtils.isEmpty(userList)) {
                // 登录名不重复
                BeanUtils.copyProperties(user, newUser);
                newUser.setUpdateTime(null);
                userService.update(newUser);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * 将密码进行两次 MD5 加密，返回加密后的结果
     * 其中第一次加密后，需要加上一个6位的随机字符串，再进行第二次加密
     *
     * @param password
     * @param salt
     * @return
     */
    public static String getEncryptedPassword(String password, String salt) {
        return DigestUtils.md5Hex(DigestUtils.md5Hex(password) + salt);
    }

    /**
     * 生成一个包含字母和数字的6位随机字符串
     *
     * @return
     */
    public static String getRandomSalt() {
        return RandomStringUtils.randomAlphanumeric(6);
    }

    public boolean isPermission(Integer userId, String url) {
        // 1. 查询用户角色
        Map<String, Object> paramsMap = Maps.newHashMap();
        paramsMap.put("userId", userId);
        paramsMap.put("status", true);
        List<UserRole> userRoleList = userRoleService.findListByParams(paramsMap);
        if (!CollectionUtils.isEmpty(userRoleList)) {
            // 2. 根据角色查询用户权限
            List<Integer> roleIdList = Lists.newArrayList();
            for (UserRole userRole : userRoleList) {
                roleIdList.add(userRole.getRoleId());
            }

            // 获取权限IdList
            List<RolePermission> rolePermissionList = rolePermissionService.searchByRoleIds(roleIdList);
            if (!CollectionUtils.isEmpty(rolePermissionList)) {
                List<Integer> permissionIdList = Lists.newArrayList();
                for (RolePermission rolePermission : rolePermissionList) {
                    permissionIdList.add(rolePermission.getPermissionId());
                }

                // 获取用户的权限,api
                List<Permission> permissionList = permissionService.searchByIds(permissionIdList, "api");
                List<String> urlList = Lists.newArrayList();
                if (!CollectionUtils.isEmpty(permissionList)) {
                    for (Permission permission : permissionList) {
                        if (StringUtils.isNotBlank(permission.getUrl())) {
                            urlList.add(permission.getUrl());
                        }
                    }
                }

                return urlList.contains(url);
            }
        }
        return false;
    }

}
