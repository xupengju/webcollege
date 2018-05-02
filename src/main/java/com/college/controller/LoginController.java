package com.college.controller;

import com.college.contants.AppCode;
import com.college.contants.Path;
import com.college.entity.User;
import com.college.model.LoginReqModel;
import com.college.model.LoginRespModel;
import com.college.model.Resp;
import com.college.service.user.ApiOAuthTokenService;
import com.college.service.user.ApiUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录Controller
 */
@RestController
public class LoginController extends BaseController {
    @Autowired
    private ApiUserService apiUserService;
    @Autowired
    private ApiOAuthTokenService apiOAuthTokenService;
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(Path.USER_LOGIN)
    public Object login(LoginReqModel user) {
        logger.info("login : {}", user.toString());
        User loginUser = apiUserService.attempLogin(user.getUserName(), user.getPassword());
        if (null == loginUser) {
            return Resp.error(AppCode._10002);
        } else {
            // 登录成功,设置token,保存token到数据库
            String token = apiOAuthTokenService.generatorTokenByUserId(loginUser.getId());
            logger.info("token : {}", token);
            // 返回用户信息
            LoginRespModel loginRespModel = new LoginRespModel();
            loginRespModel.setUserId(loginUser.getId());
            loginRespModel.setRealName(loginUser.getRealName());
            loginRespModel.setToken(token);
            return Resp.success(loginRespModel);
        }
    }

    @RequestMapping(value = "/index")
    public String index() {
        logger.info("index : {}", "index");
        return "index";
    }
}
