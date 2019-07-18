package com.sacc.ComprehensiveSystem.admin.service;

import com.sacc.ComprehensiveSystem.admin.Utils.CacheUtils;
import com.sacc.ComprehensiveSystem.admin.shrio.entity.UserSimpleAuthorizationInfo;
import com.sacc.ComprehensiveSystem.admin.sys.entity.SysMenu;
import com.sacc.ComprehensiveSystem.admin.sys.entity.SysUser;
import com.sacc.ComprehensiveSystem.admin.sys.service.SysUserService;
import com.sacc.ComprehensiveSystem.common.utils.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.json.JSONObject;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class LoginService {
    static Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    AuthService authService;

    @Autowired
    SysUserService sysUserService;


    @Transactional(readOnly = false)
    public HashMap<String, Object> login(String postJson){
        System.out.println("login attempt");
        JSONObject jsonObject = new JSONObject(postJson);
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        HashMap<String,Object> data = null;
        Object result = null;
        UserSimpleAuthorizationInfo userSimpleAuthorizationInfo = new UserSimpleAuthorizationInfo();
        HashMap<String, Object> hashMap = authService.authtication(username, password);
        if (hashMap != null) {
            String userInfoStr = JSONUtils.toJson(hashMap.get("userInfo"));
            SysUser sysUser = JSONUtils.readValue(userInfoStr, SysUser.class);
            //密码不可见
            sysUser.setPassword(null);
            logger.debug("sysUser from auth: {}", sysUser);

            List<SysMenu> menuList = (List<SysMenu>) hashMap.get("menuList");
            logger.debug("menuList from auth:{}", menuList);

            List<String> roleList = (List<String>) hashMap.get("roleList");
            logger.debug("roleList from auth:{}", roleList);

            Set set = (Set) hashMap.get("permission");
            List<String> permission = new ArrayList<>(set);
            logger.debug("permission from auth:{}", permission);

            userSimpleAuthorizationInfo.setSysUser(sysUser);
            userSimpleAuthorizationInfo.setMenuList(menuList);
            userSimpleAuthorizationInfo.addRoles(roleList);
            userSimpleAuthorizationInfo.addStringPermissions(permission);
            logger.debug("userSimpleAuthorizationInfo:{}", userSimpleAuthorizationInfo);
            data = new HashMap<>();
            data.put("user",userSimpleAuthorizationInfo);
            String authKey = UUID.randomUUID().toString();
            data.put("authKey", authKey);
            //cacheService.cacheUser(authKey, userSimpleAuthorizationInfo);
            CacheUtils.putUserCache(authKey, userSimpleAuthorizationInfo,3600 * 12);
            return data;
        } else {
            return null;
        }
    }
}