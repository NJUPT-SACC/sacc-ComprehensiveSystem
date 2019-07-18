package com.sacc.ComprehensiveSystem.admin.sys.entity.auto;

import com.sacc.ComprehensiveSystem.common.entity.BasicEntity;

public class _SysRole extends BasicEntity {
    public static final String COLUMN_USER_ID_ = "user_id";
    public static final String COLUMN_USER_ID_ASC_ = "user_id asc";
    public static final String COLUMN_USER_ID_DESC_ = "user_id desc";

    public static final String COLUMN_ROLE_ID_ = "role_id";
    public static final String COLUMN_ROLE_ID_ASC_ = "role_id asc";
    public static final String COLUMN_ROLE_ID_DESC_ = "role_id desc";


    // ---------------------------------
    // user_id的属性
    // ---------------------------------
    private String userId;

    // ---------------------------------
    // role_id的属性
    // ---------------------------------
    private String roleId;


    public String getUserId() {
        return this.userId;
    }

    public String getRoleId() {
        return this.roleId;
    }


    public void setUserId(String userId) {
        this.userId =  userId;
    }

    public void setRoleId(String roleId) {
        this.roleId =  roleId;
    }
}
