package com.sacc.comprehensivesystem.modules.competition.dao;

import com.sacc.comprehensivesystem.modules.assignment.entity.QuestionBank;
import com.sacc.comprehensivesystem.modules.competition.entity.Competition;
import com.sacc.comprehensivesystem.modules.competition.entity.CompetitionResult;
import com.sacc.comprehensivesystem.modules.competition.entity.CompetitionStage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface CompetitionDao {

    public List<Competition> listCompetition();

    public List<QuestionBank> queryQuestion(@Param("id") Long id);

    public void insertCompetition(Competition competition);

    public Long findIdByName(String name);

    public void deleteOldResult(@Param("id") Long id);

    public void save(@Param("params") Map<Long, CompetitionStage> list);

    public CompetitionResult checRepeat(@Param("userId") Long userId, @Param("competitionId") Long competitionId);

    public List<QuestionBank> getRightAnswer(@Param("competitionId") Long competitionId);

    public void competitionUpdate(Competition competition);
}
