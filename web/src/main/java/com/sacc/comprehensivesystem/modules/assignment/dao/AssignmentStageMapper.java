package com.sacc.comprehensivesystem.modules.assignment.dao;

import com.sacc.comprehensivesystem.common.dao.BasicDao;
import com.sacc.comprehensivesystem.modules.assignment.entity.AssignmentStage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AssignmentStageMapper extends BasicDao<AssignmentStage> {
    int selectByAssignmentIdAndQuestionId(@Param("assignment_id") long assignmentId, @Param("question_id") long questionId);
}