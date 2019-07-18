package com.sacc.ComprehensiveSystem.admin.sys.dao;

import com.sacc.ComprehensiveSystem.admin.sys.entity.SysMenu;
import com.sacc.ComprehensiveSystem.admin.sys.entity.SysUser;
import com.sacc.ComprehensiveSystem.common.dao.BasicDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserDao extends BasicDao<SysUser> {
    SysUser findByLoginName(String loginName);

    List<SysMenu> findMenuByUserId(String id);

    List<String> findRoleByUserId(String id);

    List<String> findPermissionByUserId(String id);

    List<SysUser> findUserNotAdmin(SysUser sysUser);

}
