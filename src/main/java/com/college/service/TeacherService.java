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
import com.college.entity.Teacher;
import com.college.repository.TeacherDao;


/**
* @Title: 
* @Description
* @author milo    
*/
@Service
public class TeacherService {

	private static Logger logger = LoggerFactory
			.getLogger(TeacherService.class);

	@Autowired
	private TeacherDao teacherDao;

	
	
	public Teacher get(Long id) {
		return teacherDao.get(id);
	}

	public Integer insert(Teacher teacher) {
		teacherDao.insert(teacher);
		return teacher.getId();
	}
	
	public void update(Teacher teacher) {
		teacherDao.update(teacher);
	}

	public void delete(Long id) {
		teacherDao.delete(id);
	}
	
	public Page<Teacher> searchPageList(int offset , int size,Map<String, Object> params){		
		PageHelper.startPage(offset, size);
		return teacherDao.searchPageList(params);
	}

	public Long getTotalCount(Map<String, Object> params) {
		return teacherDao.getTotalCount(params);
	}

	public List<Teacher> findListByParams(Map<String, Object> params){
		return teacherDao.findListByParams(params);
	}
	
	public Teacher searchOne(Map<String, Object> params) {
        return teacherDao.searchOne(params);
    }
 
}
