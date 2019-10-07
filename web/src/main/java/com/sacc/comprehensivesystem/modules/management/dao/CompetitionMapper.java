package com.sacc.comprehensivesystem.modules.management.dao;

import com.sacc.comprehensivesystem.modules.assignment.entity.QuestionBank;
import com.sacc.comprehensivesystem.modules.competition.entity.CompetitionResult;
import com.sacc.comprehensivesystem.modules.competition.entity.CompetitionStage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CompetitionMapper {
    public List<com.sacc.comprehensivesystem.modules.competition.entity.Competition> listCompetition();

    public List<QuestionBank> queryQuestion(@Param("id") Long id);

    public void insertCompetition(com.sacc.comprehensivesystem.modules.competition.entity.Competition competition);

    public Long findIdByName(String name);

    public void deleteOldResult(@Param("id") Long id);

    public void save(@Param("params") Map<Long, CompetitionStage> list);

    public CompetitionResult checRepeat(@Param("userId") Long userId, @Param("competitionId") Long competitionId);

    public List<QuestionBank> getRightAnswer(@Param("competitionId") Long competitionId);

    public void competitionUpdate(com.sacc.comprehensivesystem.modules.competition.entity.Competition competition);
}
