package com.sacc.comprehensivesystem.admin.sys.entity.auto;

import com.sacc.comprehensivesystem.common.entity.BasicEntity;

/**
 * 对应的实体类父类，请勿做任何修改
 */
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
    private Integer roleId;

    // ---------------------------------
    // permission_id的属性
    // ---------------------------------
    private Integer permissionId;



    public Integer getRoleId() {
        return this.roleId;
    }

    public Integer getPermissionId() {
        return this.permissionId;
    }



    public void setRoleId(Integer roleId) {
        this.roleId =  roleId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId =  permissionId;
    }

}
