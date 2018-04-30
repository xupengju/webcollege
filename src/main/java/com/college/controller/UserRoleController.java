package com.college.controller;

import com.college.service.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author milo
 * @Title:
 * @Description
 */
@RestController
public class UserRoleController extends BaseController {

    private static Logger logger = LoggerFactory
            .getLogger(UserRoleController.class);

    @Autowired
    private UserRoleService userRoleService;
}
