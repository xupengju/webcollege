package com.college.service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.college.entity.Notice;
import com.college.repository.NoticeDao;


/**
* @Title: 
* @Description
* @author milo    
*/
@Service
public class NoticeService {

	private static Logger logger = LoggerFactory
			.getLogger(NoticeService.class);

	@Autowired
	private NoticeDao noticeDao;

	
	
	public Notice get(Long id) {
		return noticeDao.get(id);
	}

	public Integer insert(Notice notice) {
		noticeDao.insert(notice);
		return notice.getId();
	}
	
	public void update(Notice notice) {
		noticeDao.update(notice);
	}

	public void delete(Long id) {
		noticeDao.delete(id);
	}
	
	public Page<Notice> searchPageList(int offset , int size,Map<String, Object> params){		
		PageHelper.startPage(offset, size);
		return noticeDao.searchPageList(params);
	}

	public Long getTotalCount(Map<String, Object> params) {
		return noticeDao.getTotalCount(params);
	}

	public List<Notice> findListByParams(Map<String, Object> params){
		return noticeDao.findListByParams(params);
	}
	
	public Notice searchOne(Map<String, Object> params) {
        return noticeDao.searchOne(params);
    }
 
}
