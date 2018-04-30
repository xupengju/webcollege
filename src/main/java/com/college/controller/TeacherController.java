package com.college.controller;

import com.college.contants.AppCode;
import com.college.contants.Path;
import com.college.entity.Teacher;
import com.college.service.TeacherService;
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
public class TeacherController extends BaseController {

    private static Logger logger = LoggerFactory
            .getLogger(TeacherController.class);

    @Autowired
    private TeacherService teacherService;

    /**
     * get list
     *
     * @param pageNum
     * @param pageSize
     * @param id
     * @param teacherName
     * @param resume
     * @param userId
     * @param academicTitle
     * @param type
     * @param image
     * @param link
     * @param content
     * @param status        0: 正常 1:删除
     * @param createTime    创建时间
     * @param updateTime    更新时间
     * @return
     */
    @RequestMapping(value = Path.TEACHER_LIST)
    @ResponseBody
    public Map<String, Object> getList(@RequestParam(value = "pagenum", defaultValue = "1") int pageNum,
                                       @RequestParam(value = "pagesize", defaultValue = "10") int pageSize,
                                       @RequestParam(value = "id", required = false) Integer id,
                                       @RequestParam(value = "teacherName", required = false) Integer teacherName,
                                       @RequestParam(value = "resume", required = false) Integer resume,
                                       @RequestParam(value = "userId", required = false) Integer userId,
                                       @RequestParam(value = "academicTitle", required = false) Integer academicTitle,
                                       @RequestParam(value = "type", required = false) Integer type,
                                       @RequestParam(value = "image", required = false) Integer image,
                                       @RequestParam(value = "link", required = false) Integer link,
                                       @RequestParam(value = "content", required = false) Integer content,
                                       @RequestParam(value = "status", required = false) boolean status,
                                       @RequestParam(value = "createTime", required = false) Integer createTime,
                                       @RequestParam(value = "updateTime", required = false) Integer updateTime
    ) {
        Map<String, Object> params = Maps.newHashMap();
        Page<Teacher> page = teacherService.searchPageList(pageNum, pageSize, params);
        Map<String, Object> resultMap = Maps.newHashMap();
        logger.info(" TeacherController -->  pageResult :{}", page.getResult());
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
     * @param teacherName
     * @param resume
     * @param userId
     * @param academicTitle
     * @param type
     * @param image
     * @param link
     * @param content
     * @param status        0: 正常 1:删除
     * @param createTime    创建时间
     * @param updateTime    更新时间
     * @return
     */
    @RequestMapping(value = Path.TEACHER_ADD)
    @ResponseBody
    public Map<String, Object> add(
            @RequestParam(value = "teacherName", required = false) String teacherName,
            @RequestParam(value = "resume", required = false) String resume,
            @RequestParam(value = "userId", required = false) Integer userId,
            @RequestParam(value = "academicTitle", required = false) String academicTitle,
            @RequestParam(value = "type", required = false) Integer type,
            @RequestParam(value = "image", required = false) String image,
            @RequestParam(value = "link", required = false) String link,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "status", required = false) boolean status,
            @RequestParam(value = "createTime", required = false) java.util.Date createTime,
            @RequestParam(value = "updateTime", required = false) java.util.Date updateTime) {
        Teacher teacher = new Teacher();
        teacher.setTeacherName(teacherName);
        teacher.setResume(resume);
        teacher.setUserId(userId);
        teacher.setAcademicTitle(academicTitle);
        teacher.setType(type);
        teacher.setImage(image);
        teacher.setLink(link);
        teacher.setContent(content);
        teacher.setStatus(status);
        teacher.setCreateTime(createTime);
        teacher.setUpdateTime(updateTime);
        Integer id = teacherService.insert(teacher);
        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("resultcode", AppCode._200);
        resultMap.put("resultd", id);
        logger.info("这里是add");
        return resultMap;
    }

    /**
     * update
     *
     * @param id
     * @param teacherName
     * @param resume
     * @param userId
     * @param academicTitle
     * @param type
     * @param image
     * @param link
     * @param content
     * @param status        0: 正常 1:删除
     * @param createTime    创建时间
     * @param updateTime    更新时间
     * @return
     */
    @RequestMapping(value = Path.TEACHER_UPDATE)
    @ResponseBody
    public Map<String, Object> update(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "teacherName", required = false) String teacherName,
            @RequestParam(value = "resume", required = false) String resume,
            @RequestParam(value = "userId", required = false) Integer userId,
            @RequestParam(value = "academicTitle", required = false) String academicTitle,
            @RequestParam(value = "type", required = false) Integer type,
            @RequestParam(value = "image", required = false) String image,
            @RequestParam(value = "link", required = false) String link,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "status", required = false) boolean status,
            @RequestParam(value = "createTime", required = false) java.util.Date createTime,
            @RequestParam(value = "updateTime", required = false) java.util.Date updateTime
    ) {
        Teacher teacher = new Teacher();
        teacher.setId(id);
        teacher.setTeacherName(teacherName);
        teacher.setResume(resume);
        teacher.setUserId(userId);
        teacher.setAcademicTitle(academicTitle);
        teacher.setType(type);
        teacher.setImage(image);
        teacher.setLink(link);
        teacher.setContent(content);
        teacher.setStatus(status);
        teacher.setCreateTime(createTime);
        teacher.setUpdateTime(updateTime);
        teacherService.update(teacher);
        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("resultcode", AppCode._200);
        resultMap.put("resultd", id);
        logger.info("这里是update");
        return resultMap;
    }

}
