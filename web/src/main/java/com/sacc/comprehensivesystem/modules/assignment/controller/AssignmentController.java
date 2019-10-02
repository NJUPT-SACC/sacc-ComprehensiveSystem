package com.sacc.comprehensivesystem.modules.assignment.controller;

import com.sacc.comprehensivesystem.admin.Utils.CacheUtils;
import com.sacc.comprehensivesystem.admin.shrio.entity.UserSimpleAuthorizationInfo;
import com.sacc.comprehensivesystem.admin.sys.entity.SysUser;
import com.sacc.comprehensivesystem.common.enums.Difficulty;
import com.sacc.comprehensivesystem.common.enums.QuestionType;
import com.sacc.comprehensivesystem.common.utils.JSONUtils;
import com.sacc.comprehensivesystem.modules.assignment.dto.*;
import com.sacc.comprehensivesystem.modules.assignment.entity.Assignment;
import com.sacc.comprehensivesystem.modules.assignment.entity.QuestionBank;
import com.sacc.comprehensivesystem.modules.assignment.exception.AssignmentException;
import com.sacc.comprehensivesystem.modules.assignment.service.AssignmentQuestionService;
import com.sacc.comprehensivesystem.modules.assignment.service.AssignmentStageService;
import com.sacc.comprehensivesystem.modules.assignment.service.QuestionBankService;
import com.sacc.comprehensivesystem.modules.assignment.service.VojService;
import com.sacc.comprehensivesystem.modules.voj.entity.Problem;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author goufaan
 */
@RestController
@RequestMapping("/assignment")
public class AssignmentController {

    @Autowired
    private AssignmentQuestionService assignmentQuestionService;

    @Autowired
    private QuestionBankService questionBankService;

    @Autowired
    private AssignmentStageService assignmentStageService;

    @Autowired
    private VojService vojService;

    @GetMapping("/categories")
    public String getCategories(){
        return "TODO";
    }

    /**
     * 获取题目列表
     * @param assignment 作业id
     * @return 该作业的所有题目
     */
    @GetMapping("/questionList")
    public List<QuestionListItem> getQuestionList(Long assignment){
        List<QuestionListItem> list = new ArrayList<>();
        assignmentQuestionService.getQuestions((Assignment)new Assignment().setId(assignment)).forEach((item) -> {
            if(item.getQuestionType() == QuestionType.MultipleChoice){
                QuestionBank question = questionBankService.get(item.getQuestionId());
                list.add(new QuestionListItem()
                        .setId(question.getId())
                        .setTitle(question.getTitle())
                        .setQuestionType(item.getQuestionType())
                        .setDifficulty(question.getDifficulty())
                        .setFinish(assignmentStageService.isProblemFinish(assignment, item.getId()))
                );
            }else if (item.getQuestionType() == QuestionType.Programming){
                Problem problem = vojService.getProblem(item.getId());
                list.add(new QuestionListItem()
                        .setId(problem.getProblemId())
                        .setTitle(problem.getProblemName())
                        .setQuestionType(item.getQuestionType())
                        .setDifficulty(Difficulty.Middle) //TODO 给Voj的问题设置难度
                        .setFinish(false) //TODO 编程题完成度
                );

            }

        });
        return list;
    }

    /**
     * 获取题目详情
     * @param type
     * @param question
     * @return
     */
    @GetMapping("/questionDetail")
    public QuestionDetail getQuestionDetail(String type, Long question){
        if(QuestionType.MultipleChoice.name().equals(type)){
            QuestionBank questionBankEntity = questionBankService.get(question);
            return new QuestionDetail()
                    .setId(question)
                    .setTitle(questionBankEntity.getTitle())
                    .setDisc(questionBankEntity.getDescription())
                    .setDifficulty(questionBankEntity.getDifficulty())
                    .setChoices(questionBankEntity.getChoices());

        }else if (QuestionType.Programming.name().equals(type)){
            Problem problem = vojService.getProblem(question);
            return new QuestionDetail()
                    .setId(question)
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
        return null;
    }

    /**
     * 提交作业(仅选择题)
     * @return
     */
    @RequiresAuthentication
    @PostMapping("/submit")
    public boolean submitAnswer(Long assignment, @RequestBody AnswersModel answers){
        //List<Answer> answerList = new JSONObject(answers).get;
        for (Answer answer: answers.getAnswers()){
            assignmentStageService.judgeAnswer(assignment, answer.getId(), answer.getAnswer());
        }
        return true;
    }
}
