package com.sacc.comprehensivesystem.modules.competition.dao;

import com.sacc.comprehensivesystem.modules.assignment.entity.QuestionBank;
import com.sacc.comprehensivesystem.modules.competition.entity.Competition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CompetitionDao {

    public List<Competition> listCompetition();

    public List<QuestionBank> queryQuestion(@Param("id")Long id);

    public void insertCompetition(Competition competition);
    public Long findIdByName(String name);

}
