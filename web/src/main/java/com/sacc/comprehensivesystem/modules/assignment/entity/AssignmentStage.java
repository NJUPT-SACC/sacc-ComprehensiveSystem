package com.sacc.comprehensivesystem.modules.assignment.entity;

import com.sacc.comprehensivesystem.common.entity.BasicEntity;
import com.sacc.comprehensivesystem.common.enums.JudgeResult;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 作业批改结果实体
 * @author goufaan
 */
public class AssignmentStage extends BasicEntity {
    private Long assignmentId;
    private Long userId;
    private Long questionId;
    private JudgeResult questionResult;
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

    public JudgeResult getQuestionResult() {
        return questionResult;
    }

    public AssignmentStage setQuestionResult(JudgeResult questionResult) {
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