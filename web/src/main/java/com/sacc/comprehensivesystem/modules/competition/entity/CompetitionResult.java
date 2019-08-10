package com.sacc.comprehensivesystem.modules.competition.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CompetitionResult {
    private Long id;
    private Long userId;
    private Long competitionId;
    private Integer score;
    private int delFlag;

    // ---------------------------------
    // submit_time对应的属性
    // ---------------------------------
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date submitTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date _minSubmitTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date _maxSubmitTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Long competitionId) {
        this.competitionId = competitionId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Date get_minSubmitTime() {
        return _minSubmitTime;
    }

    public void set_minSubmitTime(Date _minSubmitTime) {
        this._minSubmitTime = _minSubmitTime;
    }

    public Date get_maxSubmitTime() {
        return _maxSubmitTime;
    }

    public void set_maxSubmitTime(Date _maxSubmitTime) {
        this._maxSubmitTime = _maxSubmitTime;
    }
}
