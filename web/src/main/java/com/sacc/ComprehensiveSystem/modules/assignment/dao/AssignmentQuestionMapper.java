package com.sacc.ComprehensiveSystem.modules.assignment.dao;

import com.sacc.ComprehensiveSystem.modules.assignment.entity.AssignmentQuestion;

public interface AssignmentQuestionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AssignmentQuestion record);

    int insertSelective(AssignmentQuestion record);

    AssignmentQuestion selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AssignmentQuestion record);

    int updateByPrimaryKey(AssignmentQuestion record);
}