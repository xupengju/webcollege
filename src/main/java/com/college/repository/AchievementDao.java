package com.college.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

import com.college.entity.Achievement;

/**
* @Title: 
* @Description
* @author milo    
*/
@Repository("achievementDao")
public interface AchievementDao {

	Achievement get(Long id);
	
	void insert(Achievement achievement);

	void delete(Long id);

	void update(Achievement achievement);

	/**
	 * 分页查询 返回Page 对象
	 * @param params psge
	 * @return  Page
	 */
    Page<Achievement> searchPageList(@Param("searchFields") Map<String, Object> params);
	
	Long getTotalCount(Map<String, Object> params);

	List<Achievement> findListByParams(@Param("searchFields") Map<String, Object> params);

	/**
	 * 根据查询条件 返回单个对象
	 * @param params
	 * @return  
	 */
    Achievement searchOne(@Param("searchFields") Map<String, Object> params);

}
