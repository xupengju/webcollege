package com.college.controller;

import com.college.contants.AppCode;
import com.college.contants.Path;
import com.college.entity.Signin;
import com.college.entity.User;
import com.college.model.Resp;
import com.college.model.UserVo;
import com.college.service.SigninService;
import com.college.service.UserService;
import com.github.pagehelper.Page;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
     * @param status     0: 删除 1:正常
     * @param createTime 创建时间

     * @return
     */
    @RequestMapping(value = Path.SIGNIN_LIST)
    @ResponseBody
    public Map<String, Object> getList(@RequestParam(value = "pagenum", defaultValue = "1") int pageNum,
                                       @RequestParam(value = "pagesize", defaultValue = "10") int pageSize,
                                       @RequestParam(value = "id", required = false) Integer id,
                                       @RequestParam(value = "userId", required = false) Integer userId,
                                       @RequestParam(value = "status", required = false) boolean status,
                                       @RequestParam(value = "createTime", required = false) String createTime
    ) {
        Map<String, Object> params = Maps.newHashMap();
        Integer currentId = getCurrentId();
        User user = userService.get(currentId.longValue());
        if (!user.getAdministrator()) {
            params.put("userId", currentId);
        }
        if (user.getAdministrator() && null != userId) {
            params.put("userId", userId);
        }
        params.put("status", 1);
        logger.info("params :{}",params);
        logger.info("user :{}",user);
        Page<Signin> page = signinService.searchPageList(pageNum, pageSize, params);
        Map<String, Object> resultMap = Maps.newHashMap();
        List<Signin> singins = page.getResult();
        ArrayList<Object> results = Lists.newArrayList();
        for (Signin signin : singins) {
            User signUser = userService.get(signin.getUserId().longValue());
            if (null != signUser) {
                signin.setUserName(signUser.getRealName());
                signin.setIdCard(signUser.getIdCard());
            }
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
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);
        resultMap.put("user", userVo);
        return resultMap;
    }

    /**
     * insert
     *
     * @return
     */
    @RequestMapping(value = Path.SIGNIN_ADD)
    @ResponseBody
    public Resp add() {
        Integer currentId = getCurrentId();
        logger.info("currentId :{}", currentId);
        if (signInToday(currentId)) {
            return Resp.error(AppCode._10011);
        }
        Signin signin = new Signin();
        signin.setUserId(currentId);
        Integer id = signinService.insert(signin);
        return null != id ? Resp.success(id) : Resp.error(AppCode._10003);
    }

    //是否能够签到
    private boolean signInToday(Integer userId) {
        DateTime dateTime = new DateTime();
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        String endTime = dateTime.toString(dateTimeFormatter) + " 23:59:59";
        String startTime = dateTime.toString(dateTimeFormatter) + " 00:00:00";
        Map<String, Object> params = Maps.newHashMap();
        logger.info(" startTime :{}", startTime);
        logger.info(" endTime :{}", endTime);
        params.put("startTime", startTime);
        params.put("endTime", endTime);
        params.put("userId", userId);
        params.put("status", true);
        List<Signin> listByParams = signinService.findListByParams(params);
        if (listByParams.size() == 0) {
            return false;
        }
        return true;
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
        logger.info("status :{}", status);
        signin.setStatus(status);
        signinService.update(signin);
        return Resp.success();
    }

    /**
     * 条件查询
     */
    @RequestMapping(value = Path.CONDITIONAL_QUERY)
    @ResponseBody
    public Map<String, Object> conditionalQuery(@RequestParam(value = "realName", required = false) String realName,
                                 @RequestParam(value = "idCard", required = false) String idCard,
                                 @RequestParam(value = "userId", required = false) String userId){
        Map<String, Object> params = Maps.newHashMap();
        params.put("realName",realName);
        params.put("idCard",idCard);
        params.put("userId",userId);
        Map<String, Object> resultMap = Maps.newHashMap();
        List<User> listByParams = signinService.conditionalQuery(params);
        logger.info(" SigninController -->  conditionalQueryListByParams :{}", listByParams);
        resultMap.put("result", listByParams);
        return resultMap;
    }

}
