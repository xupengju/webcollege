package com.college.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

import com.college.entity.Teacher;

/**
* @Title: 
* @Description
* @author milo    
*/
@Repository("teacherDao")
public interface TeacherDao {

	Teacher get(Long id);
	
	void insert(Teacher teacher);

	void delete(Long id);

	void update(Teacher teacher);

	/**
	 * 分页查询 返回Page 对象
	 * @param params psge
	 * @return  Page
	 */
    Page<Teacher> searchPageList(@Param("searchFields") Map<String, Object> params);
	
	Long getTotalCount(Map<String, Object> params);

	List<Teacher> findListByParams(@Param("searchFields") Map<String, Object> params);

	/**
	 * 根据查询条件 返回单个对象
	 * @param params
	 * @return  
	 */
    Teacher searchOne(@Param("searchFields") Map<String, Object> params);

}
