package com.sacc.comprehensivesystem.modules.assignment.dao;

import com.sacc.comprehensivesystem.common.dao.BasicDao;
import com.sacc.comprehensivesystem.modules.assignment.entity.AssignmentQuestion;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AssignmentQuestionMapper extends BasicDao<AssignmentQuestion> {

    List<AssignmentQuestion> selectQuestionsByAssignmentId(Long assignmentId);

}