package com.sacc.comprehensivesystem.modules.assignment.dao;

import com.sacc.comprehensivesystem.common.dao.BasicDao;
import com.sacc.comprehensivesystem.modules.assignment.entity.Assignment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author goufaan
 */
@Mapper
public interface AssignmentMapper extends BasicDao<Assignment> {
    public void insertAssignment(Assignment assignment);
    public Long findIdByName(String name);

    public int update(Assignment assignment);
}
