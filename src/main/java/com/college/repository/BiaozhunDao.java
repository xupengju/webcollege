package com.college.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

import com.college.entity.Biaozhun;

/**
* @Title: 
* @Description
* @author milo    
*/
@Repository("biaozhunDao")
public interface BiaozhunDao {

	Biaozhun get(Long id);
	
	void insert(Biaozhun biaozhun);

	void delete(Long id);

	void update(Biaozhun biaozhun);

	/**
	 * 分页查询 返回Page 对象
	 * @param params psge
	 * @return  Page
	 */
    Page<Biaozhun> searchPageList(@Param("searchFields") Map<String, Object> params);
	
	Long getTotalCount(Map<String, Object> params);

	List<Biaozhun> findListByParams(@Param("searchFields") Map<String, Object> params);

	/**
	 * 根据查询条件 返回单个对象
	 * @param params
	 * @return  
	 */
    Biaozhun searchOne(@Param("searchFields") Map<String, Object> params);

}
