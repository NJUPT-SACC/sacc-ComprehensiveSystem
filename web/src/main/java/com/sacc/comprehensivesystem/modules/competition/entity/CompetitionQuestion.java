package com.sacc.comprehensivesystem.modules.competition.entity;


import com.sacc.comprehensivesystem.common.entity.BasicEntity;

/**
 * @author yujinbiao
 */
public class CompetitionQuestion extends BasicEntity {


    // ---------------------------------
    // questionId的对应属性
    // ---------------------------------
    private Long questionId;

    // ---------------------------------
    // competitionId的对应属性
    // ---------------------------------
    private Long competitionId;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Long competitionId) {
        this.competitionId = competitionId;
    }
}
