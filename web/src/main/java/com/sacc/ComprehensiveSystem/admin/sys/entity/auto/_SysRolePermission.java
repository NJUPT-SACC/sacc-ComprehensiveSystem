package com.sacc.ComprehensiveSystem.admin.sys.entity.auto;

import com.sacc.ComprehensiveSystem.common.entity.BasicEntity;

public class _SysRolePermission extends BasicEntity {

    public static final String COLUMN_ROLE_ID_ = "role_id";
    public static final String COLUMN_ROLE_ID_ASC_ = "role_id asc";
    public static final String COLUMN_ROLE_ID_DESC_ = "role_id desc";

    public static final String COLUMN_PERMISSION_ID_ = "permission_id";
    public static final String COLUMN_PERMISSION_ID_ASC_ = "permission_id asc";
    public static final String COLUMN_PERMISSION_ID_DESC_ = "permission_id desc";



    // ---------------------------------
    // role_id的属性
    // ---------------------------------
    private String roleId;

    // ---------------------------------
    // permission_id的属性
    // ---------------------------------
    private String permissionId;



    public String getRoleId() {
        return this.roleId;
    }

    public String getPermissionId() {
        return this.permissionId;
    }



    public void setRoleId(String roleId) {
        this.roleId =  roleId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId =  permissionId;
    }

}
