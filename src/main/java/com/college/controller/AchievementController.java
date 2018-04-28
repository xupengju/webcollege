package com.college.controller;

import com.college.contants.AppCode;
import com.college.entity.Achievement;
import com.college.service.AchievementService;
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
@RequestMapping("/achievement")
public class AchievementController {

    private static Logger logger = LoggerFactory
            .getLogger(AchievementController.class);

    @Autowired
    private AchievementService achievementService;

    /**
     * get list
     *
     * @param pageNum
     * @param pageSize
     * @param id
     * @param title
     * @param resume
     * @param userId
     * @param type
     * @param image
     * @param link
     * @param content
     * @param status     0: 正常 1:删除
     * @param createTime 创建时间
     * @param updateTime 更新时间
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> getList(@RequestParam(value = "pagenum", defaultValue = "1") int pageNum,
                                       @RequestParam(value = "pagesize", defaultValue = "10") int pageSize,
                                       @RequestParam(value = "id", required = false) Integer id,
                                       @RequestParam(value = "title", required = false) Integer title,
                                       @RequestParam(value = "resume", required = false) Integer resume,
                                       @RequestParam(value = "userId", required = false) Integer userId,
                                       @RequestParam(value = "type", required = false) Integer type,
                                       @RequestParam(value = "image", required = false) Integer image,
                                       @RequestParam(value = "link", required = false) Integer link,
                                       @RequestParam(value = "content", required = false) Integer content,
                                       @RequestParam(value = "status", required = false) Integer status,
                                       @RequestParam(value = "createTime", required = false) Integer createTime,
                                       @RequestParam(value = "updateTime", required = false) Integer updateTime
    ) {
        Map<String, Object> params = Maps.newHashMap();
        Page<Achievement> page = achievementService.searchPageList(pageNum, pageSize, params);
        Map<String, Object> resultMap = Maps.newHashMap();
        logger.info(" AchievementController -->  pageResult :{}", page.getResult());
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
     * @param title
     * @param resume
     * @param userId
     * @param type
     * @param image
     * @param link
     * @param content
     * @param status     0: 正常 1:删除
     * @param createTime 创建时间
     * @param updateTime 更新时间
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Map<String, Object> add(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "resume", required = false) String resume,
            @RequestParam(value = "userId", required = false) Integer userId,
            @RequestParam(value = "type", required = false) Integer type,
            @RequestParam(value = "image", required = false) String image,
            @RequestParam(value = "link", required = false) String link,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "createTime", required = false) java.util.Date createTime,
            @RequestParam(value = "updateTime", required = false) java.util.Date updateTime) {
        Achievement achievement = new Achievement();
        achievement.setTitle(title);
        achievement.setResume(resume);
        achievement.setUserId(userId);
        achievement.setType(type);
        achievement.setImage(image);
        achievement.setLink(link);
        achievement.setContent(content);
        achievement.setStatus(status);
        achievement.setCreateTime(createTime);
        achievement.setUpdateTime(updateTime);
        Integer id = achievementService.insert(achievement);
        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("resultcode", AppCode._200);
        resultMap.put("resultd", id);
        return resultMap;
    }

    /**
     * update
     *
     * @param id
     * @param title
     * @param resume
     * @param userId
     * @param type
     * @param image
     * @param link
     * @param content
     * @param status     0: 正常 1:删除
     * @param createTime 创建时间
     * @param updateTime 更新时间
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public Map<String, Object> update(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "resume", required = false) String resume,
            @RequestParam(value = "userId", required = false) Integer userId,
            @RequestParam(value = "type", required = false) Integer type,
            @RequestParam(value = "image", required = false) String image,
            @RequestParam(value = "link", required = false) String link,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "createTime", required = false) java.util.Date createTime,
            @RequestParam(value = "updateTime", required = false) java.util.Date updateTime
    ) {
        Achievement achievement = new Achievement();
        achievement.setId(id);
        achievement.setTitle(title);
        achievement.setResume(resume);
        achievement.setUserId(userId);
        achievement.setType(type);
        achievement.setImage(image);
        achievement.setLink(link);
        achievement.setContent(content);
        achievement.setStatus(status);
        achievement.setCreateTime(createTime);
        achievement.setUpdateTime(updateTime);
        achievementService.update(achievement);
        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("resultcode", AppCode._200);
        resultMap.put("resultd", id);
        return resultMap;
    }

}
