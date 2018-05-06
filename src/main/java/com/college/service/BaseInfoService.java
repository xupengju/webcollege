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
import com.college.entity.BaseInfo;
import com.college.repository.BaseInfoDao;


/**
* @Title: 
* @Description
* @author milo    
*/
@Service
public class BaseInfoService {

	private static Logger logger = LoggerFactory
			.getLogger(BaseInfoService.class);

	@Autowired
	private BaseInfoDao baseInfoDao;

	
	
	public BaseInfo get(Long id) {
		return baseInfoDao.get(id);
	}

	public Integer insert(BaseInfo baseInfo) {
		baseInfoDao.insert(baseInfo);
		return baseInfo.getId();
	}
	
	public void update(BaseInfo baseInfo) {
		baseInfoDao.update(baseInfo);
	}

	public void delete(Long id) {
		baseInfoDao.delete(id);
	}
	
	public Page<BaseInfo> searchPageList(int offset , int size,Map<String, Object> params){		
		PageHelper.startPage(offset, size);
		return baseInfoDao.searchPageList(params);
	}

	public Long getTotalCount(Map<String, Object> params) {
		return baseInfoDao.getTotalCount(params);
	}

	public List<BaseInfo> findListByParams(Map<String, Object> params){
		return baseInfoDao.findListByParams(params);
	}
	
	public BaseInfo searchOne(Map<String, Object> params) {
        return baseInfoDao.searchOne(params);
    }
 
}
