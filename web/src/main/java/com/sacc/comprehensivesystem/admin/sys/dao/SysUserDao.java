package com.sacc.comprehensivesystem.admin.sys.dao;

import com.sacc.comprehensivesystem.admin.sys.entity.SysMenu;
import com.sacc.comprehensivesystem.admin.sys.entity.SysUser;
import com.sacc.comprehensivesystem.common.dao.BasicDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserDao extends BasicDao<SysUser> {
    List<SysUser> findByLoginName(String loginName,String email);

    Long findIdByLoginName(String name);

    SysUser findUserByLoginName(String loginName);

    SysUser findUserByEmail(String loginName);

    @Override
    SysUser get(Long id);

    List<SysMenu> findMenuByUserId(Long id);

    List<String> findRoleByUserId(Long id);

    List<String> findPermissionByUserId(Long id);

    List<SysUser> findUserNotAdmin(SysUser sysUser);



}
