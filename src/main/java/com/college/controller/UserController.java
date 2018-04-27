package com.college.controller;

import com.college.entity.User;
import com.college.service.UserService;
import com.github.pagehelper.Page;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author Milo on 2018/3/24.
 * @description
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public ModelAndView login(ServerHttpRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> studentList(@RequestParam(value = "pagenum",defaultValue = "1") int pageNum,
                                           @RequestParam(value = "pagesize",defaultValue = "10") int pageSize,
                                           @RequestParam(value = "username",required = false) String userName
                                           ) {
        Map<String, Object> params = Maps.newHashMap();
        // FIXME: 2018/4/7 根据登录的用户 ID  过滤应该展示的用户列表
        if (null != userName){
            params.put("userName",userName);
        }
        Page<User> page = userService.searchPageList(pageNum, pageSize, params);
        Map<String, Object> resultMap = Maps.newHashMap();
        logger.info(" UserController --> students :{}", page.getResult());
        resultMap.put("result",  page.getResult());
        resultMap.put("pages",  page.getPages());
        resultMap.put("startrow",  page.getStartRow());
        resultMap.put("endrow",  page.getEndRow());
        resultMap.put("pagesize",  page.getPageSize());
        resultMap.put("pagenum",  page.getPageNum());
        resultMap.put("total",  page.getTotal());
        return resultMap;
    }

}
