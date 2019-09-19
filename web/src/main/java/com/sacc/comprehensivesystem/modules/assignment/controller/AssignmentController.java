package com.sacc.comprehensivesystem.modules.assignment.controller;

import com.sacc.comprehensivesystem.admin.service.LoginService;
import com.sacc.comprehensivesystem.common.enums.Difficulty;
import com.sacc.comprehensivesystem.common.enums.QuestionType;
import com.sacc.comprehensivesystem.common.utils.RestResult;
import com.sacc.comprehensivesystem.modules.assignment.dto.Answer;
import com.sacc.comprehensivesystem.modules.assignment.dto.IoSample;
import com.sacc.comprehensivesystem.modules.assignment.dto.QuestionDetail;
import com.sacc.comprehensivesystem.modules.assignment.dto.QuestionListItem;
import com.sacc.comprehensivesystem.modules.assignment.entity.Assignment;
import com.sacc.comprehensivesystem.modules.assignment.entity.QuestionBank;
import com.sacc.comprehensivesystem.modules.assignment.service.*;
import com.sacc.comprehensivesystem.modules.voj.entity.Problem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author goufaan
 */
@RestController
@RequestMapping("/assignment")
public class AssignmentController {
    static Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    private AssignmentQuestionService assignmentQuestionService;

    @Autowired
    private QuestionBankService questionBankService;

    @Autowired private AssignmentService assignmentService;

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
        assignmentQuestionService.getQuestions((Assignment) new Assignment().setId(assignment)).forEach((item) -> {
            if (item.getQuestionType() == QuestionType.MultipleChoice) {
                QuestionBank question = questionBankService.get(item.getQuestionId());
                list.add(new QuestionListItem()
                        .setId(question.getId())
                        .setTitle(question.getTitle())
                        .setQuestionType(item.getQuestionType())
                        .setDifficulty(question.getDifficulty())
                        .setFinish(false)
                );
            } else if (item.getQuestionType() == QuestionType.Programming){
                Problem problem = vojService.getProblem(item.getId());
                list.add(new QuestionListItem()
                        .setId(problem.getProblemId())
                        .setTitle(problem.getProblemName())
                        .setQuestionType(item.getQuestionType())
                        .setDifficulty(Difficulty.Middle) //TODO 给Voj的问题设置难度
                        .setFinish(false)
                );

            }

        });
        return list;
    }

    /**
     * 获取题目详情
     * @param type 题目类型，选择题或编程题
     * @param question 题目Id
     * @return QuestionDetail
     */
    @GetMapping("/questionDetail")
    public QuestionDetail getQuestionDetail(String type, Long question){
        if (QuestionType.MultipleChoice.name().equals(type)) {
            QuestionBank questionBankEntity = questionBankService.get(question);
            return new QuestionDetail()
                    .setId(question)
                    .setTitle(questionBankEntity.getTitle())
                    .setDisc(questionBankEntity.getDescription())
                    .setDifficulty(questionBankEntity.getDifficulty())
                    .setChoices(questionBankEntity.getChoices());

        } else if (QuestionType.Programming.name().equals(type)){
            Problem problem = vojService.getProblem(question);
            return new QuestionDetail()
                    .setId(question)
                    .setTitle(problem.getProblemName())
                    .setDisc(problem.getDescription())
                    .setDifficulty(Difficulty.Middle)
                    // TODO
                    .setSpaceLimit(problem.getMemoryLimit())
                    .setTimeLimit(problem.getTimeLimit())
                    .setiStandard(problem.getInputFormat())
                    .setoStandard(problem.getOutputFormat())
                    .setIOSample(new IoSample()
                            .setiSample(problem.getSampleInput())
                            .setoSample(problem.getSampleOutput()));
        }
        return null;
    }

    /**
     * 提交作业(仅选择题)
     * @return
     */
    @PostMapping("/submit")
    public boolean submitAnswer(Long assignment, @RequestBody Answer[] answers){
        for (Answer answer: answers) {
            assignmentStageService.judgeAnswer(assignment, answer.getId(), answer.getAnswer());
        }
        return true;
    }

    @RequestMapping("/add")
    public RestResult AddCompetition(@RequestBody String postJson){
        int result=0;

        RestResult<Object> resultt = null;
        try{
            result=assignmentService.addASsignments(postJson);

        }catch (Exception e){
            e.getStackTrace();
            result=0;
        }

        switch(result) {
            case 1:
                resultt = new RestResult<Object>(RestResult.STATUS_SUCCESS, "作业添加成功",null);
                break;
            case 0:
                resultt = new RestResult<Object>(RestResult.STATUS_OTHERS, "作业添加失败", null);
                break;
            default:
                resultt = new RestResult<>(RestResult.STATUS_OTHERS, "调用失败", null);
        }
        return resultt;
    }

    @RequestMapping("/update")
    public RestResult updateData(@RequestBody String postJson)
    {

        int result=0;

        RestResult<Object> resultt = null;
        try{
            result=assignmentService.updateAssignment(postJson);

        }catch (Exception e){
            e.getStackTrace();
            logger.error("Error: {}\n3{}", e.getMessage(), e.getStackTrace());
            result=0;
        }
        switch(result) {
            case 1:
                resultt = new RestResult<Object>(RestResult.STATUS_SUCCESS, "作业更新成功",null);
                break;
            case 0:
                resultt = new RestResult<Object>(RestResult.STATUS_OTHERS, "作业更新失败", null);
                break;
            default:
                resultt = new RestResult<>(RestResult.STATUS_OTHERS, "调用失败", null);
        }
        return resultt;
    }
    @RequestMapping("/addquestion")
    public RestResult addQuestion(@RequestBody String postJson)
    {

        int result=0;

        RestResult<Object> resultt = null;
        try{
            result=assignmentService.addQuestion(postJson);

        }catch (Exception e){
            e.getStackTrace();
            logger.error("Error: {}\n3{}", e.getMessage(), e.getStackTrace());
            result=0;
        }
        switch(result) {
            case 1:
                resultt = new RestResult<Object>(RestResult.STATUS_SUCCESS, "题目添加新成功",null);
                break;
            case 0:
                resultt = new RestResult<Object>(RestResult.STATUS_OTHERS, "题目添加失败", null);
                break;
            default:
                resultt = new RestResult<>(RestResult.STATUS_OTHERS, "调用失败", null);
        }
        return resultt;
    }

    @RequestMapping("/deletequestion")
    public RestResult deleteQuestion(@RequestBody String postJson)
    {

        int result=0;

        RestResult<Object> resultt = null;
        try{
            result=assignmentService.delAssignmentQuestion(postJson);

        }catch (Exception e){
            e.getStackTrace();
            logger.error("Error: {}\n3{}", e.getMessage(), e.getStackTrace());
            result=0;
        }
        switch(result) {
            case 1:
                resultt = new RestResult<Object>(RestResult.STATUS_SUCCESS, "题目删除新成功",null);
                break;
            case 0:
                resultt = new RestResult<Object>(RestResult.STATUS_OTHERS, "题目删除失败", null);
                break;
            default:
                resultt = new RestResult<>(RestResult.STATUS_OTHERS, "调用失败", null);
        }
        return resultt;
    }




}
