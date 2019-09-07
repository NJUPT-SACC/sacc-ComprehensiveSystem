package com.sacc.comprehensivesystem.admin.service;

import com.sacc.comprehensivesystem.admin.Utils.CacheUtils;
import com.sacc.comprehensivesystem.admin.shrio.entity.UserSimpleAuthorizationInfo;
import com.sacc.comprehensivesystem.admin.sys.entity.SysMenu;
import com.sacc.comprehensivesystem.admin.sys.entity.SysUser;
import com.sacc.comprehensivesystem.admin.sys.service.SysUserService;
import com.sacc.comprehensivesystem.common.utils.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.json.JSONObject;

import java.util.*;

/**
 * @author yuyim
 */
@Service
@Transactional(readOnly = true)
public class LoginService {
    private static Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    AuthService authService;

    @Autowired
    SysUserService sysUserService;

    @Transactional(readOnly = false)
    public HashMap<String, Object> login(String postJson){
        JSONObject jsonObject = new JSONObject(postJson);
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");

        HashMap<String,Object> data = null;
        UserSimpleAuthorizationInfo userSimpleAuthorizationInfo =
                new UserSimpleAuthorizationInfo();
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

            Set<String> set = (Set<String>) hashMap.get("permission");
            List<String> permission = new ArrayList<>(set);
            logger.debug("permission from auth:{}", permission);

            userSimpleAuthorizationInfo.setSysUser(sysUser);
            userSimpleAuthorizationInfo.setMenuList(menuList);
            userSimpleAuthorizationInfo.addRoles(roleList);
            userSimpleAuthorizationInfo.addStringPermissions(permission);
            logger.debug("userSimpleAuthorizationInfo:{}", userSimpleAuthorizationInfo);
            data = new HashMap<>(2);
            data.put("user",userSimpleAuthorizationInfo);
            String authKey = UUID.randomUUID().toString();
            data.put("authKey", authKey);
            //cacheService.cacheUser(authKey, userSimpleAuthorizationInfo);
            //CacheUtils.putUserCache(authKey, userSimpleAuthorizationInfo,3600 * 12);
            CacheUtils.put("user_info", authKey, userSimpleAuthorizationInfo);
            return data;
        } else {
            return null;
        }
    }
}
