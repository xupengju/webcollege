package com.college.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

import com.college.entity.Notice;

/**
* @Title: 
* @Description
* @author milo    
*/
@Repository("noticeDao")
public interface NoticeDao {

	Notice get(Long id);
	
	void insert(Notice notice);

	void delete(Long id);

	void update(Notice notice);

	/**
	 * 分页查询 返回Page 对象
	 * @param params psge
	 * @return  Page
	 */
    Page<Notice> searchPageList(@Param("searchFields") Map<String, Object> params);
	
	Long getTotalCount(Map<String, Object> params);

	List<Notice> findListByParams(@Param("searchFields") Map<String, Object> params);

	/**
	 * 根据查询条件 返回单个对象
	 * @param params
	 * @return  
	 */
    Notice searchOne(@Param("searchFields") Map<String, Object> params);

}
