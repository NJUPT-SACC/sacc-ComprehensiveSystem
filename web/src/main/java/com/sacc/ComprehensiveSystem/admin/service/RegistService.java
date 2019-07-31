package com.sacc.ComprehensiveSystem.admin.service;

import com.sacc.ComprehensiveSystem.common.utils.Base64;
import com.sacc.ComprehensiveSystem.common.utils.MD5Utils;
import com.sacc.ComprehensiveSystem.admin.sys.entity.SysUser;
import com.sacc.ComprehensiveSystem.admin.sys.entity.SysUserRole;
import com.sacc.ComprehensiveSystem.admin.sys.service.SysUserService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String UserEmailPost(SysUser sysUser) {
        String md5Encode =  MD5Utils.MD5Encode(String.valueOf(sysUser.getCreateDate()),"utf8");
        String base64Code = Base64.Base64Decoder(String.valueOf(sysUser.getId()));
        return md5Encode+base64Code;
    }

    public int SignatureCheck(String url) {
        String base64=url.substring(32);
        System.out.println(base64);
        String md5=url.substring(0,32);
        System.out.println(md5);
        Long ID=sysUserService.CheckBase64(base64);
        return sysUserService.CheckMd5(ID,md5);
    }

}

