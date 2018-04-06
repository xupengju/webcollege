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
import com.college.entity.Biaozhun;
import com.college.repository.BiaozhunDao;


/**
* @Title: 
* @Description
* @author milo    
*/
@Service
public class BiaozhunService {

	private static Logger logger = LoggerFactory
			.getLogger(BiaozhunService.class);

	@Autowired
	private BiaozhunDao biaozhunDao;

	
	
	public Biaozhun get(Long id) {
		return biaozhunDao.get(id);
	}

	public Integer insert(Biaozhun biaozhun) {
		biaozhunDao.insert(biaozhun);
		return biaozhun.getId();
	}
	
	public void update(Biaozhun biaozhun) {
		biaozhunDao.update(biaozhun);
	}

	public void delete(Long id) {
		biaozhunDao.delete(id);
	}
	
	public Page<Biaozhun> searchPageList(int offset , int size,Map<String, Object> params){		
		PageHelper.startPage(offset, size);
		return biaozhunDao.searchPageList(params);
	}

	public Long getTotalCount(Map<String, Object> params) {
		return biaozhunDao.getTotalCount(params);
	}

	public List<Biaozhun> findListByParams(Map<String, Object> params){
		return biaozhunDao.findListByParams(params);
	}
	
	public Biaozhun searchOne(Map<String, Object> params) {
        return biaozhunDao.searchOne(params);
    }
 
}
