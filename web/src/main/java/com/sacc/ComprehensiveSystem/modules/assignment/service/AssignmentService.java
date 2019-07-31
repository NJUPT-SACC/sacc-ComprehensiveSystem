package com.sacc.ComprehensiveSystem.modules.assignment.service;

import com.sacc.ComprehensiveSystem.common.service.BasicService;
import com.sacc.ComprehensiveSystem.modules.assignment.entity.Assignment;
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
