package com.sacc.ComprehensiveSystem.admin;

import com.sacc.ComprehensiveSystem.admin.service.LoginService;
import com.sacc.ComprehensiveSystem.common.utils.RestResult;
import org.apache.shiro.crypto.hash.Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/admin")
public class LoginController {
    static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    LoginService loginService;

    @CrossOrigin
    @RequestMapping("/login")
    public RestResult<Object> login(@RequestBody String postJson) {
        //验证登录，将登录成功后的数据缓存起来Todo
        logger.debug("/login -> postJson:{}", postJson);
        RestResult<Object> result = null;
        try {
            HashMap data =loginService.login(postJson);
            if (data != null) {
                result = new RestResult<Object>(RestResult.STATUS_SUCCESS, "登陆成功", data);
            } else {
                result = new RestResult<>(RestResult.STATUS_OTHERS, "登陆失败", null);
            }
        } catch (Exception e) {
            logger.error("Error: {}\n{}",e.getMessage(), e.getStackTrace());
            result = new RestResult<>(RestResult.STATUS_OTHERS, "登陆失败", null);
        }
        logger.debug("/login -> result {}",result);
        return result;
    }

    @RequestMapping("/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public RestResult<Object> unauthorized() {
        return new RestResult<>(401, "invalid authKey or not login", null);
    }
}
