package com.sacc.comprehensivesystem.admin.sys.entity.auto;

import com.sacc.comprehensivesystem.common.entity.BasicEntity;

import javax.validation.constraints.NotNull;

/**
 * 对应的实体类父类，请勿做任何修改
 */
public class _SysUser extends BasicEntity {
    public static final String COLUMN_NAME_ = "name";
    public static final String COLUMN_NAME_ASC_ = "name asc";
    public static final String COLUMN_NAME_DESC_ = "name desc";

    public static final String COLUMN_LOGIN_NAME_ = "login_name";
    public static final String COLUMN_LOGIN_NAME_ASC_ = "login_name asc";
    public static final String COLUMN_LOGIN_NAME_DESC_ = "login_name desc";

    public static final String COLUMN_EMAIL_ = "email";
    public static final String COLUMN_EMAIL_ASC_ = "email asc";
    public static final String COLUMN_EMAIL_DESC_ = "email desc";

    public static final String COLUMN_TEL_ = "tel";
    public static final String COLUMN_TEL_ASC_ = "tel asc";
    public static final String COLUMN_TEL_DESC_ = "tel desc";

    public static final String COLUMN_PASSWORD_ = "password";
    public static final String COLUMN_PASSWORD_ASC_ = "password asc";
    public static final String COLUMN_PASSWORD_DESC_ = "password desc";

    public static final String COLUMN_PIC_URL_ = "pic_url";
    public static final String COLUMN_PIC_URL_ASC_ = "pic_url asc";
    public static final String COLUMN_PIC_URL_DESC_ = "pic_url desc";





    private String name;


    private String loginName;


    private String email;


    private String tel;

    @NotNull
    private String password;


    private String picUrl;


    public String getName() {
        return this.name;
    }

    public String getLoginName() {
        return this.loginName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getTel() {
        return this.tel;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPicUrl() {
        return this.picUrl;
    }




    public void setName(String name) {
        this.name =  name;
    }

    public void setLoginName(String loginName) {
        this.loginName =  loginName;
    }

    public void setEmail(String email) {
        this.email =  email;
    }

    public void setTel(String tel) {
        this.tel =  tel;
    }

    public void setPassword(String password) {
        this.password =  password;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl =  picUrl;
    }




}
