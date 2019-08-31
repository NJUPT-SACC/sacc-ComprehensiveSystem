package com.sacc.comprehensivesystem.admin.service;

import com.sacc.comprehensivesystem.admin.Utils.CacheUtils;
import com.sacc.comprehensivesystem.admin.shrio.entity.UserSimpleAuthorizationInfo;
import com.sacc.comprehensivesystem.common.service.BasicService;
import com.sacc.comprehensivesystem.common.utils.Base64;
import com.sacc.comprehensivesystem.common.utils.MD5Utils;
import com.sacc.comprehensivesystem.admin.sys.entity.SysUser;
import com.sacc.comprehensivesystem.admin.sys.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class RegistService {
    static Logger logger = LoggerFactory.getLogger(LoginService.class);

    /**
     * 1.插入用户信息
     * 2.
     */
    @Autowired
    SysUserService sysUserService;

    @Transactional(readOnly = false, rollbackFor = Exception.class)
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
    public String userEmailPost(SysUser sysUser) {
        String md5Encode =  MD5Utils.MD5Encode(String.valueOf(sysUser.getCreateDate()),"utf8");
        String base64Code = Base64.Base64Decoder(String.valueOf(sysUser.getId()));
        return md5Encode+base64Code;
    }

    public int signatureCheck(String url) {
        String base64 = url.substring(32);
        String md5 = url.substring(0,32);
        Long ID = sysUserService.checkBase64(base64);
        return sysUserService.checkMd5(ID,md5);
    }

    public void activate(String authKey) {
        UserSimpleAuthorizationInfo info = (UserSimpleAuthorizationInfo)CacheUtils.getUserCache(authKey);
        Set<String> set = new HashSet<>();
        set.remove("unvalidation");
        set.add("user");
        info.setRoles(set);
        CacheUtils.removeUserCache(authKey);
        CacheUtils.putUserCache(authKey, info);
    }
}

