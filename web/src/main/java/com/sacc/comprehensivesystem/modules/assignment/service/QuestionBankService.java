package com.sacc.comprehensivesystem.modules.assignment.service;

import com.sacc.comprehensivesystem.common.enums.QuestionType;
import com.sacc.comprehensivesystem.common.service.BasicService;
import com.sacc.comprehensivesystem.modules.assignment.dto.QuestionDetail;
import com.sacc.comprehensivesystem.modules.assignment.dto.QuestionListItem;
import com.sacc.comprehensivesystem.modules.assignment.entity.QuestionBank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author goufaan
 */
@Component
public class QuestionBankService extends BasicService<QuestionBank> {

    @Override
    public String[] getNatureKeys() {
        return new String[]{"title","description","choice_a","choice_b","choice_c","choice_d","choice_e","choice_f","correct_answer"};
    }

    @Override
    public void customInit(QuestionBank entity) {

    }

    /**
     * 获取单个问题
     * @param questionId
     * @return
     */
    public QuestionListItem getQuestion(Long questionId){
        QuestionBank question = get(questionId);
        return new QuestionListItem()
                .setId(question.getId())
                .setTitle(question.getTitle())
                .setQuestionType(QuestionType.MultipleChoice)
                .setDifficulty(question.getDifficulty());
    }
    /**
     * 获取单个问题详情
     * @param questionId id
     * @return 问题详情DTO
     */
    public QuestionDetail getQuestionDetail(Long questionId){
        QuestionBank questionBank = get(questionId);
        return new QuestionDetail()
                .setId(questionId)
                .setTitle(questionBank.getTitle())
                .setDisc(questionBank.getDescription())
                .setDifficulty(questionBank.getDifficulty())
                .setChoices(questionBank.getChoices());
    }

}
