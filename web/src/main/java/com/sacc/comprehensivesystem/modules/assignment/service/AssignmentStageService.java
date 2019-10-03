package com.sacc.comprehensivesystem.modules.assignment.service;

import com.sacc.comprehensivesystem.common.enums.JudgeResult;
import com.sacc.comprehensivesystem.common.service.BasicService;
import com.sacc.comprehensivesystem.modules.assignment.dao.AssignmentStageMapper;
import com.sacc.comprehensivesystem.modules.assignment.entity.AssignmentStage;
import com.sacc.comprehensivesystem.modules.assignment.entity.QuestionBank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AssignmentStageService extends BasicService<AssignmentStage> {
    @Override
    public String[] getNatureKeys() {
        return new String[]{"assignment_id","user_id","question_id","question_result"};
    }

    @Override
    public void customInit(AssignmentStage entity) {

    }
    @Autowired
    private AssignmentStageMapper assignmentStageMapper;

    @Autowired
    private QuestionBankService questionBankService;

    public void judgeAnswer(Long assignmentId, Long questionId, String choices){
        QuestionBank question = questionBankService.get(questionId);
        dao.insert(new AssignmentStage()
                .setUserId(getCurrentUser().getId())
                .setAssignmentId(assignmentId)
                .setQuestionId(questionId)
                .setSaveTime(new Date())
                .setQuestionResult(question.getCorrectAnswer().equals(choices) ?
                        JudgeResult.Correct : JudgeResult.Wrong)
        );
    }
    public boolean isProblemFinish(Long assignmentId, Long questionId){
        return assignmentStageMapper.selectByAssignmentIdAndQuestionId(assignmentId, questionId) > 0;
    }
}
