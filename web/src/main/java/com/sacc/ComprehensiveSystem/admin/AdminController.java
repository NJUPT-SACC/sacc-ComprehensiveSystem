package com.sacc.ComprehensiveSystem.admin;

import com.sacc.ComprehensiveSystem.admin.service.LoginService;
import com.sacc.ComprehensiveSystem.admin.service.RegistService;
import com.sacc.ComprehensiveSystem.common.utils.RestResult;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/admin")
public class AdminController {
    static Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    LoginService loginService;

    @CrossOrigin
    @RequestMapping("/login")
    public RestResult<Object> login(@RequestBody JSONObject postJson) {
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

    @Autowired
    RegistService registService;

    @RequestMapping("/signup")
    public RestResult signUp(@RequestBody String postJson) {
        logger.debug("/signUp -> postJson:{}", postJson);
        int resultt=2;

        RestResult<Object> result = null;
        try {
            resultt =registService.signUp(postJson);
        } catch (Exception e) {
            logger.error("Error: {}\n{}",e.getMessage(), e.getStackTrace());
            result = new RestResult<>(RestResult.STATUS_OTHERS, "注册失败", null);
        }
        logger.debug("/signup -> result {}",result);
        switch(resultt) {
            case 1:
                result= new RestResult<Object>(RestResult.STATUS_SUCCESS, "注册成功",null);
                break;
            case 2:
                result = new RestResult<>(RestResult.STATUS_OTHERS, "注册失败", null);
                break;
            case 3:
                result=new RestResult<Object>(RestResult.STATUS_DUPLICATION,"重复注册",null);
                break;
            default:
                break;
        }
        return result;
    }

    @RequestMapping("/check")
    public  RestResult GetSignature(@RequestParam String signature)
    {
        int resultt=1;

        RestResult<Object> result = null;
       try{
           resultt=registService.SignatureCheck(signature);
       }
       catch (Exception e){
           logger.error("Error: {}\n{}",e.getMessage(), e.getStackTrace());
           result = new RestResult<>(RestResult.STATUS_OTHERS, "验证失败", null);
       }
       switch (resultt) {
           case 1:
               result = new RestResult<Object>(RestResult.STATUS_SUCCESS, "验证成功",null);
               break;
           case 0:
               result = new RestResult<>(RestResult.STATUS_OTHERS, "验证失败", null);
               break;
       }
       return result;

    }


    @RequestMapping("/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public RestResult<Object> unauthorized() {
        return new RestResult<>(401, "invalid authKey or not login", null);
    }
}
