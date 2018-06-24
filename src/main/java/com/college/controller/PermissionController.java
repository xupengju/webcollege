package com.college.controller;

import com.college.contants.AppCode;
import com.college.contants.Path;
import com.college.entity.Permission;
import com.college.model.Resp;
import com.college.service.PermissionService;
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
public class PermissionController extends BaseController {

    private static Logger logger = LoggerFactory
            .getLogger(PermissionController.class);

    @Autowired
    private PermissionService permissionService;

    /**
     * get list
     *
     * @param pageNum
     * @param pageSize
     * @param id
     * @param action
     * @param url
     * @param status     0: 正常 1:删除
     * @param createTime 创建时间
     * @return
     */
    @RequestMapping(value = Path.PERMISSION_ALL)
    @ResponseBody
    public Map<String, Object> getList(@RequestParam(value = "pagenum", defaultValue = "1") int pageNum,
                                       @RequestParam(value = "pagesize", defaultValue = "10") int pageSize,
                                       @RequestParam(value = "id", required = false) Integer id,
                                       @RequestParam(value = "action", required = false) Integer action,
                                       @RequestParam(value = "url", required = false) Integer url,
                                       @RequestParam(value = "status", required = false) boolean status,
                                       @RequestParam(value = "createTime", required = false) String createTime
    ) {
        Map<String, Object> params = Maps.newHashMap();
        Page<Permission> page = permissionService.searchPageList(pageNum, pageSize, params);
        Map<String, Object> resultMap = Maps.newHashMap();
        logger.info(" PermissionController -->  pageResult :{}", page.getResult());
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
     * @param action
     * @param url
     * @param status     0: 正常 1:删除
     * @param createTime 创建时间
     * @return
     */
    @RequestMapping(value = Path.PERMISSION_ADD)
    @ResponseBody
    public Resp add(
            @RequestParam(value = "action", required = false) String action,
            @RequestParam(value = "url", required = false) String url,
            @RequestParam(value = "status", required = false) boolean status,
            @RequestParam(value = "createTime", required = false) String createTime) {
        Permission permission = new Permission();
        permission.setAction(action);
        permission.setUrl(url);
        permission.setStatus(status);
        permission.setCreateTime(DateTimeUtil.parseDateTime(createTime, "yyyy-MM-dd HH:mm:ss"));
        Integer id = permissionService.insert(permission);
        return null != id ? Resp.success(id) : Resp.error(AppCode._10003);
    }

    /**
     * update
     *
     * @param id
     * @param action
     * @param url
     * @param status     0: 正常 1:删除
     * @param createTime 创建时间
     * @return
     */
    @RequestMapping(value = Path.PERMISSION_UPDATE)
    @ResponseBody
    public Resp update(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "action", required = false) String action,
            @RequestParam(value = "url", required = false) String url,
            @RequestParam(value = "status", required = false) boolean status,
            @RequestParam(value = "createTime", required = false) String createTime
    ) {
        Permission permission = new Permission();
        permission.setId(id);
        permission.setAction(action);
        permission.setUrl(url);
        permission.setStatus(status);
        permission.setCreateTime(DateTimeUtil.parseDateTime(createTime, "yyyy-MM-dd HH:mm:ss"));
        permissionService.update(permission);
        return Resp.success();
    }

}
