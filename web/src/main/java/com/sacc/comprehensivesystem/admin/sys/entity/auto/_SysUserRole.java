package com.sacc.comprehensivesystem.admin.sys.entity.auto;

import com.sacc.comprehensivesystem.common.entity.BasicEntity;

/**
 * 对应的实体类父类，请勿做任何修改
 */
public class _SysUserRole extends BasicEntity {

    public static final String COLUMN_USER_ID_ = "user_id";
    public static final String COLUMN_USER_ID_ASC_ = "user_id asc";
    public static final String COLUMN_USER_ID_DESC_ = "user_id desc";

    public static final String COLUMN_ROLE_ID_ = "role_id";
    public static final String COLUMN_ROLE_ID_ASC_ = "role_id asc";
    public static final String COLUMN_ROLE_ID_DESC_ = "role_id desc";



    // ---------------------------------
    // user_id的属性
    // ---------------------------------
    private Long userId;

    // ---------------------------------
    // role_id的属性
    // ---------------------------------
    private Long roleId;



    public Long getUserId() {
        return this.userId;
    }

    public Long getRoleId() {
        return this.roleId;
    }



    public void setUserId(Long userId) {
        this.userId =  userId;
    }

    public void setRoleId(Long roleId) {
        this.roleId =  roleId;
    }



}
