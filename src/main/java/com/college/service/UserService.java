package com.college.service;

import com.college.dao.UserDao;
import com.college.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Milo on 2018/3/24.
 * @description
 */
@Service
public class UserService {

    private static Logger logger = LoggerFactory
            .getLogger(UserService.class);

    @Autowired
    private UserDao userDao;


    public User get(Long id){
        return userDao.get(id);
    }

    public List<User> search (Map<String, Object> params){
        return userDao.search(params);
    }

    public List<User> searchStudent(Map<String, Object> params) {
        return userDao.searchStudent(params);
    }
}
