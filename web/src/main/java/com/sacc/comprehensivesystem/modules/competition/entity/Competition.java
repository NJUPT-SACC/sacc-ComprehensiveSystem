package com.sacc.comprehensivesystem.modules.competition.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sacc.comprehensivesystem.common.entity.BasicEntity;

import java.util.Date;

/**
 * @author yujinbiao
 */
public class Competition extends BasicEntity {
    private String name;

    private String location;
    // ---------------------------------
    // create_date对应的属性
    // ---------------------------------
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date _minStartTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date _maxStartTime;

    // ---------------------------------
    // create_date对应的属性
    // ---------------------------------
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date _minEndTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date _maxEndTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date get_minStartTime() {
        return _minStartTime;
    }

    public void set_minStartTime(Date _minStartTime) {
        this._minStartTime = _minStartTime;
    }

    public Date get_maxStartTime() {
        return _maxStartTime;
    }

    public void set_maxStartTime(Date _maxStartTime) {
        this._maxStartTime = _maxStartTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date get_minEndTime() {
        return _minEndTime;
    }

    public void set_minEndTime(Date _minEndTime) {
        this._minEndTime = _minEndTime;
    }

    public Date get_maxEndTime() {
        return _maxEndTime;
    }

    public void set_maxEndTime(Date _maxEndTime) {
        this._maxEndTime = _maxEndTime;
    }
}
