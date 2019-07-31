package com.sacc.ComprehensiveSystem.admin.sys.dao;

import com.sacc.ComprehensiveSystem.admin.sys.entity.SysRole;
import com.sacc.ComprehensiveSystem.admin.sys.entity.SysUserRole;
import com.sacc.ComprehensiveSystem.common.dao.BasicDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserRoleDao extends BasicDao<SysUserRole> {
    public List<SysRole> listRoleByUserId(@Param("userId") String userId);
    void insert_role(SysUserRole sysUserRole);
    void update_role(SysUserRole sysUserRole);
}
