package com.sacc.comprehensivesystem.modules.assignment.dao;

import com.sacc.comprehensivesystem.common.dao.BasicDao;
import com.sacc.comprehensivesystem.modules.assignment.entity.AssignmentQuestion;
import com.sacc.comprehensivesystem.modules.competition.entity.CompetitionQuestion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AssignmentQuestionMapper extends BasicDao<AssignmentQuestion> {

    List<AssignmentQuestion> selectQuestionsByAssignmentId(Long assignmentId);
    public void insertAssignmentQuestion(AssignmentQuestion assignmentQuestion);

    public Long findIdByAssignmentIdAndQuestionId(@Param("questionId") Long questionId, @Param("assignmentId") Long assignmentId);
    public void deleteQuestion(Long id);
}