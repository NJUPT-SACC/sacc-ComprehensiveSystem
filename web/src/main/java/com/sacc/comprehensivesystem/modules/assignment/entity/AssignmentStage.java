package com.sacc.comprehensivesystem.modules.assignment.entity;

import com.sacc.comprehensivesystem.common.entity.BasicEntity;

import java.util.Date;

/**
 * 作业批改结果实体
 * @author goufaan
 */
public class AssignmentStage extends BasicEntity {
    private Long assignmentId;
    private Long userId;
    private Long questionId;
    private String questionResult;
    private Date saveTime;

    public Long getAssignmentId() {
        return assignmentId;
    }

    public AssignmentStage setAssignmentId(Long assignmentId) {
        this.assignmentId = assignmentId;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public AssignmentStage setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public AssignmentStage setQuestionId(Long questionId) {
        this.questionId = questionId;
        return this;
    }

    public String getQuestionResult() {
        return questionResult;
    }

    public AssignmentStage setQuestionResult(String questionResult) {
        this.questionResult = questionResult;
        return this;
    }

    public Date getSaveTime() {
        return saveTime;
    }

    public AssignmentStage setSaveTime(Date saveTime) {
        this.saveTime = saveTime;
        return this;
    }
}