package com.sacc.ComprehensiveSystem.admin.sys.entity.auto;

import com.sacc.ComprehensiveSystem.common.entity.BasicEntity;

public class _SysPermission extends BasicEntity {

    public static final String COLUMN_URL_ = "url";
    public static final String COLUMN_URL_ASC_ = "url asc";
    public static final String COLUMN_URL_DESC_ = "url desc";

    public static final String COLUMN_PERMISSION_ = "permission";
    public static final String COLUMN_PERMISSION_ASC_ = "permission asc";
    public static final String COLUMN_PERMISSION_DESC_ = "permission desc";



    // ---------------------------------
    // url的属性
    // ---------------------------------
    private String url;

    // ---------------------------------
    // permission的属性
    // ---------------------------------
    private String permission;



    public String getUrl() {
        return this.url;
    }

    public String getPermission() {
        return this.permission;
    }



    public void setUrl(String url) {
        this.url =  url;
    }

    public void setPermission(String permission) {
        this.permission =  permission;
    }
}
