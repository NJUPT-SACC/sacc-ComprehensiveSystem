package com.sacc.ComprehensiveSystem.modules.assignment.dao;

import com.sacc.ComprehensiveSystem.modules.assignment.entity.AssignmentStage;

public interface AssignmentStageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AssignmentStage record);

    int insertSelective(AssignmentStage record);

    AssignmentStage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AssignmentStage record);

    int updateByPrimaryKey(AssignmentStage record);
}