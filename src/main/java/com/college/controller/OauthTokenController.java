package com.college.controller;

import com.college.contants.AppCode;
import com.college.entity.OauthToken;
import com.college.service.OauthTokenService;
import com.github.pagehelper.Page;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


/**
 * @author milo
 * @Title:
 * @Description
 */
@Controller
@RequestMapping("/oauthToken")
public class OauthTokenController {

    private static Logger logger = LoggerFactory
            .getLogger(OauthTokenController.class);

    @Autowired
    private OauthTokenService oauthTokenService;

    /**
     * get list
     *
     * @param pageNum
     * @param pageSize
     * @param id
     * @param userId
     * @param token
     * @param expiredSecond 秒数
     * @param expiredTime   过期时间
     * @param status        0: 正常 1:删除
     * @param createTime    创建时间
     * @param updateTime    更新时间
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> getList(@RequestParam(value = "pagenum", defaultValue = "1") int pageNum,
                                       @RequestParam(value = "pagesize", defaultValue = "10") int pageSize,
                                       @RequestParam(value = "id", required = false) Integer id,
                                       @RequestParam(value = "userId", required = false) Integer userId,
                                       @RequestParam(value = "token", required = false) Integer token,
                                       @RequestParam(value = "expiredSecond", required = false) Integer expiredSecond,
                                       @RequestParam(value = "expiredTime", required = false) Integer expiredTime,
                                       @RequestParam(value = "status", required = false) Integer status,
                                       @RequestParam(value = "createTime", required = false) Integer createTime,
                                       @RequestParam(value = "updateTime", required = false) Integer updateTime
    ) {
        Map<String, Object> params = Maps.newHashMap();
        Page<OauthToken> page = oauthTokenService.searchPageList(pageNum, pageSize, params);
        Map<String, Object> resultMap = Maps.newHashMap();
        logger.info(" OauthTokenController -->  pageResult :{}", page.getResult());
        resultMap.put("result", page.getResult());
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
     * @param userId
     * @param token
     * @param expiredSecond 秒数
     * @param expiredTime   过期时间
     * @param status        0: 正常 1:删除
     * @param createTime    创建时间
     * @param updateTime    更新时间
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Map<String, Object> add(
            @RequestParam(value = "userId", required = false) Integer userId,
            @RequestParam(value = "token", required = false) String token,
            @RequestParam(value = "expiredSecond", required = false) Integer expiredSecond,
            @RequestParam(value = "expiredTime", required = false) java.util.Date expiredTime,
            @RequestParam(value = "status", required = false) boolean status,
            @RequestParam(value = "createTime", required = false) java.util.Date createTime,
            @RequestParam(value = "updateTime", required = false) java.util.Date updateTime) {
        OauthToken oauthToken = new OauthToken();
        oauthToken.setUserId(userId);
        oauthToken.setToken(token);
        oauthToken.setExpiredSecond(expiredSecond);
        oauthToken.setExpiredTime(expiredTime);
        oauthToken.setStatus(status);
        oauthToken.setCreateTime(createTime);
        oauthToken.setUpdateTime(updateTime);
        Integer id = oauthTokenService.insert(oauthToken);
        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("resultcode", AppCode._200);
        resultMap.put("resultd", id);
        return resultMap;
    }

    /**
     * update
     *
     * @param id
     * @param userId
     * @param token
     * @param expiredSecond 秒数
     * @param expiredTime   过期时间
     * @param status        0: 正常 1:删除
     * @param createTime    创建时间
     * @param updateTime    更新时间
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public Map<String, Object> update(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "userId", required = false) Integer userId,
            @RequestParam(value = "token", required = false) String token,
            @RequestParam(value = "expiredSecond", required = false) Integer expiredSecond,
            @RequestParam(value = "expiredTime", required = false) java.util.Date expiredTime,
            @RequestParam(value = "status", required = false) boolean status,
            @RequestParam(value = "createTime", required = false) java.util.Date createTime,
            @RequestParam(value = "updateTime", required = false) java.util.Date updateTime
    ) {
        OauthToken oauthToken = new OauthToken();
        oauthToken.setId(id);
        oauthToken.setUserId(userId);
        oauthToken.setToken(token);
        oauthToken.setExpiredSecond(expiredSecond);
        oauthToken.setExpiredTime(expiredTime);
        oauthToken.setStatus(status);
        oauthToken.setCreateTime(createTime);
        oauthToken.setUpdateTime(updateTime);
        oauthTokenService.update(oauthToken);
        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("resultcode", AppCode._200);
        resultMap.put("resultd", id);
        return resultMap;
    }

}
