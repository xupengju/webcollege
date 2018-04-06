package com.college.service;

import com.college.entity.Permission;
import com.college.repository.PermissionDao;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * @author milo
 * @Title:
 * @Description
 */
@Service
public class PermissionService {

    private static Logger logger = LoggerFactory
            .getLogger(PermissionService.class);

    @Autowired
    private PermissionDao permissionDao;


    public Permission get(Long id) {
        return permissionDao.get(id);
    }

    public Integer insert(Permission permission) {
        permissionDao.insert(permission);
        return permission.getId();
    }

    public void update(Permission permission) {
        permissionDao.update(permission);
    }

    public void delete(Long id) {
        permissionDao.delete(id);
    }

    public Page<Permission> searchPageList(int offset, int size, Map<String, Object> params) {
        PageHelper.startPage(offset, size);
        return permissionDao.searchPageList(params);
    }

    public Long getTotalCount(Map<String, Object> params) {
        return permissionDao.getTotalCount(params);
    }

    public List<Permission> findListByParams(Map<String, Object> params) {
        return permissionDao.findListByParams(params);
    }

    public Permission searchOne(Map<String, Object> params) {
        return permissionDao.searchOne(params);
    }

    public List<Permission> searchByIds(List<Integer> permissionIdList, String resourceType) {
        return permissionDao.searchByIds(permissionIdList, resourceType);
    }
}
