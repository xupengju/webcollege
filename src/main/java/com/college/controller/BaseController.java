package com.college.controller;

import com.college.contants.Constants;
import com.college.entity.User;
import com.college.service.UserService;
import com.college.service.user.ApiUserService;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author Milo on 2018/4/7.
 * @description
 */
public class BaseController {
    protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    protected HttpServletRequest request;
    @Autowired
    private ApiUserService apiUserService;
    @Autowired
    private UserService userService;


    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        //initDataBinder(request, binder);
    }

    /**
     * 获取登录用户Id
     *
     * @return
     */
    protected Integer getCurrentId() {
        Object userIdObj = request.getAttribute(Constants.LOGIN_ENTITY);
        String userIdStr = (null == userIdObj ? "" : userIdObj.toString());
        return StringUtils.isBlank(userIdStr) ? 0 : Integer.valueOf(userIdStr);
    }

    protected boolean isAdmin(int userId) {
        Map<String, Object> paramsMap = Maps.newHashMap();
        paramsMap.put("userId", userId);
        paramsMap.put("status", true);
        User user = userService.searchOne(paramsMap);
        if (null != user && user.getAdministrator()) {
            logger.info("管理员 boolean : {}", user.getAdministrator());
            return true;
        }
        return false;
    }

    /**
     * 获取登录用户
     *
     * @return
     */
    protected User getCurrentUser() {
        return apiUserService.getUser(new Long(getCurrentId()));
    }

    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (ip.equals("127.0.0.1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ip = inet.getHostAddress();
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }
}
