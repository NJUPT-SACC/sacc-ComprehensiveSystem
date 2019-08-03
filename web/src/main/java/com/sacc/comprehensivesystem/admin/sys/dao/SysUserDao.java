package com.sacc.comprehensivesystem.admin.sys.dao;

import com.sacc.comprehensivesystem.admin.sys.entity.SysMenu;
import com.sacc.comprehensivesystem.admin.sys.entity.SysUser;
import com.sacc.comprehensivesystem.common.dao.BasicDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserDao extends BasicDao<SysUser> {
    SysUser findByLoginName(String loginName);

    Long findIdByLoginName(String name);

    SysUser get(Long id);

    List<SysMenu> findMenuByUserId(Long id);

    List<String> findRoleByUserId(Long id);

    List<String> findPermissionByUserId(Long id);

    List<SysUser> findUserNotAdmin(SysUser sysUser);

}
