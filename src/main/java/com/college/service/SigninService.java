package com.college.service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.college.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.college.entity.Signin;
import com.college.repository.SigninDao;


/**
* @Title: 
* @Description
* @author milo    
*/
@Service
public class SigninService {

	private static Logger logger = LoggerFactory
			.getLogger(SigninService.class);

	@Autowired
	private SigninDao signinDao;

	
	
	public Signin get(Long id) {
		return signinDao.get(id);
	}

	public Integer insert(Signin signin) {
		signinDao.insert(signin);
		return signin.getId();
	}
	
	public void update(Signin signin) {
		signinDao.update(signin);
	}

	public void delete(Long id) {
		signinDao.delete(id);
	}
	
	public Page<Signin> searchPageList(int offset , int size,Map<String, Object> params){		
		PageHelper.startPage(offset, size);
		return signinDao.searchPageList(params);
	}

	public Long getTotalCount(Map<String, Object> params) {
		return signinDao.getTotalCount(params);
	}

	public List<Signin> findListByParams(Map<String, Object> params){
		return signinDao.findListByParams(params);
	}
	
	public Signin searchOne(Map<String, Object> params) {
        return signinDao.searchOne(params);
    }

	public List<User> conditionalQuery(Map<String, Object> params){
		return signinDao.conditionalQuery(params);
	}
 
}
