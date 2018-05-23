package com.college.controller;

import com.college.contants.AppCode;
import com.college.contants.Path;
import com.college.entity.BaseInfo;
import com.college.model.Resp;
import com.college.service.BaseInfoService;
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
public class BaseInfoController {

    private static Logger logger = LoggerFactory
            .getLogger(BaseInfoController.class);

    @Autowired
    private BaseInfoService baseInfoService;

    /**
     * get list
     *
     * @param pageNum
     * @param pageSize
     * @param id          主键
     * @param title       标题
     * @param resume      摘要
     * @param contentType 类别
     * @param image       图片
     * @param link        链接
     * @param content     内容
     * @param status      0: 正常 1:删除
     * @param createTime  创建时间
     * @param updateUser  修改人
     * @param updateTime  更新时间
     * @return
     */
    @RequestMapping(value = Path.BASEINFO_LIST)
    @ResponseBody
    public Map<String, Object> getList(@RequestParam(value = "pagenum", defaultValue = "1") int pageNum,
                                       @RequestParam(value = "pagesize", defaultValue = "10") int pageSize,
                                       @RequestParam(value = "id", required = false) Integer id,
                                       @RequestParam(value = "title", required = false) Integer title,
                                       @RequestParam(value = "resume", required = false) Integer resume,
                                       @RequestParam(value = "contentType", required = false) Integer contentType,
                                       @RequestParam(value = "image", required = false) Integer image,
                                       @RequestParam(value = "link", required = false) Integer link,
                                       @RequestParam(value = "content", required = false) Integer content,
                                       @RequestParam(value = "status", required = false) boolean status,
                                       @RequestParam(value = "createTime", required = false) Integer createTime,
                                       @RequestParam(value = "updateUser", required = false) Integer updateUser,
                                       @RequestParam(value = "updateTime", required = false) Integer updateTime) {
        Map<String, Object> params = Maps.newHashMap();
        Page<BaseInfo> page = baseInfoService.searchPageList(pageNum, pageSize, params);
        Map<String, Object> resultMap = Maps.newHashMap();
        logger.info(" BaseInfoController -->  pageResult :{}", page.getResult());
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
     * @param title       标题
     * @param resume      摘要
     * @param contentType 类别
     * @param image       图片
     * @param link        链接
     * @param content     内容
     * @param status      0: 正常 1:删除
     * @param createTime  创建时间
     * @param updateUser  修改人
     * @param updateTime  更新时间
     * @return
     */
    @RequestMapping(value = Path.BASEINFO_ADD)
    @ResponseBody
    public Resp add(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "resume", required = false) String resume,
            @RequestParam(value = "contentType", required = false) Integer contentType,
            @RequestParam(value = "image", required = false) String image,
            @RequestParam(value = "link", required = false) String link,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "createTime", required = false) java.util.Date createTime,
            @RequestParam(value = "updateUser", required = false) String updateUser,
            @RequestParam(value = "updateTime", required = false) java.util.Date updateTime) {
        BaseInfo baseInfo = new BaseInfo();
        baseInfo.setTitle(title);
        baseInfo.setResume(resume);
        baseInfo.setContentType(contentType);
        baseInfo.setImage(image);
        baseInfo.setLink(link);
        baseInfo.setContent(content);
        baseInfo.setStatus(status);
        baseInfo.setCreateTime(createTime);
        baseInfo.setUpdateUser(updateUser);
        baseInfo.setUpdateTime(updateTime);
        Integer id = baseInfoService.insert(baseInfo);
        return null != id ? Resp.success(id) : Resp.error(AppCode._10003);
    }

    /**
     * update
     *
     * @param id          主键
     * @param title       标题
     * @param resume      摘要
     * @param contentType 类别
     * @param image       图片
     * @param link        链接
     * @param content     内容
     * @param status      0: 正常 1:删除
     * @param createTime  创建时间
     * @param updateUser  修改人
     * @param updateTime  更新时间
     * @return
     */
    @RequestMapping(value =  Path.BASEINFO_UPDATE)
    @ResponseBody
    public Resp update(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "resume", required = false) String resume,
            @RequestParam(value = "contentType", required = false) Integer contentType,
            @RequestParam(value = "image", required = false) String image,
            @RequestParam(value = "link", required = false) String link,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "createTime", required = false) java.util.Date createTime,
            @RequestParam(value = "updateUser", required = false) String updateUser,
            @RequestParam(value = "updateTime", required = false) java.util.Date updateTime) {
        BaseInfo baseInfo = new BaseInfo();
        baseInfo.setTitle(title);
        baseInfo.setResume(resume);
        baseInfo.setContentType(contentType);
        baseInfo.setImage(image);
        baseInfo.setLink(link);
        baseInfo.setContent(content);
        baseInfo.setStatus(status);
        baseInfo.setCreateTime(createTime);
        baseInfo.setUpdateUser(updateUser);
        baseInfo.setUpdateTime(updateTime);
        baseInfoService.update(baseInfo);
        return Resp.success();
    }

    @RequestMapping(value =  Path.BASEINFO_GET)
    @ResponseBody
    public Resp get(@RequestParam(value = "id") Long id) {
        BaseInfo baseInfo= baseInfoService.get(id);
        return null != baseInfo ? Resp.success(baseInfo) : Resp.error(AppCode._10012);
    }

    @RequestMapping(value = "/api/baseInfo/searchOne.json")
    @ResponseBody
    public Resp searchOne(@RequestParam(value = "contentType") Integer contentType) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("contentType",contentType);
        BaseInfo baseInfo= baseInfoService.searchOne(params);
        return null != baseInfo ? Resp.success(baseInfo) : Resp.error(AppCode._10012);
    }

}
