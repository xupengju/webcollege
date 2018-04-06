package com.college.service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.college.entity.UserRole;
import com.college.repository.UserRoleDao;


/**
* @Title: 
* @Description
* @author milo    
*/
@Service
public class UserRoleService {

	private static Logger logger = LoggerFactory
			.getLogger(UserRoleService.class);

	@Autowired
	private UserRoleDao userRoleDao;

	
	
	public UserRole get(Long id) {
		return userRoleDao.get(id);
	}

	public Integer insert(UserRole userRole) {
		userRoleDao.insert(userRole);
		return userRole.getId();
	}
	
	public void update(UserRole userRole) {
		userRoleDao.update(userRole);
	}

	public void delete(Long id) {
		userRoleDao.delete(id);
	}
	
	public Page<UserRole> searchPageList(int offset , int size,Map<String, Object> params){		
		PageHelper.startPage(offset, size);
		return userRoleDao.searchPageList(params);
	}

	public Long getTotalCount(Map<String, Object> params) {
		return userRoleDao.getTotalCount(params);
	}

	public List<UserRole> findListByParams(Map<String, Object> params){
		return userRoleDao.findListByParams(params);
	}
	
	public UserRole searchOne(Map<String, Object> params) {
        return userRoleDao.searchOne(params);
    }
 
}
