package com.sacc.comprehensivesystem.modules.assignment.service;

import com.sacc.comprehensivesystem.modules.voj.dao.ProblemDao;
import com.sacc.comprehensivesystem.modules.voj.entity.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author goufaan
 */
@Component
public class VojService {

    @Autowired
    private ProblemDao problemDao;

    public Problem getProblem(long id){
        return problemDao.getProblem(id);
    }
}
