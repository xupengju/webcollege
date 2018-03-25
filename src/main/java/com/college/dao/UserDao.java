package com.college.dao;

import com.college.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Milo on 2018/3/24.
 * @description
 */
@Repository("userDao")
public interface UserDao {

    User get(Long id);

    User update(Long id);

    User insert(Long id);

    List<User> search(Map<String, Object> params);

    List<User> searchStudent(@Param(value = "searchFields") Map<String, Object> params);
}

