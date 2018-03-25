package com.college.controller;

import com.alibaba.fastjson.JSONObject;
import com.college.entity.User;
import com.college.service.UserService;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Milo on 2018/3/24.
 * @description
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> list() {
        Map<String, Object> params = Maps.newHashMap();
        List<User> users = userService.search(params);
        Map<String, Object> resultMap = Maps.newHashMap();
        logger.info("UserController --> UserController --> users :{}", users);
        resultMap.put("users", users);
        return resultMap;
    }

    @RequestMapping(value = "/studentlist")
    @ResponseBody
    public Map<String, Object> studentList() {
        Map<String, Object> params = Maps.newHashMap();
        params.put("rolename","Student");
        List<User> students = userService.searchStudent(params);
        Map<String, Object> resultMap = Maps.newHashMap();
        logger.info("UserController --> UserController --> students :{}", students);
        resultMap.put("students", students);
        return resultMap;
    }

    @RequestMapping(value = "/mv", method = RequestMethod.GET)
    public ModelAndView hello() {
        logger.debug("Method hello");
        ModelAndView mv = new ModelAndView("apply");
        Map<String, String> map = new HashMap<String, String>();
        map.put("test", "test");
        map.put("username", "用户姓名");
        map.put("password", "德玛西亚");
        mv.addAllObjects(map);
        return mv;
    }

    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    @ResponseBody
    public Object hello1() {
        Map<String, Object> params = new HashMap<String, Object>();

        Object o = JSONObject.toJSON(userService.search(params));

        return o;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String hello12() {

        return "upload";
    }


    @RequestMapping(value = "/hello-world", method = RequestMethod.GET)
    public String helloWorld() {
        logger.debug("Method helloWorld");
        return "hello-world";
    }

    @RequestMapping(value = "/hello-redirect", method = RequestMethod.GET)
    public String helloRedirect() {
        logger.debug("Method helloRedirect");
        return "redirect:/hello-world";
    }


}
