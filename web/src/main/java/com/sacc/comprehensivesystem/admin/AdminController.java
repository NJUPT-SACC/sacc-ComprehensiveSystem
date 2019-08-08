package com.sacc.comprehensivesystem.admin;

import com.sacc.comprehensivesystem.admin.service.LoginService;
import com.sacc.comprehensivesystem.admin.service.RegistService;
import com.sacc.comprehensivesystem.common.utils.RestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
            case 4:
                result=new RestResult<Object>(RestResult.STATUS_DUPLICATION,"用户名重复注册",null);
                break;
            case 5:
                result=new RestResult<Object>(RestResult.STATUS_DUPLICATION,"邮箱重复注册",null);
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

    @CrossOrigin
    @RequestMapping(value = "/test",consumes = MediaType.IMAGE_PNG_VALUE)
    public RestResult test(@RequestParam byte[] multipartFile) {
        System.out.println(multipartFile);
        return new RestResult(200, "成功", multipartFile);
    }


    @RequestMapping("/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public RestResult<Object> unauthorized() {
        return new RestResult<>(401, "invalid authKey or not login", null);
    }
}
