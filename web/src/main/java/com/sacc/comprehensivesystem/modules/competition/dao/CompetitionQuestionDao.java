package com.sacc.comprehensivesystem.modules.competition.dao;

import com.sacc.comprehensivesystem.modules.competition.entity.CompetitionQuestion;

public interface CompetitionQuestionDao {
    public void insertCompetitionQuestion(CompetitionQuestion competitionQuestion);
    public Long findIdByCompetitionIdAndQuestionId(Long questionId,Long competitionId);
    public void deleteQuestion(Long id);
}
