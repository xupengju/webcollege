package com.college.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

import com.college.entity.User;

/**
* @Title: 
* @Description
* @author milo    
*/
@Repository("userDao")
public interface UserDao {

	User get(Long id);
	
	void insert(User user);

	void delete(Long id);

	void update(User user);

	/**
	 * 分页查询 返回Page 对象
	 * @param params psge
	 * @return  Page
	 */
    Page<User> searchPageList(@Param("searchFields") Map<String, Object> params);
	
	Long getTotalCount(Map<String, Object> params);

	List<User> findListByParams(@Param("searchFields") Map<String, Object> params);

	/**
	 * 根据查询条件 返回单个对象
	 * @param params
	 * @return  
	 */
    User searchOne(@Param("searchFields") Map<String, Object> params);

}
