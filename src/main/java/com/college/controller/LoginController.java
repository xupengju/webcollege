package com.college.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录Controller
 */
@Controller
@RequestMapping(value = "login")
public class LoginController {

    @RequestMapping
    public String login(){
        // FIXME: 2018/4/5 不传参数 or  失败  返回 login
        //return "login";
        // FIXME: 2018/4/5  成功登录返回 index
        return "index";
    }

}
