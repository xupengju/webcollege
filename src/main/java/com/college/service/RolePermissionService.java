package com.college.service;

import com.college.entity.RolePermission;
import com.college.repository.RolePermissionDao;
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
public class RolePermissionService {

    private static Logger logger = LoggerFactory
            .getLogger(RolePermissionService.class);

    @Autowired
    private RolePermissionDao rolePermissionDao;


    public RolePermission get(Long id) {
        return rolePermissionDao.get(id);
    }

    public Integer insert(RolePermission rolePermission) {
        rolePermissionDao.insert(rolePermission);
        return rolePermission.getId();
    }

    public void update(RolePermission rolePermission) {
        rolePermissionDao.update(rolePermission);
    }

    public void delete(Long id) {
        rolePermissionDao.delete(id);
    }

    public Page<RolePermission> searchPageList(int offset, int size, Map<String, Object> params) {
        PageHelper.startPage(offset, size);
        return rolePermissionDao.searchPageList(params);
    }

    public Long getTotalCount(Map<String, Object> params) {
        return rolePermissionDao.getTotalCount(params);
    }

    public List<RolePermission> findListByParams(Map<String, Object> params) {
        return rolePermissionDao.findListByParams(params);
    }

    public RolePermission searchOne(Map<String, Object> params) {
        return rolePermissionDao.searchOne(params);
    }

    public List<RolePermission> searchByRoleIds(List<Integer> roleIdList) {
        return rolePermissionDao.searchByRoleIds(roleIdList);

    }
}
