package com.sacc.ComprehensiveSystem.modules.assignment.service;

import com.sacc.ComprehensiveSystem.common.service.BasicService;
import com.sacc.ComprehensiveSystem.modules.assignment.entity.Assignment;
import com.sacc.ComprehensiveSystem.modules.assignment.entity.AssignmentQuestion;
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

    public List<AssignmentQuestion> getQuestions(Assignment assignmentEntity){
        return dao.listBy(new AssignmentQuestion().setAssignmentId(assignmentEntity.getId()));
    }
}
