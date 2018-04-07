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
import com.college.entity.Resource;
import com.college.repository.ResourceDao;


/**
* @Title: 
* @Description
* @author milo    
*/
@Service
public class ResourceService {

	private static Logger logger = LoggerFactory
			.getLogger(ResourceService.class);

	@Autowired
	private ResourceDao resourceDao;

	
	
	public Resource get(Long id) {
		return resourceDao.get(id);
	}

	public Integer insert(Resource resource) {
		resourceDao.insert(resource);
		return resource.getId();
	}
	
	public void update(Resource resource) {
		resourceDao.update(resource);
	}

	public void delete(Long id) {
		resourceDao.delete(id);
	}
	
	public Page<Resource> searchPageList(int offset , int size,Map<String, Object> params){		
		PageHelper.startPage(offset, size);
		return resourceDao.searchPageList(params);
	}

	public Long getTotalCount(Map<String, Object> params) {
		return resourceDao.getTotalCount(params);
	}

	public List<Resource> findListByParams(Map<String, Object> params){
		return resourceDao.findListByParams(params);
	}
	
	public Resource searchOne(Map<String, Object> params) {
        return resourceDao.searchOne(params);
    }
 
}
