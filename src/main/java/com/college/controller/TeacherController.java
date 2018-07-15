package com.college.controller;

import com.college.contants.AppCode;
import com.college.contants.Path;
import com.college.entity.Teacher;
import com.college.model.Resp;
import com.college.service.TeacherService;
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

import java.util.Date;
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
     * @param status        0: 删除 1:正常
     * @param createTime    创建时间
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
                                       @RequestParam(value = "createTime", required = false) String createTime
    ) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("type",type);
        params.put("status",1);
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
     * @param createTime    创建时间
     * @return
     */
    @RequestMapping(value = Path.TEACHER_ADD)
    @ResponseBody
    public Resp add(
            @RequestParam(value = "teacherName", required = false) String teacherName,
            @RequestParam(value = "resume", required = false) String resume,
            @RequestParam(value = "userId", required = false) Integer userId,
            @RequestParam(value = "academicTitle", required = false) String academicTitle,
            @RequestParam(value = "type", required = false) Integer type,
            @RequestParam(value = "image", required = false) String image,
            @RequestParam(value = "link", required = false) String link,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "createTime", required = false) String createTime) {
        Integer currentId = getCurrentId();
        if (!isAdmin(currentId)) {
            return Resp.error(AppCode._10004);
        }
        Teacher teacher = new Teacher();
        teacher.setTeacherName(teacherName);
        teacher.setResume(resume);
        teacher.setUserId(userId);
        teacher.setAcademicTitle(academicTitle);
        teacher.setType(type);
        teacher.setImage(image);
        teacher.setLink(link);
        teacher.setContent(content);
        teacher.setStatus(true);
        if (null != createTime){
            logger.info("create time :{}",DateTimeUtil.parseDateTime(createTime,"yyyy-MM-dd HH:mm:ss"));
            teacher.setCreateTime(DateTimeUtil.parseDateTime(createTime,"yyyy-MM-dd HH:mm:ss"));
        }else {
            teacher.setCreateTime(new Date());
        }

        Integer id = teacherService.insert(teacher);
        return null != id ? Resp.success(id) : Resp.error(AppCode._10003);
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
     * @param status        0: 删除 1:正常
     * @param createTime    创建时间
     * @return
     */
    @RequestMapping(value = Path.TEACHER_UPDATE)
    @ResponseBody
    public Resp update(
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
            @RequestParam(value = "createTime", required = false) String createTime
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
        teacherService.update(teacher);
        return Resp.success();
    }

}
