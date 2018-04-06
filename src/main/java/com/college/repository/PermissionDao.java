package com.college.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

import com.college.entity.Permission;

/**
* @Title: 
* @Description
* @author milo    
*/
@Repository("permissionDao")
public interface PermissionDao {

	Permission get(Long id);
	
	void insert(Permission permission);

	void delete(Long id);

	void update(Permission permission);

	/**
	 * 分页查询 返回Page 对象
	 * @param params psge
	 * @return  Page
	 */
    Page<Permission> searchPageList(@Param("searchFields") Map<String, Object> params);
	
	Long getTotalCount(Map<String, Object> params);

	List<Permission> findListByParams(@Param("searchFields") Map<String, Object> params);

	/**
	 * 根据查询条件 返回单个对象
	 * @param params
	 * @return  
	 */
    Permission searchOne(@Param("searchFields") Map<String, Object> params);

	public List<Permission> searchByIds(@Param("list") List<Integer> ids,@Param("resourceType") String resourceType);
}
