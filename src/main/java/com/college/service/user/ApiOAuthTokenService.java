package com.college.service.user;

import com.college.contants.Constants;
import com.college.entity.OauthToken;
import com.college.service.OauthTokenService;
import com.college.utils.OAuthUtils;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author Milo on 2018/4/6.
 * @description
 */
@Service
public class ApiOAuthTokenService {

    private static final String scope = "APIOAUTHTOKENSERVICE_";

    private static Logger logger = LoggerFactory.getLogger(ApiOAuthTokenService.class);
    @Autowired
    private OauthTokenService oauthTokenService;


    /**
     * 根据用户Id返回token
     *
     * @param userId
     * @return
     */
    public String generatorTokenByUserId(Integer userId) {
        String token = null;
        OauthToken oauthToken = getOauthTokenByUserId(userId);
        if (null == oauthToken) {
            token = OAuthUtils.generatorToken(userId);

            oauthToken = new OauthToken();
            oauthToken.setUserId(userId);
            oauthToken.setToken(token);
            oauthToken.setExpiredSecond(Constants.EXPIRED_TIME);
            long tokenExpiredTime = System.currentTimeMillis() + (long) Constants.EXPIRED_TIME * 1000L;
            oauthToken.setExpiredTime(new Date(tokenExpiredTime));
            oauthToken.setStatus(true);
            oauthToken.setCreateTime(new Date());
            oauthToken.setUpdateTime(new Date());
            oauthTokenService.insert(oauthToken);
            return token;
        }
        logger.info("是否过期 : {}", isTokenExpired(oauthToken));
        if (isTokenExpired(oauthToken)) {
            // 过期
            token = OAuthUtils.generatorToken(userId);
            oauthToken.setToken(token);
            oauthToken.setExpiredSecond(Constants.EXPIRED_TIME);
            long tokenExpiredTime = System.currentTimeMillis() + (long) Constants.EXPIRED_TIME * 1000L;
            oauthToken.setExpiredTime(new Date(tokenExpiredTime));
            oauthTokenService.update(oauthToken);
        } else {
            token = oauthToken.getToken();
        }

        return token;

    }

    /**
     * 根据用户Id查询token信息
     *
     * @param userId
     * @return
     */
    public OauthToken getOauthTokenByUserId(Integer userId) {
        Map<String, Object> paramsMap = Maps.newHashMap();
        paramsMap.put("userId", userId);
        OauthToken oauthToken = oauthTokenService.searchOne(paramsMap);
        return oauthToken;
    }

    /**
     * 根据用户Id查询token信息
     *
     * @param token
     * @return
     */
    public OauthToken getOauthTokenByToken(String token) {
        Map<String, Object> paramsMap = Maps.newHashMap();
        paramsMap.put("token", token);
        OauthToken oauthToken = oauthTokenService.searchOne(paramsMap);
        return oauthToken;
    }

    /**
     * 判断token是否过期
     *
     * @param oauthToken
     * @return true:过期,false:未过期
     */
    public boolean isTokenExpired(OauthToken oauthToken) {
        //logger.info("当前毫秒值 :{}", System.currentTimeMillis());
        //logger.info("令牌毫秒值 :{}", oauthToken.getExpiredTime().getTime());
        return System.currentTimeMillis() > oauthToken.getExpiredTime().getTime();
    }
}