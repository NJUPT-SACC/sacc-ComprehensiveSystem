package com.sacc.comprehensivesystem.admin.service;

import com.sacc.comprehensivesystem.admin.sys.entity.SysMenu;
import com.sacc.comprehensivesystem.admin.sys.entity.SysUser;
import com.sacc.comprehensivesystem.admin.sys.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class AuthService {
    static Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    SysUserService sysUserService;

    public HashMap<String, Object> authtication(String username, String password) {
        HashMap<String, Object> hashMap = null;
        SysUser sysUser = sysUserService.login(username, password);
        if (sysUser != null) {
            hashMap = new HashMap<>();
            hashMap.put("userInfo", sysUser);

            //menu
            List<SysMenu> menuList = sysUserService.findMenuByUserId(sysUser.getId());
            hashMap.put("menuList", menuList);

            //role
            List<String> roleList = sysUserService.findRoleByUserId(sysUser.getId());
            hashMap.put("roleList", roleList);

            //permission
            List<String> permissionList = sysUserService.findPermissionByUserId(sysUser.getId());
            Set<String> permission = new HashSet<>();

            for (String str : permissionList) {
                permission.addAll(Arrays.asList(str.split(",")));
            }
            hashMap.put("permission", permission);
        }
        return hashMap;
    }
}
