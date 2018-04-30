package com.college.model;

import java.io.Serializable;

/**
 * 登录返回模板
 * Created by pengzhan.rong on 2016/11/4.
 */
public class LoginRespModel implements Serializable {

    private Integer userId;
    private String realName;
    private String token;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
