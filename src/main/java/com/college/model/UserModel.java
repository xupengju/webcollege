package com.college.model;

/**
 * @author Milo on 2018/4/6.
 * @description
 */
public class UserModel {
    /**
     *
     */
    private Integer id;
    /**
     * 登录名称
     */
    private String userName;
    /**
     * 真实名
     */
    private String realName;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 是否有效
     */
    private Boolean status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
