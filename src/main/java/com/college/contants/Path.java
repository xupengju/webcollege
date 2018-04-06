package com.college.contants;

/**
 * Created by milo on 2016/10/31.
 */
public class Path {

    // ===================================== 公共:ERP COMMONS START ======================================== //
    // 上传图片
    public static final String FILE_UPLOAD = "/api/file/upload.json";

    public static final String JS = "/js";
    public static final String CSS = "/css";
    public static final String IMG = "/img";
    // ===================================== 用户: USER  ============================================ //
    // 用户相关接口
    public static final String USER_LOGIN = "/api/user/login.json";//用户登录
    public static final String USER_ADD = "/api/user/add.json";//添加用户
    public static final String USER_FIND = "/api/user/find.json";//添加用户
    public static final String USER_GET = "/api/user/get.json";
    public static final String USER_UPDATE = "/api/user/update.json";//修改用户
    public static final String USER_ROLE_UPDATE = "/api/user/role/update.json";
    public static final String USER_PASSWORD_UPDATE = "/api/user/password/update.json";
    public static final String USER_ROLE_GET = "/api/user/role/get.json";
    public static final String USER_PERMISSION = "/api/user/permission.json";
    public static final String USER_LIST = "/api/user/userList.json";

    // 角色相关接口
    public static final String ROLE_ADD = "/api/role/add.json";
    public static final String ROLE_GET = "/api/role/get.json";
    public static final String ROLE_UPDATE = "/api/role/update.json";
    public static final String ROLE_LIST = "/api/role/list.json";
    public static final String ROLE_ALL = "/api/role/all.json";
    public static final String ROLE_PERMISSION_ADD = "/api/role/permission/add.json";
    public static final String ROLE_PERMISSION_UPDATE = "/api/role/permission/update.json";
    public static final String ROLE_PERMISSION_LIST = "/api/role/permission/list.json";

    // 权限相关接口
    public static final String PERMISSION_ADD = "/api/permission/add.json";
    public static final String PERMISSION_UPDATE = "/api/permission/update.json";
    public static final String PERMISSION_GET = "/api/permission/get.json";
    public static final String PERMISSION_ALL = "/api/permission/all.json";
    public static final String PERMISSION_TREE = "/api/permission/tree.json";
    public static final String PERMISSION_SEARCH = "/api/permission/search.json";
    public static final String PERMISSION_DELETE = "/api/permission/delete.json";




}
