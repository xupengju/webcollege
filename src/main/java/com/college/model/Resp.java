package com.college.model;


import com.college.contants.AppCode;
import com.github.pagehelper.Page;
import com.google.common.collect.Maps;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Map;

/**
 * 统一HTTP响应格式，通过_200、_900等静态方法快速构造响应报文
 * <p/>
 * Created by fechin on 15/4/30.
 */
public class Resp implements Serializable {

    // 为200时表示成功，否则为失败错误码
    private final int code;
    // 错误信息
    private final String message;
    // 业务数据体
    private final Object data;

    private static final Resp success = new Resp(AppCode._200, null);
    private static final Resp error = new Resp(AppCode._900, null);


    public Resp(AppCode code, Object data) {
        this.data = data;
        this.code = code.getCode();
        this.message = code.getMsg();
    }

    public Resp(AppCode code, Object[] args, Object data) {
        this.data = data;
        this.code = code.getCode();

        MessageFormat messageFormat = new MessageFormat(code.getMsg());
        this.message = messageFormat.format(args);

    }

    /**
     * 封装成EasyUi Datagrid数据格式
     *
     * @param page 分页对象
     * @return
     */
    public static Map<String, Object> datagrid(Page page) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("total", page.getTotal());
        map.put("rows", page.getResult());
        return map;
    }


    public static Resp success() {
        return success;
    }

    public static Resp success(Object data) {
        return new Resp(AppCode._200, data);
    }

    public static Object codeTable(Object data) {

        return data;
    }


    public static Resp error() {
        return error;
    }

    public static Resp error(AppCode code) {
        return new Resp(code, null);
    }


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
