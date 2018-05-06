package com.college.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

import com.college.entity.BaseInfo;

/**
* @Title: 
* @Description
* @author milo    
*/
@Repository("baseInfoDao")
public interface BaseInfoDao {

	BaseInfo get(Long id);
	
	void insert(BaseInfo baseInfo);

	void delete(Long id);

	void update(BaseInfo baseInfo);

	/**
	 * 分页查询 返回Page 对象
	 * @param params psge
	 * @return  Page
	 */
    Page<BaseInfo> searchPageList(@Param("searchFields") Map<String, Object> params);
	
	Long getTotalCount(Map<String, Object> params);

	List<BaseInfo> findListByParams(@Param("searchFields") Map<String, Object> params);

	/**
	 * 根据查询条件 返回单个对象
	 * @param params
	 * @return  
	 */
    BaseInfo searchOne(@Param("searchFields") Map<String, Object> params);

}
