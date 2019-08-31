package com.sacc.comprehensivesystem.modules.assignment.service;

import com.sacc.comprehensivesystem.common.service.BasicService;
import com.sacc.comprehensivesystem.modules.assignment.dao.AssignmentQuestionMapper;
import com.sacc.comprehensivesystem.modules.assignment.entity.Assignment;
import com.sacc.comprehensivesystem.modules.assignment.entity.AssignmentQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author goufaan
 */
@Component
public class AssignmentQuestionService extends BasicService<AssignmentQuestion> {
    @Override
    public String[] getNatureKeys() {
        return new String[]{"assignment_id","question_id","question_type"};
    }

    @Override
    public void customInit(AssignmentQuestion entity) {

    }

    @Autowired
    private AssignmentQuestionMapper assignmentQuestionMapper;

    public List<AssignmentQuestion> getQuestions(Assignment assignmentEntity){
        return assignmentQuestionMapper.selectQuestionsByAssignmentId(assignmentEntity.getId());
    }
}
