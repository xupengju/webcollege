package com.college.interceptor;

import com.college.contants.AppCode;
import com.college.contants.Constants;
import com.college.contants.Path;
import com.college.entity.OauthToken;
import com.college.exception.CollegeException;
import com.college.service.user.ApiOAuthTokenService;
import com.college.service.user.ApiUserService;
import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 权限验证拦截器
 * Created by milo on 2016/11/5.
 */
public class OAuthInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(OAuthInterceptor.class);
    private static final Marker marker = MarkerFactory.getMarker(Constants.TRACE_OPERATION);

    @Autowired
    private ApiOAuthTokenService apiOAuthTokenService;
    @Autowired
    private ApiUserService apiUserService;

    private static final List<String> SKIP_URLS = ImmutableList.of(
            Path.USER_LOGIN,
            Path.CSS,
            Path.JS,
            Path.IMG,
            Path.FILE_UPLOAD,
            Path.NOTICE_GET,
            Path.NOTICE_LIST,
            Path.ACHIEVEMENT_LIST,
            Path.ACHIEVEMENT_GET,
            Path.TEACHER_LIST,
            Path.RESOURCE_LISTS,
            Path.RESOURCE_GETS,
            Path.FILE_UPLOAD_FILE1,
            Path.FILE_UPLOAD_FILE2,
            Path.FILE_DOWN_FILE,
            Path.BASEINFO_SEARCHONE,
            Path.BASEINFO_GET
    );

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       // HandlerMethod handlerMethod = (HandlerMethod) handler;

        String requestUrl = request.getRequestURI();

        // 验证权限
        logger.info("验证权限的url:{}", requestUrl);

        // 跳过不需要验证的url
        if (SKIP_URLS.contains(requestUrl)) {
            logger.info("跳过不拦截的url:{}", requestUrl);
            return true;
        }

        // 跳过所有不是api开始的请求
        if (!requestUrl.startsWith("/api") || requestUrl.startsWith("/api/test")) {
            logger.info("跳过不是API开始的url:{}", requestUrl);
            return true;
        }

        Map<String, String> paramsMap = getParameterMap(request);

        // 获取请求token
        String token = paramsMap.get("token");
        if (StringUtils.isBlank(token)) {
            logger.error("获取到的toke为空");
            throw new CollegeException(AppCode._10001);
        }
        logger.info("获取到的token为:{}", token);

        OauthToken oauthToken = apiOAuthTokenService.getOauthTokenByToken(token);
        // 判断token是否已过期
        if (null == oauthToken || apiOAuthTokenService.isTokenExpired(oauthToken)) {
            // 已过期
            if (null != oauthToken) {
                logger.info("token:{}已过期", oauthToken);
            }
            throw new CollegeException(AppCode._10001);
        }

        // 设置用户信息
        Integer userId = oauthToken.getUserId();
        logger.info("待验证权限的用户 ID:{}", userId + "");
        boolean isPermission = apiUserService.isPermission(userId, requestUrl);
        logger.info("验证结果:{}", isPermission + "");
        // TODO 添加权限校验
        if(!isPermission){
            throw new CollegeException(AppCode._10004);
        }

        // 设置用户信息
        request.setAttribute(Constants.LOGIN_ENTITY, userId);

        // 记录操作日志
        logRequest(request, userId, paramsMap);
        logger.info("handler : {}",handler);
        return super.preHandle(request, response, handler);
    }

    /**
     * 从request中获得参数Map，并返回可读的Map
     *
     * @param request
     * @return
     */
    private static Map<String, String> getParameterMap(HttpServletRequest request) {
        // 参数Map
        Map<String, String[]> properties = request.getParameterMap();
        // 返回值Map
        Map<String, String> returnMap = new HashMap<String, String>();
        Iterator entries = properties.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++) {
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }

    /**
     * 记录请求
     *
     * @param request
     */
    protected void logRequest(HttpServletRequest request, Integer userId, Map<String, String> requestMap) {
        StringBuilder msg = new StringBuilder();
        msg.append("[");
        msg.append("uri=").append(request.getRequestURI());
        if (org.apache.commons.lang3.StringUtils.isNotBlank(request.getQueryString())) {
            msg.append('?').append(request.getQueryString());
        }

        // 记录POST请求
        if (request.getMethod().equalsIgnoreCase(HttpMethod.POST.name())) {
            msg.append("?");
            for (Map.Entry<String, String> entry : requestMap.entrySet()) {
                msg.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            msg.deleteCharAt(msg.length() - 1);
        }
        msg.append("]");
        logger.info(marker, "用户:{}--请求内容:{}", userId, msg.toString());
    }
}
