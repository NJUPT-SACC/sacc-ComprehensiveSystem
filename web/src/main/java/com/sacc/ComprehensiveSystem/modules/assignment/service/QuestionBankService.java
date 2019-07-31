package com.sacc.ComprehensiveSystem.modules.assignment.service;

import com.sacc.ComprehensiveSystem.common.service.BasicService;
import com.sacc.ComprehensiveSystem.modules.assignment.entity.QuestionBank;
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

//    public List<QuestionBank> getMultipleChoiceQuestions(List<Integer> questionIds){
//        List<QuestionBank> list = new ArrayList<>();
//        questionIds.forEach((item) -> {
//            list.add(get(item));
//        });
//        return list;
//    }
}
