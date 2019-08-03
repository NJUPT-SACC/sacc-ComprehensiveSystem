package com.sacc.comprehensivesystem.admin.sys.service;

import com.sacc.comprehensivesystem.admin.sys.entity.SysUser;
import com.sacc.comprehensivesystem.admin.sys.entity.SysUserRole;
import org.springframework.stereotype.Service;

@Service
public class SysUserRoleService {

    public SysUserRole userRoleService(SysUser sysUser)
    {
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setRoleId(4l);
        return sysUserRole;
    }
}
