package com.college.controller;

import com.college.contants.AppCode;
import com.college.contants.Path;
import com.college.entity.Achievement;
import com.college.model.Resp;
import com.college.service.AchievementService;
import com.college.utils.DateTimeUtil;
import com.github.pagehelper.Page;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * @author milo
 * @Title:
 * @Description
 */
@RestController
public class AchievementController extends BaseController {

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
     * @param status     0: 删除 1:正常
     * @param createTime 创建时间
     * @return
     */
    @RequestMapping(value = Path.ACHIEVEMENT_LIST)
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
                                       @RequestParam(value = "status", required = false) boolean status,
                                       @RequestParam(value = "createTime", required = false) String createTime
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
     * @param status     0: 删除 1:正常
     * @param createTime 创建时间
     * @return
     */
    @RequestMapping(value = Path.ACHIEVEMENT_ADD)
    @ResponseBody
    public Resp add(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "resume", required = false) String resume,
            @RequestParam(value = "userId", required = false) Integer userId,
            @RequestParam(value = "type", required = false) Integer type,
            @RequestParam(value = "image", required = false) String image,
            @RequestParam(value = "link", required = false) String link,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "status", required = false) boolean status,
            @RequestParam(value = "createTime", required = false) String createTime) {
        Achievement achievement = new Achievement();
        achievement.setTitle(title);
        achievement.setResume(resume);
        achievement.setUserId(userId);
        achievement.setType(type);
        achievement.setImage(image);
        achievement.setLink(link);
        achievement.setContent(content);
        achievement.setStatus(status);
        achievement.setCreateTime(DateTimeUtil.parseDateTime(createTime,"yyyy-MM-dd HH:mm:ss"));
        Integer id = achievementService.insert(achievement);
        return null != id ? Resp.success(id) : Resp.error(AppCode._10003);
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
     * @param status     0: 删除 1:正常
     * @param createTime 创建时间
     * @return
     */
    @RequestMapping(value = Path.ACHIEVEMENT_UPDATE)
    @ResponseBody
    public Resp update(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "resume", required = false) String resume,
            @RequestParam(value = "userId", required = false) Integer userId,
            @RequestParam(value = "type", required = false) Integer type,
            @RequestParam(value = "image", required = false) String image,
            @RequestParam(value = "link", required = false) String link,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "status", required = false) boolean status,
            @RequestParam(value = "createTime", required = false) String createTime
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
        achievement.setCreateTime(DateTimeUtil.parseDateTime(createTime,"yyyy-MM-dd HH:mm:ss"));
        achievementService.update(achievement);
        return Resp.success();
    }

    @RequestMapping(value = Path.ACHIEVEMENT_GET)
    @ResponseBody
    public Resp get(@RequestParam(value = "id") Long id) {
        Achievement achievement = achievementService.get(id);
        return null != achievement ? Resp.success(achievement) : Resp.error(AppCode._10012);
    }

}
