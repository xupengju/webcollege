package com.college.controller;

import com.college.contants.AppCode;
import com.college.entity.Notice;
import com.college.service.NoticeService;
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
@RequestMapping("/notice")
public class NoticeController {

    private static Logger logger = LoggerFactory
            .getLogger(NoticeController.class);

    @Autowired
    private NoticeService noticeService;

    /**
     * get list
     *
     * @param pageNum
     * @param pageSize
     * @param id
     * @param title
     * @param resume
     * @param type
     * @param image
     * @param link
     * @param content
     * @param status     0: 正常 1:删除
     * @param createTime 创建时间
     * @param updateUser
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
                                       @RequestParam(value = "type", required = false) Integer type,
                                       @RequestParam(value = "image", required = false) Integer image,
                                       @RequestParam(value = "link", required = false) Integer link,
                                       @RequestParam(value = "content", required = false) Integer content,
                                       @RequestParam(value = "status", required = false) Integer status,
                                       @RequestParam(value = "createTime", required = false) Integer createTime,
                                       @RequestParam(value = "updateUser", required = false) Integer updateUser,
                                       @RequestParam(value = "updateTime", required = false) Integer updateTime
    ) {
        Map<String, Object> params = Maps.newHashMap();
        Page<Notice> page = noticeService.searchPageList(pageNum, pageSize, params);
        Map<String, Object> resultMap = Maps.newHashMap();
        logger.info(" NoticeController -->  pageResult :{}", page.getResult());
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
     * @param type
     * @param image
     * @param link
     * @param content
     * @param status     0: 正常 1:删除
     * @param createTime 创建时间
     * @param updateUser
     * @param updateTime 更新时间
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Map<String, Object> add(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "resume", required = false) String resume,
            @RequestParam(value = "type", required = false) Integer type,
            @RequestParam(value = "image", required = false) String image,
            @RequestParam(value = "link", required = false) String link,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "createTime", required = false) java.util.Date createTime,
            @RequestParam(value = "updateUser", required = false) String updateUser,
            @RequestParam(value = "updateTime", required = false) java.util.Date updateTime) {
        Notice notice = new Notice();
        notice.setTitle(title);
        notice.setResume(resume);
        notice.setType(type);
        notice.setImage(image);
        notice.setLink(link);
        notice.setContent(content);
        notice.setStatus(status);
        notice.setCreateTime(createTime);
        notice.setUpdateUser(updateUser);
        notice.setUpdateTime(updateTime);
        Integer id = noticeService.insert(notice);
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
     * @param type
     * @param image
     * @param link
     * @param content
     * @param status     0: 正常 1:删除
     * @param createTime 创建时间
     * @param updateUser
     * @param updateTime 更新时间
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public Map<String, Object> update(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "resume", required = false) String resume,
            @RequestParam(value = "type", required = false) Integer type,
            @RequestParam(value = "image", required = false) String image,
            @RequestParam(value = "link", required = false) String link,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "createTime", required = false) java.util.Date createTime,
            @RequestParam(value = "updateUser", required = false) String updateUser,
            @RequestParam(value = "updateTime", required = false) java.util.Date updateTime
    ) {
        Notice notice = new Notice();
        notice.setTitle(title);
        notice.setResume(resume);
        notice.setType(type);
        notice.setImage(image);
        notice.setLink(link);
        notice.setContent(content);
        notice.setStatus(status);
        notice.setCreateTime(createTime);
        notice.setUpdateUser(updateUser);
        notice.setUpdateTime(updateTime);
        noticeService.update(notice);
        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("resultcode", AppCode._200);
        resultMap.put("resultd", id);
        return resultMap;
    }

}
