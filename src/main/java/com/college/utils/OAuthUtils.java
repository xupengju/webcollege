package com.college.utils;

import com.google.common.base.Strings;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * OAuth工具类
 */
public class OAuthUtils {
    public static final String TOKEN_NAME = "token";

    /**
     * 获取Request请求中的token
     *
     * @param request
     * @return
     */
    public static String getRequestToken(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_NAME);
        if (StringUtils.isBlank(token)) {
            token = request.getParameter(TOKEN_NAME);
        }

        return Strings.nullToEmpty(token);
    }

    /**
     * 生成Token
     *
     * @return
     */
    public static String generatorToken(Integer userId) {
        Long key = userId + System.nanoTime();
        return DigestUtils.md5Hex(key + "");
    }
}
