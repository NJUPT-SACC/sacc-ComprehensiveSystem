package com.sacc.comprehensivesystem.modules.assignment.service;

import com.sacc.comprehensivesystem.common.service.BasicService;
import com.sacc.comprehensivesystem.modules.assignment.dao.AssignmentQuestionMapper;
import com.sacc.comprehensivesystem.modules.assignment.entity.AssignmentQuestion;
import com.sacc.comprehensivesystem.modules.assignment.exception.AssignmentException;
import com.sacc.comprehensivesystem.modules.assignment.exception.AssignmentExceptionCode;
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

    /**
     * 获取作业的全部问题引用
     * @param assignmentId
     * @return
     */
    public List<AssignmentQuestion> getAssignmentQuestions(Long assignmentId){
        List<AssignmentQuestion> assignmentQuestionList = assignmentQuestionMapper.selectQuestionsByAssignmentId(assignmentId);
        if(assignmentQuestionList.size() == 0){
            throw new AssignmentException(AssignmentExceptionCode.ASSIGNMENT_NOT_EXIST);
        }
        return assignmentQuestionList;
    }

}
