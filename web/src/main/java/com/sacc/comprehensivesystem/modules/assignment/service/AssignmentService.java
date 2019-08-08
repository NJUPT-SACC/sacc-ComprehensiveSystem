package com.sacc.comprehensivesystem.modules.assignment.service;

import com.sacc.comprehensivesystem.common.service.BasicService;
import com.sacc.comprehensivesystem.modules.assignment.entity.Assignment;
import org.springframework.stereotype.Component;

/**
 * @author goufaan
 */
@Component
public class AssignmentService extends BasicService<Assignment> {
    @Override
    public String[] getNatureKeys() {
        return new String[]{"name","startTime","endTime"};
    }

    @Override
    public void customInit(Assignment entity) {

    }

}
