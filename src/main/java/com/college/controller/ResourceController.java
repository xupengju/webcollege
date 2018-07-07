package com.college.controller;

import com.college.contants.AppCode;
import com.college.contants.Path;
import com.college.entity.Notice;
import com.college.entity.Resource;
import com.college.model.Resp;
import com.college.service.ResourceService;
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
public class ResourceController extends BaseController {

    private static Logger logger = LoggerFactory
            .getLogger(ResourceController.class);

    @Autowired
    private ResourceService resourceService;

    /**
     * get list
     *
     * @param pageNum
     * @param pageSize
     * @param id
     * @param resourceName
     * @param resume
     * @param userId
     * @param type
     * @param image
     * @param link
     * @param content
     * @param status      0: 删除 1:正常
     * @param createTime  创建时间

     * @return
     */
    @RequestMapping(value = Path.RESOURCE_LIST)
    @ResponseBody
    public Map<String, Object> getList(@RequestParam(value = "pagenum", defaultValue = "1") int pageNum,
                                       @RequestParam(value = "pagesize", defaultValue = "10") int pageSize,
                                       @RequestParam(value = "id", required = false) Integer id,
                                       @RequestParam(value = "resourceName", required = false) Integer resourceName,
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
        params.put("type",type);
        Page<Resource> page = resourceService.searchPageList(pageNum, pageSize, params);
        Map<String, Object> resultMap = Maps.newHashMap();
        logger.info(" ResourceController -->  pageResult :{}", page.getResult());
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
     * @param resourceName
     * @param resume
     * @param userId
     * @param type
     * @param image
     * @param link
     * @param content
     * @param status      0: 删除 1:正常
     * @param createTime  创建时间

     * @return
     */
    @RequestMapping(value = Path.RESOURCE_ADD)
    @ResponseBody
    public Resp add(
            @RequestParam(value = "resourceName", required = false) String resourceName,
            @RequestParam(value = "resume", required = false) String resume,
            @RequestParam(value = "userId", required = false) Integer userId,
            @RequestParam(value = "type", required = false) Integer type,
            @RequestParam(value = "image", required = false) String image,
            @RequestParam(value = "link", required = false) String link,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "status", required = false) boolean status,
            @RequestParam(value = "createTime", required = false) String createTime) {
        Resource resource = new Resource();
        resource.setResourceName(resourceName);
        resource.setResume(resume);
        resource.setUserId(userId);
        resource.setType(type);
        resource.setImage(image);
        resource.setLink(link);
        resource.setContent(content);
        resource.setStatus(true);
        if (null != createTime){
            logger.info("create time :{}",DateTimeUtil.parseDateTime(createTime,"yyyy-MM-dd HH:mm:ss"));
            resource.setCreateTime(DateTimeUtil.parseDateTime(createTime,"yyyy-MM-dd HH:mm:ss"));
        }else {
            resource.setCreateTime(new Date());
        }

        Integer id = resourceService.insert(resource);
        return null != id ? Resp.success(id) : Resp.error(AppCode._10003);
    }

    /**
     * update
     *
     * @param id
     * @param resourceName
     * @param resume
     * @param userId
     * @param type
     * @param image
     * @param link
     * @param content
     * @param status      0: 删除 1:正常
     * @param createTime  创建时间

     * @return
     */
    @RequestMapping(value = Path.RESOURCE_UPDATE)
    @ResponseBody
    public Resp update(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "resourceName", required = false) String resourceName,
            @RequestParam(value = "resume", required = false) String resume,
            @RequestParam(value = "userId", required = false) Integer userId,
            @RequestParam(value = "type", required = false) Integer type,
            @RequestParam(value = "image", required = false) String image,
            @RequestParam(value = "link", required = false) String link,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "status", required = false) boolean status,
            @RequestParam(value = "createTime", required = false) String createTime
    ) {
        Resource resource = new Resource();
        resource.setResourceName(resourceName);
        resource.setResume(resume);
        resource.setUserId(userId);
        resource.setType(type);
        resource.setImage(image);
        resource.setLink(link);
        resource.setContent(content);
        resource.setStatus(status);
        resource.setCreateTime(DateTimeUtil.parseDateTime(createTime,"yyyy-MM-dd HH:mm:ss"));
        resourceService.update(resource);
        return Resp.success();
    }


    @RequestMapping(value = Path.RESOURCE_GET)
    @ResponseBody
    public Resp get(@RequestParam(value = "id") Long id) {
        Resource resource = resourceService.get(id);
        return null != resource ? Resp.success(resource) : Resp.error(AppCode._10012);
    }

}
