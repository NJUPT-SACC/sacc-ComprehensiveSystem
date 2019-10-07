package com.sacc.comprehensivesystem.modules.management.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompetitionQuestionMapper {
    public void insertCompetitionQuestion(com.sacc.comprehensivesystem.modules.competition.entity.CompetitionQuestion competitionQuestion);
    public Long findIdByCompetitionIdAndQuestionId(Long questionId,Long competitionId);
    public void deleteQuestion(Long id);
}
