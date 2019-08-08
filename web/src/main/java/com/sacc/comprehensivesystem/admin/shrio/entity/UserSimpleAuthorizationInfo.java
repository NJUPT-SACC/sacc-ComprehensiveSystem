package com.sacc.comprehensivesystem.admin.shrio.entity;

import com.sacc.comprehensivesystem.admin.sys.entity.SysMenu;
import com.sacc.comprehensivesystem.admin.sys.entity.SysRole;
import com.sacc.comprehensivesystem.admin.sys.entity.SysUser;
import org.apache.shiro.authz.SimpleAuthorizationInfo;

import java.util.List;

public class UserSimpleAuthorizationInfo extends SimpleAuthorizationInfo {

    private SysUser sysUser;

    private List<SysRole> roleList;

    private List<SysMenu> menuList;

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }

    public List<SysMenu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<SysMenu> menuList) {
        this.menuList = menuList;
    }
}
