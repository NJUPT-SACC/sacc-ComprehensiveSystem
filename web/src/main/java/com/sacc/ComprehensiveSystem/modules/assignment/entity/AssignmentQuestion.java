package com.sacc.ComprehensiveSystem.modules.assignment.entity;

import com.sacc.ComprehensiveSystem.common.entity.BasicEntity;
import com.sacc.ComprehensiveSystem.common.enums.QuestionType;
import org.apache.activemq.util.LongSequenceGenerator;

/**
 * 作业里面全部问题实体
 * @author goufaan
 */
public class AssignmentQuestion extends BasicEntity {
    private Long assignmentId;
    private Long questionId;
    private QuestionType questionType;

    public Long getAssignmentId() {
        return assignmentId;
    }

    public AssignmentQuestion setAssignmentId(Long assignmentId) {
        this.assignmentId = assignmentId;
        return this;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public AssignmentQuestion setQuestionId(Long questionId) {
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