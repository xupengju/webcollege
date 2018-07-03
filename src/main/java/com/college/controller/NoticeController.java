package com.college.controller;

import com.college.contants.AppCode;
import com.college.contants.Path;
import com.college.entity.Notice;
import com.college.model.Resp;
import com.college.service.NoticeService;
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
public class NoticeController extends BaseController {

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
     * @param status     0: 删除 1:正常
     * @param createTime 创建时间
     * @param updateUser

     * @return
     */
    @RequestMapping(value = Path.NOTICE_LIST)
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
                                       @RequestParam(value = "status", required = false) boolean status,
                                       @RequestParam(value = "createTime", required = false) String createTime,
                                       @RequestParam(value = "updateUser", required = false) Integer updateUser

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
     * @param status     0: 删除 1:正常
     * @param createTime 创建时间
     * @param updateUser

     * @return
     */
    @RequestMapping(value = Path.NOTICE_ADD)
    @ResponseBody
    public Resp add(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "resume", required = false) String resume,
            @RequestParam(value = "type", required = false) Integer type,
            @RequestParam(value = "image", required = false) String image,
            @RequestParam(value = "link", required = false) String link,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "status", required = false) boolean status,
            @RequestParam(value = "createTime", required = false) String createTime,
            @RequestParam(value = "updateUser", required = false) String updateUser) {
        Notice notice = new Notice();
        notice.setTitle(title);
        notice.setResume(resume);
        notice.setType(type);
        notice.setImage(image);
        notice.setLink(link);
        notice.setContent(content);
        notice.setStatus(status);
        logger.info("create time :{}",DateTimeUtil.parseDateTime(createTime,"yyyy-MM-dd HH:mm:ss"));
        notice.setCreateTime(DateTimeUtil.parseDateTime(createTime,"yyyy-MM-dd HH:mm:ss"));
        notice.setUpdateUser(updateUser);

        Integer id = noticeService.insert(notice);
        return null != id ? Resp.success(id) : Resp.error(AppCode._10003);
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
     * @param status     0: 删除 1:正常
     * @param createTime 创建时间
     * @param updateUser

     * @return
     */
    @RequestMapping(value = Path.NOTICE_UPDATE)
    @ResponseBody
    public Resp update(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "resume", required = false) String resume,
            @RequestParam(value = "type", required = false) Integer type,
            @RequestParam(value = "image", required = false) String image,
            @RequestParam(value = "link", required = false) String link,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "status", required = false) boolean status,
            @RequestParam(value = "createTime", required = false) String createTime,
            @RequestParam(value = "updateUser", required = false) String updateUser
    ) {
        Notice notice = new Notice();
        notice.setId(id);
        notice.setTitle(title);
        notice.setResume(resume);
        notice.setType(type);
        notice.setImage(image);
        notice.setLink(link);
        notice.setContent(content);
        notice.setStatus(status);
        notice.setCreateTime(DateTimeUtil.parseDateTime(createTime,"yyyy-MM-dd HH:mm:ss"));
        notice.setUpdateUser(updateUser);
        noticeService.update(notice);
        return Resp.success();
    }



    @RequestMapping(value = Path.NOTICE_GET)
    @ResponseBody
    public Resp get(@RequestParam(value = "id") Long id) {
        Notice notice = noticeService.get(id);
        return null != notice ? Resp.success(notice) : Resp.error(AppCode._10012);
    }

}
