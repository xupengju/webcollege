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
import com.college.entity.User;
import com.college.repository.UserDao;


/**
* @Title: 
* @Description
* @author milo    
*/
@Service
public class UserService {

	private static Logger logger = LoggerFactory
			.getLogger(UserService.class);

	@Autowired
	private UserDao userDao;

	
	
	public User get(Long id) {
		return userDao.get(id);
	}

	public Integer insert(User user) {
		userDao.insert(user);
		return user.getId();
	}
	
	public void update(User user) {
		userDao.update(user);
	}

	public void delete(Long id) {
		userDao.delete(id);
	}
	
	public Page<User> searchPageList(int offset , int size,Map<String, Object> params){		
		PageHelper.startPage(offset, size);
		return userDao.searchPageList(params);
	}

	public Long getTotalCount(Map<String, Object> params) {
		return userDao.getTotalCount(params);
	}

	public List<User> findListByParams(Map<String, Object> params){
		return userDao.findListByParams(params);
	}
	
	public User searchOne(Map<String, Object> params) {
        return userDao.searchOne(params);
    }
 
}
