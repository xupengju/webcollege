package com.college.controller;

import com.college.contants.AppCode;
import com.college.contants.Path;
import com.college.entity.Signin;
import com.college.entity.User;
import com.college.model.Resp;
import com.college.service.SigninService;
import com.college.service.UserService;
import com.github.pagehelper.Page;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author milo
 * @Title:
 * @Description
 */
@RestController
public class SigninController extends BaseController {

    private static Logger logger = LoggerFactory
            .getLogger(SigninController.class);

    @Autowired
    private SigninService signinService;

    @Autowired
    private UserService userService;

    /**
     * get list
     *
     * @param pageNum
     * @param pageSize
     * @param id
     * @param userId     用户 ID
     * @param status     0: 正常 1:删除
     * @param createTime 创建时间
     * @param updateTime 更新时间
     * @return
     */
    @RequestMapping(value = Path.SIGNIN_LIST)
    @ResponseBody
    public Map<String, Object> getList(@RequestParam(value = "pagenum", defaultValue = "1") int pageNum,
                                       @RequestParam(value = "pagesize", defaultValue = "10") int pageSize,
                                       @RequestParam(value = "id", required = false) Integer id,
                                       @RequestParam(value = "userId", required = false) Integer userId,
                                       @RequestParam(value = "status", required = false) boolean status,
                                       @RequestParam(value = "createTime", required = false) Integer createTime,
                                       @RequestParam(value = "updateTime", required = false) Integer updateTime
    ) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("userId",userId);
        params.put("status",1);
        Page<Signin> page = signinService.searchPageList(pageNum, pageSize, params);
        Map<String, Object> resultMap = Maps.newHashMap();
        List<Signin> singins = page.getResult();
        ArrayList<Object> results = Lists.newArrayList();
        for (Signin signin : singins){
            User user = userService.get(signin.getUserId().longValue());
            String realName = user.getRealName();
            signin.setUserName(realName);
            results.add(signin);
        }
        logger.info(" SigninController -->  pageResult :{}", results);
        resultMap.put("result", results);
        resultMap.put("pages", page.getPages());
        resultMap.put("startrow", page.getStartRow());
        resultMap.put("endrow", page.getEndRow());
        resultMap.put("pagesize", page.getPageSize());
        resultMap.put("pagenum", page.getPageNum());
        resultMap.put("total", page.getTotal());
        return resultMap;
    }

    /**
     * insert
     *
     * @param userId     用户 ID
     * @param status     0: 正常 1:删除
     * @param createTime 创建时间
     * @param updateTime 更新时间
     * @return
     */
    @RequestMapping(value = Path.SIGNIN_ADD)
    @ResponseBody
    public Resp add(
            @RequestParam(value = "userId", required = false) Integer userId,
            @RequestParam(value = "status", required = false) boolean status,
            @RequestParam(value = "createTime", required = false) java.util.Date createTime,
            @RequestParam(value = "updateTime", required = false) java.util.Date updateTime) {
        Signin signin = new Signin();
        signin.setUserId(userId);
        signin.setStatus(status);
        Integer id = signinService.insert(signin);
        return null != id ? Resp.success(id) : Resp.error(AppCode._10003);
    }

    /**
     * update
     *
     * @param id
     * @return
     */
    @RequestMapping(value = Path.SIGNIN_UPDATE)
    @ResponseBody
    public Resp update(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "status", required = false) boolean status
    ) {
        Signin signin = new Signin();
        signin.setId(id);
        logger.info("status :{}",status);
        signin.setStatus(status);
        signinService.update(signin);
        return Resp.success();
    }

}
