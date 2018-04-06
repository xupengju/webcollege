package com.college.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

import com.college.entity.RolePermission;

/**
* @Title: 
* @Description
* @author milo    
*/
@Repository("rolePermissionDao")
public interface RolePermissionDao {

	RolePermission get(Long id);
	
	void insert(RolePermission rolePermission);

	void delete(Long id);

	void update(RolePermission rolePermission);

	/**
	 * 分页查询 返回Page 对象
	 * @param params psge
	 * @return  Page
	 */
    Page<RolePermission> searchPageList(@Param("searchFields") Map<String, Object> params);
	
	Long getTotalCount(Map<String, Object> params);

	List<RolePermission> findListByParams(@Param("searchFields") Map<String, Object> params);

	/**
	 * 根据查询条件 返回单个对象
	 * @param params
	 * @return  
	 */
    RolePermission searchOne(@Param("searchFields") Map<String, Object> params);

	/**
	 * 根据角色Id查询权限列表
	 * @param roleIdList
	 * @return
	 */
	List<RolePermission> searchByRoleIds(@Param("list") List<Integer> roleIdList);


}
