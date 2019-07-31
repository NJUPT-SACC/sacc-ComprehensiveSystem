package com.sacc.ComprehensiveSystem.modules.assignment.controller;

import com.sacc.ComprehensiveSystem.common.enums.QuestionType;
import com.sacc.ComprehensiveSystem.modules.assignment.dto.QuestionDetail;
import com.sacc.ComprehensiveSystem.modules.assignment.dto.QuestionListItem;
import com.sacc.ComprehensiveSystem.modules.assignment.entity.Assignment;
import com.sacc.ComprehensiveSystem.modules.assignment.entity.QuestionBank;
import com.sacc.ComprehensiveSystem.modules.assignment.service.AssignmentQuestionService;
import com.sacc.ComprehensiveSystem.modules.assignment.service.QuestionBankService;
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

    @Autowired
    private AssignmentQuestionService assignmentQuestionService;

    @Autowired
    private QuestionBankService questionBankService;

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
    public List<QuestionListItem> getQuestionList(Integer assignment){
        List<QuestionListItem> list = new ArrayList<>();
        assignmentQuestionService.getQuestions((Assignment)new Assignment().setId(assignment)).forEach((item) -> {
            if(item.getQuestionType() == QuestionType.MultipleChoice){
                QuestionBank question = questionBankService.get(item.getQuestionId());
                list.add(new QuestionListItem()
                        .setId(question.getId())
                        .setTitle(question.getTitle())
                        .setQuestionType(item.getQuestionType())
                        .setDifficulty(null)
                        .setFinish(false)
                );
            }else if (item.getQuestionType() == QuestionType.Programming){
                // TODO
            }

        });
        return list;
    }

    @GetMapping("/questionDetail")
    public QuestionDetail getQuestionDetail(QuestionType type, Integer question){
        if(type == QuestionType.MultipleChoice){
            QuestionBank questionBankEntity = questionBankService.get(question);
            return new QuestionDetail()
                    .setId(question)
                    .setTitle(questionBankEntity.getTitle())
                    .setDisc(questionBankEntity.getDescription())
                    .setDifficulty(questionBankEntity.getDifficulty())
                    .setChoices(new String[]{questionBankEntity.getChoiceA()});
            //TODO
        }else if (type == QuestionType.Programming){
            return new QuestionDetail()
                    .setId(question);
                    //TODO
        }
        return null;
    }

    /**
     *
     * @return
     */
    @PostMapping("/submit")
    public boolean submitAnswer(){

    }
}
