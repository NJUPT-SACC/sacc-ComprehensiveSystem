package com.sacc.ComprehensiveSystem.modules.assignment.dao;

import com.sacc.ComprehensiveSystem.modules.assignment.entity.Assignment;

public interface AssignmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Assignment record);

    int insertSelective(Assignment record);

    Assignment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Assignment record);

    int updateByPrimaryKey(Assignment record);
}