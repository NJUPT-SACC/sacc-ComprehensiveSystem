package com.sacc.comprehensivesystem.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sacc.comprehensivesystem.common.utils.JSONUtils;
import com.sacc.comprehensivesystem.common.utils.StringUtils;

import java.util.Date;
import java.util.Set;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
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
    // id的对应属性
    // ---------------------------------
    private Long id;

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
    private Long createBy;

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
    private Long updateBy;

    // ---------------------------------
    // del_flag对应的属性
    // ---------------------------------
    @JsonIgnore
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

    public Long getId() {
        return id;
    }


    public BasicEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public BasicEntity setCreateDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }

    public Date get_minCreateDate() {
        return _minCreateDate;
    }

    public BasicEntity set_minCreateDate(Date _minCreateDate) {
        this._minCreateDate = _minCreateDate;
        return this;
    }

    public Date get_maxCreateDate() {
        return _maxCreateDate;
    }

    public BasicEntity set_maxCreateDate(Date _maxCreateDate) {
        this._maxCreateDate = _maxCreateDate;
        return this;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public BasicEntity setCreateBy(Long createBy) {
        this.createBy = createBy;
        return this;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public BasicEntity setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
        return this;
    }

    public Date get_minUpdateDate() {
        return _minUpdateDate;
    }

    public BasicEntity set_minUpdateDate(Date _minUpdateDate) {
        this._minUpdateDate = _minUpdateDate;
        return this;
    }

    public Date get_maxUpdateDate() {
        return _maxUpdateDate;
    }

    public BasicEntity set_maxUpdateDate(Date _maxUpdateDate) {
        this._maxUpdateDate = _maxUpdateDate;
        return this;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public BasicEntity setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
        return this;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public BasicEntity setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
        return this;
    }

    public Integer get_minDelFlag() {
        return _minDelFlag;
    }

    public BasicEntity set_minDelFlag(Integer _minDelFlag) {
        this._minDelFlag = _minDelFlag;
        return this;
    }

    public Integer get_maxDelFlag() {
        return _maxDelFlag;
    }

    public BasicEntity set_maxDelFlag(Integer _maxDelFlag) {
        this._maxDelFlag = _maxDelFlag;
        return this;
    }

    public String getRemarks() {
        return remarks;
    }

    public BasicEntity setRemarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public String get_orderBy() {
        return _orderBy;
    }

    public BasicEntity set_orderBy(String _orderBy) {
        this._orderBy = _orderBy;
        return this;
    }

    public Integer get_pageNum() {
        return _pageNum;
    }

    public BasicEntity set_pageNum(Integer _pageNum) {
        this._pageNum = _pageNum;
        return this;
    }

    public Integer get_pageSize() {
        return _pageSize;
    }

    public BasicEntity set_pageSize(Integer _pageSize) {
        this._pageSize = _pageSize;
        return this;
    }

    public Set<String> get_updateProperties() {
        return _updateProperties;
    }

    public BasicEntity set_updateProperties(Set<String> _updateProperties) {
        this._updateProperties = _updateProperties;
        return this;
    }
}
