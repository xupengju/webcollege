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
import com.college.entity.Achievement;
import com.college.repository.AchievementDao;


/**
* @Title: 
* @Description
* @author milo    
*/
@Service
public class AchievementService {

	private static Logger logger = LoggerFactory
			.getLogger(AchievementService.class);

	@Autowired
	private AchievementDao achievementDao;

	
	
	public Achievement get(Long id) {
		return achievementDao.get(id);
	}

	public Integer insert(Achievement achievement) {
		achievementDao.insert(achievement);
		return achievement.getId();
	}
	
	public void update(Achievement achievement) {
		achievementDao.update(achievement);
	}

	public void delete(Long id) {
		achievementDao.delete(id);
	}
	
	public Page<Achievement> searchPageList(int offset , int size,Map<String, Object> params){		
		PageHelper.startPage(offset, size);
		return achievementDao.searchPageList(params);
	}

	public Long getTotalCount(Map<String, Object> params) {
		return achievementDao.getTotalCount(params);
	}

	public List<Achievement> findListByParams(Map<String, Object> params){
		return achievementDao.findListByParams(params);
	}
	
	public Achievement searchOne(Map<String, Object> params) {
        return achievementDao.searchOne(params);
    }
 
}
