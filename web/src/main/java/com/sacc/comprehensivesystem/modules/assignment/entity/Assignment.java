package com.sacc.comprehensivesystem.modules.assignment.entity;

import com.sacc.comprehensivesystem.common.entity.BasicEntity;

import java.util.Date;

/**
 * 作业实体
 * @author goufaan
 */
public class Assignment extends BasicEntity {
    private String name;
    private Date startTime;
    private Date endTime;

    public String getName() {
        return name;
    }

    public Assignment setName(String name) {
        this.name = name;
        return this;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Assignment setStartTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Assignment setEndTime(Date endTime) {
        this.endTime = endTime;
        return this;
    }
}