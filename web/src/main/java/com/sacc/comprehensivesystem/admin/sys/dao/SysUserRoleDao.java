package com.sacc.comprehensivesystem.admin.sys.dao;

import com.sacc.comprehensivesystem.admin.sys.entity.SysRole;
import com.sacc.comprehensivesystem.admin.sys.entity.SysUserRole;
import com.sacc.comprehensivesystem.common.dao.BasicDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserRoleDao extends BasicDao<SysUserRole> {
    public List<SysRole> listRoleByUserId(@Param("userId") String userId);
    void insertRole(SysUserRole sysUserRole);
    void updateRole(SysUserRole sysUserRole);
}
