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
import com.college.entity.Role;
import com.college.repository.RoleDao;


/**
* @Title: 
* @Description
* @author milo    
*/
@Service
public class RoleService {

	private static Logger logger = LoggerFactory
			.getLogger(RoleService.class);

	@Autowired
	private RoleDao roleDao;

	
	
	public Role get(Long id) {
		return roleDao.get(id);
	}

	public Integer insert(Role role) {
		roleDao.insert(role);
		return role.getId();
	}
	
	public void update(Role role) {
		roleDao.update(role);
	}

	public void delete(Long id) {
		roleDao.delete(id);
	}
	
	public Page<Role> searchPageList(int offset , int size,Map<String, Object> params){		
		PageHelper.startPage(offset, size);
		return roleDao.searchPageList(params);
	}

	public Long getTotalCount(Map<String, Object> params) {
		return roleDao.getTotalCount(params);
	}

	public List<Role> findListByParams(Map<String, Object> params){
		return roleDao.findListByParams(params);
	}
	
	public Role searchOne(Map<String, Object> params) {
        return roleDao.searchOne(params);
    }
 
}
