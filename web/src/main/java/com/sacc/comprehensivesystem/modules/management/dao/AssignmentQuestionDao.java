package com.sacc.comprehensivesystem.modules.management.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AssignmentQuestionDao {
    List<com.sacc.comprehensivesystem.modules.assignment.entity.AssignmentQuestion> selectQuestionsByAssignmentId(Long assignmentId);
    public void insertAssignmentQuestion(com.sacc.comprehensivesystem.modules.assignment.entity.AssignmentQuestion assignmentQuestion);

    public Long findIdByAssignmentIdAndQuestionId(@Param("questionId") Long questionId, @Param("assignmentId") Long assignmentId);
    public void deleteQuestion(Long id);
}
