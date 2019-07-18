package com.sacc.ComprehensiveSystem.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sacc.ComprehensiveSystem.common.utils.JSONUtils;
import com.sacc.ComprehensiveSystem.common.utils.StringUtils;

import java.util.Date;
import java.util.Set;

public class BasicEntity {
    public static final String COLUMN_ID_ = "id";
    public static final String COLUMN_ID_DESC_ = "id desc";
    public static final String COLUMN_CREATE_DATE_ = "create_date";
    public static final String COLUMN_CREATE_DATE_DESC_ = "create_date desc";
    public static final String COLUMN_CREATE_BY_ = "create_by";
    public static final String COLUMN_UPDATE_DATE_ = "update_date";
    public static final String COLUMN_UPDATE_DATE_DESC_ = "update_date desc";
    public static final String COLUMN_UPDATE_BY_ = "update_by";
    public static final String COLUMN_DEL_FLAG_ = "del_flag";
    public static final String COLUMN_REMARKS_ = "remarkss";

    // ---------------------------------
    // name的对应属性
    // ---------------------------------
    private String id;

    // ---------------------------------
    // create_date对应的属性
    // ---------------------------------
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date _minCreateDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date _maxCreateDate;

    // ---------------------------------
    // create_by对应的属性
    // ---------------------------------
    private String createBy;

    // ---------------------------------
    // update_date对应的属性
    // ---------------------------------
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date _minUpdateDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date _maxUpdateDate;

    // ---------------------------------
    // update_by对应的属性
    // ---------------------------------
    private String updateBy;

    // ---------------------------------
    // del_flag对应的属性
    // ---------------------------------
    private Integer delFlag;
    private Integer _minDelFlag;
    private Integer _maxDelFlag;

    // ---------------------------------
    // remarks对应的属性
    // ---------------------------------
    private String remarks;

    private String _orderBy;
    private Integer _pageNum;
    private Integer _pageSize;
    private Set<String> _updateProperties;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date get_minCreateDate() {
        return _minCreateDate;
    }

    public void set_minCreateDate(Date _minCreateDate) {
        this._minCreateDate = _minCreateDate;
    }

    public Date get_maxCreateDate() {
        return _maxCreateDate;
    }

    public void set_maxCreateDate(Date _maxCreateDate) {
        this._maxCreateDate = _maxCreateDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date get_minUpdateDate() {
        return _minUpdateDate;
    }

    public void set_minUpdateDate(Date _minUpdateDate) {
        this._minUpdateDate = _minUpdateDate;
    }

    public Date get_maxUpdateDate() {
        return _maxUpdateDate;
    }

    public void set_maxUpdateDate(Date _maxUpdateDate) {
        this._maxUpdateDate = _maxUpdateDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String get_orderBy() {
        return _orderBy;
    }

    public void set_orderBy(String _orderBy) {
        this._orderBy = _orderBy;
    }

    public Integer get_pageNum() {
        return _pageNum;
    }

    public void set_pageNum(Integer _pageNum) {
        this._pageNum = _pageNum;
    }

    public Integer get_pageSize() {
        return _pageSize;
    }

    public void set_pageSize(Integer _pageSize) {
        this._pageSize = _pageSize;
    }

    public Set<String> get_updateProperties() {
        return _updateProperties;
    }

    public void set_updateProperties(Set<String> _updateProperties) {
        this._updateProperties = _updateProperties;
    }

    public Integer get_minDelFlag() {
        return _minDelFlag;
    }

    public void set_minDelFlag(Integer _minDelFlag) {
        this._minDelFlag = _minDelFlag;
    }

    public Integer get_maxDelFlag() {
        return _maxDelFlag;
    }

    public void set_maxDelFlag(Integer _maxDelFlag) {
        this._maxDelFlag = _maxDelFlag;
    }

    public void buildOrderBy(String... orderbyStrs) {
        StringBuilder sb = new StringBuilder();
        for (String str : orderbyStrs) {
            if(str.contains(".")) {
                sb.append(str + ", ");
            }
            else {
                sb.append("a." + str + ", ");
            }
        }
        this._orderBy = StringUtils.removeEnd(sb.toString().trim(), ",");
    }

    @Override
    public String toString() {
        return JSONUtils.toJson(this);
    }
}
