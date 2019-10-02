package com.sacc.comprehensivesystem.admin;

import com.sacc.comprehensivesystem.admin.Utils.CacheUtils;
import com.sacc.comprehensivesystem.admin.service.LoginService;
import com.sacc.comprehensivesystem.admin.service.RegistService;
import com.sacc.comprehensivesystem.admin.shrio.entity.UserSimpleAuthorizationInfo;
import com.sacc.comprehensivesystem.admin.sys.entity.SysUser;
import com.sacc.comprehensivesystem.common.utils.RestResult;
import com.sacc.comprehensivesystem.modules.mail.MailService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author yuyim
 */
@RestController
public class AdminController {
    static Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    LoginService loginService;

    @Autowired
    MailService mailService;

    @CrossOrigin
    @RequestMapping("/admin/login")
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

    @CrossOrigin
    @RequestMapping("/admin/signup")
    public RestResult signUp(@RequestBody String postJson) {
        logger.debug("/signUp -> postJson:{}", postJson);
        int resultt=2;

        RestResult<Object> result;
        try {
            resultt =registService.signUp(postJson);
        } catch (Exception e) {
            logger.error("Error: {}\n{}",e.getMessage(), e.getStackTrace());
            result = new RestResult<>(RestResult.STATUS_OTHERS, "注册失败", null);
        }

        switch(resultt) {
            case 1:
                result = new RestResult<>(RestResult.STATUS_SUCCESS, "注册成功",null);
                break;
            case 2:
                result = new RestResult<>(RestResult.STATUS_OTHERS, "注册失败", null);
                break;
            case 3:
                result = new RestResult<>(RestResult.STATUS_DUPLICATION,"用户重复注册",null);
                break;
            case 4:
                result = new RestResult<>(RestResult.STATUS_DUPLICATION,"用户名重复注册",null);
                break;
            case 5:
                result = new RestResult<>(RestResult.STATUS_DUPLICATION,"邮箱重复注册",null);
                break;
            default:
                result = null;
                break;
        }

        return result;
    }

    @CrossOrigin
    @RequestMapping("/admin/check")
    public RestResult getSignature(@RequestParam String signature) {
        String authKey = SecurityUtils.getSubject().getPrincipal().toString();
        int resultt = 1;
        RestResult<Object> result = null;
       try {
           resultt = registService.signatureCheck(signature);
       }
       catch (Exception e) {
           logger.error("Error: {}\n{}",e.getMessage(), e.getStackTrace());
           result = new RestResult<>(RestResult.STATUS_OTHERS, "验证失败", null);
           return result;
       }
       if (resultt == 1) {
           registService.activate(authKey);
           result = new RestResult<>(RestResult.STATUS_SUCCESS, "验证成功",null);
       } else {
           result = new RestResult<>(RestResult.STATUS_OTHERS, "验证失败", null);
       }

       return result;
    }

    @CrossOrigin
    @RequestMapping(value = "/sendMail", method = RequestMethod.GET)
    public void sendMail(@RequestHeader("authKey") String token) {
        UserSimpleAuthorizationInfo info = (UserSimpleAuthorizationInfo) CacheUtils.getUserCache(token);
        SysUser sysUser = info.getSysUser();
        String signature = registService.userEmailPost(sysUser);
        String to = sysUser.getEmail();
        mailService.sendSimpleMail(to,"test", signature);
    }

    @RequestMapping("/ComprehensiveSystem/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public RestResult<Object> unauthorized() {
        return new RestResult<>(401, "invalid authKey or not login", null);
    }
}
