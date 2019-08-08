package com.sacc.comprehensivesystem.modules.competition.entity;

import com.sacc.comprehensivesystem.common.entity.BasicEntity;

/**
 * @author yujinbiao
 */
public class CompetitionUser extends BasicEntity {
    private Long userId;

    private Long competitionId;

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
}
