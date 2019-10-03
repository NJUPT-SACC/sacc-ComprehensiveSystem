package com.sacc.comprehensivesystem.modules.assignment.service;

import com.sacc.comprehensivesystem.common.enums.Difficulty;
import com.sacc.comprehensivesystem.common.enums.QuestionType;
import com.sacc.comprehensivesystem.modules.assignment.dto.IoSample;
import com.sacc.comprehensivesystem.modules.assignment.dto.QuestionDetail;
import com.sacc.comprehensivesystem.modules.assignment.dto.QuestionListItem;
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

    /**
     * 获取编程题基本信息
     * @param questionId
     * @param assignmentId
     * @return
     */
    public QuestionListItem getProblem(Long questionId, Long assignmentId){
        Problem problem = problemDao.getProblem(questionId);
        //TODO 给Voj的问题设置难度
        //TODO 编程题完成度
        return new QuestionListItem()
                .setId(problem.getProblemId())
                .setTitle(problem.getProblemName())
                .setQuestionType(QuestionType.Programming)
                .setDifficulty(Difficulty.Middle)
                .setFinish(false)
        ;
    }
    /**
     * 获取编程题详情
     * @param questionId id
     * @return 问题详情DTO
     */
    public QuestionDetail getProblemDetail(Long questionId){
        Problem problem = problemDao.getProblem(questionId);
        return new QuestionDetail()
                .setId(questionId)
                .setTitle(problem.getProblemName())
                .setDisc(problem.getDescription())
                .setDifficulty(Difficulty.Middle)
                // TODO
                .setSpaceLimit(problem.getMemoryLimit())
                .setTimeLimit(problem.getTimeLimit())
                .setIStandard(problem.getInputFormat())
                .setOStandard(problem.getOutputFormat())
                .setIOSample(new IoSample()
                        .setISample(problem.getSampleInput())
                        .setOSample(problem.getSampleOutput()));
    }

}
