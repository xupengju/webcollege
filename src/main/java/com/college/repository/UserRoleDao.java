package com.college.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

import com.college.entity.UserRole;

/**
* @Title: 
* @Description
* @author milo    
*/
@Repository("userRoleDao")
public interface UserRoleDao {

	UserRole get(Long id);
	
	void insert(UserRole userRole);

	void delete(Long id);

	void update(UserRole userRole);

	/**
	 * 分页查询 返回Page 对象
	 * @param params psge
	 * @return  Page
	 */
    Page<UserRole> searchPageList(@Param("searchFields") Map<String, Object> params);
	
	Long getTotalCount(Map<String, Object> params);

	List<UserRole> findListByParams(@Param("searchFields") Map<String, Object> params);

	/**
	 * 根据查询条件 返回单个对象
	 * @param params
	 * @return  
	 */
    UserRole searchOne(@Param("searchFields") Map<String, Object> params);

	/**
	 * 更新用户角色
	 * @param userId
	 * @param roleId
	 */
	void updateUserRole(Integer userId, Integer roleId);;
}
