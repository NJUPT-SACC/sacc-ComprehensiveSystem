package com.sacc.comprehensivesystem.admin.sys.entity.auto;

import com.sacc.comprehensivesystem.common.entity.BasicEntity;

/**
 * 对应的实体类父类，请勿做任何修改
 */
public class _SysMenu extends BasicEntity {
    public static final String COLUMN_PARENT_ID_ = "parent_id";
    public static final String COLUMN_PARENT_ID_ASC_ = "parent_id asc";
    public static final String COLUMN_PARENT_ID_DESC_ = "parent_id desc";

    public static final String COLUMN_NAME_ = "name";
    public static final String COLUMN_NAME_ASC_ = "name asc";
    public static final String COLUMN_NAME_DESC_ = "name desc";

    public static final String COLUMN_SORT_ = "sort";
    public static final String COLUMN_SORT_ASC_ = "sort asc";
    public static final String COLUMN_SORT_DESC_ = "sort desc";

    public static final String COLUMN_URL_ = "url";
    public static final String COLUMN_URL_ASC_ = "url asc";
    public static final String COLUMN_URL_DESC_ = "url desc";

    public static final String COLUMN_ICON_ = "icon";
    public static final String COLUMN_ICON_ASC_ = "icon asc";
    public static final String COLUMN_ICON_DESC_ = "icon desc";

    public static final String COLUMN_IS_SHOW_ = "is_show";
    public static final String COLUMN_IS_SHOW_ASC_ = "is_show asc";
    public static final String COLUMN_IS_SHOW_DESC_ = "is_show desc";

    // ---------------------------------
    // parent_id的属性
    // ---------------------------------
    private Integer parentId;

    // ---------------------------------
    // name的属性
    // ---------------------------------
    private String name;

    // ---------------------------------
    // sort的属性
    // ---------------------------------
    private Integer sort;
    private Integer _maxSort; // 仅用于查询条件
    private Integer _minSort; // 仅用于查询条件

    // ---------------------------------
    // url的属性
    // ---------------------------------
    private String url;

    // ---------------------------------
    // icon的属性
    // ---------------------------------
    private String icon;

    // ---------------------------------
    // is_show的属性
    // ---------------------------------
    private String isShow;

    public Integer getParentId() {
        return this.parentId;
    }

    public String getName() {
        return this.name;
    }

    public Integer getSort() {
        return this.sort;
    }

    public Integer get_maxSort() {
        return this._maxSort;
    }

    public Integer get_minSort() {
        return this._minSort;
    }

    public String getUrl() {
        return this.url;
    }

    public String getIcon() {
        return this.icon;
    }

    public String getIsShow() {
        return this.isShow;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public void set_maxSort(Integer _maxSort) {
        this._maxSort = _maxSort;
    }

    public void set_minSort(Integer _minSort) {
        this._minSort = _minSort;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

}
