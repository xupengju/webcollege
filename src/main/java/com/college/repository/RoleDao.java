package com.college.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

import com.college.entity.Role;

/**
* @Title: 
* @Description
* @author milo    
*/
@Repository("roleDao")
public interface RoleDao {

	Role get(Long id);
	
	void insert(Role role);

	void delete(Long id);

	void update(Role role);

	/**
	 * 分页查询 返回Page 对象
	 * @param params psge
	 * @return  Page
	 */
    Page<Role> searchPageList(@Param("searchFields") Map<String, Object> params);
	
	Long getTotalCount(Map<String, Object> params);

	List<Role> findListByParams(@Param("searchFields") Map<String, Object> params);

	/**
	 * 根据查询条件 返回单个对象
	 * @param params
	 * @return  
	 */
    Role searchOne(@Param("searchFields") Map<String, Object> params);

}
