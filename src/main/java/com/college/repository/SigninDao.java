package com.college.repository;

import java.util.List;
import java.util.Map;

import com.college.entity.User;
import org.apache.ibatis.annotations.Param;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

import com.college.entity.Signin;

/**
* @Title: 
* @Description
* @author milo    
*/
@Repository("signinDao")
public interface SigninDao {

	Signin get(Long id);
	
	void insert(Signin signin);

	void delete(Long id);

	void update(Signin signin);

	/**
	 * 分页查询 返回Page 对象
	 * @param params psge
	 * @return  Page
	 */
    Page<Signin> searchPageList(@Param("searchFields") Map<String, Object> params);
	
	Long getTotalCount(Map<String, Object> params);

	List<Signin> findListByParams(@Param("searchFields") Map<String, Object> params);

	/**
	 * 根据查询条件 返回单个对象
	 * @param params
	 * @return  
	 */
    Signin searchOne(@Param("searchFields") Map<String, Object> params);

	/**
	 * 条件查询
	 * @param params
	 * @return  List<User>
	 */
	List<User> conditionalQuery(@Param("searchFields") Map<String, Object> params);

}
