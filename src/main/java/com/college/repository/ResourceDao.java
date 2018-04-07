package com.college.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

import com.college.entity.Resource;

/**
* @Title: 
* @Description
* @author milo    
*/
@Repository("resourceDao")
public interface ResourceDao {

	Resource get(Long id);
	
	void insert(Resource resource);

	void delete(Long id);

	void update(Resource resource);

	/**
	 * 分页查询 返回Page 对象
	 * @param params psge
	 * @return  Page
	 */
    Page<Resource> searchPageList(@Param("searchFields") Map<String, Object> params);
	
	Long getTotalCount(Map<String, Object> params);

	List<Resource> findListByParams(@Param("searchFields") Map<String, Object> params);

	/**
	 * 根据查询条件 返回单个对象
	 * @param params
	 * @return  
	 */
    Resource searchOne(@Param("searchFields") Map<String, Object> params);

}
