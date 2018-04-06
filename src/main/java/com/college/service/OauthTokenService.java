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
import com.college.entity.OauthToken;
import com.college.repository.OauthTokenDao;


/**
* @Title: 
* @Description
* @author milo    
*/
@Service
public class OauthTokenService {

	private static Logger logger = LoggerFactory
			.getLogger(OauthTokenService.class);

	@Autowired
	private OauthTokenDao oauthTokenDao;

	
	
	public OauthToken get(Long id) {
		return oauthTokenDao.get(id);
	}

	public Integer insert(OauthToken oauthToken) {
		oauthTokenDao.insert(oauthToken);
		return oauthToken.getId();
	}
	
	public void update(OauthToken oauthToken) {
		oauthTokenDao.update(oauthToken);
	}

	public void delete(Long id) {
		oauthTokenDao.delete(id);
	}
	
	public Page<OauthToken> searchPageList(int offset , int size,Map<String, Object> params){		
		PageHelper.startPage(offset, size);
		return oauthTokenDao.searchPageList(params);
	}

	public Long getTotalCount(Map<String, Object> params) {
		return oauthTokenDao.getTotalCount(params);
	}

	public List<OauthToken> findListByParams(Map<String, Object> params){
		return oauthTokenDao.findListByParams(params);
	}
	
	public OauthToken searchOne(Map<String, Object> params) {
        return oauthTokenDao.searchOne(params);
    }
 
}
