package com.college.contants;


/**
 * Created by milo on 17/5/4.
 */
public enum AppCode {
    ///////////////////////////////////////系统错误代码
    _900(900, "服务器出现异常"),
    _200(200, "操作成功"),

    ///////////////////////////////////////业务错误代码
    _10001(10001, "用户未登录"),
    _10002(10002, "用户名或密码错误"),
    _10003(10003, "用户已存在"),
    _10004(10004, "用户未授权"),
    _10005(10005, "密码错误"),
    _10006(10006, "登录名已存在"),
    _10007(10007, "图片上传失败,请重试"),
    _10008(10008, ""),
    _10009(10009, ""),
    _10010(10010, ""),
    _10011(10011, "数据已存在"),
    _10012(10012, "数据不存在");

    private int code;
    private String msg;

    AppCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
