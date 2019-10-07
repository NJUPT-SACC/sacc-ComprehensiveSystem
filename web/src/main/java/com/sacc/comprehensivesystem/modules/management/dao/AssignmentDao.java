package com.sacc.comprehensivesystem.modules.management.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AssignmentDao {
    public void insertAssignment(com.sacc.comprehensivesystem.modules.assignment.entity.Assignment assignment);
    public Long findIdByName(String name);
    public int update(com.sacc.comprehensivesystem.modules.assignment.entity.Assignment assignment);
}
