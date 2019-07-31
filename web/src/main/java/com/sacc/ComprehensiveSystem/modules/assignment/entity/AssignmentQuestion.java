package com.sacc.ComprehensiveSystem.modules.assignment.entity;

import com.sacc.ComprehensiveSystem.common.entity.BasicEntity;
import com.sacc.ComprehensiveSystem.common.enums.QuestionType;

/**
 * 作业里面全部问题实体
 * @author goufaan
 */
public class AssignmentQuestion extends BasicEntity {
    private Integer assignmentId;
    private Integer questionId;
    private QuestionType questionType;

    public Integer getAssignmentId() {
        return assignmentId;
    }

    public AssignmentQuestion setAssignmentId(Integer assignmentId) {
        this.assignmentId = assignmentId;
        return this;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public AssignmentQuestion setQuestionId(Integer questionId) {
        this.questionId = questionId;
        return this;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public AssignmentQuestion setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
        return this;
    }
}