package com.sacc.ComprehensiveSystem.admin.service;

import com.sacc.ComprehensiveSystem.admin.sys.entity.SysUser;
import com.sacc.ComprehensiveSystem.admin.sys.service.SysUserService;
import com.sacc.ComprehensiveSystem.common.utils.RestResult;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service


public class RegistService {
    static Logger logger = LoggerFactory.getLogger(LoginService.class);

    /**
     * 1.插入用户信息
     * 2.
     */
    @Autowired
    SysUserService sysUserService;

    @Transactional(readOnly = false)
    public int signUp(String postJson) {
        int resultt = 2;
        JSONObject jsonObject = new JSONObject(postJson);

        SysUser sysUser=new SysUser();
        sysUser.setLoginName(jsonObject.getString("username"));

        sysUser.setEmail(jsonObject.getString("email"));
        sysUser.setPassword(jsonObject.getString("password"));

       try {
            resultt = sysUserService.signUp(sysUser);
        } catch (Exception e) {
            logger.error("Error: {}\n{}", e.getMessage(), e.getStackTrace());
            resultt=2;
        }
        return resultt;

    }
}

